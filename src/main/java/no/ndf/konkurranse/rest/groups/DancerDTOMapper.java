package no.ndf.konkurranse.rest.groups;

import no.ndf.konkurranse.rest.Dancer;

/**
 * Created by gloken on 06.01.2016.
 */
public class DancerDTOMapper {
    public static DancerDTO mapToDancerDTO(Dancer dancer) {
        DancerDTO dto = new DancerDTO();
        dto.setName(dancer.getFirstName() + " " + dancer.getLastName());
        dto.setAge(dancer.getAge());
        dto.setSchool(dancer.getListedSchool());
        return dto;
    }

    public static DancerDTO mapToDoubleDancerDTO(Dancer dancer) {
        DancerDTO dto = new DancerDTO();
        dto.setName(dancer.getFirstName() + " " + dancer.getLastName() + " - " + dancer.getDoublePartner());
        int doubleAge = dancer.getDoubleAge();
        if (doubleAge == 0) {
            doubleAge = dancer.getAge();
        }
        dto.setAge(doubleAge);
        dto.setSchool(dancer.getListedSchool());
        return dto;
    }

    public static DancerDTO mapToSlowDoubleDancerDTO(Dancer dancer) {
        DancerDTO dto = new DancerDTO();
        dto.setName(dancer.getFirstName() + " " + dancer.getLastName() + " - " + dancer.getSlowDoublePartner());
        dto.setAge(dancer.getSlowDoubleAge());
        dto.setSchool(dancer.getListedSchool());
        return dto;
    }

    public static DancerDTO mapToTrioDancerDTO(Dancer dancer) {
        DancerDTO dto = new DancerDTO();
        dto.setName(dancer.getFirstName() + " " + dancer.getLastName() + ", " + formatDancePartners(dancer.getTrioPartners()));
        dto.setAge(dancer.getTrioAge());
        dto.setSchool(dancer.getListedSchool());
        return dto;
    }

    private static String formatDancePartners(String trioPartners) {
        String partners = trioPartners.replace(";", ",");
        return partners;
    }

    public static DancerDTO mapToFunCoupleDancerDTO(Dancer dancer) {
        DancerDTO dto = new DancerDTO();
        dto.setName(dancer.getFirstName() + " " + dancer.getLastName() + " - " + dancer.getFunCouplePartner());
        dto.setAge(-1);
        dto.setSchool(dancer.getListedSchool());
        return dto;
    }

    public static DancerDTO mapToBigLittleDancerDTO(Dancer dancer) {
        DancerDTO dto = new DancerDTO();
        dto.setName(dancer.getFirstName() + " " + dancer.getLastName() + " - " + dancer.getBigLittlePartner());
        dto.setAge(-1);
        dto.setSchool(dancer.getListedSchool());
        return dto;
    }

    public static DancerDTO mapToTeamDTO(Dancer dancer) {
        DancerDTO dto = new DancerDTO();
        dto.setName(dancer.getTeamName());
        dto.setAge(dancer.getAge());
        dto.setSchool(dancer.getListedSchool());
        return dto;
    }
}
