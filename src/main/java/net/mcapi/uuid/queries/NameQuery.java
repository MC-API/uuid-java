package net.mcapi.uuid.queries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

import lombok.AllArgsConstructor;
import net.mcapi.uuid.utils.UUIDUtils;

import org.json.simple.JSONObject;

import com.jcabi.aspects.Async;

public @AllArgsConstructor class NameQuery implements Callable<String> {

    private String uuid;

    // Might add the @Cacheable annotation
    @Async public String call() throws Exception {
        URL url = new URL("http://mc-api.net/name/" + uuid);
        URLConnection con = url.openConnection();

        con.addRequestProperty("User-Agent", "MC-API Java Client");
        con.connect();

        JSONObject jsonReturn = (JSONObject)UUIDUtils.getJsonParser().parse(new BufferedReader(new InputStreamReader(con.getInputStream())));
        if(jsonReturn.containsKey("name")) {
            return (String)jsonReturn.get("name");
        }
        return null;
    }

}
