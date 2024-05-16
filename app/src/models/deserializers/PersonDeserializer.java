package models.deserializers;

import com.google.gson.*;
import models.PersonImpl;
import models.contracts.Person;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersonDeserializer implements JsonDeserializer<PersonImpl> {

    @Override
    public PersonImpl deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement classElement = jsonObject.get("class");
        if (classElement != null && classElement.isJsonPrimitive()) {
            String className = classElement.getAsString();
            try {
                Class<?> personClass = Class.forName(className);
                return context.deserialize(jsonObject, personClass);
            } catch (ClassNotFoundException e) {
                throw new JsonParseException("Unable to deserialize Person. Class not found: " + className, e);
            }
        } else {
            throw new JsonParseException("Missing or invalid 'class' field in JSON for Person object");
        }
    }
}
