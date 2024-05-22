package models.serializers;

import com.google.gson.*;
import models.BoardImpl;

import java.lang.reflect.Type;

public class BoardSerializer implements JsonSerializer<BoardImpl> {
    @Override
    public JsonElement serialize(BoardImpl board, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("class", board.getClass().getName());
        jsonObject.addProperty("name", board.getName());

        JsonArray activityArray = new JsonArray();
        for (String activity : board.getActivity()) {
            activityArray.add(new JsonPrimitive(activity));
        }
        jsonObject.add("activityHistory", activityArray);

        JsonArray tasksArray = new JsonArray();
        for (int id : board.getTasks()) {
            tasksArray.add(new JsonPrimitive(id));  // Corrected line
        }
        jsonObject.add("tasks", tasksArray);

        return jsonObject;
    }
}
