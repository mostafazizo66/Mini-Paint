import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MiniPaint extends JFrame {
    private DrawingEngineGUI drawingEngine;
    private JPanel canvasPanel;
    private JComboBox<String> shapeSelector;
    private JButton colorizeButton;
    private JButton deleteButton;

    public MiniPaint() {
        setTitle("Vector Drawing Application");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawingEngine = new DrawingEngineGUI();

        // Set up main layout
        setLayout(new BorderLayout());

        // Top panel for shape buttons
        JPanel shapeButtonPanel = new JPanel();
        shapeButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton circleButton = new JButton("Circle");
        JButton lineSegmentButton = new JButton("LineSegment");
        JButton squareButton = new JButton("Square");
        JButton rectangleButton = new JButton("Rectangle");

        circleButton.addActionListener(e -> createShape("Circle"));
        lineSegmentButton.addActionListener(e -> createShape("LineSegment"));
        squareButton.addActionListener(e -> createShape("Square"));
        rectangleButton.addActionListener(e -> createShape("Rectangle"));

        shapeButtonPanel.add(circleButton);
        shapeButtonPanel.add(lineSegmentButton);
        shapeButtonPanel.add(squareButton);
        shapeButtonPanel.add(rectangleButton);

        add(shapeButtonPanel, BorderLayout.NORTH);

        // Left control panel for shape selection and operations
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setPreferredSize(new Dimension(200, getHeight()));

        // Shape selector dropdown with label
        JLabel selectShapeLabel = new JLabel("Select Shape");
        selectShapeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(selectShapeLabel);

        shapeSelector = new JComboBox<>();
        controlPanel.add(shapeSelector);

        // Colorize button
        colorizeButton = new JButton("Colorize");
        colorizeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        colorizeButton.addActionListener(e -> colorizeShape());
        controlPanel.add(colorizeButton);

        // Delete button
        deleteButton = new JButton("Delete");
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.addActionListener(e -> deleteShape());
        controlPanel.add(deleteButton);

        add(controlPanel, BorderLayout.WEST);

        // Canvas panel in the center
        canvasPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawingEngine.refresh(g);
            }
        };
        canvasPanel.setBackground(Color.WHITE);
        add(canvasPanel, BorderLayout.CENTER);
    }

    // Create a shape and add it to the drawing engine
    private void createShape(String shapeType) {
        String shapeName = shapeType.toLowerCase() + (shapeSelector.getItemCount() + 1);

        // Show dialog for shape properties
        Map<String, Double> properties = new HashMap<>();
        Point position = new Point();
        Shape shape = null;


        if (shapeType.equals("Circle")) {
            String radiusStr = JOptionPane.showInputDialog(this, "Enter radius:");
            int radius = Integer.parseInt(radiusStr);
            properties.put("Radius", (double) radius);

            String xStr = JOptionPane.showInputDialog(this, "Enter X position:");
            String yStr = JOptionPane.showInputDialog(this, "Enter Y position:");
            position = new Point(Integer.parseInt(xStr), Integer.parseInt(yStr));
            shape = new Circle();
        } else if (shapeType.equals("Rectangle")) {
            String widthStr = JOptionPane.showInputDialog(this, "Enter width:");
            String heightStr = JOptionPane.showInputDialog(this, "Enter height:");
            int width = Integer.parseInt(widthStr);
            int height = Integer.parseInt(heightStr);
            properties.put("Width", (double) width);
            properties.put("Height", (double) height);
            shape = new Rectangle();

            String xStr = JOptionPane.showInputDialog(this, "Enter X position:");
            String yStr = JOptionPane.showInputDialog(this, "Enter Y position:");
            position = new Point(Integer.parseInt(xStr), Integer.parseInt(yStr));
        } else if (shapeType.equals("Square")) {
            String sideLengthStr = JOptionPane.showInputDialog(this, "Enter side length:");
            int sideLength = Integer.parseInt(sideLengthStr);
            properties.put("SideLength", (double) sideLength);

            String xStr = JOptionPane.showInputDialog(this, "Enter X position:");
            String yStr = JOptionPane.showInputDialog(this, "Enter Y position:");
            position = new Point(Integer.parseInt(xStr), Integer.parseInt(yStr));
            shape = new Square();
        } else if (shapeType.equals("LineSegment")) {
            String endXStr = JOptionPane.showInputDialog(this, "Enter end X:");
            String endYStr = JOptionPane.showInputDialog(this, "Enter end Y:");
            properties.put("EndX", Double.parseDouble(endXStr));
            properties.put("EndY", Double.parseDouble(endYStr));

            String xStr = JOptionPane.showInputDialog(this, "Enter start X position:");
            String yStr = JOptionPane.showInputDialog(this, "Enter start Y position:");
            position = new Point(Integer.parseInt(xStr), Integer.parseInt(yStr));
            shape = new LineSegment();
        }


        if(shape == null) {
            return;
        }
        shape.setName(shapeName);
        shape.setPosition(position);
        shape.setProperties(properties);
        shape.setColor(Color.BLACK);
        shape.setFillColor(Color.WHITE);

        drawingEngine.addShape(shape);
        shapeSelector.addItem(shapeName);

        canvasPanel.repaint();
    }

    // Colorize the selected shape
    private void colorizeShape() {
        String selectedShapeName = (String) shapeSelector.getSelectedItem();
        if (selectedShapeName != null) {
            Shape shape = drawingEngine.getShapeByName(selectedShapeName);
            if (shape != null) {
                Color newColor = JColorChooser.showDialog(this, "Choose a color", shape.getColor());
                if (newColor != null) {
                    shape.setColor(newColor);
                    shape.setFillColor(newColor);
                    canvasPanel.repaint();
                }
            }
        }
    }

    // Delete the selected shape
    private void deleteShape() {
        String selectedShapeName = (String) shapeSelector.getSelectedItem();
        if (selectedShapeName != null) {
            Shape shape = drawingEngine.getShapeByName(selectedShapeName);
            if (shape != null) {
                drawingEngine.removeShape(shape);
                shapeSelector.removeItem(selectedShapeName);
                canvasPanel.repaint();
            }
        }
    }


}
