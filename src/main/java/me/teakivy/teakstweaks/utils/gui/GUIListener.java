package me.teakivy.teakstweaks.utils.gui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.translation.GlobalTranslator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {

    /**
     * Handles the GUI clicks
     * @param event InventoryClickEvent
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Component title = Component.translatable("mechanics.gui.title");
        Component resolved = GlobalTranslator.render(title, java.util.Locale.ROOT);
        if (event.getView().title().equals(resolved == null ? title : resolved)) {
            event.setCancelled(true);
            if (event.getRawSlot() == 53) {
                // Next page button clicked
                PaginatedGUI.next((Player) event.getWhoClicked());
            } else if (event.getRawSlot() == 45) {
                // Previous page button clicked
                PaginatedGUI.previous((Player) event.getWhoClicked());
            }
        }
    }
}
