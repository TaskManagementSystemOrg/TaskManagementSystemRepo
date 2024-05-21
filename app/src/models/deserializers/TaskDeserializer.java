package models.deserializers;

import com.google.gson.*;
import models.*;

import java.lang.reflect.Type;

public class TaskDeserializer implements JsonDeserializer<TaskImpl> {
    @Override
    public TaskImpl deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String title = jsonObject.get("title").getAsString();
        String description = jsonObject.get("description").getAsString();
        String taskType = jsonObject.get("type").getAsString();


        switch (taskType) {
            case "BUG":
                return context.deserialize(json, BugImpl.class);
            case "STORY":
                return context.deserialize(json, StoryImpl.class);
            case "FEEDBACK":
                return context.deserialize(json, FeedbackImpl.class);
            default:
                throw new JsonParseException("Unknown task type: " + taskType);
        }
    }
}
