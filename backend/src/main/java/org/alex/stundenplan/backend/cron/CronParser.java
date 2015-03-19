package org.alex.stundenplan.backend.cron;

import org.alex.stundenplan.backend.CombinationEndPoint;
import org.alex.stundenplan.backend.ParserEndpoint;
import org.alex.stundenplan.backend.allURL.OneLink;
import org.alex.stundenplan.backend.allURL.linksParser.allLinksParser;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by alexandru on 2/5/15.
 *
 * A cron job for insert timetable in database
 */

public class CronParser extends HttpServlet{


    public void  cronParse(){

        boolean parsed = false;
        ArrayList<OneLink> linksList = new ArrayList<>();
        ParserEndpoint parser =new ParserEndpoint();
        JSONObject fachs = new JSONObject();

        String mainUrl = "http://informatik.fh-brandenburg.de/Stundenplan/ss15/liste-zu.html";

        String season = "ss15";

        //1. get List of links:

        linksList = allLinksParser.parse(mainUrl, season).getSecond();

        //2. parse them and insert in database:

        int i = 0;

        for (OneLink oneLink: linksList) {

            i++;
            String url= oneLink.getUrl();
            String fach = oneLink.getFach();
            Integer sem = oneLink.getSem();
            String group = oneLink.getGroup();
            // for all combination fach sem and group do this:
            parser.parseE_TimetablefromWebsite(season, url, fach, sem, group);

        }
        //Json:
        fachs = allLinksParser.parse(mainUrl, season).getFirst();
        CombinationEndPoint comb = new CombinationEndPoint();
        comb.setCombination(fachs, season);



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        cronParse();
    }
}
