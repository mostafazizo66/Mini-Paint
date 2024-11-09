import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DarkPaintingWindow extends PaintingWindow {
    public DarkPaintingWindow() {
        super();
        super.mainPanel.setBackground(Color.black);
        mainPanel.setForeground(Color.white);
        mainPanel.setOpaque(true);
        mainPanel.setVisible(true);
        setTitle("Dark Mode on");


        canvasPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawingEngine.refresh(g);
            }
        };
        canvasPanel.setBorder(BorderFactory.createLineBorder(Color.blue,4));
        canvasPanel.setBackground(Color.black);
        mainPanel.add(canvasPanel, BorderLayout.CENTER);
    }


    @Override
    public void createShape(String shapeType) {
        String shapeName = shapeType.toLowerCase() + (shapeSelector.getItemCount() + 1);


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2, 10, 10));


        Map<String, Double> properties = new HashMap<>();
        Point position = new Point();
        Shape shape = null;


        JTextField xField = new JTextField(5);
        JTextField yField = new JTextField(5);


        switch (shapeType) {
            case "Circle" -> {
                JTextField radiusField = new JTextField(5);
                inputPanel.add(new JLabel("Radius:"));
                inputPanel.add(radiusField);

                inputPanel.add(new JLabel("X Position:"));
                inputPanel.add(xField);
                inputPanel.add(new JLabel("Y Position:"));
                inputPanel.add(yField);

                int result = JOptionPane.showConfirmDialog(this, inputPanel, "Enter Circle Properties", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION && Validations.isValidLength(radiusField.getText()) && Validations.isValidDouble(xField.getText()) && Validations.isValidDouble(yField.getText())) {

                    int radius = Integer.parseInt(radiusField.getText());
                    properties.put("Radius", (double) radius);

                    position = new Point(Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()));
                    shape = new Circle();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
                }
            }
            case "Rectangle" -> {
                JTextField widthField = new JTextField(5);
                JTextField heightField = new JTextField(5);

                inputPanel.add(new JLabel("Width:"));
                inputPanel.add(widthField);
                inputPanel.add(new JLabel("Height:"));
                inputPanel.add(heightField);

                inputPanel.add(new JLabel("X Position:"));
                inputPanel.add(xField);
                inputPanel.add(new JLabel("Y Position:"));
                inputPanel.add(yField);

                int result = JOptionPane.showConfirmDialog(this, inputPanel, "Enter Rectangle Properties", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION && Validations.isValidLength(widthField.getText()) && Validations.isValidLength(heightField.getText()) && Validations.isValidDouble(xField.getText()) && Validations.isValidDouble(yField.getText())) {
                    int width = Integer.parseInt(widthField.getText());
                    int height = Integer.parseInt(heightField.getText());
                    properties.put("Width", (double) width);
                    properties.put("Height", (double) height);

                    position = new Point(Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()));
                    shape = new Rectangle();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
                }
            }
            case "Square" -> {
                JTextField sideLengthField = new JTextField(5);

                inputPanel.add(new JLabel("Side Length:"));
                inputPanel.add(sideLengthField);

                inputPanel.add(new JLabel("X Position:"));
                inputPanel.add(xField);
                inputPanel.add(new JLabel("Y Position:"));
                inputPanel.add(yField);

                int result = JOptionPane.showConfirmDialog(this, inputPanel, "Enter Square Properties", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION && Validations.isValidLength(sideLengthField.getText()) && Validations.isValidDouble(xField.getText()) && Validations.isValidDouble(yField.getText())) {
                    int sideLength = Integer.parseInt(sideLengthField.getText());
                    properties.put("SideLength", (double) sideLength);

                    position = new Point(Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()));
                    shape = new Square();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
                }
            }
            case "LineSegment" -> {
                JTextField endXField = new JTextField(5);
                JTextField endYField = new JTextField(5);

                inputPanel.add(new JLabel("Start X Position:"));
                inputPanel.add(xField);
                inputPanel.add(new JLabel("Start Y Position:"));
                inputPanel.add(yField);

                inputPanel.add(new JLabel("End X Position:"));
                inputPanel.add(endXField);
                inputPanel.add(new JLabel("End Y Position:"));
                inputPanel.add(endYField);

                int result = JOptionPane.showConfirmDialog(this, inputPanel, "Enter Line Segment Properties", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION && Validations.isValidDouble(xField.getText()) && Validations.isValidDouble(yField.getText()) && Validations.isValidDouble(endXField.getText()) && Validations.isValidDouble(endYField.getText())) {
                    position = new Point(Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText()));
                    properties.put("EndX", Double.parseDouble(endXField.getText()));
                    properties.put("EndY", Double.parseDouble(endYField.getText()));
                    shape = new LineSegment();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
                }
            }
        }


        if (shape == null) {
            return;
        }


        shape.setName(shapeName);
        shape.setPosition(position);
        shape.setProperties(properties);
        shape.setColor(Color.WHITE);
        shape.setFillColor(new Color(0, 0, 0, 0));

        drawingEngine.addShape(shape);
        shapeSelector.addItem(shapeName);
        canvasPanel.repaint();
    }

}
