package api;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class server {

    // 🌐 URLs principais (mesmas do repositório)
    private static final String BASE_URL = "https://raw.githubusercontent.com/fernandathays95-blip/Kaka-AI-API-App/main/";
    private static final Map<String, String> FILES = new LinkedHashMap<>();

    static {
        FILES.put("kakaai/kaka-ai-all-api.json", "data/kaka-ai-all-api.json");
        FILES.put("kakaai/automation.json", "data/automation.json");
        FILES.put("com/kaka/ai/oficial/funny/funny.txt", "data/funny.txt");
        FILES.put("com/kaka/ai/oficial/funny/funny.json", "data/funny.json");
    }

    // 🕒 Intervalo de atualização (1 minuto)
    private static final long REFRESH_INTERVAL = 60_000;

    public static void main(String[] args) {
        System.out.println("🚀 Servidor de Sincronização KakaAI iniciado!");
        System.out.println("📡 Monitorando arquivos no repositório principal...");

        // Cria pasta de dados se não existir
        new File("data").mkdirs();

        while (true) {
            try {
                syncFiles();
                Thread.sleep(REFRESH_INTERVAL);
            } catch (Exception e) {
                System.out.println("❌ Erro no loop de sincronização: " + e.getMessage());
            }
        }
    }

    // 🔄 Faz a sincronização de todos os arquivos
    private static void syncFiles() {
        for (Map.Entry<String, String> entry : FILES.entrySet()) {
            String remotePath = BASE_URL + entry.getKey();
            String localPath = entry.getValue();

            try {
                System.out.println("⬇️  Baixando: " + remotePath);
                String content = download(remotePath);

                if (content != null && !content.isEmpty()) {
                    saveToFile(localPath, content);
                    System.out.println("✅ Atualizado: " + localPath);
                } else {
                    System.out.println("⚠️  Sem conteúdo: " + localPath);
                }

            } catch (Exception e) {
                System.out.println("❌ Erro ao baixar " + remotePath + ": " + e.getMessage());
            }
        }
    }

    // 🌍 Faz download de um arquivo do GitHub
    private static String download(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
            return result.toString();
        }
    }

    // 💾 Salva conteúdo em arquivo local
    private static void saveToFile(String path, String content) {
        try {
            Files.createDirectories(Paths.get(path).getParent());
            Files.write(Paths.get(path), content.getBytes());
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar arquivo: " + path + " (" + e.getMessage() + ")");
        }
    }
}
