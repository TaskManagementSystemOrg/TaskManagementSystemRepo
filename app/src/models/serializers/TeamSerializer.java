package models.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import models.PersonImpl;
import models.TeamImpl;

import java.lang.reflect.Type;

public class TeamSerializer implements JsonSerializer<TeamImpl> {
    @Override
    public JsonElement serialize(TeamImpl team, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("class", team.getClass().getName());
        jsonObject.addProperty("name",team.getName());
        return jsonObject;
    }
}
