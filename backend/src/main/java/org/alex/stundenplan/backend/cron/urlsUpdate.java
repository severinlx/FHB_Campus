package org.alex.stundenplan.backend.cron;

/**
 * Created by alexandru on 2/5/15.
 */
public class urlsUpdate {

    //loop the website with url List and make UrlEntitys in Database
    // do it from 20 feb till 15 March daily, and from 20 august till 15 sept Daily:
    public void urlsUpdate(){
        String url="";
        String mail="severinlx@yahoo.com";
        String season = "ws1415";

        //look wich next Semester have to begin:
        season = getSeason();

        //get mainURL for this Semester:
        url = getMainUrl(season);

        //check if works sent me a mail with this Url:
        checkifWork(url, mail);

        //loop the main Url to obtain all Urls an save them in Database:
        saveAllLinks(url);

    }


    private String getSeason() {
        //todo get season
        //if feb-juli ss, else ws.  year= if(ss) = this.year, else this.year+next.year( last 2 numbers to take for year)
        String season;
        return null;
    }
    private String getMainUrl(String season){
        String url="http://freya.fh-brandenburg.de/Stundenplan/"+season+"/liste-zu.html";
        return url;
    }
    public boolean checkifWork(String url, String mail){
        //todo check if work and mail it to me
        return true;
    }

    public boolean saveAllLinks(String mainURL){
        //parse this URL and make allURL Entity in the Database

        //todo return true if success
        return true;
    }
}
