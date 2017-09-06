package no.ndf.konkurranse.rest;

import no.ndf.konkurranse.CreateCompetitionModel;
import no.ndf.konkurranse.SingleLevel;
import no.ndf.konkurranse.SlowLevel;
import no.ndf.konkurranse.dao.CompetitionDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gloken on 19.11.2015.
 */
@Path("dancers")
public class DancerResource {

    @Autowired
    CompetitionDao competitionDao;

    @Autowired
    CreateCompetitionModel createCompetitionModel;

    @GET
    @Path("{dancerId}")
    @Produces("application/json")
    public Dancer getDancer(@PathParam("dancerId") String dancerId) {
        return getDummyDancer();
    }

    private Dancer getDummyDancer() {
        return getDummyDancers().get(0);
    }

    @GET
    @Produces("application/json")
    public List<Dancer> getDancers() {
        List<Dancer> dancerList = new ArrayList<>(createCompetitionModel.getDancers().values());
        return dancerList;
    }

    private List<Dancer> getDummyDancers() {
        ArrayList<Dancer> dancers = new ArrayList<Dancer>();
        Dancer jenny = new Dancer();
        jenny.setFirstName("Jenny Andrea");
        jenny.setLastName("Løken");
        jenny.setAge(13);
        jenny.setSingleLevel(SingleLevel.MESTER);
        jenny.setSlowLevel(SlowLevel.MESTER);
        jenny.setListedSchool("Danseloftet");
        dancers.add(jenny);

        Dancer julie = new Dancer();
        julie.setFirstName("Julie");
        julie.setLastName("Lunde");
        julie.setAge(10);
        julie.setSingleLevel(SingleLevel.CHAMP);
        julie.setSlowLevel(SlowLevel.CHAMP);
        julie.setListedSchool("Danseloftet");
        dancers.add(julie);

        return dancers;
    }


}
