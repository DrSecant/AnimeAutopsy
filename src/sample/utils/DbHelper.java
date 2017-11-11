package sample.utils;

import javafx.collections.ObservableList;
import sample.Show;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;

/**
 * Created by Flameborg the Bold on 9/19/2017.
 */
public class DbHelper {
    public static void writeToDB(String title, boolean hasDub, boolean isFave, boolean isTrash,
                                 double rating, double plot, double art, double characters,
                                 String notes, String color, String colorTemp, String genre)
    {
        Connection con = null;
        Statement stmt = null;
        String query = "INSERT INTO shows_tbl VALUES (LOCALTIMESTAMP, '" + title +
                       "', " + hasDub + ", " + isFave + ", " + isTrash + ", " + rating +
                       ", " + plot + ", " + art + ", " + characters + ", '" + notes +
                       "', '" + color + "', '" + colorTemp + "', '" + genre + "')";
        System.out.println(query);
        int result = 0;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection( "jdbc:hsqldb:file:rsc/hsqldb/aadb", "SA", "");
            stmt = con.createStatement();
            result = stmt.executeUpdate(query);
            con.commit();
        }catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println(result + " rows effected");
        System.out.println("Rows inserted successfully");
    }

    public static void createTable()
    {
        Connection con = null;
        Statement stmt = null;
        int result = 0;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:file:rsc/hsqldb/aadb", "SA", "");
            stmt = con.createStatement();

            result = stmt.executeUpdate(Settings.CREATE_QUERY);
            System.out.println(result);
        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void retrieveShows(ObservableList<Show> shows, String restraints, String printOrder)
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet result = null;
        String query = Settings.SELECT_QUERY;

        query += restraints;

        switch (printOrder)
        {
            case "Most Recent":
                query += " ORDER BY id DESC;";
                break;
            case "Least Recent":
                query += ";";
                break;
            case "A to Z":
                query += " ORDER BY LOWER(title) ASC;";
                break;
            case "Z to A":
                query += " ORDER BY LOWER(title) DESC;";
                break;
        }

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:file:rsc/hsqldb/aadb", "SA", "");
            stmt = con.createStatement();
            result = stmt.executeQuery(query);

            while(result.next()){
                Show show = new Show(result.getTimestamp("id"),
                        result.getString("title"),
                        result.getBoolean("has_dub"),
                        result.getBoolean("is_fave"),
                        result.getBoolean("is_trash"),
                        result.getDouble("rating"),
                        result.getDouble("plot"),
                        result.getDouble("art"),
                        result.getDouble("characters"),
                        result.getString("notes"),
                        result.getString("color"),
                        result.getString("color_temp"),
                        result.getString("genre"));
                String genre = show.getGenre();
                if(!Settings.GENRE_LIST.contains(genre))
                {
                    Settings.GENRE_LIST.add(genre);
                    Collections.sort(Settings.GENRE_LIST);
                }
                shows.add(show);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void pushChanges(ObservableList<Show> shows)
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet result = null;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:file:rsc/hsqldb/aadb", "SA", "");
            stmt = con.createStatement();

            for (Show show : shows) {
                if (show.getId() != null) // Show has not been deleted
                {
                    String query = Settings.createUpdateQuery(show);
                    result = stmt.executeQuery(query);
                    System.out.println(result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void removeShow(Show show)
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet result = null;
        String query = "DELETE from shows_tbl WHERE id = TIMESTAMP '"
                     + show.getId().toString() + "'";

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:file:rsc/hsqldb/aadb", "SA", "");
            stmt = con.createStatement();
            result = stmt.executeQuery(query);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
