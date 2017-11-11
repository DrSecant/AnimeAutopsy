package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.utils.DbHelper;

import java.util.Collections;
import java.util.Optional;

import static sample.utils.Settings.COLOR_LIST;
import static sample.utils.Settings.GENRE_LIST;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller con = loader.getController();
        con.setRoot(primaryStage);

        primaryStage.setTitle("Anime Autopsy");
        primaryStage.setScene(new Scene(root, 800, 540));
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(540);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Exit Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Do you want to push any changes before exiting?");

                ButtonType buttonTypeYes = new ButtonType("Yes");
                ButtonType buttonTypeNo = new ButtonType("No");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes){
                    // ... user chose "Yes"
                    con.refreshResults(new ActionEvent());
                } else if (result.get() == buttonTypeNo) {
                    // ... user chose "No" thus nothing happens and the exit continues
                } else {
                    // ... user chose CANCEL or closed the dialog
                    event.consume();
                }
            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        GENRE_LIST.addAll("Action", "Dementia", "Fantasy", "Horror",
                "Martial Arts", "Mystery", "Romance", "Super Power", "Yaoi", "Adventure",
                "Game", "Mecha", "Parody", "Slice of Life", "Supernatural", "Cars", "Drama",
                "Harem", "Military", "Police", "School", "Space", "Thriller", "Comedy",
                "Ecchi", "Historical", "Magic", "Music", "Psychological", "Sci-Fi", "Sports",
                "Vampire");
        Collections.sort(GENRE_LIST);
        COLOR_LIST.addAll("Cool", "Neutral", "Warm");
        Collections.sort(COLOR_LIST);
        DbHelper.createTable();
        launch(args);
    }
}
