import java.awt.*;
import java.util.Map;

public class LineSegment implements Shape {
    private Point position;
    private Map<String, Double> properties;
    private Color color;
    private String name;

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setProperties(Map<String, Double> properties) {
        this.properties = properties;
    }

    @Override
    public Map<String, Double> getProperties() {
        return properties;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setFillColor(Color color) {
        // Not applicable for line segments
    }

    @Override
    public Color getFillColor() {
        return color;
    }

    @Override
    public void draw(Graphics canvas) {
        if (properties != null && properties.containsKey("EndX") && properties.containsKey("EndY")) {
            int endX = properties.get("EndX").intValue();
            int endY = properties.get("EndY").intValue();
            canvas.setColor(color);
            canvas.drawLine(position.x, position.y, endX, endY);

        }
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
