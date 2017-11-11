package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.utils.DbHelper;
import sample.utils.OutputHelper;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ComboBox loadOrder; //Order in which to load
    @FXML
    private Button advButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button addButton;
    @FXML
    private Button saveButton;
    @FXML
    private TitledPane favorites;
    @FXML
    private TitledPane garbage;
    @FXML
    private ListView showView;
    @FXML
    private ListView faveView;
    @FXML
    private ListView trashView;

    private ObservableList<Show> showObservableList;
    private ObservableList<Show> faveObservableList;
    private ObservableList<Show> trashObservableList;
    private SearchConstraints constraints  = new SearchConstraints();
    private Stage myStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadOrder.getItems().removeAll(loadOrder.getItems());
        loadOrder.getItems().addAll("Most Recent", "Least Recent", "A to Z", "Z to A");
        loadOrder.getSelectionModel().select("Most Recent");

        showObservableList = FXCollections.observableArrayList(Show.extractor());
        faveObservableList = FXCollections.observableArrayList(Show.extractor());
        trashObservableList = FXCollections.observableArrayList(Show.extractor());
        DbHelper.retrieveShows(showObservableList, "", (String)loadOrder.getValue());
        this.lookForFave(showObservableList);

        showView.setItems(showObservableList);
        showView.setCellFactory(showView -> new ShowContainer());
        faveView.setItems(faveObservableList);
        faveView.setCellFactory(faveView -> new ShowContainer());
        trashView.setItems(trashObservableList);
        trashView.setCellFactory(trashView -> new ShowContainer());

        this.constraints.makeDefault();

        advButton.setTooltip(new Tooltip("Advanced search criteria"));
        refreshButton.setTooltip(new Tooltip("Push changes/Refresh results"));
        addButton.setTooltip(new Tooltip("Add new show"));
        saveButton.setTooltip(new Tooltip("Save results"));
    }

    public void setRoot(Stage myStage)
    {
        this.myStage = myStage;
    }

    @FXML
    private void addShow(ActionEvent event) throws Exception
    {
        NewShowWizard show = new NewShowWizard();
        show.start(new Stage());
    }

    @FXML
    private void addSearchCriteria(ActionEvent event) throws Exception
    {
        SearchController search = new SearchController();
        search.showStage();
        search.setConstraints(this.constraints);
        this.constraints = search.getConstraints();
    }

    @FXML
    public void refreshResults(ActionEvent event)
    {
        for(Show s : faveObservableList)
        {
            if(!showObservableList.contains(s))
                showObservableList.add(s);
        }
        for(Show s : trashObservableList)
        {
            if(!showObservableList.contains(s))
                showObservableList.add(s);
        }
        DbHelper.pushChanges(showObservableList);
        showObservableList.clear();
        faveObservableList.clear();
        trashObservableList.clear();

        DbHelper.retrieveShows(showObservableList, "", (String)loadOrder.getValue());
        this.lookForFave(showObservableList);
        if(!constraints.isDefault())
        {
            showObservableList.clear();
            DbHelper.retrieveShows(showObservableList, constraints.makeConstraintString(),
                    (String)loadOrder.getValue());
        }
    }

    @FXML
    private void save(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
        if(showObservableList.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Creating Save File");
            alert.setHeaderText(null);
            alert.setContentText("Cannot create file with 0 results.\n" +
                                 "Please populate with results and try again.");

            alert.showAndWait();
        }
        else
        {
            OutputHelper.printResults(showObservableList, advButton.getScene().getWindow());
        }
    }

    public void lookForFave(ObservableList<Show> shows)
    {
        for (Show show : shows) {
            if(show.isFave())
            {
                this.addFave(show);
            }
            else if(this.faveObservableList.contains(show))
            {
                this.removeFave(show);
            }

            if(show.isTrash())
            {
                this.addTrash(show);
            }
            else if(this.trashObservableList.contains(show))
            {
                this.removeTrash(show);
            }
        }
    }

    public void addFave(Show show)
    {
        faveObservableList.add(show);
        faveView.refresh();
    }

    public void removeFave(Show show)
    {
        faveObservableList.remove(show);
        faveView.refresh();
    }

    public void addTrash(Show show)
    {
        trashObservableList.add(show);
        trashView.refresh();
    }

    public void removeTrash(Show show)
    {
        trashObservableList.remove(show);
        trashView.refresh();
    }
}
