import java.awt.*;
import java.util.Map;

public class Square extends ShapeTemp {




    @Override
    public void draw(Graphics canvas) {
        if (properties != null && properties.containsKey("SideLength")) {
            int sideLength = properties.get("SideLength").intValue();
            canvas.setColor(fillColor);
            canvas.fillRect(position.x, position.y, sideLength, sideLength);
            canvas.setColor(color);
            canvas.drawRect(position.x, position.y, sideLength, sideLength);
        }
    }


}
