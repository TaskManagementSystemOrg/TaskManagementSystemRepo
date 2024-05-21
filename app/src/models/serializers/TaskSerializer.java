package models.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import models.BugImpl;
import models.FeedbackImpl;
import models.StoryImpl;
import models.TaskImpl;

import java.lang.reflect.Type;

public class TaskSerializer implements JsonSerializer<TaskImpl> {
    @Override
    public JsonElement serialize(TaskImpl task, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("class", task.getClass().getName());
        jsonObject.addProperty("title", task.getTitle());
        jsonObject.addProperty("description", task.getDescription());
        jsonObject.addProperty("type", task.getType().toString());

        if (task instanceof BugImpl) {
            BugImpl bug = (BugImpl) task;
            jsonObject.addProperty("priority", bug.getPriority().toString());
            jsonObject.addProperty("severity", bug.getSeverity().toString());
            jsonObject.addProperty("status", bug.getStatus().toString());
            jsonObject.add("stepsToReproduce", context.serialize(bug.getStepsToReproduce()));
            //jsonObject.add("assignee", context.serialize(bug.getAssignee()));
        } else if (task instanceof StoryImpl) {
            StoryImpl story = (StoryImpl) task;
            jsonObject.addProperty("priority", story.getPriority().toString());
            jsonObject.addProperty("size", story.getSize().toString());
            jsonObject.addProperty("status", story.getStatus().toString());
            //jsonObject.add("assignee", context.serialize(story.getAssignee()));
        } else if (task instanceof FeedbackImpl) {
            FeedbackImpl feedback = (FeedbackImpl) task;
            jsonObject.addProperty("rating", feedback.getRating());
            jsonObject.addProperty("status", feedback.getStatus().toString());
        }

        return jsonObject;
    }

}
