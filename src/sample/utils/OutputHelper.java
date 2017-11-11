package sample.utils;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.Show;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

/**
 * Created by Flameborg the Bold on 10/10/2017.
 */
public class OutputHelper {
    public static void printResults(ObservableList<Show> shows, Window owner) throws UnsupportedEncodingException {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select a save location");
        File defaultDirectory = new File(System.getProperty("user.home"));
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(new Stage());

        Optional<String> result = null;
        if(selectedDirectory != null) {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Result File Name");
            dialog.setHeaderText(null);
            dialog.setContentText("Please enter a name for the result file:");

            result = dialog.showAndWait();
        }

        if (result != null && result.isPresent()){
            String dir = selectedDirectory.getPath();
            String filename = result.get() + ".txt";
            String path = dir + "/" + filename;

            PrintWriter writer = null;
            try {
                writer = new PrintWriter(path, "UTF-8");
            } catch (FileNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Directory Not Found");
                alert.setHeaderText(null);
                alert.setContentText("Possible problem with chosen directory.");
                alert.showAndWait();
            }

            if(writer != null) {
                for (Show s : shows) {
                    writer.print(s + "\r\n");
                    writer.println("");
                }
                writer.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("File Creation Successful!");
                alert.setHeaderText(null);
                alert.setContentText("File \"" + filename + "\" created in \n" + dir);
                alert.showAndWait();
            }
        }
        else
        {
            String alertStr = "";
            if(result == null)
            {
                alertStr = "Problem with directory.";
            }
            else
                alertStr = "Problem with file name.";

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("File Creation Unsuccessful :(");
            alert.setHeaderText(null);
            alert.setContentText(alertStr);
            alert.showAndWait();
        }
    }
}
