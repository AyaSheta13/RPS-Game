# 🪨 📄 ✂️ Rock Paper Scissors Game

<div align="center">
<br>
A beautifully designed, feature-rich Rock Paper Scissors game built with modern Android technologies
<br><br>

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-purple.svg)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.5-blue.svg)
![Dagger Hilt](https://img.shields.io/badge/Dagger%20Hilt-2.48-red.svg)
![Architecture](https://img.shields.io/badge/Architecture-MVVM-green.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)
![Min SDK](https://img.shields.io/badge/Min%20SDK-24-orange.svg)
</div>

---

## 📱 Screenshots

<div align="center">

### 🏁 Game Flow Screens

| Home Screen | VS Computer | Counter Screen |
|:---:|:---:|:---:|
| <img src="Screenshots/homeScreen.jpg" width="180" /> | <img src="Screenshots/vscomputerScreen.jpg" width="180" /> | <img src="Screenshots/counterScreen.jpg" width="180" /> |

<br>

| Round Winner (1) | Round Winner (2) |
|:---:|:---:|
| <img src="Screenshots/whoWonScreen.jpg" width="220" /> | <img src="Screenshots/whoWonScreen2.jpg" width="220" /> |

<br>

| Game Result: Win | Game Result: Lose |
|:---:|:---:|
| <img src="Screenshots/resultScreen_playerWon.jpg" width="220" /> | <img src="Screenshots/resultScreen_computerWon.jpg" width="220" /> |

</div>

---

## ✨ Features

### 🎮 Game Modes
- **VS Computer**: Play against AI
- **VS Friend**: Local multiplayer
- **Rounds**: 3, 5, or 10 rounds

### 🎯 Game Features
- **Animations**: Smooth countdown and results
- **History**: Round tracking
- **Score**: Real-time score updates
- **Winner Detection**: Automatic winner detection

### 🎨 UI/UX Design
- **Material 3 Design**
- **Custom Colors**
- **Responsive UI**
- **Interactive Feedback**

### ⚙️ Technical Features
- **Clean Architecture**
- **Dependency Injection**
- **Kotlin Flow State**
- **Safe Navigation**
- **Offline Support**

---

## 🏗️ Architecture

```text
com.example.rpsgame
├── data                # Data Layer
│   ├── datasource      # Data sources (Local/Remote)
│   ├── model           # Data classes and enums
│   └── repository      # Repository implementations
├── domain              # Domain Layer
│   ├── engine          # Game logic and rules
│   └── usecase         # Business logic use cases
├── ui                  # Presentation Layer
│   ├── components      # Reusable Compose components
│   ├── navigation      # Navigation setup
│   ├── screen          # Screen composables
│   ├── theme           # Theme and styling
│   └── utils           # Utility functions
└── di                  # Dependency Injection
```
---

## 🛠️ Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Architecture:** MVVM with Clean Architecture
- **DI:** Dagger Hilt
- **Navigation:** Compose Navigation
- **State Management:** Kotlin Flows
- **Build System:** Gradle with Kotlin DSL
- **Min SDK:** Android 7.0 (API 24)

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Flamingo or later
- Android SDK 33+
- JDK 17+

### Installation
1. Clone the repository:
```bash
git clone [https://github.com/yourusername/rps-game.git](https://github.com/yourusername/rps-game.git)
cd rps-game
```
2. Open the project in Android Studio

3. Wait for Gradle sync to complete

4. Run on a device or emulator

---
### Build Commands

```bash
# Clean build
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Run tests
./gradlew test
```

----

### 🔧 Configuration
## Build Variants:

- Debug: For development with logging

- Release: For production with minification

## Main Dependencies:

- androidx.compose.*: UI framework

- dagger.hilt.*: Dependency injection

- androidx.navigation.*: Navigation

- kotlinx.coroutines.*: Async operations
