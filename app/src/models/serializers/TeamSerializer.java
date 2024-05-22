package models.serializers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import models.PersonImpl;
import models.TeamImpl;

import java.lang.reflect.Type;
import java.util.List;

public class TeamSerializer implements JsonSerializer<TeamImpl> {
    @Override
    public JsonElement serialize(TeamImpl team, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("class", team.getClass().getName());
        jsonObject.addProperty("name",team.getName());

        JsonArray membersArray = new JsonArray();
        List<String> members = team.getMembers();
        for (String member : members) {
            membersArray.add(member);
        }
        jsonObject.add("members", membersArray);

        JsonArray boardsArray = new JsonArray();
        List<String> boards = team.getBoards();
        for (String board : boards) {
            boardsArray.add(board);
        }
        jsonObject.add("boards", boardsArray);

        return jsonObject;
    }
}
