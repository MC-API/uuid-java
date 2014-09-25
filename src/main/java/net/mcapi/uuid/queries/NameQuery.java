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

    /*
     * Might add the @Cacheable annotation
     */
    @Async public String call() throws Exception {
        URL url = new URL("http://mc-api.net/name/" + uuid);
        URLConnection con = url.openConnection();

        con.addRequestProperty("User-Agent", "Mozilla/4.0");
        con.connect();

        JSONObject jsonReturn = (JSONObject)UUIDUtils.getParser().parse(new BufferedReader(new InputStreamReader(con.getInputStream())));
        return String.valueOf(jsonReturn.get("name"));
    }

}
