import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TestApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent parent =  FXMLLoader.load(getClass().getResource("/view/reportForm.fxml"));
        stage.setScene(new Scene(parent));
        stage.setTitle("ReportForm");
        stage.centerOnScreen();

        stage.show();
    }
}
