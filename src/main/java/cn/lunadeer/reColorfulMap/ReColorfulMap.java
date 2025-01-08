package cn.lunadeer.reColorfulMap;

import cn.lunadeer.reColorfulMap.commands.ToMap;
import cn.lunadeer.reColorfulMap.events.ImageMapEvent;
import cn.lunadeer.reColorfulMap.events.MapInitEvent;
import cn.lunadeer.reColorfulMap.utils.Notification;
import cn.lunadeer.reColorfulMap.utils.Scheduler;
import cn.lunadeer.reColorfulMap.utils.XLogger;
import cn.lunadeer.reColorfulMap.utils.configuration.ConfigurationPart;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ReColorfulMap extends JavaPlugin {

    public static class ReColorfulMapText extends ConfigurationPart {
        public String failToLoadConfig = "Failed to load configuration file: %s";
        public String failToLoadLanguage = "Failed to load language file: %s, using default language.";
        public String loading = "ReColorfulMap is loading...";
        public String loaded = "ReColorfulMap is loaded successfully!";
    }

    @Override
    public void onEnable() {
        instance = this;
        new Notification(this);
        new XLogger(this);
        new Scheduler(this);
        Configuration.load(ReColorfulMap.getInstance().getServer().getConsoleSender());
        XLogger.setDebug(Configuration.debug);
        XLogger.info(Language.reColorfulMapText.loading);

        Objects.requireNonNull(getCommand("tomap")).setExecutor(new ToMap());

        getServer().getPluginManager().registerEvents(new ImageMapEvent(), this);
        getServer().getPluginManager().registerEvents(new MapInitEvent(), this);

        XLogger.info(Language.reColorfulMapText.loaded);
        XLogger.debug("Debug mode is enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private static ReColorfulMap instance;

    public static ReColorfulMap getInstance() {
        return instance;
    }
}
