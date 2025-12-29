package kakaai

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class KakaAi : AppCompatActivity() {

    private lateinit var inputText: EditText
    private lateinit var sendButton: Button
    private lateinit var chatView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kaka_ai_ui)

        inputText = findViewById(R.id.inputText)
        sendButton = findViewById(R.id.sendButton)
        chatView = findViewById(R.id.chatView)

        sendButton.setOnClickListener {
            val message = inputText.text.toString()
            if (message.isNotBlank()) {
                chatView.append("\nVocê: $message")
                inputText.text.clear()
                callKakaApi(message)
            }
        }
    }

    private fun callKakaApi(userMessage: String) {
        thread {
            try {
                val url = URL("https://raw.githubusercontent.com/fernandathays95-blip/Kaka-AI-API-App/main/kakaai/kaka-ai-all-api.json") // vai ser o seu arquivo
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"

                val response = connection.inputStream.bufferedReader().use { it.readText() }
                val json = JSONObject(response)
                val aiResponse = json.optString(userMessage.lowercase(), "Desculpe, não entendi 🤖")

                runOnUiThread {
                    chatView.append("\nKakaAI: $aiResponse")
                }

                connection.disconnect()
            } catch (e: Exception) {
                runOnUiThread {
                    chatView.append("\n[Erro ao conectar à IA]")
                }
            }
        }
    }
}
