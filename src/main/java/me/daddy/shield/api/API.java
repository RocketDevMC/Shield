package me.daddy.shield.api;

import me.daddy.shield.utils.Detection;
import org.bukkit.entity.Player;

public class API {

    public static boolean onVPN(Player player){
        return Detection.checkIP(player.getAddress().getHostString());
    }
}
