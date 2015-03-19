package org.alex.stundenplan.backend.allURL;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by alexandru on 2/5/15.
 */
@Entity
public class AllLinksData {
    //a class to store all links
    @Id
    String id;
    String xml;
    String fach;
    Integer sem;
    Integer group;
    String url;
    Integer year;//
    String season;// ss = sommer(from 1 march till 31 August); ws - winter (from 1 September till 1 day befor 1 March)


    // mainlink: where all ather links to faecher are stored, ex:
    //--------------------sommer 2014---------------------------------------
    //http://freya.fh-brandenburg.de/Stundenplan/ss14/liste-zu.html
    //--------------------winter 2014-2015
    //http://freya.fh-brandenburg.de/Stundenplan/ws14/liste-zu.html
    String mainlink;

    public  String setID(){
        if (this.fach!=null && this.sem!=null && this.group!=null) {
            this.id = this.fach + "_" + this.sem + "_" + this.group;
        }
        return this.id;
    }

    public String setID(String fach, Integer sem, Integer group) {
        this.id =  fach+"_"+sem+"_"+group;
        return this.id;
    }





    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getFach() {
        return fach;
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

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getMainlink() {
        return mainlink;
    }

    public void setMainlink(String mainlink) {
        this.mainlink = mainlink;
    }
}

