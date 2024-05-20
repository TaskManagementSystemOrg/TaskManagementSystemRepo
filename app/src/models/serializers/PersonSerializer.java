package models.serializers;

import com.google.gson.*;
import models.PersonImpl;
import models.contracts.Person;
import models.contracts.Task;

import java.lang.reflect.Type;

public class PersonSerializer implements JsonSerializer<PersonImpl> {
    public JsonElement serialize(PersonImpl person, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("class", person.getClass().getName());
        jsonObject.addProperty("name",person.getName());

        JsonArray activityArray = new JsonArray();
        for (String activity : person.getActivity()) {
            activityArray.add(new JsonPrimitive(activity));
        }

        jsonObject.add("activityHistory", activityArray);

        return jsonObject;

    }
}