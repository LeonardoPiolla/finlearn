package com.seuusuario.finlearn

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seuusuario.finlearn.ui.theme.*

@Composable
fun LessonScreen(
    onFinishLesson: (xpGained: Int) -> Unit,
    onBack: () -> Unit
) {
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var answerState by remember { mutableStateOf<String?>(null) }

    val options = listOf(
        "R$ 22,00",
        "R$ 18,00",
        "R$ 10,00",
        "R$ 28,00"
    )
    val correctAnswerIndex = 1 // R$ 18,00

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "✕",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark,
                modifier = Modifier
                    .clickable { onBack() }
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            LinearProgressIndicator(
                progress = 0.5f,
                modifier = Modifier
                    .weight(1f)
                    .height(12.dp)
                    .clip(RoundedCornerShape(6.dp)),
                color = CorrectGreen,
                trackColor = Color(0xFFE5E5E5)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F7F7)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "💡 Fundamentos Financeiros",
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Margem de Contribuição é o valor que sobra da receita após deduzir os custos e despesas variáveis. É essa margem que ajuda a pagar os custos fixos e gerar lucro.",
                    color = TextDark,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Caso Prático: A Dolce Momento vende uma caixa de mini caseirinhos por R$ 28,00. O custo variável (ingredientes como chocolate, farinha e a embalagem) é de R$ 10,00 por caixa. Qual é a Margem de Contribuição unitária?",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextDark,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        options.forEachIndexed { index, text ->
            val isSelected = selectedOption == index
            val borderColor = if (isSelected) PrimaryBlue else Color(0xFFE5E5E5)
            val backgroundColor = if (isSelected) Color(0xFFEBF8FF) else Color.White

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(backgroundColor)
                    .border(2.dp, borderColor, RoundedCornerShape(14.dp))
                    .clickable {
                        if (answerState == null) selectedOption = index
                    }
                    .padding(16.dp)
            ) {
                Text(
                    text = text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextDark
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        if (answerState == "correct") {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF8D8)),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "🎉 Excelente raciocínio!",
                        fontWeight = FontWeight.Bold,
                        color = CorrectGreen,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "R$ 28 (Receita) - R$ 10 (Custo Variável) = R$ 18 de Margem de Contribuição.",
                        color = TextDark,
                        fontSize = 13.sp
                    )
                }
            }
        } else if (answerState == "wrong") {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEAEA)),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "👨‍🏫 Professor Financeiro:",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF4B4B),
                        fontSize = 15.sp
                    )
                    Text(
                        text = "Lembre-se da lógica: você deve subtrair os R$ 10,00 de custo variável do valor total de venda (R$ 28,00). Tente novamente!",
                        color = TextDark,
                        fontSize = 13.sp
                    )
                }
            }
        }

        Button(
            onClick = {
                if (answerState == "correct") {
                    onFinishLesson(15) // Ganha 15 XP
                } else if (selectedOption != null) {
                    if (selectedOption == correctAnswerIndex) {
                        answerState = "correct"
                    } else {
                        answerState = "wrong"
                    }
                }
            },
            enabled = selectedOption != null,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (answerState == "correct") CorrectGreen else PrimaryBlue
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        ) {
            Text(
                text = if (answerState == "correct") "CONTINUAR" else "VERIFICAR",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}
