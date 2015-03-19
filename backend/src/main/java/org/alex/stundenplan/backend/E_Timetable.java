package org.alex.stundenplan.backend;

/**
 * Created by alexandru on 2/3/15.
 */

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;


class E_Subjectlist{
    protected ArrayList<E_Subject> E_SubjectArrayList = new ArrayList<>();

    E_Subjectlist(ArrayList<E_Subject> E_SubjectArrayList) {
        this.E_SubjectArrayList = E_SubjectArrayList;
    }

    public ArrayList<E_Subject> getE_SubjectArrayList() {
        return E_SubjectArrayList;
    }

    public void setE_SubjectArrayList(ArrayList<E_Subject> E_SubjectArrayList) {
        this.E_SubjectArrayList = E_SubjectArrayList;
    }
};
@Entity
class E_Timetable {
    @Id
    String url;
    String xml;
    String fach;
    Integer semester;
    String group;
    String season;
    ArrayList<E_Subject> E_SubjectList = new ArrayList<E_Subject>();


    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }


    public ArrayList<E_Subject> getE_SubjectList() {
        return E_SubjectList;
    }

    public void setE_SubjectList(ArrayList<E_Subject> E_SubjectList) {
        this.E_SubjectList = E_SubjectList;
    }





    public E_Timetable() {

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

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
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
