package org.alex.stundenplan.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.cmd.Query;

import org.alex.stundenplan.backend.StundenPlanOnlineParser.tableParser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import static org.alex.stundenplan.backend.OfyService.ofy;
/*
 * Created by alexandru on 2/3/15.
 */

@Api(name = "parserEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain  = "backend.stundenplan.alex.org", ownerName = "backend.stundenplan.alex.org", packagePath = ""))
public class ParserEndpoint {

// Make sure to add this endpoint to your web.xml file if this is a web application.

    public ParserEndpoint() {

    }

    //------------------------find an insert methods to do with cron-------------------------------
    //parse it from website
    @ApiMethod(name = "parseE_Timetable")
    public E_Timetable parseE_TimetablefromWebsite(@Named("season") String season, @Named("url") String url, @Named("fach") String fach, @Named("sem") Integer sem, @Named("group") String group) {


        ArrayList<E_Subject> subjectList = new ArrayList();

        //get the ArrayList of Subjects
        try {
            subjectList = tableParser.tableParser(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Add the array list to Timetabale Object
        E_Timetable timetable = new E_Timetable();
        timetable.setSeason(season);
        timetable.setFach(fach);
        timetable.setSemester(sem);
        timetable.setGroup(group);
        timetable.setUrl(url);
        timetable.setE_SubjectList(subjectList);


        //Save timetable to Datastore

            ofy().save().entity(timetable).now();


        return timetable;
    }



    //store in database
    @ApiMethod(name = "insertE_Timetable")
    public E_Timetable insertE_Timetable(E_Timetable timetable) throws ConflictException {
//If if is not null, then check if it exists. If yes, throw an Exception
//that it is already present
        if (timetable.getUrl() != null) {
            if (findRecord(timetable.getUrl()) != null) {
                throw new ConflictException("Object already exists");
            }
        }
//Since our @Id field is a Long, Objectify will generate a unique value for us
//when we use put
        ofy().save().entity(timetable).now();

        return timetable;
    }

    //--------------------------------methods to use in Android------------------------------
    //get it from database to be used by android
    @ApiMethod(name = "getE_Timetable")
    public E_Timetable getE_Timetable(@Named("url") String url) {

        return ofy().load().type(E_Timetable.class).id(url).now();
    }

    @ApiMethod(name = "getList")
    public ArrayList<E_Subject> getList(@Named("url") String url) {

        return getE_Timetable(url).getE_SubjectList();
    }



    /**
     * Return a collection of E_Timetables
     *
     * @param count The number of E_Timetables
     * @return a list of E_Timetables
     */
    @ApiMethod(name = "listE_Timetable")
    public CollectionResponse<E_Timetable> listE_Timetable(@Nullable @Named("cursor") String cursorString,
                                                           @Nullable @Named("count") Integer count) {

        Query<E_Timetable> query = ofy().load().type(E_Timetable.class);
        if (count != null) query.limit(count);
        if (cursorString != null && cursorString != "") {
            query = query.startAt(Cursor.fromWebSafeString(cursorString));
        }

        List<E_Timetable> records = new ArrayList<E_Timetable>();
        QueryResultIterator<E_Timetable> iterator = query.iterator();
        int num = 0;
        while (iterator.hasNext()) {
            records.add(iterator.next());
            if (count != null) {
                num++;
                if (num == count) break;
            }
        }

//Find the next cursor
        if (cursorString != null && cursorString != "") {
            Cursor cursor = iterator.getCursor();
            if (cursor != null) {
                cursorString = cursor.toWebSafeString();
            }
        }
        return CollectionResponse.<E_Timetable>builder().setItems(records).setNextPageToken(cursorString).build();
    }

    /**
     * This inserts a new <code>E_Timetable</code> object.
     * @param timetable The object to be added.
     * @return The object to be added.
     */


    /**
     * This updates an existing <code>E_Timetable</code> object.
     *
     * @param timetable The object to be added.
     * @return The object to be updated.
     */
    @ApiMethod(name = "updateE_Timetable")
    public E_Timetable updateE_Timetable(E_Timetable timetable) throws NotFoundException {
        if (findRecord(timetable.getUrl()) == null) {
            throw new NotFoundException("E_Timetable Record does not exist");
        }
        ofy().save().entity(timetable).now();
        return timetable;
    }

    /**
     * This deletes an existing <code>E_Timetable</code> object.
     *
     * @param id The id of the object to be deleted.
     */
    @ApiMethod(name = "removeE_Timetable")
    public void removeE_Timetable(@Named("id") String id) throws NotFoundException {
        E_Timetable record = findRecord(id);
        if (record == null) {
            throw new NotFoundException("E_Timetable Record does not exist");
        }
        ofy().delete().entity(record).now();
    }

    //Private method to retrieve a <code>E_Timetable</code> record
    private E_Timetable findRecord(String id) {
        return ofy().load().type(E_Timetable.class).id(id).now();
//or return ofy().load().type(E_Timetable.class).filter("id",id).first.now();
    }


}
