package no.ndf.konkurranse.rest;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by gloken on 04.01.2016.
 */
@XmlRootElement
public class UpdatedSchoolsDTO {
    private List<DancerWithAlternativeSchoolDTO> updatedDancers;

    public UpdatedSchoolsDTO() {
    }

    public UpdatedSchoolsDTO(List<DancerWithAlternativeSchoolDTO> updatedDancers) {
        this.updatedDancers = updatedDancers;
    }

    public List<DancerWithAlternativeSchoolDTO> getUpdatedDancers() {
        return updatedDancers;
    }

    public void setUpdatedDancers(List<DancerWithAlternativeSchoolDTO> updatedDancers) {
        this.updatedDancers = updatedDancers;
    }
}
