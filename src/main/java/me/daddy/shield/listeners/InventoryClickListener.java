package me.daddy.shield.listeners;

import me.daddy.shield.Shield;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(event.getInventory().getTitle().equals(ChatColor.RED + "Click the emerald block!")){
            event.setCancelled(true);
            if(event.getCurrentItem() != null && !event.getCurrentItem().getType().equals(Material.EMERALD_BLOCK)){
                player.kickPlayer(ChatColor.RED +  "You've failed the captcha!! If this is an error \n please report it to staff!");
                return;
            }
            Shield.getCaptchaList().add(player);
            player.closeInventory();
            PlayerJoinListener.getPlayerAddress().put(player.getAddress().getHostString(), true);
            player.sendMessage(ChatColor.GREEN + "You've passed the captcha!");
        }
    }
}
