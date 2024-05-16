package models.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import models.PersonImpl;
import models.contracts.Person;

import java.lang.reflect.Type;

public class PersonSerializer implements JsonSerializer<PersonImpl> {
    public JsonElement serialize(PersonImpl person, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("class", person.getClass().getName());
        jsonObject.addProperty("name",person.getName());
        return jsonObject;
    }
}