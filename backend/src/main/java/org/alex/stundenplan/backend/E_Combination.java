package org.alex.stundenplan.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import org.alex.stundenplan.backend.allURL.OneLink;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Created by alexandru on 16.03.15.
 */
@Entity
public class E_Combination {
    @Id
    String season;
    String mainUrl;
    String fachs;//String for the Json to store fach-sem- group links
   // ArrayList<OneLink> linksList;

   public String getFachs() {
        return fachs;
    }

    public void setFachs(String fachs) {
        this.fachs = fachs;
    }
     /*
    public ArrayList<OneLink> getLinksList() {
        return linksList;
    }

    public void setLinksList(ArrayList<OneLink> linksList) {
        this.linksList = linksList;
    }
    */

    public String getMainUrl() {
        return mainUrl;
    }

    public void setMainUrl(String mainUrl) {
        this.mainUrl = mainUrl;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
