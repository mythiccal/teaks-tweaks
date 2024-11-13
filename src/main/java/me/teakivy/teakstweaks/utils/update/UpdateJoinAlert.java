package me.teakivy.teakstweaks.utils.update;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.MM;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import me.teakivy.teakstweaks.utils.permission.Permission;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateJoinAlert implements Listener {

    /**
     * Sends a message to players with the teakstweaks.manage permission when they join the server if there is an update available
     * @param event PlayerJoinEvent
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("teakstweaks.manage")) return;
        if (!Config.getBoolean("settings.alert-on-new-version")) return;

        Bukkit.getScheduler().runTaskAsynchronously(TeaksTweaks.getInstance(), () -> {
            if (!UpdateChecker.hasUpdate()) return;
            
            Bukkit.getScheduler().runTask(TeaksTweaks.getInstance(), () -> {
                String message = Translatable.getString("startup.update.join_alert");
                message = "<hover:show_text:\"" + Translatable.getString("startup.update.join_alert.hover") + "\">" + message;
                message = "<click:open_url:\"" + Translatable.getString("plugin.base_url") + "\">" + message;

                MM.player(player).sendMessage(MiniMessage.miniMessage().deserialize(message,Placeholder.parsed("version", UpdateChecker.getLatestVersion())));
                player.sendMessage("");
            });
        });
    }

}
