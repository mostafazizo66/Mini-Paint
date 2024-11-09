import java.awt.*;

public class LineSegment extends ShapeTemp {


    @Override
    public void draw(Graphics canvas) {
        if (properties != null && properties.containsKey("EndX") && properties.containsKey("EndY")) {
            int endX = properties.get("EndX").intValue();
            int endY = properties.get("EndY").intValue();
            canvas.setColor(color);
            canvas.drawLine(position.x, position.y, endX, endY);

        }
    }


}
