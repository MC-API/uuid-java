package net.mcapi.uuid.queries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;
import java.util.concurrent.Callable;

import lombok.AllArgsConstructor;
import net.mcapi.uuid.utils.UUIDUtils;

import org.json.simple.JSONObject;

import com.jcabi.aspects.Async;

public @AllArgsConstructor class UUIDQuery implements Callable<UUID> {

    private String name;

    // Might add the @Cacheable annotation
    @Async public UUID call() throws Exception {
        URL url = new URL("http://mc-api.net/uuid/" + name);
        URLConnection con = url.openConnection();

        con.addRequestProperty("User-Agent", "MC-API Java Client");
        con.connect();

        JSONObject jsonReturn = (JSONObject)UUIDUtils.getJsonParser().parse(new BufferedReader(new InputStreamReader(con.getInputStream())));
        if(jsonReturn.containsKey("uuid")) {
            String uuidString = String.valueOf(jsonReturn.get("uuid"));
            uuidString = uuidString.contains("-") ? uuidString : UUIDUtils.convertUUIDToJava(uuidString);
            return UUID.fromString(uuidString);
        }
        return null;
    }

}
