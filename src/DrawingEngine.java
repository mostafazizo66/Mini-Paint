import java.awt.*;
import java.util.List;

public interface DrawingEngine {
    void addShape(Shape shape);
    void removeShape(Shape shape);
    List<Shape> getShapes();
    void refresh(Graphics canvas);
    Shape getShapeByName(String name);
}
