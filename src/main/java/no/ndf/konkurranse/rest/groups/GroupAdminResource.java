package no.ndf.konkurranse.rest.groups;

import no.ndf.konkurranse.*;
import no.ndf.konkurranse.TwoDanceLevel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gloken on 04.01.2016.
 */
@Path("competitionGroups")
public class GroupAdminResource {

    @Autowired
    CompetitionGroupHelper competitionGroupHelper;

    @Autowired
    CreateCompetitionModel createCompetitionModel;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CompetitionGroupDTO> getCompetitionGroups() {
        List<CompetitionGroupDTO> competitionGroups = new ArrayList<>();
        
        competitionGroups.add(competitionGroupHelper.getFreestyleSingleGroup(SingleLevel.REKRUTT));
        competitionGroups.add(competitionGroupHelper.getFreestyleSingleGroup(SingleLevel.LITT_OVET));
        competitionGroups.add(competitionGroupHelper.getFreestyleSingleGroup(SingleLevel.MESTER));
        competitionGroups.add(competitionGroupHelper.getFreestyleSingleGroup(SingleLevel.CHAMP));
        competitionGroups.add(competitionGroupHelper.getFreestyleSingleGroup(SingleLevel.ELITE));

        competitionGroups.add(competitionGroupHelper.getDoubleGroup(DoubleLevel.UNKNOWN));
        competitionGroups.add(competitionGroupHelper.getDoubleGroup(DoubleLevel.OPEN));
        competitionGroups.add(competitionGroupHelper.getDoubleGroup(DoubleLevel.MESTER));
        competitionGroups.add(competitionGroupHelper.getDoubleGroup(DoubleLevel.CHAMP_ELITE));

        competitionGroups.add(competitionGroupHelper.getSlowSingleGroup(SlowLevel.OPEN));
        competitionGroups.add(competitionGroupHelper.getSlowSingleGroup(SlowLevel.MESTER));
        competitionGroups.add(competitionGroupHelper.getSlowSingleGroup(SlowLevel.CHAMP));
        competitionGroups.add(competitionGroupHelper.getSlowSingleGroup(SlowLevel.ELITE));

        competitionGroups.add(competitionGroupHelper.getSlowDoubleGroup(SlowDoubleLevel.AAPEN));
        competitionGroups.add(competitionGroupHelper.getSlowDoubleGroup(SlowDoubleLevel.MESTER_CHAMP_ELITE));

        competitionGroups.add(competitionGroupHelper.getTrioGroup(TrioLevel.MESTER_CHAMP_ELITE));

        competitionGroups.add(competitionGroupHelper.getHipHopGroup(HipHopLevel.BEGINNER));
        competitionGroups.add(competitionGroupHelper.getHipHopGroup(HipHopLevel.INTERMEDIATE));

        competitionGroups.add(competitionGroupHelper.getTwoDanceGroup(TwoDanceLevel.OPEN));
        competitionGroups.add(competitionGroupHelper.getTwoDanceGroup(TwoDanceLevel.CHAMP_ELITE));

        competitionGroups.add(competitionGroupHelper.getDiscoKidSingleGroup());
        competitionGroups.add(competitionGroupHelper.getDiscoKidSlowGroup());

        competitionGroups.add(competitionGroupHelper.getTheWorldsGroup(TheWorldsLevel.OPEN));
        competitionGroups.add(competitionGroupHelper.getTheWorldsGroup(TheWorldsLevel.ELITE));

        competitionGroups.add(competitionGroupHelper.getFunCoupleGroup());

        competitionGroups.add(competitionGroupHelper.getBigLittleGroup());

        competitionGroups.add(competitionGroupHelper.getTeams());

        competitionGroups.add(competitionGroupHelper.getLationSamba());
        competitionGroups.add(competitionGroupHelper.getLationChaChaCha());

        createCompetitionModel.setGroupedCompetitors(competitionGroups);

        return competitionGroups;
    }
}
