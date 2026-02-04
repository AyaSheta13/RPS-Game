# 🪨 📄 ✂️ Rock Paper Scissors Game

<div align="center">
<img src="https://via.placeholder.com/800x200/4873BE/FFFFFF?text=Rock+Paper+Scissors+Game" alt="Rock Paper Scissors Game" />
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

| Main Screen | Game Screen | Results Screen |
|------------|------------|----------------|
| <img src="https://via.placeholder.com/300x600/4873BE/FFFFFF?text=Main+Screen" width="200" /> | <img src="https://via.placeholder.com/300x600/4680C9/FFFFFF?text=Game+Screen" width="200" /> | <img src="https://via.placeholder.com/300x600/00316C/FFFFFF?text=Results+Screen" width="200" /> |

</div>

---

## ✨ Features

### 🎮 Game Modes
- **VS Computer**: Challenge the AI in strategic battles
- **VS Friend**: Play locally with a friend on the same device
- **Multiple Rounds**: Choose from 3, 5, or 10 rounds per game

### 🎯 Game Features
- **Smooth Animations**: Countdown timers, choice reveals, and result animations
- **Round History**: Track all previous rounds with visual indicators
- **Score Tracking**: Real-time score updates with elegant UI
- **Winner Detection**: Automatic winner calculation and celebration screens

### 🎨 UI/UX Design
- **Modern Material Design**: Following Material 3 guidelines
- **Custom Color Palette**: Professional blue-themed color scheme
- **Responsive Layout**: Adapts to different screen sizes
- **Visual Feedback**: Interactive buttons with selection states

### ⚙️ Technical Features
- **Clean Architecture**: Separation of concerns with clear layers
- **Dependency Injection**: Using Dagger Hilt for better testability
- **State Management**: Reactive UI with Kotlin Flows
- **Navigation**: Type-safe navigation with arguments
- **Offline Support**: All data stored locally

---

## 🏗️ Architecture

text
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

---

### Tech Stack
