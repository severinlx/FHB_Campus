package org.alex.stundenplan.backend.cron;

import org.alex.stundenplan.backend.CombinationEndPoint;
import org.alex.stundenplan.backend.allURL.OneLink;
import org.alex.stundenplan.backend.allURL.linksParser.allLinksParser;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by alexandru on 2/5/15.
 */
public class urlsUpdate extends HttpServlet{

    //loop the website with url List and make UrlEntitys in Database
    // do it from 20 feb till 15 March daily, and from 20 august till 15 sept Daily:
    public  void urlsUpdate()

    {
        String mainUrl="";
        String season = "season_not_setted";
        CombinationEndPoint combinationEndPoint = new CombinationEndPoint();
        JSONObject fachs;

        //look wich next Semester have to begin:
        try {
            season = getActualSeason();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //get mainURL for this Semester:
        mainUrl = getMainUrl(season);


       //call the endpoint setCombination and put season and url in it:

        fachs = allLinksParser.parse(mainUrl, season).getFirst();
        combinationEndPoint.setCombination(fachs, season);




    }
    public  String getActualSeason() throws ParseException {

        String season;

        Calendar calendar = Calendar.getInstance();

        Date date = calendar.getTime();

        DateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);



        if (date.after(formatDate.parse("01.03.2015")) && date.before(formatDate.parse("01.09.2015"))) {
            season = "ss15";
            return season;
        }

        if (date.after(formatDate.parse("01.09.2015")) && date.before(formatDate.parse("01.03.2016"))) {
            season = "ws1516";
            return season;
        }

        if (date.after(formatDate.parse("01.03.2016")) && date.before(formatDate.parse("01.09.2016"))) {
            season = "ss16";
            return season;
        }

        if (date.after(formatDate.parse("01.09.2016")) && date.before(formatDate.parse("01.03.2017"))) {
            season = "ws1617";
            return season;
        }

        if (date.after(formatDate.parse("01.03.2017")) && date.before(formatDate.parse("01.09.2017"))) {
            season = "ss17";
            return season;
        }

        if (date.after(formatDate.parse("01.09.2017")) && date.before(formatDate.parse("01.03.2018"))) {
            season = "ws1718";
            return season;
        }

        if (date.after(formatDate.parse("01.03.2018")) && date.before(formatDate.parse("01.09.2018"))) {
            season = "ss18";
            return season;
        }

        if (date.after(formatDate.parse("01.09.2018")) && date.before(formatDate.parse("01.03.2019"))) {
            season = "ws1819";
            return season;
        }

        if (date.after(formatDate.parse("01.03.2019")) && date.before(formatDate.parse("01.09.2019"))) {
            season = "ss19";
            return season;
        }

        if (date.after(formatDate.parse("01.09.2019")) && date.before(formatDate.parse("01.03.2020"))) {
            season = "ws1920";
            return season;
        }

        if (date.after(formatDate.parse("01.03.2020")) && date.before(formatDate.parse("01.09.2020"))) {
            season = "ss20";
            return season;

        }
        if (date.after(formatDate.parse("01.09.2020")) && date.before(formatDate.parse("01.03.2021"))) {
            season = "ws2021";
            return season;
        }

        if (date.after(formatDate.parse("01.03.2021")) && date.before(formatDate.parse("01.09.2021"))) {
            season = "ss21";
            return season;
        }

        if (date.after(formatDate.parse("01.09.2021")) && date.before(formatDate.parse("01.03.2022"))) {
            season = "ws2122";
            return season;
        }

        if (date.after(formatDate.parse("01.03.2022")) && date.before(formatDate.parse("01.09.2022"))) {
            season = "ss22";
            return season;
        }

        if (date.after(formatDate.parse("01.09.2022")) && date.before(formatDate.parse("01.03.2023"))) {
            season = "ws2223";
            return season;
        }
            if (date.after(formatDate.parse("01.03.2023")) && date.before(formatDate.parse("01.09.2023"))) {
                season = "ss23";
                return season;

            }
            if (date.after(formatDate.parse("01.09.2023")) && date.before(formatDate.parse("01.03.2024"))) {
                season = "ws2324";
                return season;
            }

            if (date.after(formatDate.parse("01.03.2024")) && date.before(formatDate.parse("01.09.2024"))) {
                season = "ss24";
                return season;
            }

            if (date.after(formatDate.parse("01.09.2024")) && date.before(formatDate.parse("01.03.2025"))) {
                season = "ws2425";
                return season;
            }

            if (date.after(formatDate.parse("01.03.2025")) && date.before(formatDate.parse("01.09.2025"))) {
                season = "ss25";
                return season;
            }

            if (date.after(formatDate.parse("01.09.2025")) && date.before(formatDate.parse("01.09.2026"))) {
                season = "ws2526";
                return season;
        }

        else season = "cant set";

        return season;
    }

    private  String getMainUrl(String season){
        String url="http://informatik.fh-brandenburg.de/Stundenplan/"+season+"/liste-zu.html";
        return url;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        urlsUpdate();
    }
}
