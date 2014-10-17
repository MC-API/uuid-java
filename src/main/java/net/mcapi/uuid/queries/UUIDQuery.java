package net.mcapi.uuid.queries;

import java.util.UUID;
import java.util.concurrent.Callable;

import javax.json.JsonObject;
import javax.json.JsonReader;

import lombok.AllArgsConstructor;
import net.mcapi.uuid.utils.UUIDUtils;

import com.jcabi.aspects.Async;
import com.jcabi.http.request.JdkRequest;
import com.jcabi.http.response.JsonResponse;
import com.jcabi.http.wire.AutoRedirectingWire;

public @AllArgsConstructor class UUIDQuery implements Callable<UUID> {

    private String name;

    // Might add the @Cacheable annotation
    @Async public UUID call() throws Exception {
        JsonReader jsonReturn = new JdkRequest("http://mc-api.net/uuid/" + name).through(AutoRedirectingWire.class, 1).header("User-Agent", "MC-API Java Client").fetch().as(JsonResponse.class).json();

        JsonObject object = jsonReturn.readObject();
        if(!object.containsKey("uuid")) {
            return null;
        }

        String uuidString = object.getString("uuid");
        uuidString = uuidString.contains("-") ? uuidString : UUIDUtils.convertUUIDToJava(uuidString);
        return UUID.fromString(uuidString);
    }

}
