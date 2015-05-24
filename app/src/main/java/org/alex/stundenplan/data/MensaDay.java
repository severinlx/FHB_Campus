package org.alex.stundenplan.data;

import java.util.Date;
import java.util.List;

/**
 * Created by bschramke on 21.04.15.
 */
public class MensaDay {

    private String date;
    private List<String> meals;

    public String getDateString() {
        return dateString;
    }

    private String dateString;

    public String getDate(){
        return date;
    }

    public List<String> getMeals(){
        return meals;
    }
}
