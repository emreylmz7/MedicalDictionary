# Tıp Sözlüğü

Tıp öğrencileri için geliştirilmiş, tıbbi terimleri açıklayan bir Android uygulaması.

## Kurulum

1. Projeyi klonlayın
```bash
git clone https://github.com/[kullanıcı-adınız]/MedicalDictionary.git
```

2. `local.properties.example` dosyasını `local.properties` olarak kopyalayın
3. `local.properties` dosyasında:
   - `sdk.dir` değerini kendi Android SDK yolunuzla değiştirin
   - `apiKey` değerini kendi Google AI API anahtarınızla değiştirin

## Özellikler

- Tıbbi terimlerin hızlı ve kolay aranması
- Detaylı terim açıklamaları
- Kullanım alanları ve örnekler
- Modern ve kullanıcı dostu arayüz

## Teknolojiler

- Kotlin
- Jetpack Compose
- Google AI API (Gemini)
- Material Design 3

## Güvenlik

API anahtarınızı güvende tutmak için:
- Asla API anahtarınızı GitHub'a yüklemeyin
- Her zaman `local.properties` dosyasını `.gitignore` içinde tutun
- Örnek yapılandırma için `local.properties.example` dosyasını kullanın 