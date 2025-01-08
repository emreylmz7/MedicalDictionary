
![MEDİCAL](https://github.com/user-attachments/assets/f103a91c-acb7-47f1-b7c3-6d97f25c430a)

# Medical Dictionary

An Android application developed for medical students to explore and understand medical terminology.

## Description

Medical Dictionary is a comprehensive mobile application that helps medical students and healthcare professionals quickly access and understand medical terms. Built with modern Android technologies, it provides an intuitive interface for searching and learning medical terminology.

## Features

- Fast and efficient search functionality for medical terms
- Comprehensive explanations with clinical context
- Usage examples and practical applications
- Integration with Google AI for enhanced definitions
- Material Design 3 compliant user interface

## Technologies

- Kotlin programming language
- Jetpack Compose for modern UI
- Google AI API (Gemini) for enhanced definitions
- Material Design 3 components

## Installation

1. Clone the repository:
```bash
git clone https://github.com/emreylmz7/MedicalDictionary.git
```

2. Create configuration file:
```bash
cp local.properties.example local.properties
```

3. Configure the local.properties file:
```properties
# Android SDK path
sdk.dir=/path/to/your/Android/sdk

# API Keys (never commit this file)
GOOGLE_AI_API_KEY=your_api_key_here
```

## Configuration

### Required Environment

- Android Studio Arctic Fox or newer
- Android SDK 21 or higher
- JDK 11 or higher

### Build Configuration

```gradle
minSdkVersion: 21
targetSdkVersion: 34
```

## Security Best Practices

To maintain security when using this application:

1. API Key Protection:
   - Never commit your `local.properties` file to version control
   - Keep your API keys confidential
   - Rotate API keys periodically
   - Use API key encryption in production builds

2. Version Control:
   - Ensure `local.properties` is listed in `.gitignore`
   - Use `local.properties.example` as a template for configuration
   - Never commit sensitive credentials to the repository

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgments

- Material Design 3 Guidelines
- Google AI API Documentation
- Android Jetpack Documentation
