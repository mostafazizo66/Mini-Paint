import java.awt.*;
import java.util.Map;

public class Rectangle extends ShapeTemp {


    @Override
    public void draw(Graphics canvas) {
        if (properties != null && properties.containsKey("Width") && properties.containsKey("Height")) {
            int width = properties.get("Width").intValue();
            int height = properties.get("Height").intValue();
            canvas.setColor(fillColor);
            canvas.fillRect(position.x, position.y, width, height);
            canvas.setColor(color);
            canvas.drawRect(position.x, position.y, width, height);
        }
    }

    @Override
    public void move(int newX, int newY) {
        Point p = new Point((int) (newX), (int) (newY));
        this.setPosition(p);

    }


    public void resize(int newWidth, int newHeight) {
        if (properties != null && properties.containsKey("Width") && properties.containsKey("Height")) {

            properties.put("Width", (double) (newWidth));
            properties.put("Height", (double) (newHeight));
        }

    }


}
