package api

import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object KakaAiApiLoader {

    // 🧠 Estruturas de dados
    var mainApi: JSONObject? = null
    var automation: JSONObject? = null
    var funnyTxt: String? = null
    var funnyJson: JSONObject? = null

    // 📡 Função principal para carregar tudo
    fun loadAll() {
        println("🔄 Carregando dados da KakaAI do servidor principal...")
        try {
            mainApi = loadJson(KakaAiApiConfig.PATH_MAIN_API)
            automation = loadJson(KakaAiApiConfig.PATH_AUTOMATION)
            funnyTxt = loadText(KakaAiApiConfig.PATH_FUNNY_TXT)
            funnyJson = loadJson(KakaAiApiConfig.PATH_FUNNY_JSON)
            println("✅ Todos os arquivos da KakaAI foram carregados com sucesso!")
        } catch (e: Exception) {
            println("❌ Erro ao carregar dados da KakaAI: ${e.message}")
        }
    }

    // 📜 Função para carregar um arquivo JSON
    private fun loadJson(urlString: String): JSONObject? {
        return try {
            val url = URL(urlString.replace("github.com", "raw.githubusercontent.com").replace("/tree/main", "/main"))
            val connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = KakaAiApiConfig.CONNECTION_TIMEOUT
            connection.readTimeout = KakaAiApiConfig.CONNECTION_TIMEOUT
            connection.requestMethod = "GET"

            val response = connection.inputStream.bufferedReader().use { it.readText() }
            connection.disconnect()
            JSONObject(response)
        } catch (e: Exception) {
            println("⚠️ Falha ao carregar JSON de $urlString: ${e.message}")
            null
        }
    }

    // 📄 Função para carregar um arquivo de texto
    private fun loadText(urlString: String): String? {
        return try {
            val url = URL(urlString.replace("github.com", "raw.githubusercontent.com").replace("/tree/main", "/main"))
            val connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = KakaAiApiConfig.CONNECTION_TIMEOUT
            connection.readTimeout = KakaAiApiConfig.CONNECTION_TIMEOUT
            connection.requestMethod = "GET"

            val response = connection.inputStream.bufferedReader().use { it.readText() }
            connection.disconnect()
            response
        } catch (e: Exception) {
            println("⚠️ Falha ao carregar TXT de $urlString: ${e.message}")
            null
        }
    }

    // 🧩 Mostrar status atual
    fun showStatus() {
        println("📦 Status da KakaAI API Loader:")
        println("→ API principal: ${if (mainApi != null) "✅" else "❌"}")
        println("→ Automação: ${if (automation != null) "✅" else "❌"}")
        println("→ Funny TXT: ${if (funnyTxt != null) "✅" else "❌"}")
        println("→ Funny JSON: ${if (funnyJson != null) "✅" else "❌"}")
    }
}
