package shapes;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ShapeDeserializer implements JsonDeserializer<Shape> {
    private String shapeTypeElementName;
    private Gson gson;
    private Map<String, Class<? extends Shape>> shapeTypeRegistry;

    public ShapeDeserializer(String animalTypeElementName) {
        this.shapeTypeElementName = animalTypeElementName;
        this.gson = new Gson();
        this.shapeTypeRegistry = new HashMap<>();
    }

    public void registerShapeType(String shapeTypeName, Class<? extends Shape> shapeType) {
        shapeTypeRegistry.put(shapeTypeName, shapeType);
    }

    public Shape deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject animalObject = json.getAsJsonObject();
        JsonElement animalTypeElement = animalObject.get(shapeTypeElementName);

        Class<? extends Shape> animalType = shapeTypeRegistry.get(animalTypeElement.getAsString());
        return gson.fromJson(animalObject, animalType);
    }
}