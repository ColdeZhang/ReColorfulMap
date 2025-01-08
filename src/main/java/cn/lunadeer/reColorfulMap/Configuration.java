package cn.lunadeer.reColorfulMap;

import cn.lunadeer.reColorfulMap.utils.Notification;
import cn.lunadeer.reColorfulMap.utils.configuration.ConfigurationFile;
import cn.lunadeer.reColorfulMap.utils.configuration.ConfigurationManager;
import cn.lunadeer.reColorfulMap.utils.configuration.ConfigurationPart;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.List;

public class Configuration extends ConfigurationFile {
    public static int version = 1;

    public static int maxFrameX = 32;
    public static int maxFrameY = 18;

    public static Economy economy = new Economy();

    public static String language = "en_us";

    public static class Economy extends ConfigurationPart {
        public boolean enable = false;
        public double costPerMap = 100.0f;
    }

    public static List<String> addressWhiteList = List.of("");

    public static boolean checkUpdate = true;

    public static boolean debug = false;

    /**
     * Load the configuration file and language file.
     *
     * @param sender The command sender.
     */
    public static boolean load(CommandSender sender) {
        try {
            File configFile = new File(ReColorfulMap.getInstance().getDataFolder(), "config.yml");
            ConfigurationManager.load(Configuration.class, configFile, "version");
            Language.load(ReColorfulMap.getInstance().getServer().getConsoleSender());  // Load language file
            return true;
        } catch (Exception e) {
            Notification.error(sender, Language.reColorfulMapText.failToLoadConfig, e.getMessage());
            return false;
        }
    }

}
