package com.seuusuario.finlearn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seuusuario.finlearn.ui.theme.*

@Composable
fun HomeTrailScreen(
    currentXp: Int,
    streakDays: Int,
    onStartLesson: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cabeçalho: Foguinho e XP Dinâmicos
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "🔥 $streakDays dias", color = StreakFlame, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "🏆 $currentXp XP", color = PrimaryBlue, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Text(
            text = "Trilha de Finanças", 
            fontSize = 28.sp, 
            fontWeight = FontWeight.ExtraBold,
            color = TextDark
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Módulo 1: Fundamentos",
            fontSize = 16.sp,
            color = TextDark.copy(alpha = 0.7f)
        )
        
        Spacer(modifier = Modifier.height(40.dp))
        
        // Botão Gamificado do Módulo 1
        Button(
            onClick = { onStartLesson() },
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
            shape = RoundedCornerShape(100.dp),
            modifier = Modifier.size(140.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "📚", fontSize = 40.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Iniciar", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}
