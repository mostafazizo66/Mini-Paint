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

        if (newX < this.getPosition().getX() && newY < this.getPosition().getY()) {
            newX = (int) (newX - this.getPosition().getX());
            newY = (int) (newY - this.getPosition().getY());
        } else if (newX < this.getPosition().getX()) {
            newX = (int) (newX - this.getPosition().getX());
        } else if (newY < this.getPosition().getY()) {
            newY = (int) (newY - this.getPosition().getY());
        }
        Point p = new Point((int) (this.getPosition().getX() + newX), (int) (this.getPosition().getY() + newY));
        this.setPosition(p);
        this.properties.put("EndX", (double) (properties.get("EndX") + newX));
        this.properties.put("EndY", (double) (properties.get("EndY") + newY));
    }


    public void resize(int newEndX, int newEndY) {
        if (properties != null && properties.containsKey("EndX") && properties.containsKey("EndY")) {
            properties.put("EndX", (double) (newEndX));
            properties.put("EndY", (double) (newEndY));
        }

    }


}
