package app;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import com.google.gson.*;

import com.google.gson.reflect.TypeToken;
import shapes.*;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;


public class Main extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage)
    {
        String style = "-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: teal;";
        Label mainLbl = new Label();
        Button deleteBtn = new Button("Delete");
        Button moveUpButton = new Button("Move Up");
        Button moveDownButton = new Button("Move Down");
        Button createTriangleButton = new Button("Create triangle");
        Button createRectangleButton = new Button("Create rectangle");
        Button createCircleButton = new Button("Create circle");
        Button createSquareButton = new Button("Create square");

        List<Shape> shapes = Utils.readShapes();
        ListView<Shape> shapeViews = new ListView<>();
        ObservableList<Shape> shapesObservableList = FXCollections.observableList(shapes);
        shapeViews.setItems(shapesObservableList);
        shapeViews.getSelectionModel().selectFirst();

        deleteBtn.setOnAction(event -> {
            int index = shapeViews.getSelectionModel().getSelectedIndex();
            if (index >= 0 && index < shapesObservableList.size()) {
                shapeViews.getItems().remove(shapeViews.getSelectionModel().getSelectedIndex());
                if (!Utils.saveShapes(shapeViews.getItems())) {
                    mainLbl.setText("Data saving fail, try again");
                }
            }
        });

        moveUpButton.setOnAction(event -> {
            int index = shapeViews.getSelectionModel().getSelectedIndex();
            if (index > 0) {
                Collections.swap(shapesObservableList, index, index-1);
                if (!Utils.saveShapes(shapeViews.getItems())) {
                    mainLbl.setText("Data saving fail, try again");
                }
            }
        });

        moveDownButton.setOnAction(event -> {
            int index = shapeViews.getSelectionModel().getSelectedIndex();
            if (index >= 0 && index + 1 < shapesObservableList.size()) {
                Collections.swap(shapesObservableList, index, index+1);
                if (!Utils.saveShapes(shapeViews.getItems())) {
                    mainLbl.setText("Data saving fail, try again");
                }
            }
        });

        createTriangleButton.setOnAction(event -> {
            GridPane root = new GridPane();
            TextField aField = new TextField();
            TextField bField = new TextField();
            TextField cField = new TextField();
            Button okBtn = new Button("OK");
            Button cancelBtn = new Button("Cancel");
            Label infoLbl = new Label();
            okBtn.setOnAction(okEvent -> {
                Stage thisWindow = (Stage) okBtn.getScene().getWindow();
                double a, b, c;
                try {
                    a = Double.parseDouble(aField.getText());
                    b = Double.parseDouble(bField.getText());
                    c = Double.parseDouble(cField.getText());
                    Triangle triangle;
                    try {
                        triangle = new Triangle(a, b, c);
                        shapesObservableList.add(triangle);
                        if (!Utils.saveShapes(shapeViews.getItems())) {
                            mainLbl.setText("Data saving fail, try again");
                        }
                        thisWindow.close();
                    } catch (IllegalStateException e) {
                        infoLbl.setText("Error: invalid tringle");
                    }
                } catch (NumberFormatException e) {
                    infoLbl.setText("Error: not numeric values");
                }
            });
            cancelBtn.setOnAction(cancelEvent -> {
                Stage thisWindow = (Stage) cancelBtn.getScene().getWindow();
                thisWindow.close();
            });
            GridPane.setColumnSpan(infoLbl, 2);
            root.addRow(0, new Label("Side A "), aField);
            root.addRow(1, new Label("Side B "), bField);
            root.addRow(2, new Label("Side C "), cField);
            root.addRow(3, okBtn, cancelBtn);
            root.addRow(4, infoLbl);
            root.setStyle(style);
            Scene scene = new Scene(root);
            Stage newWindow = new Stage();
            newWindow.setScene(scene);
            newWindow.setX(stage.getX() + 200);
            newWindow.setY(stage.getY() + 100);
            newWindow.show();
        });

        createRectangleButton.setOnAction(event -> {
            GridPane root = new GridPane();
            TextField heightField = new TextField();
            TextField widthField = new TextField();
            Button okBtn = new Button("OK");
            Button cancelBtn = new Button("Cancel");
            Label infoLbl = new Label();
            okBtn.setOnAction(okEvent -> {
                Stage thisWindow = (Stage) okBtn.getScene().getWindow();
                double height, width;
                try {
                    height = Double.parseDouble(heightField.getText());
                    width = Double.parseDouble(widthField.getText());
                    Rectangle rectangle;
                    try {
                        rectangle = new Rectangle(width, height);
                        shapesObservableList.add(rectangle);
                        if (!Utils.saveShapes(shapeViews.getItems())) {
                            mainLbl.setText("Data saving fail, try again");
                        }
                        thisWindow.close();
                    } catch (IllegalStateException e) {
                        infoLbl.setText("Error: invalid rectangle");
                    }
                } catch (NumberFormatException e) {
                    infoLbl.setText("Error: not numeric values");
                }
            });
            cancelBtn.setOnAction(cancelEvent -> {
                Stage thisWindow = (Stage) cancelBtn.getScene().getWindow();
                thisWindow.close();
            });
            GridPane.setColumnSpan(infoLbl, 2);
            root.addRow(0, new Label("Height "), heightField);
            root.addRow(1, new Label("Width "), widthField);
            root.addRow(2, okBtn, cancelBtn);
            root.addRow(3, infoLbl);
            root.setStyle(style);
            Scene scene = new Scene(root);
            Stage newWindow = new Stage();
            newWindow.setScene(scene);
            newWindow.setX(stage.getX() + 200);
            newWindow.setY(stage.getY() + 100);
            newWindow.show();
        });

        createSquareButton.setOnAction(event -> {
            GridPane root = new GridPane();
            TextField heightField = new TextField();
            Button okBtn = new Button("OK");
            Button cancelBtn = new Button("Cancel");
            Label infoLbl = new Label();
            okBtn.setOnAction(okEvent -> {
                Stage thisWindow = (Stage) okBtn.getScene().getWindow();
                double height;
                try {
                    height = Double.parseDouble(heightField.getText());
                    Square square;
                    try {
                        square = new Square(height);
                        shapesObservableList.add(square);
                        if (!Utils.saveShapes(shapeViews.getItems())) {
                            mainLbl.setText("Data saving fail, try again");
                        }
                        thisWindow.close();
                    } catch (IllegalStateException e) {
                        infoLbl.setText("Error: invalid square");
                    }
                } catch (NumberFormatException e) {
                    infoLbl.setText("Error: not numeric values");
                }
            });
            cancelBtn.setOnAction(cancelEvent -> {
                Stage thisWindow = (Stage) cancelBtn.getScene().getWindow();
                thisWindow.close();
            });
            GridPane.setColumnSpan(infoLbl, 2);
            root.addRow(0, new Label("Height "), heightField);
            root.addRow(1, okBtn, cancelBtn);
            root.addRow(2, infoLbl);
            root.setStyle(style);
            Scene scene = new Scene(root);
            Stage newWindow = new Stage();
            newWindow.setScene(scene);
            newWindow.setX(stage.getX() + 200);
            newWindow.setY(stage.getY() + 100);
            newWindow.show();
        });

        createCircleButton.setOnAction(event -> {
            GridPane root = new GridPane();
            TextField radiusField = new TextField();
            Button okBtn = new Button("OK");
            Button cancelBtn = new Button("Cancel");
            Label infoLbl = new Label();
            okBtn.setOnAction(okEvent -> {
                Stage thisWindow = (Stage) okBtn.getScene().getWindow();
                double radius;
                try {
                    radius = Double.parseDouble(radiusField.getText());
                    Circle circle;
                    try {
                        circle = new Circle(radius);
                        shapesObservableList.add(circle);
                        if (!Utils.saveShapes(shapeViews.getItems())) {
                            mainLbl.setText("Data saving fail, try again");
                        }
                        thisWindow.close();
                    } catch (IllegalStateException e) {
                        infoLbl.setText("Error: invalid circle");
                    }
                } catch (NumberFormatException e) {
                    infoLbl.setText("Error: not numeric values");
                }
            });
            cancelBtn.setOnAction(cancelEvent -> {
                Stage thisWindow = (Stage) cancelBtn.getScene().getWindow();
                thisWindow.close();
            });
            GridPane.setColumnSpan(infoLbl, 2);
            root.addRow(0, new Label("Radius "), radiusField);
            root.addRow(1, okBtn, cancelBtn);
            root.addRow(2, infoLbl);
            root.setStyle(style);
            Scene scene = new Scene(root);
            Stage newWindow = new Stage();
            newWindow.setScene(scene);
            newWindow.setX(stage.getX() + 200);
            newWindow.setY(stage.getY() + 100);
            newWindow.show();
        });

        GridPane root = new GridPane();
        root.setVgap(10);
        root.setHgap(10);
        root.addRow(0, shapeViews);
        root.addRow(1, mainLbl);
        root.addRow(2, deleteBtn);
        root.addRow(3, moveUpButton);
        root.addRow(4, moveDownButton);
        root.addRow(5, createTriangleButton);
        root.addRow(6, createRectangleButton);
        root.addRow(7, createSquareButton);
        root.addRow(8, createCircleButton);
        root.setMinSize(300, 200);
        root.setStyle(style);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Shapes App");
        stage.show();
    }
}