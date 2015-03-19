package org.alex.stundenplan.backend;

/**
 * Created by alexandru on 2/4/15.
 */
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

/**
 * Objectify service wrapper so we can statically register our persistence classes
 * More on Objectify here : https://code.google.com/p/objectify-appengine/
 *
 */
public class OfyService {

    static {
  try {
      factory().register(E_Timetable.class);
      factory().register(E_Combination.class);
  }catch(NoClassDefFoundError e) {
            //handle carefully
      e.printStackTrace();
        }

    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}