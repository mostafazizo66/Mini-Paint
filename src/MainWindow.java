import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    JButton LightModeButton = new JButton("Light Mode");
    JButton DarkModeButton = new JButton("Dark Mode");
    JPanel mainPanel = new JPanel();
    DarkPaintingWindow darkPaintingWindow = new DarkPaintingWindow();
    LightPaintingWindow lightPaintingWindow = new LightPaintingWindow();
    private final ImageIcon backgroundImage = new ImageIcon("src/g1.jpg");

    public MainWindow() {
        setTitle("Drawing Application");
        setSize(1540, 820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        LightModeButton.setBounds(100, 100, 200, 50);
        DarkModeButton.setBounds(100, 200, 200, 50);
        mainPanel.add(LightModeButton);
        mainPanel.add(DarkModeButton);
        add(mainPanel);
        setUp();
        setVisible(true);
    }

    private void setUp() {
        LightModeButton.addActionListener(e -> {
            LightPaintingWindow lightPaintingWindow = new LightPaintingWindow();
            lightPaintingWindow.setVisible(true);
            dispose();
        });
        DarkModeButton.addActionListener(e -> {
            DarkPaintingWindow darkPaintingWindow = new DarkPaintingWindow();
            darkPaintingWindow.setVisible(true);
            dispose();
        });
    }


}
