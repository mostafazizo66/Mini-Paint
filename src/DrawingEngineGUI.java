import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawingEngineGUI implements DrawingEngine {
    private List<Shape> shapes = new ArrayList<Shape>();

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
    public Shape getShapeByName(String name) {
        for (Shape shape : shapes) {
            if (shape.getName().equals(name)) {
                return shape;
            }
        }
        return null;
    }
}