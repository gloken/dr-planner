package no.ndf.konkurranse.rest.groups;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gloken on 04.01.2016.
 */
@XmlRootElement
public class CompetitionGroupsDTO {
    private List<CompetitionGroupDTO> competitionGroups;

    public CompetitionGroupsDTO() {
    }

    public void addGroup(CompetitionGroupDTO groupDTO) {
        if (competitionGroups == null) {
            competitionGroups = new ArrayList<>();
        }
        this.competitionGroups.add(groupDTO);
    }

    public List<CompetitionGroupDTO> getCompetitionGroups() {
        return competitionGroups;
    }

    public void setCompetitionGroups(List<CompetitionGroupDTO> competitionGroups) {
        this.competitionGroups = competitionGroups;
    }
}
