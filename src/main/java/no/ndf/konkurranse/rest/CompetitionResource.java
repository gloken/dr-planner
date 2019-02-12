package no.ndf.konkurranse.rest;

import no.ndf.konkurranse.CreateCompetitionModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by gloken on 29.12.2015.
 */
@Path("competition")
public class CompetitionResource {

    @Autowired
    CreateCompetitionModel createCompetitionModel;

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCompetition(Competition competition) {
        createCompetitionModel.setCompetitionName(competition.getName());
        return Response.ok().build();
    }
}
