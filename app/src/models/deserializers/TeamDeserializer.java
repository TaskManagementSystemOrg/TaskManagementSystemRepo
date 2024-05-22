package models.deserializers;

import com.google.gson.*;
import models.PersonImpl;
import models.TeamImpl;

import java.lang.reflect.Type;

public class TeamDeserializer implements JsonDeserializer<TeamImpl> {

    @Override
    public TeamImpl deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        TeamImpl team = new TeamImpl(name);
        for(JsonElement member : jsonObject.getAsJsonArray("members"))
        {
            team.addMember(member.getAsString());
        }
        for(JsonElement board : jsonObject.getAsJsonArray("boards"))
        {
            team.addBoard(board.getAsString());
        }

        return team;
    }
}
