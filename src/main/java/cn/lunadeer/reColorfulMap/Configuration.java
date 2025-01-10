package cn.lunadeer.reColorfulMap;

import cn.lunadeer.reColorfulMap.utils.Notification;
import cn.lunadeer.reColorfulMap.utils.VaultConnect.VaultConnect;
import cn.lunadeer.reColorfulMap.utils.configuration.*;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.List;

public class Configuration extends ConfigurationFile {
    @Comments("Do not modify this value.")
    public static int version = 2;

    @Comments({
            "The maximum size of item frame matrix.",
            "32 x 18 means image can not be larger than 4096 x 2304 pixels.",
            "Don't set this value too large, it may cause lag."
    })
    public static int maxFrameX = 32;
    public static int maxFrameY = 18;

    @Comments("Language of the plugin, see others in the plugins/FurnitureCore/languages folder.")
    public static String language = "en_us";

    @Comments({
            "Enable economy system. (Require Vault and an economy plugin)",
            "If true, players need to pay for each map they create.",
            "This may prevent players from abusing usage of the plugin."
    })
    public static Economy economy = new Economy();

    public static class Economy extends ConfigurationPart {
        public boolean enable = false;
        public double costPerMap = 100.0f;
    }

    @Comments({
            "The whitelist of image url address, use this to prevent players from using inappropriate images.",
            "Leave it empty to allow all addresses.",
    })
    public static List<String> addressWhiteList = List.of("");

    @Comments("Debug mode, if report bugs turn this on.")
    public static boolean debug = false;

    @PostProcess
    public static void hookVault() {
        if (economy.enable) {
            new VaultConnect(ReColorfulMap.getInstance());
        }
    }

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
