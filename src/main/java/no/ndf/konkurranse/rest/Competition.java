package no.ndf.konkurranse.rest;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by gloken on 16.12.2015.
 */
@XmlRootElement
public class Competition implements Serializable {
    private String name;

    public Competition() {
    }

    public Competition(String name) {
            this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
