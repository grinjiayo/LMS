import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;

public class index extends Application {

    @Override
    public void start(Stage Stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("stages/login/login_view.fxml"));

            Stage.setTitle("LMS!");
            Image icon = new Image(getClass().getResource("icons/icon_LMS.png").toExternalForm());
            Stage.getIcons().add(icon);

            Stage.setScene(new Scene(root));
            Stage.initStyle(StageStyle.TRANSPARENT);
            Stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
