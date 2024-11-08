import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeManager implements DrawingEngine {
    private List<Shape> shapes = new ArrayList<>();

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    @Override
    public List<Shape> getShapes() {
        return shapes;
    }

    @Override
    public void refresh(Graphics canvas) {
        for (Shape shape : shapes) {
            shape.draw(canvas);
        }
    }

    @Override
    public Shape getShapeByName(String selectedShapeName) {
        return null;
    }
}
