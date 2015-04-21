package org.alex.stundenplan.data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by bschramke on 21.04.15.
 */
public class Event {

    private long id;
    private String title;
    private String description;
    private String loyalty;
    private String image_url;

    @SerializedName("start_time")
    private Date start;

    @SerializedName("end_time")
    private Date end;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLoyalty() {
        return loyalty;
    }

    public String getImageUrl() {
        return image_url;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
