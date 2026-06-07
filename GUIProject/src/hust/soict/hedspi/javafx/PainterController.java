package hust.soict.hedspi.javafx;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {
    @FXML
    private static boolean eraserMode = false;

    @FXML
    private Pane drawingAreaPane;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void selectPen(javafx.event.ActionEvent e) {
        eraserMode = false;
    }

    @FXML
    void selectEraser(javafx.event.ActionEvent e) {
        eraserMode = true;
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        Color chosenColor = Color.BLACK;
        Point2D p = drawingAreaPane.sceneToLocal(event.getSceneX(), event.getSceneY());
        // set the limit of x and y
        double x = Math.clamp(p.getX(), 0, drawingAreaPane.getWidth());
        double y = Math.clamp(p.getY(), 0, drawingAreaPane.getHeight());
        Circle newCircle = new Circle(x, y, 4, chosenColor);
        if (eraserMode) {
            newCircle.setFill(Color.WHITE);
        }
        drawingAreaPane.getChildren().add(newCircle);
    }
}
