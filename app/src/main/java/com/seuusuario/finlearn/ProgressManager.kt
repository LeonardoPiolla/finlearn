package com.seuusuario.finlearn

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Cria o arquivo de banco de dados invisível no celular
val Context.dataStore by preferencesDataStore(name = "user_progress")

class ProgressManager(private val context: Context) {
    
    private val XP_KEY = intPreferencesKey("total_xp")
    private val STREAK_KEY = intPreferencesKey("streak_days")

    // Lê o XP em tempo real (sempre que mudar, a tela atualiza sozinha)
    val totalXpFlow: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[XP_KEY] ?: 0
    }

    val streakDaysFlow: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[STREAK_KEY] ?: 1
    }

    // Função que soma o XP da aula com o XP que já estava salvo
    suspend fun addXp(amount: Int) {
        context.dataStore.edit { preferences ->
            val current = preferences[XP_KEY] ?: 0
            preferences[XP_KEY] = current + amount
        }
    }
}
