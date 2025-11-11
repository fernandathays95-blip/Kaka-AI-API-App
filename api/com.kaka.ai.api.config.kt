package api

object KakaAiApiConfig {

    // 🌐 Servidor principal da API (repositório GitHub)
    const val SERVER_BASE_URL = "https://github.com/fernandathays95-blip/Kaka-AI-API-App/tree/main"

    // 🌎 Versão da API
    const val API_VERSION = "1.0.0"

    // 📦 Estrutura padrão de diretórios da API KakaAI
    const val PATH_MAIN_API = "$SERVER_BASE_URL/kakaai/kaka-ai-all-api.json"
    const val PATH_AUTOMATION = "$SERVER_BASE_URL/kakaai/automation.json"
    const val PATH_FUNNY_TXT = "$SERVER_BASE_URL/com/kaka/ai/oficial/funny/funny.txt"
    const val PATH_FUNNY_JSON = "$SERVER_BASE_URL/com/kaka/ai/oficial/funny/funny.json"

    // 🧠 Modo de depuração
    const val DEBUG_MODE = true

    // 🕒 Intervalo de atualização (em milissegundos)
    const val REFRESH_INTERVAL = 60000L // 1 minuto

    // 🔌 Timeout de conexão (em milissegundos)
    const val CONNECTION_TIMEOUT = 5000

    // 🧩 Identificação da IA
    const val AI_NAME = "KakaAI"
    const val AI_ID = "com.kaka.ai.oficial"
    const val AI_AUTHOR = "KakaAI System / Fernanda Thays & Enzo Gabryel"
    const val AI_DESCRIPTION = "Inteligência Artificial Kotlin baseada em arquivos JSON e TXT, hospedada no GitHub."

    // 🔐 Função auxiliar para debug
    fun logConfig() {
        if (DEBUG_MODE) {
            println("KakaAI API Config:")
            println("→ Servidor: $SERVER_BASE_URL")
            println("→ Versão: $API_VERSION")
            println("→ API principal: $PATH_MAIN_API")
            println("→ Automação: $PATH_AUTOMATION")
            println("→ Funny TXT: $PATH_FUNNY_TXT")
            println("→ Funny JSON: $PATH_FUNNY_JSON")
            println("→ Atualiza a cada ${REFRESH_INTERVAL / 1000}s")
        }
    }
}
