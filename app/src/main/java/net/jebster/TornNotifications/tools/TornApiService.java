package net.jebster.TornNotifications.tools;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.jebster.TornNotifications.model.TornUser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jeggy on 9/10/16.
 */
public class TornApiService {

    public static final String BASIC = "basic";
    public static final String BARS = "bars";
    public static final String TRAVEL = "travel";

    public static TornUser getUserData(String key){
        JsonObject obj = apiRequest(key, "basic,bars,travel");

        TornUser tu;

        if(obj != null && obj.has("error")){
            tu = new TornUser(
                    obj.get("error").getAsJsonObject().get("error").getAsString()
            );
        } else if(obj != null) {
            tu = new TornUser(
                    get(obj, "player_id").getAsInt(),
                    get(obj, "name").getAsString(),
                    key,
                    get(obj, "energy","current").getAsInt(),
                    get(obj, "energy","maximum").getAsInt(),
                    get(obj, "happy","current").getAsInt(),
                    get(obj, "happy","maximum").getAsInt(),
                    get(obj, "nerve","current").getAsInt(),
                    get(obj, "nerve","maximum").getAsInt(),
                    get(obj, "life","current").getAsInt(),
                    get(obj, "life","maximum").getAsInt(),

                    get(obj, "travel", "destination").getAsString(),
                    get(obj, "travel", "time_left").getAsInt(),
                    get(obj, "travel", "timestamp").getAsInt() - get(obj, "travel", "departed").getAsInt()
            );
//            (obj.get("travel").getAsJsonObject().get("timestamp").getAsInt())-(obj.get("travel").getAsJsonObject().get("departed").getAsInt())
        } else {
            tu = new TornUser("Damn error");
        }

        return tu;
    }

    private static JsonElement get(JsonObject obj, String... g){
        for (int i = 0; i < g.length - 1; i++) {
            obj = obj.get(g[i]).getAsJsonObject();
        }

        return obj.get(g[g.length-1]);
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
