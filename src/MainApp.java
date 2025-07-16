import controller.AuthController;
import controller.MainController;
import controller.MainFrame;

public class MainApp {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        new AuthController(mainFrame);
        new MainController(mainFrame.getMainView());  // ✅ Must be initialized
    }
}
