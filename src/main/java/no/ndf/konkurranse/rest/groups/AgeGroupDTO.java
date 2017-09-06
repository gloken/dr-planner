package no.ndf.konkurranse.rest.groups;

import no.ndf.konkurranse.rest.Dancer;

import java.util.List;

/**
 * Created by gloken on 10.01.2016.
 */
public class AgeGroupDTO {

    private final String description;
    private final List<DancerDTO> dancers;

    public AgeGroupDTO(String groupDescription, List<DancerDTO> dancers) {
        description = groupDescription;
        this.dancers = dancers;
    }

    public String getDescription() {
        return description;
    }

    public List<DancerDTO> getDancers() {
        return dancers;
    }
}
