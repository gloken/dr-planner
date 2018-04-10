package no.ndf.konkurranse.rest.export;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.ndf.konkurranse.CreateCompetitionModel;
import no.ndf.konkurranse.rest.groups.AgeGroupDTO;
import no.ndf.konkurranse.rest.groups.CompetitionGroupDTO;
import no.ndf.konkurranse.rest.groups.DancerDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by gloken on 13.01.2016.
 */
@Path("export")
public class CsvExportResource {

    private static final Logger logger = Logger.getLogger(CsvExportResource.class.getName());

    @Autowired
    CreateCompetitionModel createCompetitionModel;

    @GET
    @Path("csv")
    public Response getCsv() {
        ObjectMapper mapper = new ObjectMapper();

        logger.info("Competition: " + createCompetitionModel.getCompetitionName());
        try {
            logger.info(mapper.writeValueAsString(createCompetitionModel.getGroupedCompetitors()));
        } catch (JsonProcessingException e) {
            logger.warning("Error printing competitors: " + e.getMessage());
        }

        StreamingOutput fileStream = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                byte[] content = getFile().getBytes("ISO-8859-1");
                outputStream.write(content);
                outputStream.flush();
            }
        };
        return Response.ok(fileStream, "application/csv; charset=ISO-8859-1")
                .header("Content-Disposition", "attachment; filename=\"" + createCompetitionModel.getCompetitionName() + ".csv\"")
                .build();
    }

    private String getFile() {
        StringBuffer file = new StringBuffer();
        file.append("#name;#club;#class;#license;#number\n\n");

        List<CompetitionGroupDTO> groupedCompetitors = createCompetitionModel.getGroupedCompetitors();

        for (CompetitionGroupDTO group : groupedCompetitors) {
            for (AgeGroupDTO ageGroup : group.getAgeGroups()) {
                for (DancerDTO dancer : ageGroup.getDancers()) {
                    file.append(dancer.getName());
                    file.append(";");
                    file.append(dancer.getSchool());
                    file.append(";");
                    file.append(group.getCategory() + " " + group.getLevel() + " " + ageGroup.getDescription());
                    file.append(";");
                    file.append(dancer.getAge() + "År");
                    file.append(";\n\n");
                }
            }
        }

        return file.toString();
    }
}
