package no.ndf.konkurranse.rest;

import no.ndf.konkurranse.CreateCompetitionModel;
import no.ndf.konkurranse.FileReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by gloken on 03.12.2015.
 */
@Path("uploadFiles")
public class FileUploadResource {
    private static final Logger logger = Logger.getLogger(FileUploadResource.class.getName());

    @Autowired
    CreateCompetitionModel createCompetitionModel;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Competition uploadFiles(@FormDataParam("file") InputStream fileData, @QueryParam("konkurranse-id") String competitionId) {
        logger.info("Entering uploadFiles");
        Competition competition = new Competition(competitionId);
        List<Dancer> dancers = null;
        try {
            FileReader fileReader = new FileReader(fileData);

            dancers = fileReader.getDancers();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        createCompetitionModel.setDancers(dancers);
        return competition;
    }
}
