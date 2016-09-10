package net.jebster.TornNotifications;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jeggy on 9/10/16.
 */
public class TornApiService {

    public static TornUser getUserData(String key){
        JsonObject obj = apiRequest(key, "basic,bars,travel");

        TornUser tu;

        if(obj != null && obj.has("error")){
            tu = new TornUser(
                    obj.get("error").getAsJsonObject().get("error").getAsString()
            );
        } else if(obj != null) {
            tu = new TornUser(
                    obj.get("player_id").getAsInt(),
                    obj.get("name").getAsString(),
                    key,

                    obj.get("energy").getAsJsonObject().get("current").getAsInt(),
                    obj.get("energy").getAsJsonObject().get("maximum").getAsInt(),
                    obj.get("happy").getAsJsonObject().get("current").getAsInt(),
                    obj.get("happy").getAsJsonObject().get("maximum").getAsInt(),
                    obj.get("nerve").getAsJsonObject().get("current").getAsInt(),
                    obj.get("nerve").getAsJsonObject().get("maximum").getAsInt(),

                    obj.get("travel").getAsJsonObject().get("destination").getAsString(),
                    obj.get("travel").getAsJsonObject().get("time_left").getAsInt()
            );
        } else {
            tu = new TornUser("Damn error");
        }

        return tu;
    }


    private static JsonObject apiRequest(String key, String selections){
        try {
            URL url = new URL("https://api.torn.com/user/?selections="+selections+"&key="+key);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));

            return root.getAsJsonObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
