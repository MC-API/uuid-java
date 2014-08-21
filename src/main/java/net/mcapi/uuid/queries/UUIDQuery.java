package net.mcapi.uuid.queries;

import java.io.*;
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

    /*
     * Might add the @Cacheable annotation
     */
    @Async public UUID call() throws Exception {
        URL url = new URL("http://mc-api.net/uuid/" + name);
        URLConnection con = url.openConnection();

        con.addRequestProperty("User-Agent", "Mozilla/4.0");
        con.connect();

        JSONObject jsonReturn = (JSONObject)UUIDUtils.getParser().parse(new BufferedReader(new InputStreamReader(con.getInputStream())));
        String uuidString = String.valueOf(jsonReturn.get("uuid"));
        uuidString = uuidString.contains("-") ? uuidString : UUIDUtils.convertUUIDToJava(uuidString);
        return UUID.fromString(uuidString);
    }

}
