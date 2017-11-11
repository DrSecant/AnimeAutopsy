package sample;

/**
 * Created by Flameborg the Bold on 10/10/2017.
 */
public class SearchConstraints {
    private String genre;
    private String colorTemp;
    private double ratingLow;
    private double ratingHigh;
    private double plotLow;
    private double plotHigh;
    private double artLow;
    private double artHigh;
    private double charLow;
    private double charHigh;
    private Boolean hasDub;

    public SearchConstraints()
    {
        this.makeDefault();
    }

    public void makeDefault()
    {
        if(!this.isDefault())
        {
            this.genre = "All";
            this.colorTemp = "All";
            this.ratingLow = 0.0;
            this.ratingHigh = 10.0;
            this.plotLow = 0.0;
            this.plotHigh = 5.0;
            this.artLow = 0.0;
            this.artHigh = 5.0;
            this.charLow = 0.0;
            this.charHigh = 5.0;
            this.hasDub = null;
        }
    }

    public boolean isDefault()
    {
        if(this.genre == "All" && this.colorTemp == "All" && this.ratingLow == 0.0 &&
                this.ratingHigh == 10.0 && this.plotLow == 0.0 && this.plotHigh == 5.0 &&
                this.artLow == 0.0 && this.artHigh == 5.0 && this.charLow == 0.0 &&
                this.charHigh == 5.0 && this.hasDub == null)
        {
            return true;
        }
        else
            return false;
    }

    public String makeConstraintString()
    {
        String constraints = "";

        if(!this.isDefault())
        {
            if(!genre.equals("All"))
            {
                constraints += " genre = '" + this.genre + "'";
            }
            if(!colorTemp.equals("All"))
            {
                if(!constraints.equals(""))
                    constraints += " AND";

                constraints += " color_temp = '" + this.colorTemp + "'";
            }
            if(!(this.ratingLow == 0.0 && this.ratingHigh == 10.0))
            {
                if(!constraints.equals(""))
                    constraints += " AND";

                constraints += " rating >= " + this.ratingLow
                         + " AND rating <= " + this.ratingHigh;
            }
            if(!(this.plotLow == 0.0 && this.plotHigh == 5.0))
            {
                if(!constraints.equals(""))
                    constraints += " AND";

                constraints += " plot >= " + this.plotLow
                         + " AND plot <= " + this.plotHigh;
            }
            if(!(this.artLow == 0.0 && this.artHigh == 5.0))
            {
                if(!constraints.equals(""))
                    constraints += " AND";

                constraints += " art >= " + this.artLow
                         + " AND art <= " + this.artHigh;
            }
            if(!(this.charLow == 0.0 && this.charHigh == 5.0))
            {
                if(!constraints.equals(""))
                    constraints += " AND";

                constraints += " characters >= " + this.charLow
                         + " AND characters <= " + this.charHigh;
            }
            if(this.hasDub != null)
            {
                if(!constraints.equals(""))
                    constraints += " AND";

                constraints += " has_dub = " + this.hasDub;
            }
        }

        if(!constraints.equals(""))
        {
            constraints = " WHERE" + constraints;
        }
        return constraints;
    }

    //Getters and Setters
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getColorTemp() {
        return colorTemp;
    }

    public void setColorTemp(String colorTemp) {
        this.colorTemp = colorTemp;
    }

    public double getRatingLow() {
        return ratingLow;
    }

    public void setRatingLow(double ratingLow) {
        this.ratingLow = ratingLow;
    }

    public double getRatingHigh() {
        return ratingHigh;
    }

    public void setRatingHigh(double ratingHigh) {
        this.ratingHigh = ratingHigh;
    }

    public double getPlotLow() {
        return plotLow;
    }

    public void setPlotLow(double plotLow) {
        this.plotLow = plotLow;
    }

    public double getPlotHigh() {
        return plotHigh;
    }

    public void setPlotHigh(double plotHigh) {
        this.plotHigh = plotHigh;
    }

    public double getArtLow() {
        return artLow;
    }

    public void setArtLow(double artLow) {
        this.artLow = artLow;
    }

    public double getArtHigh() {
        return artHigh;
    }

    public void setArtHigh(double artHigh) {
        this.artHigh = artHigh;
    }

    public double getCharLow() {
        return charLow;
    }

    public void setCharLow(double charLow) {
        this.charLow = charLow;
    }

    public double getCharHigh() {
        return charHigh;
    }

    public void setCharHigh(double charHigh) {
        this.charHigh = charHigh;
    }

    public Boolean getHasDub() {
        return hasDub;
    }

    public void setHasDub(Boolean hasDub) {
        this.hasDub = hasDub;
    }
}
