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

    public static TornUser getUserData(String key, String[] f){
        String fields = "";
        for (String field : f) {
            fields += field+",";
        }
        fields = fields.substring(0, fields.length()-1);
        JsonObject obj = apiRequest(key, fields); //"basic,bars,travel");

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
                    new TornUser.Bar(
                            get(obj, "energy","current").getAsInt(),
                            get(obj, "energy","maximum").getAsInt(),
                            get(obj, "energy","increment").getAsInt(),
                            get(obj, "energy","interval").getAsInt(),
                            get(obj, "energy","ticktime").getAsInt(),
                            get(obj, "energy","fulltime").getAsInt()
                    ),
                    new TornUser.Bar(
                            get(obj, "happy","current").getAsInt(),
                            get(obj, "happy","maximum").getAsInt(),
                            get(obj, "happy","increment").getAsInt(),
                            get(obj, "happy","interval").getAsInt(),
                            get(obj, "happy","ticktime").getAsInt(),
                            get(obj, "happy","fulltime").getAsInt()
                    ),
                    new TornUser.Bar(
                            get(obj, "nerve","current").getAsInt(),
                            get(obj, "nerve","maximum").getAsInt(),
                            get(obj, "nerve","increment").getAsInt(),
                            get(obj, "nerve","interval").getAsInt(),
                            get(obj, "nerve","ticktime").getAsInt(),
                            get(obj, "nerve","fulltime").getAsInt()
                    ),
                    new TornUser.Bar(
                            get(obj, "life","current").getAsInt(),
                            get(obj, "life","maximum").getAsInt(),
                            get(obj, "life","increment").getAsInt(),
                            get(obj, "life","interval").getAsInt(),
                            get(obj, "life","ticktime").getAsInt(),
                            get(obj, "life","fulltime").getAsInt()
                    ),
                    new TornUser.Travel(
                            get(obj, "travel", "destination").getAsString(),
                            get(obj, "travel", "timestamp").getAsLong(),
                            get(obj, "travel", "departed").getAsLong(),
                            get(obj, "travel", "time_left").getAsLong()
                    )
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
