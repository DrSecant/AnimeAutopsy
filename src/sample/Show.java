package sample;


import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.util.Callback;

import java.sql.Timestamp;

/**
 * Created by Flameborg the Bold on 9/20/2017.
 */
public class Show {
    //Instance Variables
    private ObjectProperty<Timestamp> id;
    private StringProperty title;
    private BooleanProperty dub;
    private BooleanProperty fave;
    private BooleanProperty trash;
    private DoubleProperty rateVal;
    private DoubleProperty plotVal;
    private DoubleProperty artVal;
    private DoubleProperty charVal;
    private StringProperty noteVal;
    private StringProperty colorVal;
    private StringProperty colorTemp;
    private StringProperty genre;

    public Show(Timestamp id, String title, boolean dub, boolean fave, boolean trash,
                double rateVal, double plotVal, double artVal, double charVal,
                String noteVal, String colorVal, String colorTemp, String genre)
    {
        setId(id);
        setTitle(title);
        setDub(dub);
        setFave(fave);
        setTrash(trash);
        setRateVal(rateVal);
        setPlotVal(plotVal);
        setArtVal(artVal);
        setCharVal(charVal);
        setNoteVal(noteVal);
        setColorVal(colorVal);
        setColorTemp(colorTemp);
        setGenre(genre);
    }

    //ID property section
    public final ObjectProperty idProperty() {
        if (this.id == null) {
            this.id = new SimpleObjectProperty<>();
        }
        return this.id;
    }
    public Timestamp getId() {
        return id.get();
    }
    public void setId(Timestamp id) {
        idProperty();
        this.id.set(id);
    }

    //Title property section
    public final StringProperty titleProperty() {
        if (this.title == null) {
            this.title = new SimpleStringProperty();
        }
        return this.title;
    }
    public String getTitle() {
        return title.get();
    }
    public void setTitle(String title) {
        titleProperty();
        this.title.set(title);
    }

    //Dub property section
    public final BooleanProperty dubProperty() {
        if (this.dub == null) {
            this.dub = new SimpleBooleanProperty();
        }
        return this.dub;
    }
    public boolean isDub() {
        return dub.get();
    }
    public void setDub(boolean dub) {
        dubProperty();
        this.dub.set(dub);
    }


    //Fave property Section
    public final BooleanProperty faveProperty() {
        if (this.fave == null) {
            this.fave = new SimpleBooleanProperty();
        }
        return this.fave;
    }
    public boolean isFave() {
        return fave.get();
    }
    public void setFave(boolean fave) {
        faveProperty();
        this.fave.set(fave);
    }

    //Trash property Section
    public final BooleanProperty trashProperty() {
        if (this.trash == null) {
            this.trash = new SimpleBooleanProperty();
        }
        return this.trash;
    }
    public boolean isTrash() {
        return trash.get();
    }
    public void setTrash(boolean trash) {
        trashProperty();
        this.trash.set(trash);
    }

    //Rate Value Section
    public final DoubleProperty rateValProperty() {
        if (this.rateVal == null) {
            this.rateVal = new SimpleDoubleProperty();
        }
        return this.rateVal;
    }
    public double getRateVal() {
        return rateVal.get();
    }
    public void setRateVal(double rateVal) {
        rateValProperty();
        this.rateVal.set(rateVal);
    }

    //Plot Value Section
    public final DoubleProperty plotValProperty() {
        if (this.plotVal == null) {
            this.plotVal = new SimpleDoubleProperty();
        }
        return this.plotVal;
    }
    public double getPlotVal() {
        return plotVal.get();
    }
    public void setPlotVal(double plotVal) {
        plotValProperty();
        this.plotVal.set(plotVal);
    }

    //Art Value Section
    public final DoubleProperty artValProperty() {
        if (this.artVal == null) {
            this.artVal = new SimpleDoubleProperty();
        }
        return this.artVal;
    }
    public double getArtVal() {
        return artVal.get();
    }
    public void setArtVal(double artVal) {
        artValProperty();
        this.artVal.set(artVal);
    }

    //Character Value Section
    public final DoubleProperty charValProperty() {
        if (this.charVal == null) {
            this.charVal = new SimpleDoubleProperty();
        }
        return this.charVal;
    }
    public double getCharVal() {
        return charVal.get();
    }
    public void setCharVal(double charVal) {
        charValProperty();
        this.charVal.set(charVal);
    }

    //Note Property Section
    public final StringProperty noteValProperty() {
        if (this.noteVal == null) {
            this.noteVal = new SimpleStringProperty();
        }
        return this.noteVal;
    }
    public String getNoteVal() {
        return noteVal.get();
    }
    public void setNoteVal(String noteVal) {
        noteValProperty();
        this.noteVal.set(noteVal);
    }

    //Color Value Section
    public final StringProperty colorValProperty() {
        if (this.colorVal == null) {
            this.colorVal = new SimpleStringProperty();
        }
        return this.colorVal;
    }
    public String getColorVal() {
        return colorVal.get();
    }
    public void setColorVal(String colorVal) {
        colorValProperty();
        this.colorVal.set(colorVal);
    }

    //Color Temperature Section
    public final StringProperty colorTempProperty() {
        if (this.colorTemp == null) {
            this.colorTemp = new SimpleStringProperty();
        }
        return this.colorTemp;
    }
    public String getColorTemp() {
        return colorTemp.get();
    }
    public void setColorTemp(String colorTemp) {
        colorTempProperty();
        this.colorTemp.set(colorTemp);
    }

    //Genre Property Section
    public final StringProperty genreProperty() {
        if (this.genre == null) {
            this.genre = new SimpleStringProperty();
        }
        return this.genre;
    }
    public String getGenre() {
        return genre.get();
    }
    public void setGenre(String genre) {
        genreProperty();
        this.genre.set(genre);
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Show))
            return false;

        return this.getId() == ((Show) o).getId();
    }

    @Override
    public String toString()
    {
        String showStr = "";
        showStr += "Show: " + this.getTitle() + "\r\nCreated: " + this.getId().toString() +
                   "\r\nColor: " + this.getColorVal() + "\r\nRating: " + this.getRateVal() +
                   "\r\nPlot: " + this.getPlotVal() + "\r\nArt: " + this.getArtVal() +
                   "\r\nCharacters: " + this.getCharVal() + "\r\nDubbed: ";

        if(this.isDub())
            showStr += "Yes";
        else
            showStr += "No";

        showStr += "\r\nNotes:\r\n" + this.getNoteVal();

        return showStr;
    }

    public static Callback<Show, Observable[]> extractor() {
        return (Show s) -> new Observable[]{s.idProperty(), s.titleProperty(),
            s.dubProperty(), s.faveProperty(), s.trashProperty(), s.rateValProperty(),
            s.plotValProperty(), s.artValProperty(), s.charValProperty(), s.noteValProperty(),
            s.colorValProperty(), s.colorTempProperty(), s.genreProperty()};
    }
}
