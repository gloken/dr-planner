package no.ndf.konkurranse.rest.groups;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by gloken on 06.01.2016.
 */
@XmlRootElement
public class DancerDTO implements Comparable<DancerDTO> {
    private String name;
    private int age;
    private String school;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    @Override
    public int compareTo(DancerDTO dancerToCompareTo) {
        return this.age - dancerToCompareTo.getAge();
    }
}
