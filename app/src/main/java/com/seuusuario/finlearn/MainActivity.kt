package com.seuusuario.finlearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentScreen by remember { mutableStateOf("home") }
            var totalXp by remember { mutableStateOf(0) }
            var streakDays by remember { mutableStateOf(1) }

            when (currentScreen) {
                "home" -> HomeTrailScreen(
                    currentXp = totalXp,
                    streakDays = streakDays,
                    onStartLesson = { currentScreen = "lesson" }
                )
                "lesson" -> LessonScreen(
                    onFinishLesson = { xpGained ->
                        totalXp += xpGained
                        currentScreen = "home"
                    },
                    onBack = { currentScreen = "home" }
                )
            }
        }
    }
}
