package cn.lunadeer.reColorfulMap;

import cn.lunadeer.reColorfulMap.commands.ToMap;
import cn.lunadeer.reColorfulMap.events.ImageMapEvent;
import cn.lunadeer.reColorfulMap.utils.Notification;
import cn.lunadeer.reColorfulMap.utils.configuration.ConfigurationFile;
import cn.lunadeer.reColorfulMap.utils.configuration.ConfigurationManager;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.List;

public class Language extends ConfigurationFile {

    public static ReColorfulMap.ReColorfulMapText reColorfulMapText = new ReColorfulMap.ReColorfulMapText();

    public static ImageRenderer.ImageRendererText imageRenderer = new ImageRenderer.ImageRendererText();

    public static ImageMapItem.ImageMapItemText imageMapItem = new ImageMapItem.ImageMapItemText();

    public static ToMap.ToMapCommandText toMapCommand = new ToMap.ToMapCommandText();

    public static ImageMapEvent.ImageMapEventText imageMapEvent = new ImageMapEvent.ImageMapEventText();

    public static void load(CommandSender sender) {
        try {
            final List<String> languages = List.of(
                    "languages/en_us.yml",
                    "languages/zh_cn.yml"
            );
            for (String language : languages) {
                if (new File(ReColorfulMap.getInstance().getDataFolder(), language).exists()) {
                    continue;
                }
                ReColorfulMap.getInstance().saveResource(language, false);
            }
            File languageFile = new File(ReColorfulMap.getInstance().getDataFolder(), "languages/" + Configuration.language + ".yml");
            ConfigurationManager.load(Language.class, languageFile);    // This will save the default language file if it doesn't exist.
        } catch (Exception e) {
            Notification.error(sender, Language.reColorfulMapText.failToLoadLanguage, e.getMessage());
        }
    }

}
