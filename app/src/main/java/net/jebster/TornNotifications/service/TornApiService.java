package net.jebster.TornNotifications.service;

import android.support.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.jebster.TornNotifications.model.TornUser;
import net.jebster.TornNotifications.tools.ArrayUtils;

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
    public static final String COOLDOWNS = "cooldowns";

    private static String[] selectedFields;

    public static TornUser getUserData(String key, String[] f){
        selectedFields = f;
        String fields = "";
        for (String field : f) {
            fields += field+",";
        }
        fields = fields.substring(0, fields.length()-1);
        JsonObject obj = apiRequest(key, fields); //"basic,bars,travel");

        TornUser tu = new TornUser();
        tu.setApiKey(key);

        if(obj != null && obj.has("error"))
            tu.setErrorText(get(obj, "error", "error").getAsString());
        else if(obj != null)
            tu = makeTornObject(obj, tu);
        else
            tu.setErrorText("Some error");


        return tu;
    }

    private static TornUser makeTornObject(JsonObject obj, TornUser user){

        user.setId(get(obj,"player_id").getAsInt());
        user.setUsername(get(obj, "name").getAsString());

        user.setEnergy(bar(obj, "energy"));
        user.setHappy(bar(obj, "happy"));
        user.setNerve(bar(obj, "nerve"));
        user.setLife(bar(obj, "life"));

        user.setTravel(travel(obj));
        user.setNotifications(notifications(obj));
        user.setCooldowns(cooldowns(obj));


        return user;
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

    private static TornUser.Bar bar(JsonObject obj, String type){
        if(ArrayUtils.contains(selectedFields, BARS))
            return new TornUser.Bar(
                    get(obj, type, "current").getAsInt(),
                    get(obj, type, "maximum").getAsInt(),
                    get(obj, type, "increment").getAsInt(),
                    get(obj, type, "interval").getAsInt(),
                    get(obj, type, "ticktime").getAsInt(),
                    get(obj, type, "fulltime").getAsInt()
            );
        else
            return new TornUser.Bar();
    }

    private static TornUser.Travel travel(JsonObject obj){
        if(ArrayUtils.contains(selectedFields, TRAVEL))
            return new TornUser.Travel(
                    get(obj, "travel", "destination").getAsString(),
                    get(obj, "travel", "timestamp").getAsLong(),
                    get(obj, "travel", "departed").getAsLong(),
                    get(obj, "travel", "time_left").getAsLong()
            );
        else
            return new TornUser.Travel();
    }

    private static TornUser.Notifications notifications(JsonObject obj){
        if(ArrayUtils.contains(selectedFields, NOTIFICATIONS))
            return new TornUser.Notifications(
                    get(obj, "notifications", "messages").getAsInt(),
                    get(obj, "notifications", "events").getAsInt(),
                    get(obj, "notifications", "awards").getAsInt(),
                    get(obj, "notifications", "competition").getAsInt()
            );
        else
            return new TornUser.Notifications();
    }

    private static TornUser.Cooldown cooldowns(JsonObject obj){
        if(ArrayUtils.contains(selectedFields, COOLDOWNS))
            return new TornUser.Cooldown(
                    get(obj, "cooldowns", "drug").getAsInt(),
                    get(obj, "cooldowns", "medical").getAsInt(),
                    get(obj, "cooldowns", "booster").getAsInt()
            );
        else
            return new TornUser.Cooldown();
    }
}
