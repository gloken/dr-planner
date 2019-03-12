package no.ndf.konkurranse;

import no.ndf.konkurranse.rest.Dancer;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK;
import static org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL;

/**
 * Created by gloken on 07.12.2015.
 */
public class FileReader {
    private final Workbook workbook;

    // The column numbers of all relevant fields
    private int orderIdPosition = -1;
    private int lastNamePosition = -1;
    private int firstNamePosition = -1;
    private int agePosition = -1;
    private int schoolPosition1 = -1;
    private int schoolPosition2 = -1;
    private int freestyleSingelLevelPosition = -1;
    private int slowdanceSingelLevelPosition = -1;
    private int registerdForFreestyleDoublePosition = -1;
    private int doubleLevelPosition = -1;
    private int doublePartnerNamePosition = -1;
    private int doubleAgePosition = -1;
    private int registeredForSlowDoublePosition = -1;
    private int slowDoubleLevelPosition = -1;
    private int slowDoublePartnerPosition = -1;
    private int slowDoubleAgePosition = -1;
    private int registeredForHipHopPosition = -1;
    private int hipHopLevelPosition = -1;
    private int registeredFor2DancePosition = -1;
    private int level2DancePosition = -1;
    private int bigLittleRegisteredPosition = -1;
    private int bigLittlePartnerPosition = -1;
    private int bigLittleAgeDiffPosition = -1;
    private int emailPosition = -1;
    private int registeredForTrioPosition = -1;
    private int trioLevelPosition = -1;
    private int trioPartnersPosition = -1;
    private int trioAgePosition = -1;
    private int registeredForDiscoKidPosition = -1;
    private int discoKidEventPosition = -1;
    private int registeredForFunCouplePosition = -1;
    private int funCouplePartnerPosition = -1;
    private int registeredForTheWorldsPosition = -1;
    private int theWorldsLevelPosition = -1;
    private int registeredForTeamPosition = -1;
    private int teamNamePosition = -1;
    private int latinoSambaPosition = -1;
    private int latinoChaChaChaPosition = -1;

    private static final Logger logger = Logger.getLogger(FileReader.class.getName());

    public FileReader(InputStream inputStream) throws IOException, InvalidFormatException {

        workbook = WorkbookFactory.create(inputStream);
        prepareHeaders();
    }

    private void prepareHeaders() {
        Sheet sheet = workbook.getSheetAt(0);
        Row headerRow = sheet.getRow(0);

        for (Cell cell : headerRow) {
            handleHeaderCell(cell);
        }
    }

    private void handleHeaderCell(Cell cell) {
        handleGeneralInfoHeaders(cell);
        handleSchoolHeaders(cell);
        handleFreestyleSingleHeaders(cell);
        handleFreestyleDoubleHeaders(cell);
        handleSlowSingleHeaders(cell);
        handleSlowDoubleHeaders(cell);
        handleHipHopHeaders(cell);
        handleTrioHeaders(cell);
        handleTwoDanceHeaders(cell);
        handleBigLittleHeaders(cell);
        handleDiscoKidHeaders(cell);
        handleFunCouplesHeaders(cell);
        handleTheWorldsHeaders(cell);
        handleTeamHeaders(cell);
        handleLatinoHeaders(cell);
    }

    private void handleLatinoHeaders(Cell cell) {
        String heading = cell.getStringCellValue();
        if ("Latino solo Samba".equalsIgnoreCase(heading)) {
            this.latinoSambaPosition = cell.getColumnIndex();
        } else if ("Latino solo Cha cha cha".equalsIgnoreCase(heading)) {
            this.latinoChaChaChaPosition = cell.getColumnIndex();
        }
    }

    private void handleTeamHeaders(Cell cell) {
        String heading = cell.getStringCellValue();
        if ("Påmelding Team".equalsIgnoreCase(heading)
                || "Team".equalsIgnoreCase(heading)) {
            this.registeredForTeamPosition = cell.getColumnIndex();
        } else if ("Navn på team".equalsIgnoreCase(heading)) {
            this.teamNamePosition = cell.getColumnIndex();
        }
    }

    private void handleSlowSingleHeaders(Cell cell) {
        String heading = cell.getStringCellValue();
        if (heading.equalsIgnoreCase("Slowdance singel")) {
            this.slowdanceSingelLevelPosition = cell.getColumnIndex();
        }
    }

    private void handleGeneralInfoHeaders(Cell cell) {
        String heading = cell.getStringCellValue();

        if (heading.equalsIgnoreCase("OrderID")) {
            this.orderIdPosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Etternavn")) {
            this.lastNamePosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Fornavn")) {
            this.firstNamePosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("E-post adresse")) {
            this.emailPosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Alder på konkurransedagen")) {
            this.agePosition = cell.getColumnIndex();
        }
    }

    private void handleSchoolHeaders(Cell cell) {
        String heading = cell.getStringCellValue();

        if (heading.equalsIgnoreCase("Danseskole/klubb")) {
            if (this.schoolPosition1 == -1) {
                this.schoolPosition1 = cell.getColumnIndex();
            } else {
                this.schoolPosition2 = cell.getColumnIndex();
            }
        } else if (heading.equalsIgnoreCase("Navn på danseskole/klubb")) {
            this.schoolPosition2 = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Danseskole/klubb2")) {
            this.schoolPosition2 = cell.getColumnIndex();
        }
    }

    private void handleFreestyleSingleHeaders(Cell cell) {
        String heading = cell.getStringCellValue();
        if (heading.equalsIgnoreCase("Freestyle singel")) {
            this.freestyleSingelLevelPosition = cell.getColumnIndex();
        }
    }

    private void handleFreestyleDoubleHeaders(Cell cell) {
        String heading = cell.getStringCellValue();
        if (heading.equalsIgnoreCase("Påmelding Freestyle dobbel")
                || heading.equalsIgnoreCase("Dobbel freestyle")
                || heading.equalsIgnoreCase("Freestyle dobbel")) {

            this.registerdForFreestyleDoublePosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Ferdighet dobbel")
                || heading.equalsIgnoreCase("Ferdighet freestyle dobbel")
                || heading.equalsIgnoreCase("Ferdighet i freestyle dobbel")) {

            this.doubleLevelPosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Navn på partner")
                || heading.equalsIgnoreCase("Navn på partner i freestyle dobbel")) {

            this.doublePartnerNamePosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Alder på eldste i freestyle dobbel")
                || (heading.equalsIgnoreCase("Alder på eldste i paret")
                && (cell.getColumnIndex() - this.registerdForFreestyleDoublePosition < 4))
                || heading.equalsIgnoreCase("Alder på eldste i paret i freestyle dobbel")) {

            this.doubleAgePosition = cell.getColumnIndex();
        }
    }

    private void handleSlowDoubleHeaders(Cell cell) {
        String heading = cell.getStringCellValue();
        if (heading.equalsIgnoreCase("Påmelding slowdance dobbel")
                || heading.equalsIgnoreCase("Påmelding slow dobbel")
                || heading.equalsIgnoreCase("Slowdance dobbel")) {

            this.registeredForSlowDoublePosition = cell.getColumnIndex();

        } else if (heading.equalsIgnoreCase("Ferdighet Slowdance dobbel")
                || heading.equalsIgnoreCase("Ferdighet slow dobbel")) {

            this.slowDoubleLevelPosition = cell.getColumnIndex();

        } else if (heading.equalsIgnoreCase("Navn på partner Slowdance dobbel")
                || heading.equalsIgnoreCase("Navn slowdance partner")
                || heading.equalsIgnoreCase("Navn på partner i slow dobbel")
                || (heading.equalsIgnoreCase("Navn på partner") && cell.getColumnIndex() - this.registeredForSlowDoublePosition < 3)) {

            this.slowDoublePartnerPosition = cell.getColumnIndex();

        } else if (heading.equalsIgnoreCase("Alder på eldste i slow dobbel")
                || heading.equalsIgnoreCase("Alder på eldste i paret")) {

            this.slowDoubleAgePosition = cell.getColumnIndex();
        }
    }

    private void handleHipHopHeaders(Cell cell) {
        String heading = cell.getStringCellValue();
        if (heading.equalsIgnoreCase("Hip Hop")
                || heading.equalsIgnoreCase("Påmelding Hip Hop")) {
            this.registeredForHipHopPosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Hip Hop ferdighet")
                || heading.equalsIgnoreCase("Ferdighet Hip Hop")) {
            this.hipHopLevelPosition = cell.getColumnIndex();
        }
    }

    private void handleTrioHeaders(Cell cell) {
        String heading = cell.getStringCellValue();
        if (heading.equalsIgnoreCase("Påmelding trio")
                || heading.equalsIgnoreCase("Trio")
                || heading.equalsIgnoreCase("Trio m spott")
                ) {
            this.registeredForTrioPosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Ferdighet trio")) {
            this.trioLevelPosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Navn på partnere trio")
                || heading.equalsIgnoreCase("Navn på partner trio")
                || heading.equalsIgnoreCase("Navn på partner i trio")) {
            this.trioPartnersPosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Alder på eldste i trio")
                || heading.equalsIgnoreCase("Alder på den eldste i trio")) {
            this.trioAgePosition = cell.getColumnIndex();
        }
    }

    private void handleTwoDanceHeaders(Cell cell) {
        String heading = cell.getStringCellValue();
        if (heading.equalsIgnoreCase("2-dans")
                || heading.equalsIgnoreCase("Påmelding 2-Dans")) {
            this.registeredFor2DancePosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Ferdighet 2-dans")
                || heading.equalsIgnoreCase("Ferdighet 2-Dans (høyeste ferdighetsklasse)")) {
            this.level2DancePosition = cell.getColumnIndex();
        }
    }

    private void handleBigLittleHeaders(Cell cell) {
        String heading = cell.getStringCellValue();
        if (heading.equalsIgnoreCase("Påmelding dobbel BIG & LITTLE")) {
            this.bigLittleRegisteredPosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Navn på partner BIG & LITTLE")) {
            this.bigLittlePartnerPosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Antall år mellom yngste og eldste")) {
            this.bigLittleAgeDiffPosition = cell.getColumnIndex();
        }
    }

    private void handleDiscoKidHeaders(Cell cell) {
        String heading = cell.getStringCellValue();
        if (heading.equalsIgnoreCase("Disco Kid Kvalifisering")) {
            this.registeredForDiscoKidPosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Grener Disco Kid")) {
            this.discoKidEventPosition = cell.getColumnIndex();
        }
    }

    private void handleFunCouplesHeaders(Cell cell) {
        String heading = cell.getStringCellValue();
        if (heading.equalsIgnoreCase("Fun couple")) {
            this.registeredForFunCouplePosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Navn på partner fun couple")) {
            this.funCouplePartnerPosition = cell.getColumnIndex();
        }
    }

    private void handleTheWorldsHeaders(Cell cell) {
        String heading = cell.getStringCellValue();
        if (heading.equalsIgnoreCase("Kval til The Worlds")) {
            this.registeredForTheWorldsPosition = cell.getColumnIndex();
        } else if (heading.equalsIgnoreCase("Klasse kval The Worlds")) {
            this.theWorldsLevelPosition = cell.getColumnIndex();
        }
    }

    public List<Dancer> getDancers() {
        List<Dancer> dancers = new ArrayList<Dancer>();

        dancers.add(createJenny());

        Sheet sheet = workbook.getSheetAt(0);
        boolean isHeaderRow = true;
        Long id = 0L;
        for (Row row : sheet) {
            if (!isHeaderRow) {
                Dancer dancer = createDancer(row, id++);
                if (dancer == null) {
                    System.out.println("Could not create dancer" + row.getRowNum());
                    logger.log(Level.FINE, "Could not create dancer", row.getRowNum());
                }
                dancers.add(dancer);
            }
            isHeaderRow = false;
        }

        fixDoubleClasses(dancers);
        fixSlowDoubleAge(dancers);

        return dancers;
    }

    private void fixSlowDoubleAge(List<Dancer> dancers) {
        for (Dancer dancer : dancers) {
            if (!SlowDoubleLevel.DELTAR_IKKE.equals(dancer.getSlowDoubleLevel()) && dancer.getSlowDoubleAge() == -1) {
                findSlowDoubleAge(dancer, dancers);
            }
        }
    }

    private void findSlowDoubleAge(Dancer dancer, List<Dancer> dancers) {
        int dancerAge = dancer.getAge();
        int partnerAge = -1;

        Optional<Dancer> partner = findPartner(dancers, dancer.getSlowDoublePartner());
        if (partner.isPresent()) {
            partnerAge = partner.get().getAge();
        } else {
            logger.warning(dancer.getFirstName() + " " + dancer.getLastName() + " - " + dancer.getAge()
                    + " Setter slow double alder uten å vite partners alder (" + dancer.getSlowDoublePartner() + ")");
        }

        int slowDoubleAge = dancerAge > partnerAge ? dancerAge : partnerAge;
        dancer.setSlowDoubleAge(slowDoubleAge);
    }

    private void fixDoubleClasses(List<Dancer> dancers) {
        for (Dancer dancer : dancers) {
            if (DoubleLevel.UNKNOWN.equals(dancer.getDoubleLevel())) {
                findDoubleLevel(dancer, dancers);
            }
        }
    }

    private void findDoubleLevel(Dancer dancer, List<Dancer> dancers) {
        SingleLevel dancerSingleLevel = dancer.getSingleLevel();
        String partnerName = dancer.getDoublePartner();

        Optional<Dancer> doublePartner = findPartner(dancers, partnerName);

        if (doublePartner.isPresent()) {
            SingleLevel highestSingleLevel = findHighestSingleLevel(dancerSingleLevel, doublePartner.get().getSingleLevel());
            dancer.setDoubleLevel(getDoubleLevelFromSingleLevel(highestSingleLevel));

            logger.info(dancer.getFirstName() + " " + dancer.getLastName() + ", " + dancer.getSingleLevel().getLevel()
                    + " og " + partnerName + ", " + doublePartner.get().getSingleLevel().getLevel()
                    + " settes til dobbelnivå " + dancer.getDoubleLevel().getLevel());
        } else if (!SingleLevel.DELTAR_IKKE.equals(dancerSingleLevel)) {
            logger.info("Fant ingen dobbel-partner for " + dancer.getFirstName() + " " + dancer.getLastName() + " i ferdighetsklasse " + dancerSingleLevel.getLevel());
            dancer.setDoubleLevel(getDoubleLevelFromSingleLevel(dancerSingleLevel));
        } else {
            logger.warning("Kunne ikke sette dobbelnivå for "
                    + dancer.getFirstName() + " " + dancer.getLastName() + ", " + dancer.getSingleLevel().getLevel()
                    + " og " + partnerName + ". Dobbelpartner ikke funnet.");
        }
    }

    private Optional<Dancer> findPartner(List<Dancer> dancers, String partnerName) {
        return dancers.stream()
                    .filter(partner -> partnerName.equalsIgnoreCase(partner.getFirstName() + " " + partner.getLastName()))
                    .findFirst();
    }

    private DoubleLevel getDoubleLevelFromSingleLevel(SingleLevel singleLevel) {
        if (singleLevel.equals(SingleLevel.REKRUTT) || singleLevel.equals(SingleLevel.LITT_OVET)) {
            return DoubleLevel.OPEN;
        } else if (singleLevel.equals(SingleLevel.MESTER)) {
            return DoubleLevel.MESTER;
        } else if (singleLevel.equals(SingleLevel.CHAMP) || singleLevel.equals(SingleLevel.ELITE)) {
            return DoubleLevel.CHAMP_ELITE;
        }

        return DoubleLevel.UNKNOWN;
    }

    private SingleLevel findHighestSingleLevel(SingleLevel dancerSingleLevel, SingleLevel singleLevel) {
        if (dancerSingleLevel.getRating() > singleLevel.getRating()) {
            return dancerSingleLevel;
        }
        return singleLevel;
    }

    private Dancer createJenny() {
        Dancer jenny = new Dancer();
        jenny.setOrderId(9999L);
        jenny.setEmail("lklklklkl");
        jenny.setFirstName("Jenny");
        jenny.setLastName("Løken");
        jenny.setAge(calculateAge(2002, 9, 20));
        jenny.setListedSchool("Danseloftet");
        jenny.setSingleLevel(SingleLevel.ELITE);
        jenny.setSlowLevel(SlowLevel.ELITE);
        jenny.setDoubleLevel(DoubleLevel.DELTAR_IKKE);
        jenny.setSlowDoubleLevel(SlowDoubleLevel.MESTER_CHAMP_ELITE);
        jenny.setSlowDoubleAge(16);
        jenny.setSlowDoublePartner("Julie Lunde");
        jenny.setTrioLevel(TrioLevel.DELTAR_IKKE);
        jenny.setHipHopLevel(HipHopLevel.DELTAR_IKKE);
        jenny.setTwoDanceLevel(TwoDanceLevel.DELTAR_IKKE);
        jenny.setDiscoKidEvent(DiscoKidEvent.DELTAR_IKKE);
        jenny.setTheWorldsLevel(TheWorldsLevel.DELTAR_IKKE);
        return jenny;
    }

    private int calculateAge(int year, int month, int day) {
        LocalDate competitionDay = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        LocalDate birthDay = LocalDate.of(year, month, day);

        return Math.toIntExact(birthDay.until(competitionDay, ChronoUnit.YEARS));
    }

    private Dancer createDancer(Row row, Long id) {
        Dancer dancer = new Dancer();
//        Long orderId = getOrderId(row);
        dancer.setOrderId(id);
        dancer.setLastName(getLastName(row));
        dancer.setFirstName(getFirstName(row));
        dancer.setEmail("fgfhfg");
        dancer.setAge(getAge(row));
        dancer.setListedSchool(getListedSchool(row));
        dancer.setUnlistedSchool(getUnlistedSchool(row));
        dancer.setSingleLevel(getSingleLevel(row));
        dancer.setSlowLevel(getSlowLevel(row));
        dancer.setDoubleLevel(getDoubleLevel(row));
        if (!DoubleLevel.DELTAR_IKKE.equals(dancer.getDoubleLevel())) {
            dancer.setDoubleAge(getDoubleAge(row));
            dancer.setDoublePartner(getDoublePartner(row));
        }
        dancer.setSlowDoubleLevel(getSlowDoubleLevel(row));
        if (!SlowDoubleLevel.DELTAR_IKKE.equals(dancer.getSlowDoubleLevel())) {
            dancer.setSlowDoubleAge(getSlowDoubleAge(row));
            dancer.setSlowDoublePartner(getSlowDoublePartner(row));
        }
        dancer.setTrioLevel(getTrioLevel(row));
        if (!TrioLevel.DELTAR_IKKE.equals(dancer.getTrioLevel())) {
            dancer.setTrioAge(getTrioAge(row));
            dancer.setTrioPartners(getTrioPartners(row));
        }
        dancer.setHipHopLevel(getHipHopLevel(row));

        dancer.setDiscoKidEvent(getDiscoKidEvent(row));

        dancer.setTwoDanceLevel(getTwoDanceLevel(row));

        boolean registeredForBigLittle = isRegisteredForBigLittle(row);
        dancer.setRegisteredForBigLittle(registeredForBigLittle);
        if (registeredForBigLittle) {
            dancer.setBigLittlePartner(getBigLittlePartner(row));
        }

        boolean registeredForFunCouple = isRegisteredForFunCouple(row);
        dancer.setRegisteredForFunCouple(registeredForFunCouple);
        if (registeredForFunCouple) {
            dancer.setFunCouplePartner(getFunCouplePartner(row));
        }

        dancer.setTheWorldsLevel(getTheWorldsLevel(row));

        boolean registeredForTeam = isRegisteredForTeam(row);
        dancer.setRegisteredForTeam(registeredForTeam);
        if (registeredForTeam) {
            dancer.setTeamName(getTeamName(row));
        }

        dancer.setRegisteredForLatinoSamba(isRegisteredForLatinoSamba(row));
        dancer.setRegisteredForLatinoChaChaCha(isRegisteredForLatinoChaChaCha(row));



//        System.out.println("Created dancer " + dancer.toString());
        return dancer;
    }

    private String getTeamName(Row row) {
        String temaName = row.getCell(teamNamePosition, CREATE_NULL_AS_BLANK).getStringCellValue();
        return capitalizeName(temaName);
    }

    private boolean isRegisteredForTeam(Row row) {
        if (registeredForTeamPosition != -1) {
            String value = row.getCell(registeredForTeamPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
            return "ja".equalsIgnoreCase(value);
        }
        return false;
    }

    private boolean isRegisteredForLatinoSamba(Row row) {
        if (latinoSambaPosition != -1) {
            String value = row.getCell(latinoSambaPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
            return "ja".equalsIgnoreCase(value);
        }
        return false;
    }

    private boolean isRegisteredForLatinoChaChaCha(Row row) {
        if (latinoChaChaChaPosition != -1) {
            String value = row.getCell(latinoChaChaChaPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
            return "ja".equalsIgnoreCase(value);
        }
        return false;
    }

    private String getBigLittlePartner(Row row) {
        String partner = row.getCell(bigLittlePartnerPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
        return capitalizeName(partner);
    }

    private String getFunCouplePartner(Row row) {
        String name = row.getCell(funCouplePartnerPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
        return capitalizeName(name);
    }

    private boolean isRegisteredForBigLittle(Row row) {
        if (bigLittleRegisteredPosition != -1) {
            String value = row.getCell(bigLittleRegisteredPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
            if ("Ja".equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    private boolean isRegisteredForFunCouple(Row row) {
        if (registeredForFunCouplePosition != -1) {
            String value = row.getCell(registeredForFunCouplePosition, CREATE_NULL_AS_BLANK).getStringCellValue();
            if ("Fun couple".equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    private Long getOrderId(Row row) {
        Cell cell = row.getCell(orderIdPosition, RETURN_BLANK_AS_NULL);
        double orderId = 0;
        try {
            orderId = cell.getNumericCellValue();
        } catch (IllegalStateException e) {
            String id = cell.getStringCellValue();
            try {
                orderId = Double.parseDouble(id);
            } catch (NumberFormatException nfe) {
                logger.warning("Ugyldig verdi i OrderId '" + id + "' rad " + row.getRowNum());
            }
        }
        return (long) orderId;
    }

    private String getLastName(Row row) {
        return capitalizeName(row.getCell(lastNamePosition, CREATE_NULL_AS_BLANK).getStringCellValue());
    }

    private String getFirstName(Row row) {
        return capitalizeName(row.getCell(firstNamePosition, CREATE_NULL_AS_BLANK).getStringCellValue());
    }

    private String getEmail(Row row) {
        return row.getCell(emailPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
    }

    private int getAge(Row row) {
        Cell cell = row.getCell(agePosition);
        double age = 0;
        try {
            age = cell.getNumericCellValue();
        } catch (IllegalStateException ise) {
            String ageString = cell.getStringCellValue();
            try {
                age = Double.parseDouble(ageString);
            } catch (NumberFormatException nfe) {
                logger.warning("Ugyldig verdi i alder på konkurransedagen '" + ageString + "' rad " + row.getRowNum());
            }
        }
        return (int) age;
    }

    private String getListedSchool(Row row) {
        return row.getCell(schoolPosition1, CREATE_NULL_AS_BLANK).getStringCellValue();
    }

    private String getUnlistedSchool(Row row) {
        return "";
        //String unlistedSchool = row.getCell(schoolPosition2, CREATE_NULL_AS_BLANK).getStringCellValue();
        //return unlistedSchool;
    }

    private SingleLevel getSingleLevel(Row row) {
        String level = row.getCell(freestyleSingelLevelPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
        SingleLevel singleLevel = SingleLevel.fromString(level);
        return singleLevel;
    }

    private SlowLevel getSlowLevel(Row row) {
        String slowLevel = row.getCell(slowdanceSingelLevelPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
        return SlowLevel.fromString(slowLevel);
    }

    private HipHopLevel getHipHopLevel(Row row) {
        if (registeredForHipHopPosition != -1) {
            String registered = row.getCell(registeredForHipHopPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
            if ("Ja".equalsIgnoreCase(registered)) {
                String hipHopLevel = row.getCell(hipHopLevelPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
                return HipHopLevel.fromString(hipHopLevel);
            } else {
                return HipHopLevel.fromString(registered);
            }
        }
        return HipHopLevel.DELTAR_IKKE;
    }

    private DoubleLevel getDoubleLevel(Row row) {
        if (registerdForFreestyleDoublePosition == -1) {
            return DoubleLevel.DELTAR_IKKE;
        }
        String registered = row.getCell(registerdForFreestyleDoublePosition, CREATE_NULL_AS_BLANK).getStringCellValue();
        if (registered.equalsIgnoreCase("Ja")) {
            String doubleLevel = row.getCell(doubleLevelPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
            return DoubleLevel.fromString(doubleLevel);
        } else {
            return DoubleLevel.DELTAR_IKKE;
        }
    }

    private int getDoubleAge(Row row) {
        double doubleAge = 0;
        Cell cell = row.getCell(doubleAgePosition, CREATE_NULL_AS_BLANK);
        try {
            doubleAge = cell.getNumericCellValue();
        } catch (IllegalStateException ise) {
            String ageString = cell.getStringCellValue();
            try {
                doubleAge = Double.parseDouble(ageString);
            } catch (NumberFormatException nfe) {
                logger.warning("Ugyldig verdi i freestyle double alder '" + ageString + "' rad " + row.getRowNum());
            }
        }
        return (int) doubleAge;
    }

    private String getDoublePartner(Row row) {
        String doublePartner = row.getCell(doublePartnerNamePosition, CREATE_NULL_AS_BLANK).getStringCellValue();
        return capitalizeName(doublePartner);
    }

    private SlowDoubleLevel getSlowDoubleLevel(Row row) {
        if (slowDoubleLevelPosition == -1 && registeredForSlowDoublePosition == -1) {
            return SlowDoubleLevel.DELTAR_IKKE;
        } else {
            String slowDoubleLevel = "";
            if (registeredForSlowDoublePosition != -1 && slowDoubleLevelPosition == -1) {
                slowDoubleLevel = row.getCell(registeredForSlowDoublePosition, CREATE_NULL_AS_BLANK).getStringCellValue();
            } else {
                slowDoubleLevel = row.getCell(slowDoubleLevelPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
            }
            return SlowDoubleLevel.fromString(slowDoubleLevel);
        }
    }

    private int getSlowDoubleAge(Row row) {
        if (slowDoubleAgePosition == -1) {
            return 0;
        }
        int slowDoubleAge = -1;
        try {
            double age = row.getCell(slowDoubleAgePosition, CREATE_NULL_AS_BLANK).getNumericCellValue();
            slowDoubleAge = (int) age;
        } catch (IllegalStateException e) {
            String age = row.getCell(slowDoubleAgePosition, CREATE_NULL_AS_BLANK).getStringCellValue();
            if (age.equalsIgnoreCase("8-9 år")) {
                slowDoubleAge = 8;
            } else if (age.equalsIgnoreCase("u/12")) {
                slowDoubleAge = 10;
            } else if (age.equalsIgnoreCase("Over 16 år")) {
                slowDoubleAge = 16;
            } else if (age.equalsIgnoreCase("0/18")) {
                slowDoubleAge = 18;
            } else {
                try {
                    slowDoubleAge = Integer.parseInt(age);
                } catch (NumberFormatException nfe) {
                    logger.warning("Ugyldig verdi i slow double alder '" + age + "' rad " + row.getRowNum());
                }
            }
        }
        return slowDoubleAge;
    }

    private String getSlowDoublePartner(Row row) {
        if (slowDoublePartnerPosition == -1) {
            return "";
        }
        String slowDoublePartner = row.getCell(slowDoublePartnerPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
        return capitalizeName(slowDoublePartner);
    }

    private TwoDanceLevel getTwoDanceLevel(Row row) {
        if (level2DancePosition != -1) {

            String registeredForTwoDance = row.getCell(registeredFor2DancePosition, CREATE_NULL_AS_BLANK).getStringCellValue();
            boolean registered = registeredForTwoDance.equalsIgnoreCase("ja");

            if (registered) {
                String level = row.getCell(level2DancePosition, CREATE_NULL_AS_BLANK).getStringCellValue();
                TwoDanceLevel twoDanceLevel = TwoDanceLevel.fromString(level);

                // Ikke oppgitt nivå - sjekker slow og singel
                if (TwoDanceLevel.DELTAR_IKKE.equals(twoDanceLevel)) {
                    twoDanceLevel = getTwoDanceLevelFromSingleAndSlow(row);
                }

                return twoDanceLevel;
            }
        }

        return TwoDanceLevel.DELTAR_IKKE;
    }

    private String formatForLogging(Row row) {
        String firstName = getFirstName(row);
        String lastName = getLastName(row);
        int age = getAge(row);

        return firstName + " " + lastName + ", " + age + " år.";
    }

    private TwoDanceLevel getTwoDanceLevelFromSingleAndSlow(Row row) {
        SingleLevel singleLevel = getSingleLevel(row);
        SlowLevel slowLevel = getSlowLevel(row);

        if (SingleLevel.CHAMP.equals(singleLevel) || SingleLevel.ELITE.equals(singleLevel)
                || SlowLevel.CHAMP.equals(slowLevel) || SlowLevel.ELITE.equals(slowLevel)) {
            logger.warning(formatForLogging(row) +
                    " Påmeldt todans, men ikke oppgitt nivå. Velger Champ/Elite fra Singel '" +
                    singleLevel.getLevel() + "' og Slow '" + slowLevel.getLevel() + "'");
            return TwoDanceLevel.CHAMP_ELITE;
        } else {
            logger.warning(formatForLogging(row) +
                    " Påmeldt todans, men ikke oppgitt nivå. Velger Åpen klasse fra Singel '" +
                    singleLevel.getLevel() + "' og Slow '" + slowLevel.getLevel() + "'");
            return TwoDanceLevel.OPEN;
        }
    }

    private TrioLevel getTrioLevel(Row row) {
        if (registeredForTrioPosition == -1) {
            return TrioLevel.DELTAR_IKKE;
        }
        String registered = row.getCell(registeredForTrioPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
        if (registered.equalsIgnoreCase("Ja")) {
            if (trioLevelPosition > -1) {
                String level = row.getCell(trioLevelPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
                return TrioLevel.fromString(level);
            } else {
                return TrioLevel.MESTER_CHAMP_ELITE;
            }
        } else {
            return TrioLevel.DELTAR_IKKE;
        }
    }

    private int getTrioAge(Row row) {
        if (trioAgePosition == -1) {
            return 0;
        }
        double trioAge = -1;
        Cell cell = row.getCell(trioAgePosition, CREATE_NULL_AS_BLANK);
        try {
            trioAge = cell.getNumericCellValue();
        } catch (IllegalStateException ise) {
            String ageString = cell.getStringCellValue();
            try {
                if (ageString.equalsIgnoreCase("Under 10 år")) {
                    trioAge = 9;
                } else if (ageString.equalsIgnoreCase("Over 18")) {
                    trioAge = 18;
                } else {
                    trioAge = Double.parseDouble(ageString);
                }
            } catch (NumberFormatException nfe) {
                logger.warning("Ugyldig verdi i trio alder '" + trioAge + "' Setter alder ut fra " + formatForLogging(row));
                trioAge = getAge(row);
            }
        }
        return (int) trioAge;
    }

    private String getTrioPartners(Row row) {
        if (trioPartnersPosition == -1) {
            return "";
        }
        String partners = row.getCell(trioPartnersPosition, CREATE_NULL_AS_BLANK).getStringCellValue();

        return capitalizeName(partners);
    }

    private DiscoKidEvent getDiscoKidEvent(Row row) {
        if (discoKidEventPosition == -1) {
            return DiscoKidEvent.DELTAR_IKKE;
        }

        String event = row.getCell(discoKidEventPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
        return DiscoKidEvent.fromString(event);
    }

    private TheWorldsLevel getTheWorldsLevel(Row row) {
        if (theWorldsLevelPosition == -1) {
            return TheWorldsLevel.DELTAR_IKKE;
        }

        String level = row.getCell(theWorldsLevelPosition, CREATE_NULL_AS_BLANK).getStringCellValue();
        return TheWorldsLevel.fromString(level);
    }

    private String capitalizeName(String name) {
        name = name.toLowerCase();
        return WordUtils.capitalize(name, ' ', '-');
    }
}
