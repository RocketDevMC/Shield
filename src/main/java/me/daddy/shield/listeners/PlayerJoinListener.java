package me.daddy.shield.listeners;

import lombok.Getter;
import me.daddy.shield.Shield;
import me.daddy.shield.utils.Detection;
import me.daddy.shield.utils.inventories.Captcha;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.net.InetSocketAddress;
import java.util.HashMap;

import static org.bukkit.Bukkit.getServer;

public class PlayerJoinListener implements Listener {


    @Getter private static HashMap<String, Boolean> playerAddress = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("shield.bypass")) {

            if (Shield.getPlugin().getConfig().getBoolean("captcha.always")) {
                Captcha.openCaptcha(player);
                return;
            }
            if (Shield.getPlugin().getConfig().getBoolean("antivpn.enabled")) {
                if (Shield.getPlugin().getConfig().getBoolean("captcha.enabled")) {
                    if (playerAddress.containsKey(player.getAddress().getHostString())) {
                        if (!playerAddress.get(player.getAddress().getHostString()).equals(true)) {
                            player.kickPlayer(ChatColor.RED + "Please disconnect from your VPN/Proxy! If this is an error \n please report it to staff!");
                            return;
                        }
                        return;
                    }

                    if (!Detection.checkIP(player.getAddress().getHostString())) {
                        if (Shield.getPlugin().getConfig().getBoolean("antivpn.block")) {
                            player.kickPlayer(ChatColor.RED + "Please disconnect from your VPN/Proxy! If this is an error \n please report it to staff!");
                            playerAddress.put(player.getAddress().getHostString(), false);
                            return;
                        } else {
                            Captcha.openCaptcha(player);
                        }
                    }
                }
            }
        }
    }
}