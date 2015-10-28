package Server;

import by.andrew.scrabl.control.GameControl;
import java.util.ArrayList;
import java.util.Collection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.fxml.*;

public class Main extends Application {

    public static void main(String[] args) {
        Main mai = new Main();
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("fxml_example.fxml"));

        Scene scene = new Scene(root, 300, 275);
        stage.setTitle("Server @ FXML Welcome");
        stage.setScene(scene);
		// FXMLExampleController controller = loader.getController();
        // controller.setMainApp(this);

        stage.show();

    }
}
