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


    @Override
    public void move(int newX, int newY) {
        Point p = new Point((int) (this.getPosition().getX() + newX), (int) (this.getPosition().getY() + newY));
        this.setPosition(p);
        this.properties.put("EndX", (double) (properties.get("EndX") + newX));
        this.properties.put("EndY", (double) (properties.get("EndY") + newY));
    }


    public void resize() {

    }


}
