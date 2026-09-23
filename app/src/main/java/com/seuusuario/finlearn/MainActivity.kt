package com.seuusuario.finlearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val progressManager = remember { ProgressManager(context) }
            val coroutineScope = rememberCoroutineScope()

            // Escuta o banco de dados. Se o app fechar e abrir, os dados retornam daqui.
            val totalXp by progressManager.totalXpFlow.collectAsState(initial = 0)
            val streakDays by progressManager.streakDaysFlow.collectAsState(initial = 1)

            var currentScreen by remember { mutableStateOf("home") }

            when (currentScreen) {
                "home" -> HomeTrailScreen(
                    currentXp = totalXp,
                    streakDays = streakDays,
                    onStartLesson = { currentScreen = "lesson" }
                )
                "lesson" -> LessonScreen(
                    onFinishLesson = { xpGained ->
                        coroutineScope.launch {
                            progressManager.addXp(xpGained) // Salva na memória interna
                            currentScreen = "home"
                        }
                    },
                    onBack = { currentScreen = "home" }
                )
            }
        }
    }
}
