import java.io.*;

public class DataBase {
    private String fileName;
    private DrawingEngineGUI drawingEngineGUI;
    DataBase(DrawingEngineGUI drawingEngineGUI) {
        this.drawingEngineGUI = drawingEngineGUI;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileName() {
        return fileName;
    }

    public void save() throws IOException {
        File file = new File(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        for(Shape shape : drawingEngineGUI.getShapes()) {
            objectOutputStream.writeObject(shape);
        }
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public void load() {
        try {
            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Shape shape = (Shape) objectInputStream.readObject();
            while ( shape != null) {
                drawingEngineGUI.addShape(shape);
                shape = (Shape) objectInputStream.readObject();
            }
        } catch (EOFException e) {
            System.out.println("End of file");
        } catch (Exception e) {
            e.printStackTrace();

    }
}
}
