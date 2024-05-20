package models.deserializers;

import com.google.gson.*;
import models.PersonImpl;
import models.contracts.Person;
import models.contracts.Task;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersonDeserializer implements JsonDeserializer<PersonImpl> {

    @Override
    public PersonImpl deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Retrieve name
        String name = jsonObject.get("name").getAsString();
        PersonImpl person = new PersonImpl(name);

        JsonArray activityArray = jsonObject.getAsJsonArray("activityHistory");
        List<String> activityHistory = new ArrayList<>();
        for (JsonElement activityElement : activityArray) {
            String activity = activityElement.getAsString();
            activityHistory.add(activity);
        }
        person.getActivity().addAll(activityHistory);

        return person;
    }
}
