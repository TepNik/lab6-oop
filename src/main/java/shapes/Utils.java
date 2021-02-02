package shapes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    private static Gson createShapesGson() {
        ShapeDeserializer deserializer = new ShapeDeserializer("type");
        deserializer.registerShapeType("triangle", Triangle.class);
        deserializer.registerShapeType("rectangle", Rectangle.class);
        deserializer.registerShapeType("circle", Circle.class);
        deserializer.registerShapeType("square", Square.class);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Shape.class, deserializer)
                .create();
        return gson;
    }

    public static List<Shape> readShapes() {
        String filename = "shapes.json";
        File file = new File(filename);
        String jsonShapes;
        List<Shape> shapes;

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            Scanner scanner = new Scanner(file);
            jsonShapes = scanner.next();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        shapes = createShapesGson().fromJson(jsonShapes, new TypeToken<List<Shape>>(){}.getType());
        return shapes;
    }

    public static boolean saveShapes(List<Shape> shapes) {
        String jsonShapes = createShapesGson().toJson(shapes);
        String filename = "shapes.json";

        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(jsonShapes);
            myWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
