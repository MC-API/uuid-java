package net.mcapi.uuid.queries;

import java.util.concurrent.Callable;

import javax.json.JsonObject;
import javax.json.JsonReader;

import lombok.AllArgsConstructor;

import com.jcabi.aspects.Async;
import com.jcabi.http.request.JdkRequest;
import com.jcabi.http.response.JsonResponse;
import com.jcabi.http.wire.AutoRedirectingWire;

public @AllArgsConstructor class NameQuery implements Callable<String> {

    private String uuid;

    // Might add the @Cacheable annotation
    @Async public String call() throws Exception {
        JsonReader jsonReturn = new JdkRequest("http://mc-api.net/name/" + uuid).through(AutoRedirectingWire.class, 1).header("User-Agent", "MC-API Java Client").fetch().as(JsonResponse.class).json();

        JsonObject object = jsonReturn.readObject();
        if(!object.containsKey("name")) {
            return null;
        }

        return object.getString("name");
    }

}
