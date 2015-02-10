package org.alex.stundenplan;


/**
 * Created by alexandru on 2/3/15.
 */
public class Subject {
    String subj;
    String time;
    String room;
    String day;
    boolean firstLesson;

    public Subject() {
        super();
        this.day = "day";
        this.subj = "no  name";
        this.time = "????";
        this.room = "000";
    }

    public Subject(String day, String subj, String time, String room, boolean x) {
        super();
        this.day = day;
        this.subj = subj;
        this.time = time;
        this.room = room;
        this.firstLesson = x;

    }
    public Subject(String day, String subj, String time, String room) {
        super();
        this.day = day;
        this.subj = subj;
        this.time = time;
        this.room = room;


    }

    public String getSubj() {
        return subj;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
    }

}
