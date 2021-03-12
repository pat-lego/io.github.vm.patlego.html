package io.github.vm.patlego.html.core.pages.utills;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import io.github.vm.patlego.html.core.GsonUtil;

public class PageGsonUtil implements GsonUtil {

    @Override
    public Gson getGson() {
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type type,
                    JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                        .withZone(ZoneId.of("UTC"));
                return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), formatter);
            }
        }).create();
    }
    
}
