package no.ndf.konkurranse.rest.groups;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * Created by gloken on 04.01.2016.
 */
@XmlRootElement
public class CompetitionGroupDTO {
    private String level;
    private String category;
    private List<AgeGroupDTO> ageGroups;
    private List<DancerDTO> dancers;

    public CompetitionGroupDTO(String category, String level) {
        this.category = category;
        this.level = level;
        this.ageGroups = new ArrayList<>();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void addAgeGroup(AgeGroupDTO ageGroup) {
        if (ageGroup.getDancers() != null && ageGroup.getDancers().size() > 0) {
            this.ageGroups.add(ageGroup);
        }
    }

    public List<AgeGroupDTO> getAgeGroups() {
        return ageGroups;
    }

    public List<DancerDTO> getDancers() {
        return dancers;
    }

    public void setDancers(List<DancerDTO> dancers) {
        this.dancers = dancers;
    }
}
