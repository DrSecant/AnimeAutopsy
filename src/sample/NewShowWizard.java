package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Flameborg the Bold on 8/31/2017.
 */
public class NewShowWizard extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("show.fxml"));
        primaryStage.setTitle("Add Show Review");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
