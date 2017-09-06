package no.ndf.konkurranse.rest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by gloken on 03.01.2016.
 */
@XmlRootElement
public class DancerWithAlternativeSchoolDTO {
    private String dancerId;
    private String name;
    private String schoolName;

    public DancerWithAlternativeSchoolDTO() {
    }

    public DancerWithAlternativeSchoolDTO(String dancerId, String name, String schoolName) {
        this.dancerId = dancerId;
        this.name = name;
        this.schoolName = schoolName;
    }

    public String getName() {
        return name;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getDancerId() {
        return dancerId;
    }
}
