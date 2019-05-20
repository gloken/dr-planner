package no.ndf.konkurranse.rest.groups;

import no.ndf.konkurranse.*;
import no.ndf.konkurranse.rest.Dancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gloken on 12.01.2016.
 */
@Component
@Scope("request")
public class CompetitionGroupHelper {
    public static final String DESC_U_6 = "u/6 År";
    public static final String DESC_U_8 = "u/8 År";
    public static final String DESC_U_10 = "u/10 År";
    public static final String DESC_U_11 = "u/11 År";
    public static final String DESC_U_12 = "u/12 År";
    public static final String DESC_U_13 = "u/13 År";
    public static final String DESC_U_14 = "u/14 År";
    private static final String DESC_U_15 = "u/15 År";
    private static final String DESC_U_17 = "u/17 År";
    private static final String DESC_U_25 = "u/25 År";
    public static final String DESC_6_8 = "6-8 År";
    private static final String DESC_8 = "8 År";
    private static final String DESC_9 = "9 År";
    public static final String DESC_8_10 = "8-10 År";
    public static final String DESC_10_12 = "10-12 År";
    public static final String DESC_10_13 = "10-13 År";
    public static final String DESC_10_14 = "10-14 År";
    public static final String DESC_12_14 = "12-14 År";
    public static final String DESC_12_15 = "12-15 År";
    public static final String DESC_12_16 = "12-16 År";
    public static final String DESC_13_15 = "13-15 År";
    private static final String DESC_13_16 = "13-16 År";
    public static final String DESC_14_16 = "14-16 År";
    public static final String DESC_16_18 = "16-18 År";
    public static final String DESC_O_10 = "o/10 År";
    public static final String DESC_O_11 = "o/11 År";
    public static final String DESC_O_12 = "o/12 År";
    public static final String DESC_O_13 = "o/13 År";
    public static final String DESC_O_14 = "o/14 År";
    public static final String DESC_O_15 = "o/15 År";
    public static final String DESC_O_16 = "o/16 År";
    public static final String DESC_O_17 = "o/17 År";
    public static final String DESC_O_18 = "o/18 År";
    private static final String DESC_O_25 = "o/25 År";
    public static final String DESC_UNKNOWN = "Alder ikke oppgitt";
    public static final String DESC_ALL = "Alle";
    public static final String DESC_BARN = "Barn";
    public static final String DESC_VOKSEN = "Voksen";
    public static final int MAX_AGE = 99;
    public static final int MINIMUM_NUMBER_IN_GROUP = 4;

    @Autowired
    CreateCompetitionModel createCompetitionModel;

    public CompetitionGroupDTO getSlowSingleGroup(SlowLevel slowLevel) {

        List<Dancer> allDancers = new ArrayList<>(createCompetitionModel.getDancers().values());
        List<DancerDTO> dancers = allDancers.stream()
                .filter(d -> d.getSlowLevel().equals(slowLevel))
                .map(d -> DancerDTOMapper.mapToDancerDTO(d))
                .collect(Collectors.toList());

        CompetitionGroupDTO competitionGroupDTO = new CompetitionGroupDTO("Slow", slowLevel.getLevel());
        competitionGroupDTO.setDancers(dancers);

        if (slowLevel.equals(SlowLevel.OPEN)) {
            applySlowOpenAgeGroups(competitionGroupDTO, dancers);
        } else if (slowLevel.equals(SlowLevel.MESTER)) {
            applySlowMesterAgeGroups(competitionGroupDTO, dancers);
        } else if (slowLevel.equals(SlowLevel.CHAMP)) {
            applySlowChampAgeGroups(competitionGroupDTO, dancers);
        } else if (slowLevel.equals(SlowLevel.ELITE)) {
            applyEliteAgeGroups(competitionGroupDTO, dancers);
        }

        return competitionGroupDTO;
    }

    public CompetitionGroupDTO getFreestyleSingleGroup(SingleLevel singleLevel) {

        List<Dancer> allDancers = new ArrayList<>(createCompetitionModel.getDancers().values());
        List<DancerDTO> dancers = allDancers.stream()
                .filter(d -> d.getSingleLevel().equals(singleLevel))
                .map(d -> DancerDTOMapper.mapToDancerDTO(d))
                .collect(Collectors.toList());

        CompetitionGroupDTO competitionGroupDTO = new CompetitionGroupDTO("Singel", singleLevel.getLevel());
        competitionGroupDTO.setDancers(dancers);

        if (singleLevel.equals(SingleLevel.REKRUTT)) {
            applyRekrutteringAgeGroups(competitionGroupDTO, dancers);
        } else if (singleLevel.equals(SingleLevel.LITT_OVET)) {
            applyLittOvetAgeGroups(competitionGroupDTO, dancers);
        } else if (singleLevel.equals(SingleLevel.MESTER)) {
            applyMesterAgeGroups(competitionGroupDTO, dancers);
        } else if (singleLevel.equals(SingleLevel.CHAMP)) {
            applyChampAgeGroups(competitionGroupDTO, dancers);
        } else if (singleLevel.equals(SingleLevel.ELITE)) {
            applyEliteAgeGroups(competitionGroupDTO, dancers);
        }

        return competitionGroupDTO;
    }

    public CompetitionGroupDTO getDoubleGroup(DoubleLevel level) {

        ArrayList<Dancer> allDancers = new ArrayList<>(createCompetitionModel.getDancers().values());
        List<DancerDTO> dancers = allDancers.stream()
                .filter(d -> d.getDoubleLevel().equals(level))
                .map(d -> DancerDTOMapper.mapToDoubleDancerDTO(d))
                .collect(Collectors.toList());

        CompetitionGroupDTO competitionGroupDTO = new CompetitionGroupDTO("Dobbel", level.getLevel());
        competitionGroupDTO.setDancers(dancers);

        if (level.equals(DoubleLevel.UNKNOWN)) {
            competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_ALL, dancers));
        }
        if (level.equals(DoubleLevel.OPEN)) {
            applyDoubleOpenAgeGroups(competitionGroupDTO, dancers);
        } else if (level.equals(DoubleLevel.MESTER)) {
            applyDoubleMesterAgeGroups(competitionGroupDTO, dancers);
        } else if (level.equals(DoubleLevel.CHAMP_ELITE)) {
            applyDoubleChampEliteAgeGroups(competitionGroupDTO, dancers);
        }

        return competitionGroupDTO;
    }

    public CompetitionGroupDTO getSlowDoubleGroup(SlowDoubleLevel level) {
        ArrayList<Dancer> allDancers = new ArrayList<>(createCompetitionModel.getDancers().values());
        List<DancerDTO> dancers = allDancers.stream()
                .filter(d -> d.getSlowDoubleLevel().equals(level))
                .map(d -> DancerDTOMapper.mapToSlowDoubleDancerDTO(d))
                .collect(Collectors.toList());

        CompetitionGroupDTO competitionGroupDTO = new CompetitionGroupDTO("Slow dobbel", level.getLevel());
        competitionGroupDTO.setDancers(dancers);

        if (level.equals(SlowDoubleLevel.AAPEN)) {
            applySlowDoubleOpenAgeGroups(competitionGroupDTO, dancers);
        } else if (level.equals(SlowDoubleLevel.MESTER_CHAMP_ELITE)) {
            applySlowDoubleMesterChampEliteAgeGroups(competitionGroupDTO, dancers);
        }

        return competitionGroupDTO;
    }

    public CompetitionGroupDTO getHipHopGroup(HipHopLevel level) {
        List<Dancer> allDancers = new ArrayList<>(createCompetitionModel.getDancers().values());
        List<DancerDTO> dancers = allDancers.stream()
                .filter(d -> d.getHipHopLevel().equals(level))
                .map(d -> DancerDTOMapper.mapToDancerDTO(d))
                .collect(Collectors.toList());

        CompetitionGroupDTO competitionGroupDTO = new CompetitionGroupDTO("Hip Hop", level.getLevel());
        competitionGroupDTO.setDancers(dancers);

        if (level.equals(HipHopLevel.BEGINNER)) {
            applyHipHopBeginnerAgeGroups(competitionGroupDTO, dancers);
        } else if (level.equals(HipHopLevel.INTERMEDIATE)) {
            applyHipHopIntermediateAgeGroups(competitionGroupDTO, dancers);
        }

        return competitionGroupDTO;
    }

    public CompetitionGroupDTO getTrioGroup(TrioLevel level) {
        List<Dancer> allDancers = new ArrayList<>(createCompetitionModel.getDancers().values());
        List<DancerDTO> dancers = allDancers.stream()
                .filter(d -> d.getTrioLevel().equals(level))
                .map(d -> DancerDTOMapper.mapToTrioDancerDTO(d))
                .collect(Collectors.toList());

        CompetitionGroupDTO competitionGroupDTO = new CompetitionGroupDTO("Trio", level.getLevel());
        competitionGroupDTO.setDancers(dancers);

        applyTrioAgeGroups(competitionGroupDTO, dancers);

        return competitionGroupDTO;
    }

    public CompetitionGroupDTO getDiscoKidSingleGroup() {
        List<Dancer> allDancers = new ArrayList<>(createCompetitionModel.getDancers().values());
        List<DancerDTO> dancers = allDancers.stream()
                .filter(d -> d.getDiscoKidEvent().equals(DiscoKidEvent.SINGLE_ONLY) || d.getDiscoKidEvent().equals(DiscoKidEvent.SINGLE_AND_SLOW))
                .map(d -> DancerDTOMapper.mapToDancerDTO(d))
                .collect(Collectors.toList());

        CompetitionGroupDTO competitionGroupDTO = new CompetitionGroupDTO("Disco kid kvalifisering", "Singel");
        competitionGroupDTO.setDancers(dancers);

        applyDiscoKidSingleAgeGroups(competitionGroupDTO, dancers);

        return competitionGroupDTO;
    }

    public CompetitionGroupDTO getDiscoKidSlowGroup() {
        List<Dancer> allDancers = new ArrayList<>(createCompetitionModel.getDancers().values());
        List<DancerDTO> dancers = allDancers.stream()
                .filter(d -> d.getDiscoKidEvent().equals(DiscoKidEvent.SLOW_ONLY) || d.getDiscoKidEvent().equals(DiscoKidEvent.SINGLE_AND_SLOW))
                .map(d -> DancerDTOMapper.mapToDancerDTO(d))
                .collect(Collectors.toList());

        CompetitionGroupDTO competitionGroupDTO = new CompetitionGroupDTO("Disco kid kvalifisering", "Slow");
        competitionGroupDTO.setDancers(dancers);

        applyDiscoKidSlowAgeGroups(competitionGroupDTO, dancers);

        return competitionGroupDTO;
    }

    public CompetitionGroupDTO getTwoDanceGroup(TwoDanceLevel level) {
        List<Dancer> allDancers = new ArrayList<>(createCompetitionModel.getDancers().values());
        List<DancerDTO> dancers = allDancers.stream()
                .filter(d -> d.getTwoDanceLevel().equals(level))
                .map(d -> DancerDTOMapper.mapToDancerDTO(d))
                .collect(Collectors.toList());

        CompetitionGroupDTO competitionGroupDTO = new CompetitionGroupDTO("Todans", level.getLevel());
        competitionGroupDTO.setDancers(dancers);

        if (TwoDanceLevel.OPEN.equals(level)) {
            applyTwoDanceOpenAgeGroups(competitionGroupDTO, dancers);
        } else if (TwoDanceLevel.CHAMP_ELITE.equals(level)) {
            applyTwoDanceChampEliteAgeGroups(competitionGroupDTO, dancers);
        }

        return competitionGroupDTO;
    }

    public CompetitionGroupDTO getTheWorldsGroup(TheWorldsLevel level) {
        List<Dancer> allDancers = new ArrayList<>(createCompetitionModel.getDancers().values());
        List<DancerDTO> dancers = allDancers.stream()
                .filter(d -> d.getTheWorldsLevel().equals(level))
                .map(d -> DancerDTOMapper.mapToDancerDTO(d))
                .collect(Collectors.toList());

        CompetitionGroupDTO competitionGroupDTO = new CompetitionGroupDTO("Kval til The Worlds", level.getLevel());
        competitionGroupDTO.setDancers(dancers);

        if (TheWorldsLevel.OPEN.equals(level)) {
            applyTheWorldsOpenAgeGroups(competitionGroupDTO, dancers);
        } else if (TheWorldsLevel.ELITE.equals(level)) {
            applyTheWorldsEliteAgeGroups(competitionGroupDTO, dancers);
        }

        return competitionGroupDTO;
    }

    public CompetitionGroupDTO getFunCoupleGroup() {
        List<Dancer> allDancers = new ArrayList<>(createCompetitionModel.getDancers().values());
        List<DancerDTO> dancers = allDancers.stream()
                .filter(d -> d.isRegisteredForFunCouple())
                .map(d -> DancerDTOMapper.mapToFunCoupleDancerDTO(d))
                .collect(Collectors.toList());

        CompetitionGroupDTO competitionGroupDTO = new CompetitionGroupDTO("Fun couple", "");
        competitionGroupDTO.setDancers(dancers);

        applyFunCoupleAgeGroups(competitionGroupDTO, dancers);

        return competitionGroupDTO;
    }

    public CompetitionGroupDTO getBigLittleGroup() {
        List<Dancer> allDancers = new ArrayList<>(createCompetitionModel.getDancers().values());
        List<DancerDTO> dancers = allDancers.stream()
                .filter(d -> d.isRegisteredForBigLittle())
                .map(d -> DancerDTOMapper.mapToBigLittleDancerDTO(d))
                .collect(Collectors.toList());

        CompetitionGroupDTO competitionGroupDTO = new CompetitionGroupDTO("BIG & LITTLE", "");
        competitionGroupDTO.setDancers(dancers);

        applyFunCoupleAgeGroups(competitionGroupDTO, dancers);

        return competitionGroupDTO;
    }

    public CompetitionGroupDTO getTeams() {
        List<Dancer> allDancers = new ArrayList<>(createCompetitionModel.getDancers().values());
        List<DancerDTO> dancers = allDancers.stream()
                .filter(d -> d.isRegisteredForTeam())
                .map(d -> DancerDTOMapper.mapToTeamDTO(d))
                .collect(Collectors.toList());

        CompetitionGroupDTO competitionGroupDTO = new CompetitionGroupDTO("Team", "");
        competitionGroupDTO.setDancers(dancers);

        applyTeamAgeGroups(competitionGroupDTO, dancers);

        return competitionGroupDTO;
    }

    public CompetitionGroupDTO getLationSamba() {
        List<Dancer> allDancers = new ArrayList<>(createCompetitionModel.getDancers().values());
        List<DancerDTO> dancers = allDancers.stream()
                .filter(d -> d.getRegisteredForLatinoSamba())
                .map(d -> DancerDTOMapper.mapToDancerDTO(d))
                .collect(Collectors.toList());

        CompetitionGroupDTO competitionGroupDTO = new CompetitionGroupDTO("Samba solo", "");
        competitionGroupDTO.setDancers(dancers);

        applyLatinoAgeGroups(competitionGroupDTO, dancers);

        return competitionGroupDTO;
    }

    public CompetitionGroupDTO getLationChaChaCha() {
        List<Dancer> allDancers = new ArrayList<>(createCompetitionModel.getDancers().values());
        List<DancerDTO> dancers = allDancers.stream()
                .filter(d -> d.getRegisteredForLatinoChaChaCha())
                .map(d -> DancerDTOMapper.mapToDancerDTO(d))
                .collect(Collectors.toList());

        CompetitionGroupDTO competitionGroupDTO = new CompetitionGroupDTO("Cha cha cha solo", "");
        competitionGroupDTO.setDancers(dancers);

        applyLatinoAgeGroups(competitionGroupDTO, dancers);

        return competitionGroupDTO;
    }

    private void applyLatinoAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_BARN, getDancersOfAge(dancers, 0, 18)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_VOKSEN, getDancersOfAge(dancers, 18, MAX_AGE)));
    }

    private void applyTrioAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_ALL, getDancersOfAge(dancers, 0, MAX_AGE)));
//        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_17, getDancersOfAge(dancers, 0, 17)));
//        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_17, getDancersOfAge(dancers, 17, MAX_AGE)));
    }

    private void applyHipHopBeginnerAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_ALL, getDancersOfAge(dancers, 0, MAX_AGE)));
    }

    private void applyHipHopIntermediateAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
//        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_10, getDancersOfAge(dancers, 0, 10)));
//        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_10, getDancersOfAge(dancers, 10, MAX_AGE)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_ALL, getDancersOfAge(dancers, 0, MAX_AGE)));
    }

    private void applySlowDoubleOpenAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        List<DancerDTO> allDancers = getDancersOfAge(dancers, 1, MAX_AGE);
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_ALL, allDancers));
    }

    private void applySlowDoubleMesterChampEliteAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_12, getDancersOfAge(dancers, 0, 12)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_12_15, getDancersOfAge(dancers, 12, 15)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_15, getDancersOfAge(dancers, 15, MAX_AGE)));
    }

    private void applyDoubleOpenAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_11, getDancersOfAge(dancers, 0, 11)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_11, getDancersOfAge(dancers, 11, MAX_AGE)));
//        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_ALL, getDancersOfAge(dancers, 0, MAX_AGE)));
    }

    private void applyDoubleMesterAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_ALL, getDancersOfAge(dancers,0, MAX_AGE)));
    }

    private void applyDoubleChampEliteAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_15, getDancersOfAge(dancers, 0, 15)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_15, getDancersOfAge(dancers, 15, MAX_AGE)));
//        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_ALL, dancers));
    }

    private void applySlowOpenAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_8, getDancersOfAge(dancers, 0, 8)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_8, getDancersOfAge(dancers, 8, 9)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_9, getDancersOfAge(dancers, 9, 10)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_10_12, getDancersOfAge(dancers, 10, 12)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_12, getDancersOfAge(dancers, 12, MAX_AGE)));
    }

    private void applyRekrutteringAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_6, getDancersOfAge(dancers, 0, 6)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_6_8, getDancersOfAge(dancers, 6, 8)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_8_10, getDancersOfAge(dancers, 8, 10)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_10_12, getDancersOfAge(dancers, 10, 12)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_12, getDancersOfAge(dancers, 12, MAX_AGE)));
    }

    private void applyLittOvetAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_8, getDancersOfAge(dancers, 0, 8)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_8_10, getDancersOfAge(dancers, 8, 10)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_10_12, getDancersOfAge(dancers, 10, 12)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_12, getDancersOfAge(dancers, 12, MAX_AGE)));
    }

    private void applyMesterAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_10, getDancersOfAge(dancers, 0, 10)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_10_12, getDancersOfAge(dancers, 10, 12)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_12_14, getDancersOfAge(dancers, 12, 14)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_14, getDancersOfAge(dancers, 14, MAX_AGE)));
    }

    private void applyChampAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_10, getDancersOfAge(dancers, 0, 10)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_10_12, getDancersOfAge(dancers, 10, 12)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_12_14, getDancersOfAge(dancers, 12, 14)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_14_16, getDancersOfAge(dancers, 14, 16)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_16, getDancersOfAge(dancers, 16, MAX_AGE)));
    }

    private void applySlowMesterAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        // FOLLO
//        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_10, getDancersOfAge(dancers, 0, 10)));
//        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_10_13, getDancersOfAge(dancers, 10, 13)));
//        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_13, getDancersOfAge(dancers, 13, MAX_AGE)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_10, getDancersOfAge(dancers, 0, 10)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_10_12, getDancersOfAge(dancers, 10, 12)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_12_14, getDancersOfAge(dancers, 12, 14)));

        List<DancerDTO> dancers14to16 = getDancersOfAge(dancers, 14, 16);
        List<DancerDTO> dancersOver16 = getDancersOfAge(dancers, 16, MAX_AGE);

        if ("Marianne ikke får bestemt seg".equals("Marianne ikke får bestemt seg")) {
            List<DancerDTO> dancersOver14 = Stream.concat(dancers14to16.stream(), dancersOver16.stream())
                    .collect(Collectors.toList());
            competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_14, dancersOver14));
        }
        else
        if (dancers14to16.size() > MINIMUM_NUMBER_IN_GROUP && dancersOver16.size() > MINIMUM_NUMBER_IN_GROUP) {
            competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_14_16, dancers14to16));
            competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_16, dancersOver16));
        } else {
            List<DancerDTO> dancersOver14 = Stream.concat(dancers14to16.stream(), dancersOver16.stream())
                    .collect(Collectors.toList());
            competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_14, dancersOver14));
        }
    }

    private void applySlowChampAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        // FOLLO
//        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_10, getDancersOfAge(dancers, 0, 10)));
//        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_10_13, getDancersOfAge(dancers, 10, 13)));
//        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_13_15, getDancersOfAge(dancers, 13, 15)));
//        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_15, getDancersOfAge(dancers, 15, MAX_AGE)));

        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_10, getDancersOfAge(dancers, 0, 10)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_10_12, getDancersOfAge(dancers, 10, 12)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_12_14, getDancersOfAge(dancers, 12, 14)));

        List<DancerDTO> dancers14to16 = getDancersOfAge(dancers, 14, 16);
        List<DancerDTO> dancersOver16 = getDancersOfAge(dancers, 16, MAX_AGE);

        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_14_16, dancers14to16));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_16, dancersOver16));
    }

    private void applyEliteAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        //FOLLO
        //competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_13, getDancersOfAge(dancers, 0, 13)));
        //competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_13_16, getDancersOfAge(dancers, 13, 16)));
        //competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_16, getDancersOfAge(dancers, 16, MAX_AGE)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_10_12, getDancersOfAge(dancers, 10, 12)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_12_14, getDancersOfAge(dancers, 12, 14)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_14_16, getDancersOfAge(dancers, 14, 16)));

        if (getDancersOfAge(dancers, 18, MAX_AGE).size() > 2) {
            competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_16_18, getDancersOfAge(dancers, 16, 18)));
            competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_18, getDancersOfAge(dancers, 18, MAX_AGE)));
        } else {
            competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_16, getDancersOfAge(dancers, 16, MAX_AGE)));
        }
    }

    private void applyDiscoKidSingleAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_8, getDancersOfAge(dancers, 0, 8)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_8_10, getDancersOfAge(dancers, 8, 10)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_10_12, getDancersOfAge(dancers, 10, 12)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_12_14, getDancersOfAge(dancers, 12, 14)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_14_16, getDancersOfAge(dancers, 14, 16)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_16_18, getDancersOfAge(dancers, 16, 18)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_18, getDancersOfAge(dancers, 18, MAX_AGE)));
    }

    private void applyDiscoKidSlowAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_8, getDancersOfAge(dancers, 0, 8)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_8_10, getDancersOfAge(dancers, 8, 10)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_10_12, getDancersOfAge(dancers, 10, 12)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_12_14, getDancersOfAge(dancers, 12, 14)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_14_16, getDancersOfAge(dancers, 14, 16)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_16_18, getDancersOfAge(dancers, 16, 18)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_18, getDancersOfAge(dancers, 18, MAX_AGE)));
    }

    private void applyTwoDanceOpenAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_10, getDancersOfAge(dancers, 0, 10)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_10_12, getDancersOfAge(dancers, 10, 12)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_12_14, getDancersOfAge(dancers, 12, 14)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_14, getDancersOfAge(dancers, 14, MAX_AGE)));
    }

    private void applyTwoDanceChampEliteAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_10, getDancersOfAge(dancers, 0, 10)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_10_12, getDancersOfAge(dancers, 10, 12)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_12_14, getDancersOfAge(dancers, 12, 14)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_14_16, getDancersOfAge(dancers, 14, 16)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_16, getDancersOfAge(dancers, 16, MAX_AGE)));
//        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_18, getDancersOfAge(dancers, 18, MAX_AGE)));
    }

    private void applyFunCoupleAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_ALL, getDancersOfAge(dancers, -1, MAX_AGE)));
    }

    private void applyTeamAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_25, getDancersOfAge(dancers, 0, 25)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_25, getDancersOfAge(dancers, 25, MAX_AGE)));
    }

    private void applyTheWorldsOpenAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_8, getDancersOfAge(dancers, 0, 8)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_8_10, getDancersOfAge(dancers, 8, 10)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_10_12, getDancersOfAge(dancers, 10, 12)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_12_14, getDancersOfAge(dancers, 12, 14)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_14_16, getDancersOfAge(dancers, 14, 16)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_16_18, getDancersOfAge(dancers, 16, 18)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_18, getDancersOfAge(dancers, 18, MAX_AGE)));
    }

    private void applyTheWorldsEliteAgeGroups(CompetitionGroupDTO competitionGroupDTO, List<DancerDTO> dancers) {
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_U_8, getDancersOfAge(dancers, 0, 8)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_8_10, getDancersOfAge(dancers, 8, 10)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_10_12, getDancersOfAge(dancers, 10, 12)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_12_14, getDancersOfAge(dancers, 12, 14)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_14_16, getDancersOfAge(dancers, 14, 16)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_16_18, getDancersOfAge(dancers, 16, 18)));
        competitionGroupDTO.addAgeGroup(new AgeGroupDTO(DESC_O_18, getDancersOfAge(dancers, 18, MAX_AGE)));
    }

    private static List<DancerDTO> getDancersOfAge(List<DancerDTO> freestyleSingleDancers, int fromAge, int toAge) {
        List<DancerDTO> list = freestyleSingleDancers.stream()
                .filter(d -> d.getAge() > fromAge - 1 && d.getAge() < toAge)
                .collect(Collectors.toList());
        Collections.shuffle(list);
        return list;
    }
}
