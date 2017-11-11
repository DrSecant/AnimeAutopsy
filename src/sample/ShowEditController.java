package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.utils.ColorHelper;
import sample.utils.DbHelper;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Flameborg the Bold on 9/25/2017.
 */
public class ShowEditController extends ShowController implements Initializable {
    private Show show;
    private Parent root;
    private boolean editable = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }

    public ShowEditController()
    {
        super();
        this.show = null;
    }

    public ShowEditController(Show show) throws IOException {
        super();
        this.show = show;

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("show_edit.fxml")
        );
        loader.setController(this);
        this.root = (Parent) loader.load();

        super.name.setText(this.show.getTitle());
        super.genrePicker.getSelectionModel().select(this.show.getGenre());
        super.notes.setText(this.show.getNoteVal());
        super.color.setValue(Color.web(this.show.getColorVal()));
        super.rating.setValue(this.show.getRateVal());
        super.art.setValue(this.show.getArtVal());
        super.plot.setValue(this.show.getPlotVal());
        super.characters.setValue(this.show.getCharVal());
        super.dubCheck.setSelected(this.show.isDub());
    }

    public void showStage() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setTitle("View/Edit Show Review");
        stage.setScene(new Scene(root, 600, 400));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    public Show getShow()
    {
        return this.show;
    }

    @FXML
    private void makeEditable(ActionEvent event)
    {
        if(!editable)
        {
            super.name.setDisable(false);
            super.genrePicker.setDisable(false);
            super.notes.setEditable(true);
            super.color.setDisable(false);
            super.rating.setDisable(false);
            super.art.setDisable(false);
            super.plot.setDisable(false);
            super.characters.setDisable(false);
            super.dubCheck.setDisable(false);
            editable = true;
        }
    }

    @FXML
    private void delete(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setContentText("Continuing this will delete this entry.\nAre you alright with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            DbHelper.removeShow(this.show);
            this.show = null;
            Stage stage = (Stage) super.addButton.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    protected void addShow(ActionEvent event)
    {
        //Confirm changes
        this.show.setTitle(super.name.getText());

        String genre = super.genrePicker.getValue().toString();
        if(genre.equals("Other"))
        {
            this.show.setGenre(super.otherGenre.getText());
        }
        else
        {
            this.show.setGenre(genre);
        }

        this.show.setNoteVal(super.notes.getText());

        Color colorObj = super.color.getValue();
        String colorVal = ColorHelper.toHexCode(colorObj);
        double hue = colorObj.getHue();
        double sat = colorObj.getSaturation();
        double brightness = colorObj.getBrightness();
        String colorTemp = ColorHelper.colorTemp(hue, sat, brightness);
        this.show.setColorVal(colorVal);
        this.show.setColorTemp(colorTemp);

        this.show.setRateVal(super.rating.getValue());
        this.show.setArtVal(super.art.getValue());
        this.show.setPlotVal(super.plot.getValue());
        this.show.setCharVal(super.characters.getValue());
        this.show.setDub(super.dubCheck.isSelected());

        Stage stage = (Stage) super.addButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel(ActionEvent event)
    {
        Stage stage = (Stage) addButton.getScene().getWindow();
        if(this.editable)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Cancellation");
            alert.setHeaderText(null);
            alert.setContentText("Any changes made to this show will be lost.\nAre you alright with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                stage.close();
            }
        }
        else
        {
            stage.close();
        }
    }
}
