import java.awt.*;

public class Circle extends ShapeTemp {


    @Override
    public void draw(Graphics canvas) {
        if (properties != null && properties.containsKey("Radius")) {
            int radius = properties.get("Radius").intValue();
            canvas.setColor(fillColor);
            canvas.fillOval(position.x-radius, position.y-radius, radius * 2, radius * 2);
            canvas.setColor(color);
            canvas.drawOval(position.x-radius, position.y-radius, radius * 2, radius * 2);
        }
    }

    @Override
    public void move(int newX, int newY) {
        Point p = new Point((int) (newX), (int) (newY));
        this.setPosition(p);

    }
    public void resize(int newRadius) {
        if (properties != null && properties.containsKey("Radius")) {
            properties.put("Radius", (double) (newRadius));
        }


    }
    }
