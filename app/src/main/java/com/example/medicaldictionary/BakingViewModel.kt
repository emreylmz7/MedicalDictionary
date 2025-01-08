package com.example.medicaldictionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MedicalTermResponse(
    val title: String,
    val definition: String,
    val usageAreas: List<String>
)

class BakingViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Initial)
    val uiState: StateFlow<UiState> =
        _uiState.asStateFlow()

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.apiKey
    )

    fun sendPrompt(prompt: String) {
        _uiState.value = UiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val formattedPrompt = """
                    Tıp öğrencileri için aşağıdaki tıbbi terimi açıklayın:
                    
                    Terim: $prompt
                    
                    Yanıtınızı şu formatta yapılandırın:
                    
                    BAŞLIK:
                    [Terimin Türkçe ve varsa Latince/İngilizce karşılığı]
                    
                    TANIM:
                    [Terimin kısa, öz ve teknik tanımı. Maksimum 2 cümle.]
                    
                    KULLANIM ALANLARI:
                    - [Klinik kullanım]
                    - [Tanı/tedavi yöntemi]
                    - [İlgili uzmanlık alanı]
                    
                    Not: 
                    - Yanıtlar kısa, öz ve teknik olmalı
                    - Tıp fakültesi düzeyinde terminoloji kullanın
                    - Her madde maksimum 1 cümle olmalı
                    - Gereksiz açıklamalardan kaçının
                    - Başlıklarda köşeli parantez kullanmayın
                    - Sadece tire (-) işareti kullanın
                """.trimIndent()

                val response = generativeModel.generateContent(
                    content {
                        text(formattedPrompt)
                    }
                )

                response.text?.let { outputContent ->
                    val sections = outputContent.split("\n\n")
                    var title = ""
                    var definition = ""
                    var usageAreas = listOf<String>()

                    sections.forEach { section ->
                        when {
                            section.startsWith("BAŞLIK:") -> {
                                title = section.substringAfter("BAŞLIK:")
                                    .trim()
                                    .replace(Regex("[\\[\\]]"), "")
                            }
                            section.startsWith("TANIM:") -> {
                                definition = section.substringAfter("TANIM:")
                                    .trim()
                                    .replace(Regex("[\\[\\]]"), "")
                            }
                            section.startsWith("KULLANIM ALANLARI:") -> {
                                usageAreas = section.substringAfter("KULLANIM ALANLARI:")
                                    .trim()
                                    .split("\n")
                                    .filter { it.startsWith("-") }
                                    .map { it.substringAfter("-").trim()
                                        .replace(Regex("[\\[\\]]"), "")
                                        .replace(Regex("[*]"), "")
                                    }
                                    .filter { it.isNotEmpty() }
                            }
                        }
                    }

                    _uiState.value = UiState.Success(
                        MedicalTermResponse(
                            title = title.ifEmpty { prompt },
                            definition = definition.ifEmpty { "Tanım bulunamadı" },
                            usageAreas = usageAreas.ifEmpty { listOf("Kullanım alanı bulunamadı") }
                        )
                    )
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "Bir hata oluştu.")
            }
        }
    }
}