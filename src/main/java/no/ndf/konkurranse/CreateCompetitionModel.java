package no.ndf.konkurranse;

import no.ndf.konkurranse.rest.Dancer;
import no.ndf.konkurranse.rest.groups.CompetitionGroupDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gloken on 02.01.2016.
 */
@Component
@Scope(value="session")
public class CreateCompetitionModel {
    private String competitionName;
    private Map<String, Dancer> dancers;
    private List<CompetitionGroupDTO> groupedCompetitors;

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public Map<String, Dancer> getDancers() {
        return dancers;
    }

    public void setDancers(List<Dancer> dancers) {
        this.dancers = new HashMap<>();
        for (Dancer dancer : dancers) {
            String dancerId = dancer.getDancerId();
            this.dancers.put(dancerId, dancer);
        }
    }

    public void setGroupedCompetitors(List<CompetitionGroupDTO> groupedCompetitors) {
        this.groupedCompetitors = groupedCompetitors;
    }

    public List<CompetitionGroupDTO> getGroupedCompetitors() {
        return groupedCompetitors;
    }
}
