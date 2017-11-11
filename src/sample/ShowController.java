package sample;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.utils.ColorHelper;
import sample.utils.DbHelper;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.utils.Settings.GENRE_LIST;

/**
 * Created by Flameborg the Bold on 8/31/2017.
 */
public class ShowController implements Initializable
{
    @FXML
    protected ComboBox genrePicker; //Order in which to load
    @FXML
    protected Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    protected TextField name;
    @FXML
    protected TextArea notes;
    @FXML
    protected ColorPicker color;
    @FXML
    protected Slider rating;
    @FXML
    private Label rateNum;
    @FXML
    protected Slider plot;
    @FXML
    private Label plotNum;
    @FXML
    protected Slider art;
    @FXML
    private Label artNum;
    @FXML
    protected Slider characters;
    @FXML
    private Label charNum;
    @FXML
    protected CheckBox dubCheck;
    @FXML
    protected TextField otherGenre;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genrePicker.getItems().removeAll(genrePicker.getItems());
        genrePicker.getItems().add("Other");
        genrePicker.getItems().addAll(GENRE_LIST);
        genrePicker.getSelectionModel().select("Action");

        rateNum.textProperty().bind(
                Bindings.format(
                        "%.1f",
                        rating.valueProperty()
                )
        );
        plotNum.textProperty().bind(
                Bindings.format(
                        "%.1f",
                        plot.valueProperty()
                )
        );
        artNum.textProperty().bind(
                Bindings.format(
                        "%.1f",
                        art.valueProperty()
                )
        );
        charNum.textProperty().bind(
                Bindings.format(
                        "%.1f",
                        characters.valueProperty()
                )
        );
    }

    @FXML
    private void changeOtherState(ActionEvent event)
    {
        if(genrePicker.getValue().equals("Other"))
        {
            otherGenre.setDisable(false);
        }
        else
        {
            otherGenre.setText("");
            otherGenre.setDisable(true);
        }
    }

    @FXML
    protected void addShow(ActionEvent event)
    {
        String title = makeLegalString(name.getText());
        String genre = "";
        if(genrePicker.getValue().equals("Other"))
        {
            genre = makeLegalString(otherGenre.getText());
        }
        else
        {
            genre = makeLegalString((String)genrePicker.getValue());
        }

        if(title.equals("") || genre.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Occurred");
            alert.setHeaderText("Title not found");
            alert.setContentText("Please be sure to provide a title before adding.");

            alert.showAndWait();
        }
        else
        {
            boolean dub = dubCheck.isSelected();
            boolean fave = false;
            boolean trash = false;
            double rateVal = Math.round(rating.getValue() * 10) / 10;
            double plotVal = Math.round(plot.getValue() * 10) / 10;
            double artVal = Math.round(art.getValue() * 10) / 10;
            double charVal = Math.round(characters.getValue() * 10) / 10;
            String noteVal = makeLegalString(notes.getText());
            Color colorObj = color.getValue();
            String colorVal = ColorHelper.toHexCode(colorObj);
            double hue = colorObj.getHue();
            double sat = colorObj.getSaturation();
            double brightness = colorObj.getBrightness();
            String colorTemp = ColorHelper.colorTemp(hue, sat, brightness);

            DbHelper.writeToDB(title, dub, fave, trash, rateVal, plotVal,
                               artVal, charVal, noteVal, colorVal, colorTemp, genre);
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void cancel(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancellation");
        alert.setHeaderText(null);
        alert.setContentText("Canceling now will prevent this show from being added.\nAre you alright with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }
    }

    private String makeLegalString(String old)
    {
        return old.replace("'", "^");
    }
}
