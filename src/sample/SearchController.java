package sample;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.RangeSlider;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.utils.Settings.COLOR_LIST;
import static sample.utils.Settings.GENRE_LIST;

/**
 * Created by Flameborg the Bold on 9/7/2017.
 */
public class SearchController implements Initializable {

    @FXML
    private ComboBox genreType;
    @FXML
    private Button acceptButton;
    @FXML
    private Button cancelButton;
    @FXML
    private ComboBox colorType;
    @FXML
    private RangeSlider rating;
    @FXML
    private Label ratingLow;
    @FXML
    private Label ratingHigh;
    @FXML
    private RangeSlider plot;
    @FXML
    private Label plotLow;
    @FXML
    private Label plotHigh;
    @FXML
    private RangeSlider art;
    @FXML
    private Label artLow;
    @FXML
    private Label artHigh;
    @FXML
    private RangeSlider characters;
    @FXML
    private Label charLow;
    @FXML
    private Label charHigh;
    @FXML
    private CheckBox dubCheck;

    private Parent root;
    private SearchConstraints constraints;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genreType.getItems().removeAll(genreType.getItems());
        genreType.getItems().add("All");
        genreType.getItems().addAll(GENRE_LIST);
        genreType.getSelectionModel().select("All");

        colorType.getItems().removeAll(colorType.getItems());
        colorType.getItems().add("All");
        colorType.getItems().addAll(COLOR_LIST);
        colorType.getSelectionModel().select("All");

        rating.setLowValue(rating.getMin());
        rating.setHighValue(rating.getMax());
        plot.setLowValue(plot.getMin());
        plot.setHighValue(plot.getMax());
        art.setLowValue(art.getMin());
        art.setHighValue(art.getMax());
        characters.setLowValue(characters.getMin());
        characters.setHighValue(characters.getMax());

        ratingLow.textProperty().bind(
                Bindings.format(
                        "%.1f",
                        rating.lowValueProperty()
                )
        );
        ratingHigh.textProperty().bind(
                Bindings.format(
                        "%.1f",
                        rating.highValueProperty()
                )
        );

        plotLow.textProperty().bind(
                Bindings.format(
                        "%.1f",
                        plot.lowValueProperty()
                )
        );
        plotHigh.textProperty().bind(
                Bindings.format(
                        "%.1f",
                        plot.highValueProperty()
                )
        );

        artLow.textProperty().bind(
                Bindings.format(
                        "%.1f",
                        art.lowValueProperty()
                )
        );
        artHigh.textProperty().bind(
                Bindings.format(
                        "%.1f",
                        art.highValueProperty()
                )
        );

        charLow.textProperty().bind(
                Bindings.format(
                        "%.1f",
                        characters.lowValueProperty()
                )
        );
        charHigh.textProperty().bind(
                Bindings.format(
                        "%.1f",
                        characters.highValueProperty()
                )
        );
    }

    public SearchController() throws IOException {
        this.constraints = new SearchConstraints();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("search.fxml")
        );
        loader.setController(this);
        this.root = (Parent) loader.load();
    }

    public void setConstraints(SearchConstraints c) {
        this.constraints = c;
        genreType.getSelectionModel().select(c.getGenre());
        colorType.getSelectionModel().select(c.getColorTemp());

        if(c.getHasDub() == null)
            dubCheck.setIndeterminate(true);
        else
            dubCheck.setSelected(c.getHasDub());

        rating.setLowValue(c.getRatingLow());
        rating.setHighValue(c.getRatingHigh());
        plot.setLowValue(c.getPlotLow());
        plot.setHighValue(c.getPlotHigh());
        art.setLowValue(c.getArtLow());
        art.setHighValue(c.getArtHigh());
        characters.setLowValue(c.getCharLow());
        characters.setHighValue(c.getCharHigh());
    }

    public void showStage() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setTitle("Advanced Search Options");
        stage.setScene(new Scene(root, 600, 400));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    public SearchConstraints getConstraints()
    {
        return this.constraints;
    }

    @FXML
    private void accept(ActionEvent event)
    {
        this.constraints.setGenre((String)genreType.getSelectionModel().getSelectedItem());
        this.constraints.setColorTemp((String)colorType.getSelectionModel().getSelectedItem());
        this.constraints.setRatingLow(rating.getLowValue());
        this.constraints.setRatingHigh(rating.getHighValue());
        this.constraints.setPlotLow(plot.getLowValue());
        this.constraints.setPlotHigh(plot.getHighValue());
        this.constraints.setArtLow(art.getLowValue());
        this.constraints.setArtHigh(art.getHighValue());
        this.constraints.setCharLow(characters.getLowValue());
        this.constraints.setCharHigh(characters.getHighValue());
        if(dubCheck.isIndeterminate())
            this.constraints.setHasDub(null);
        else
            this.constraints.setHasDub(dubCheck.isSelected());

        Stage stage = (Stage) acceptButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void reset(ActionEvent event)
    {
        this.constraints.makeDefault();
        genreType.getSelectionModel().select("All");
        colorType.getSelectionModel().select("All");
        dubCheck.setIndeterminate(true);
        rating.setLowValue(rating.getMin());
        rating.setHighValue(rating.getMax());
        plot.setLowValue(plot.getMin());
        plot.setHighValue(plot.getMax());
        art.setLowValue(art.getMin());
        art.setHighValue(art.getMax());
        characters.setLowValue(characters.getMin());
        characters.setHighValue(characters.getMax());
    }

    @FXML
    private void cancel(ActionEvent event)
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
