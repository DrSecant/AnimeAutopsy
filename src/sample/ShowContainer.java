package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;

/**
 * Created by Flameborg the Bold on 9/20/2017.
 */
public class ShowContainer extends ListCell<Show> {
    @FXML
    private Label titleLabel;
    @FXML
    private Button viewButton;
    @FXML
    private Button favoriteButton;
    @FXML
    private Button trashButton;
    @FXML
    private Circle label;
    @FXML
    private AnchorPane anchor;
    @FXML
    private ImageView faveImageC;
    @FXML
    private ImageView trashImageC;
    @FXML
    private ImageView faveImageUC;
    @FXML
    private ImageView trashImageUC;

    private Show cellShow;

    @Override
    protected void updateItem(Show show, boolean empty) {
        super.updateItem(show, empty);
        FXMLLoader mLLoader = null;

        if(empty || show == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("show_control.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            titleLabel.setText(show.getTitle());
            label.setStyle("-fx-fill: " + show.getColorVal());
            viewButton.setTooltip(new Tooltip("Genre: " + show.getGenre() +
                                              "\nNotes:\n" + show.getNoteVal()));
            if(show.isFave())
            {
                faveImageC.setVisible(true);
                trashImageC.setVisible(false);
            }
            else if(show.isTrash())
            {
                trashImageC.setVisible(true);
                faveImageC.setVisible(false);
            }

            this.cellShow = show;
            setText(null);
            setGraphic(anchor);
        }

    }

    @FXML
    protected void viewInfo(ActionEvent event) throws IOException {
        ShowEditController sec = new ShowEditController(this.cellShow);
        sec.showStage();

        this.cellShow = sec.getShow();
        if(this.cellShow == null)
        {
            setGraphic(null);
        }
    }

    @FXML
    protected void trash(ActionEvent event) {
        if(cellShow.isTrash())
        {
            cellShow.setTrash(false);
            trashImageC.setVisible(false);
        }
        else
        {
            cellShow.setTrash(true);
            trashImageC.setVisible(true);
            if(cellShow.isFave()) {
                cellShow.setFave(false);
                faveImageC.setVisible(false);
            }
        }
    }

    @FXML
    protected void favorite(ActionEvent event) {
        if(cellShow.isFave())
        {
            cellShow.setFave(false);
            faveImageC.setVisible(false);
        }
        else
        {
            cellShow.setFave(true);
            faveImageC.setVisible(true);
            if(cellShow.isTrash()) {
                cellShow.setTrash(false);
                trashImageC.setVisible(false);
            }
        }
    }
}
