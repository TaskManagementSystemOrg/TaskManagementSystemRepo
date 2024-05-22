package models.deserializers;

import com.google.gson.*;
import models.BoardImpl;
import models.contracts.Task;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BoardDeserializer implements JsonDeserializer<BoardImpl> {

    @Override
    public BoardImpl deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String name = jsonObject.get("name").getAsString();
        BoardImpl board = new BoardImpl(name);

        JsonArray activityArray = jsonObject.getAsJsonArray("activityHistory");
        List<String> activityHistory = new ArrayList<>();
        for (JsonElement activityElement : activityArray) {
            String activity = activityElement.getAsString();
            activityHistory.add(activity);
        }
        board.getActivity().addAll(activityHistory);

        // Deserialize tasks
        JsonArray tasksArray = jsonObject.getAsJsonArray("tasks");
        List<Integer> tasks = new ArrayList<>();
        for (JsonElement taskElement : tasksArray) {
            int taskId = taskElement.getAsInt();
            tasks.add(taskId);
        }
        board.getTasks().addAll(tasks);

        return board;
    }
}
