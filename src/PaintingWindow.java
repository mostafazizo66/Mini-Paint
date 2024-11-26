import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class PaintingWindow extends JFrame {
    protected DrawingEngineGUI drawingEngine;
    protected JPanel canvasPanel;
    protected JComboBox<String> shapeSelector;
    protected JButton colorizeButton;
    protected JButton deleteButton;
    protected JButton moveButton;
    JPanel mainPanel = new JPanel();

    public PaintingWindow() {
        setTitle("Vector Drawing Application");
        setSize(1540, 820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawingEngine = new DrawingEngineGUI();
        mainPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));

        setLayout(new BorderLayout());
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);


        JPanel shapeButtonPanel = new JPanel();
        shapeButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton circleButton = new JButton("Circle");
        JButton lineSegmentButton = new JButton("LineSegment");
        JButton squareButton = new JButton("Square");
        JButton rectangleButton = new JButton("Rectangle");
        JButton backButton = new JButton("Back");


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
                dispose();
            }
        });


        circleButton.addActionListener(e -> createShape("Circle"));
        lineSegmentButton.addActionListener(e -> createShape("LineSegment"));
        squareButton.addActionListener(e -> createShape("Square"));
        rectangleButton.addActionListener(e -> createShape("Rectangle"));

        shapeButtonPanel.add(circleButton);
        shapeButtonPanel.add(lineSegmentButton);
        shapeButtonPanel.add(squareButton);
        shapeButtonPanel.add(rectangleButton);
        shapeButtonPanel.add(backButton);

        mainPanel.add(shapeButtonPanel, BorderLayout.NORTH);


        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        controlPanel.setPreferredSize(new Dimension(200, 400));


        JLabel selectShapeLabel = new JLabel("Select Shape");
        selectShapeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectShapeLabel.setPreferredSize(new Dimension(200, 50));
        controlPanel.add(selectShapeLabel);


        shapeSelector = new JComboBox<>();
        shapeSelector.setAlignmentX(Component.CENTER_ALIGNMENT);
        shapeSelector.setMaximumSize(new Dimension(200, 50));
        controlPanel.add(shapeSelector);


        colorizeButton = new JButton("Colorize");
        colorizeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        colorizeButton.addActionListener(new ActionListener() {
            final String[] options = {"FrameColor", "FillColor"};

            @Override
            public void actionPerformed(ActionEvent e) {
                int Value = JOptionPane.showOptionDialog(null,
                        "Choose the colorize type",
                        "Colorize",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon(""),
                        options,
                        0);
                if (Value == 0) {
                    colorizeOuterShape();
                } else if (Value == 1) {
                    colorizeInnerShape();
                }
            }
        });
        controlPanel.add(colorizeButton);


        deleteButton = new JButton("Delete");
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.addActionListener(e -> deleteShape());
        controlPanel.add(deleteButton);

        moveButton = new JButton("Move");
        moveButton.addActionListener(e -> moveShape());

        moveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(moveButton, BorderLayout.SOUTH);

        mainPanel.add(controlPanel, BorderLayout.WEST);


    }

    private void moveShape() {
        if (drawingEngine.getShapes().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No shapes to move", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String selectedShapeName = (String) shapeSelector.getSelectedItem();
        if (selectedShapeName != null) {
            Shape shape = drawingEngine.getShapeByName(selectedShapeName);
            if (shape != null) {
                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new GridLayout(0, 2, 10, 10));
                JTextField xField = new JTextField(5);
                JTextField yField = new JTextField(5);
                inputPanel.add(new JLabel("X Position:"));
                inputPanel.add(xField);
                inputPanel.add(new JLabel("Y Position:"));
                inputPanel.add(yField);
                int result = JOptionPane.showConfirmDialog(this, inputPanel, "Enter new position", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION && Validations.isValidDouble(xField.getText()) && Validations.isValidDouble(yField.getText())) {
                    int newX = Integer.parseInt(xField.getText());
                    int newY = Integer.parseInt(yField.getText());
                    shape.move(newX, newY);
                    canvasPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
                }
            }
        }
    }


    private void colorizeInnerShape() {
        if (drawingEngine.getShapes().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No shapes", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String selectedShapeName = (String) shapeSelector.getSelectedItem();
        if (selectedShapeName != null) {
            Shape shape = drawingEngine.getShapeByName(selectedShapeName);
            if (shape != null) {
                Color newColor = JColorChooser.showDialog(this, "Choose a color", shape.getColor());
                if (newColor != null) {

                    shape.setFillColor(newColor);
                    canvasPanel.repaint();
                }
            }
        }
    }

    private void colorizeOuterShape() {
        if (drawingEngine.getShapes().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No shapes", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String selectedShapeName = (String) shapeSelector.getSelectedItem();
        if (selectedShapeName != null) {
            Shape shape = drawingEngine.getShapeByName(selectedShapeName);
            if (shape != null) {
                Color newColor = JColorChooser.showDialog(this, "Choose a color", shape.getColor());
                if (newColor != null) {
                    shape.setColor(newColor);
                    canvasPanel.repaint();
                }
            }
        }
    }


    private void deleteShape() {
        if (drawingEngine.getShapes().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No shapes to delete", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
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




    public void resize()
    {

    }





    protected abstract void createShape(String shapeType);

}
