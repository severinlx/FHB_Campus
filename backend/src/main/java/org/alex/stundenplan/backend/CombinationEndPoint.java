package org.alex.stundenplan.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

import org.alex.stundenplan.backend.allURL.linksParser.allLinksParser;
import org.json.simple.JSONObject;

import static org.alex.stundenplan.backend.OfyService.ofy;

/**
 * Created by alexandru on 16.03.15.
 */
@Api(name = "combinationEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain  = "backend.stundenplan.alex.org", ownerName = "backend.stundenplan.alex.org", packagePath = ""))

public class CombinationEndPoint {
    JSONObject fachs;

    public CombinationEndPoint() {
    }

    @ApiMethod(name="getCombination")
    public E_Combination getCombination(@Named("season") String season){

        return ofy().load().type(E_Combination.class).id(season).now();

    }

    @ApiMethod(name="setCombination")
    public  void setCombination(JSONObject fachs, @Named("season") String season){

        E_Combination comb = new E_Combination();
        comb.setSeason(season);
        comb.setFachs(fachs.toJSONString());

        ofy().save().entity(comb).now();
    }

    @ApiMethod(name="setComb")
    public void setComb(@Named("season") String season, @Named("mainUrl") String mainUrl){



        fachs = new JSONObject();
        fachs = allLinksParser.parse(mainUrl, season).getFirst();
        E_Combination comb = new E_Combination();
        comb.setSeason(season);
        comb.setMainUrl(mainUrl);
        comb.setFachs(fachs.toJSONString());

        ofy().save().entity(comb).now();
    }
}
