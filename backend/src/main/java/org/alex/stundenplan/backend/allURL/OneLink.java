package org.alex.stundenplan.backend.allURL;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class OneLink {

    @Id
    String fach;
    String season;
    Integer sem;
    String group;
    String url;

    public OneLink(){

    }

    public OneLink (String fach, Integer sem, String group, String url) {
        super();
        this.fach = fach;
        this.sem = sem;
        this.group = group;
        this.url = url;
    }
    public String getFach() {
        return fach;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }


    public void setFach(String fach) {
        this.fach = fach;
    }

    public Integer getSem() {
        return sem;
    }

    public void setSem(Integer sem) {
        this.sem = sem;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


	
}
