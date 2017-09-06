package no.ndf.konkurranse.rest;

import no.ndf.konkurranse.CreateCompetitionModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gloken on 03.01.2016.
 */
@Path("schoolAdmin")
public class SchoolAdminResource {

    @Autowired
    CreateCompetitionModel createCompetitionModel;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DancerWithAlternativeSchoolDTO> getDancersWithAlterativeSchool() {
        ArrayList<DancerWithAlternativeSchoolDTO> dancersToEdit = new ArrayList<>();

        for (Dancer dancer : createCompetitionModel.getDancers().values()) {
            if (!dancer.belongsToListedSchool()) {
                String name = dancer.getFirstName() + " " + dancer.getLastName();
                DancerWithAlternativeSchoolDTO dancerToEdit = new DancerWithAlternativeSchoolDTO(dancer.getDancerId(), name, dancer.getUnlistedSchool());
                dancersToEdit.add(dancerToEdit);
            }
        }

        return dancersToEdit;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSchools(UpdatedSchoolsDTO updatedSchoolsDTO) {
        for (DancerWithAlternativeSchoolDTO dto : updatedSchoolsDTO.getUpdatedDancers()) {
            Dancer dancer = createCompetitionModel.getDancers().get(dto.getDancerId());
            createCompetitionModel.getDancers().remove(dto.getDancerId());
            dancer.setListedSchool(dto.getSchoolName());
            createCompetitionModel.getDancers().put(dancer.getDancerId(), dancer);
        }

        return Response.ok().build();
    }
}
