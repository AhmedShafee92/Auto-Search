import controller.MainController;
import model.MainModel;
import view.MainView;

public class MainApp {
    public static void main(String[] args) {
        MainModel model = new MainModel();
        MainView view = new MainView();
        new MainController(model, view);
    }
}
