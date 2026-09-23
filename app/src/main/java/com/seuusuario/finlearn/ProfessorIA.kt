package com.seuusuario.finlearn

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ProfessorIA {
    
    private val API_KEY = BuildConfig.GEMINI_API_KEY

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = API_KEY,
        systemInstruction = content {
            text("Você é o Profº Finâncio, um Professor de Gestão Financeira focado em Controladoria, FP&A e Finanças. O seu objetivo é ensinar através do método socrático. Nunca dê a resposta final. Use exemplos práticos de pequenos negócios, como uma confeitaria chamada Dolce Momento que vende brownies, blondies e mini caseirinhos, para ilustrar os conceitos de forma didática. Seja lúdico, encorajador e responda em no máximo 3 frases curtas.")
        }
    )

    suspend fun obterDica(pergunta: String, respostaErrada: String): String {
        return withContext(Dispatchers.IO) {
            try {
                val prompt = "O aluno está a tentar resolver a seguinte questão:\n'$pergunta'\nEle escolheu a resposta '$respostaErrada', que está incorreta. Dê uma dica socrática curta que o faça perceber o erro de raciocínio, sem revelar a resposta certa."
                val response = generativeModel.generateContent(prompt)
                response.text ?: "Lembre-se da lógica da fórmula e tente novamente!"
            } catch (e: Exception) {
                "Ops, estou com instabilidade na ligação. Reveja o conceito e tente novamente!"
            }
        }
    }
}
