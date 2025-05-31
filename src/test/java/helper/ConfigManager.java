package helper;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigManager {
    
    // Load .env
    private static final Dotenv dotenv = Dotenv.configure().filename(".env").load();

    public static String getBaseUrl() {
        return dotenv.get("BASE_URL");
    }
}