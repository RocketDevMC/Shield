package me.daddy.shield.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.daddy.shield.Shield;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class Detection {


    public static boolean checkIP(String address) {
        try {
            Connection.Response res = Jsoup.connect("http://v2.api.iphub.info/ip/" + address)
                    .method(Connection.Method.GET)
                    .userAgent("Mozilla")
                    .header("X-Key", Shield.getPlugin().getConfig().getString("antivpn.key"))
                    .header("Connection", "close")
                    .ignoreContentType(true)
                    .execute();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(res.body(), JsonObject.class);

            String ip = jsonObject.get("ip").getAsString();
            String countryCode = jsonObject.get("countryCode").getAsString();
            String countryName = jsonObject.get("countryName").getAsString();
            String isp = jsonObject.get("isp").getAsString();
            int block = jsonObject.get("block").getAsInt();

            if(block == 0){
                return true;
            }
            if (block == 1){
                return false;
            }
            if(block == 2){
                return Shield.getPlugin().getConfig().getString("Level").equals("Low");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}