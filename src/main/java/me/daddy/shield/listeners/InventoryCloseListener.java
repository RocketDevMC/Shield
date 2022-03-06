package me.daddy.shield.listeners;

import me.daddy.shield.Shield;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        if(event.getInventory().getName().equals(ChatColor.RED + "Click the emerald block!")){
            Player player = (Player) event.getPlayer();
            if(!Shield.getCaptchaList().contains(player)) {
                player.kickPlayer(ChatColor.RED + "You've failed the captcha!! If this is an error \n please report it to staff!");
            }
        }
    }
}
