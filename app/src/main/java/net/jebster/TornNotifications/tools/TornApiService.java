package net.jebster.TornNotifications.tools;

import android.support.annotation.Nullable;

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
    public static final String NOTIFICATIONS = "notifications";

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

            tu = makeTornObject(obj, key);

        } else {
            tu = new TornUser("Some error");
        }

        return tu;
    }

    private static TornUser makeTornObject(JsonObject obj, String key){
        int player_id = get(obj,"player_id").getAsInt();
        String name = get(obj, "name").getAsString();
        long server_time = get(obj, "server_time").getAsLong();

        TornUser.Bar energy, happy, nerve, life;
        TornUser.Travel travel;
        TornUser.Notifications notifications;

        energy = new TornUser.Bar(
                get(obj, "energy","current").getAsInt(),
                get(obj, "energy","maximum").getAsInt(),
                get(obj, "energy","increment").getAsInt(),
                get(obj, "energy","interval").getAsInt(),
                get(obj, "energy","ticktime").getAsInt(),
                get(obj, "energy","fulltime").getAsInt()
        );
        happy = new TornUser.Bar(
                get(obj, "happy","current").getAsInt(),
                get(obj, "happy","maximum").getAsInt(),
                get(obj, "happy","increment").getAsInt(),
                get(obj, "happy","interval").getAsInt(),
                get(obj, "happy","ticktime").getAsInt(),
                get(obj, "happy","fulltime").getAsInt()
        );
        nerve = new TornUser.Bar(
                get(obj, "nerve","current").getAsInt(),
                get(obj, "nerve","maximum").getAsInt(),
                get(obj, "nerve","increment").getAsInt(),
                get(obj, "nerve","interval").getAsInt(),
                get(obj, "nerve","ticktime").getAsInt(),
                get(obj, "nerve","fulltime").getAsInt()
        );
        life = new TornUser.Bar(
                get(obj, "life","current").getAsInt(),
                get(obj, "life","maximum").getAsInt(),
                get(obj, "life","increment").getAsInt(),
                get(obj, "life","interval").getAsInt(),
                get(obj, "life","ticktime").getAsInt(),
                get(obj, "life","fulltime").getAsInt()
        );
        travel = new TornUser.Travel(
                get(obj, "travel", "destination").getAsString(),
                get(obj, "travel", "timestamp").getAsLong(),
                get(obj, "travel", "departed").getAsLong(),
                get(obj, "travel", "time_left").getAsLong()
        );
        try {
            notifications = new TornUser.Notifications(
                    get(obj, "notifications", "messages").getAsInt(),
                    get(obj, "notifications", "events").getAsInt(),
                    get(obj, "notifications", "awards").getAsInt(),
                    get(obj, "notifications", "competition").getAsInt()
            );
        }catch (NullPointerException e){
            notifications = null;
        }

        return new TornUser(player_id, name, key, energy, happy, nerve, life, travel, notifications, server_time);
    }

    @Nullable
    private static JsonElement get(JsonObject obj, String... g){
        try {
            for (int i = 0; i < g.length - 1; i++) {
                obj = obj.get(g[i]).getAsJsonObject();
            }

            return obj.get(g[g.length - 1]);
        }catch (NullPointerException e){
            return null;
        }
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
