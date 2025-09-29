# 🎬 TMDb Showcase App

This is an Android app built to showcase **The Movie Database (TMDb)** API.  
It demonstrates modern Android development with **Clean Architecture, MVVM, Hilt DI, Room, Retrofit/Ktor, and Jetpack Compose**.

---

## ✨ Features

- 📊 **Dashboard screen** – showcases most popular and top-rated movies
- 🔍 **Search screen** – search functionality to query movies based on user input
- 🎥 **Details screen** – provides additional information for a selected movie
- 💾 **Offline caching** – Room database for local storage and fast reloads
- 🧪 **Unit tests** – implemented in both **data** and **domain** layers

---

## 🛠️ Tech Stack & Libraries

This project uses the following core libraries (managed with **Gradle Version Catalogs**):

- **Jetpack Compose** – Modern declarative UI
- **Navigation Compose** – In-app navigation
- **Material3 + Material Components** – UI styling
- **Hilt (Dagger)** – Dependency Injection
- **Room** – Local persistence & caching
- **Retrofit / Ktor** – Networking
- **Moshi & Kotlinx Serialization** – JSON parsing
- **Coil** – Image loading
- **Coroutines & Flow** – Asynchronous programming
- **JUnit5, MockK, Turbine** – Testing

*(See `libs.versions.toml` for full dependency versions.)*

---

## 🚀 Installation

Follow these steps to set up and run the app locally:

### 1. Clone the repository
```bash
git clone https://github.com/denors/NGMovies.git
cd NGMovies
```

### 2. Open in Android Studio
- Use **Android Studio Ladybug (2024.2.1+)** or newer.
- Import the project and let Gradle sync.

### 3. Obtain a TMDb API Key (Note: I couldn't get this to work so continue to step 4)
- Sign up at [TMDb](https://www.themoviedb.org/) and request an API key.
- Add your key to `local.properties`:

```properties
TMDB_API_KEY=your_api_key_here
```

### 4. Build & Run
- Select a device or emulator (API 24+).
- Run the app with **Shift + F10** (Windows/Linux) or **Control + R** (Mac).

---

## 🧪 Running Tests

The project includes **JUnit5 tests** with **MockK** and **Turbine**.  
Run all tests with:

```bash
./gradlew test
```
Or run individual tests via Android Studio’s test runner.

--- 

## 🎥 Demo
https://github.com/user-attachments/assets/58420220-6a9a-468d-97c1-84ec4caec6e9

