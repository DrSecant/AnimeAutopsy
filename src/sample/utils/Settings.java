package sample.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Show;

/**
 * Created by Flameborg the Bold on 9/7/2017.
 */
public class Settings {
    public static final ObservableList<String> GENRE_LIST = FXCollections.observableArrayList();
    public static final ObservableList<String> COLOR_LIST = FXCollections.observableArrayList();
    public static final String GENRE_SELECTED_OPTION = "Action";
    public static final String COLOR_SELECTED_OPTION = "Cool";
    public static final String FAVE_C_URL = "file:///AnimeAutopsy/src/Images/web/ic_favorite_black_18dp_1x.png";
    public static final String FAVE_UC_URL = "file:///AnimeAutopsy/src/Images/web/ic_favorite_border_black_18dp_1x.png";
    public static final String TRASH_C_URL = "file:///AnimeAutopsy/src/Images/web/ic_interested_black_18dp_1x.png";
    public static final String TRASH_UC_URL = "file:///AnimeAutopsy/src/Images.web/ic_not_interested_black_18dp_1x.png";
    public static final String CREATE_QUERY = "CREATE TABLE IF NOT EXISTS shows_tbl (id TIMESTAMP NOT NULL, " +
                    "title VARCHAR(50) NOT NULL, has_dub BOOLEAN NOT NULL, is_fave BOOLEAN NOT NULL, " +
                    "is_trash BOOLEAN NOT NULL, rating DOUBLE NOT NULL, plot DOUBLE NOT NULL, " +
                    "art DOUBLE NOT NULL, characters DOUBLE NOT NULL, notes CLOB, color CHAR(7) NOT NULL, " +
                    "color_temp CHAR(7) NOT NULL, genre VARCHAR(50) NOT NULL, PRIMARY KEY (id));";
    public static final String SELECT_QUERY = "SELECT id, title, has_dub, is_fave, is_trash, " +
                    "rating, plot, art, characters, notes, color, color_temp, genre FROM shows_tbl";

    public static String createUpdateQuery(Show show)
    {
        String updateQuery = "UPDATE shows_tbl SET title = '" + show.getTitle() + "', has_dub = " +
                show.isDub() + ", is_fave = " + show.isFave() + ", is_trash = " + show.isTrash() +
                ", rating = " + show.getRateVal() + ", plot = " + show.getPlotVal() + ", art = " +
                show.getArtVal() + ", characters = " + show.getCharVal() + ", notes = '" + show.getNoteVal() +
                "', color = '" + show.getColorVal() + "', color_temp = '" + show.getColorTemp() +
                "', genre = '" + show.getGenre() + "' WHERE id = TIMESTAMP '" + show.getId().toString() +
                "'";
        return updateQuery;
    }
}
