package no.ndf.konkurranse.rest;

import no.ndf.konkurranse.*;

import java.util.Objects;

/**
 * Created by gloken on 19.11.2015.
 */
public class Dancer {
    public static final String UNLISTED_SCHOOL_SELECTED = "Annet, ikke oppført";

    private Long orderId;
    private String firstName;
    private String lastName;
    private int age;
    private String listedSchool;
    private String unlistedSchool;
    private SingleLevel singleLevel;
    private SlowLevel slowLevel;
    private DoubleLevel doubleLevel;
    private String doublePartner;
    private int doubleAge = -1;
    private String slowDoublePartner;
    private SlowDoubleLevel slowDoubleLevel;
    private int slowDoubleAge = -1;
    private String email;
    private HipHopLevel hipHopLevel;
    private TrioLevel trioLevel;
    private int trioAge;
    private String trioPartners;
    private DiscoKidEvent discoKidEvent;
    private TwoDanceLevel twoDanceLevel;
    private boolean registeredForFunCouple;
    private String funCouplePartner;
    private boolean registeredForBigLittle;
    private String bigLittlePartner;
    private TheWorldsLevel theWorldsLevel;
    private boolean registeredForTeam;
    private String teamName;

    public Dancer() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getListedSchool() {
        return listedSchool;
    }

    public void setListedSchool(String registeredSchool) {
        this.listedSchool = registeredSchool;
    }

    public String getUnlistedSchool() {
        return unlistedSchool;
    }

    public void setUnlistedSchool(String unregisteredSchool) {
        this.unlistedSchool = unregisteredSchool;
    }

    public SingleLevel getSingleLevel() {
        return singleLevel;
    }

    public void setSingleLevel(SingleLevel singleLevel) {
        this.singleLevel = singleLevel;
    }

    public SlowLevel getSlowLevel() {
        return slowLevel;
    }

    public void setSlowLevel(SlowLevel slowLevel) {
        this.slowLevel = slowLevel;
    }

    public DoubleLevel getDoubleLevel() {
        return doubleLevel;
    }

    public void setDoubleLevel(DoubleLevel doubleLevel) {
        this.doubleLevel = doubleLevel;
    }

    public String getDoublePartner() {
        return doublePartner;
    }

    public void setDoublePartner(String doublePartner) {
        this.doublePartner = doublePartner;
    }

    public int getDoubleAge() {
        return doubleAge;
    }

    public void setDoubleAge(int doubleAge) {
        this.doubleAge = doubleAge;
    }

    public String getSlowDoublePartner() {
        return slowDoublePartner;
    }

    public void setSlowDoublePartner(String slowDoublePartner) {
        this.slowDoublePartner = slowDoublePartner;
    }

    public SlowDoubleLevel getSlowDoubleLevel() {
        return slowDoubleLevel;
    }

    public void setSlowDoubleLevel(SlowDoubleLevel slowDoubleLevel) {
        this.slowDoubleLevel = slowDoubleLevel;
    }

    public int getSlowDoubleAge() {
        return slowDoubleAge;
    }

    public void setSlowDoubleAge(int oldestSlowDoubleAge) {
        this.slowDoubleAge = oldestSlowDoubleAge;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public boolean belongsToListedSchool() {
        return !(unlistedSchool != null && unlistedSchool.length() > 0);
        //return !(UNLISTED_SCHOOL_SELECTED.equals(listedSchool) || listedSchool == null || listedSchool.length() < 1);
    }

    public String getDancerId() {
        return orderId + firstName + lastName;
    }

    public void setHipHopLevel(HipHopLevel hipHopLevel) {
        this.hipHopLevel = hipHopLevel;
    }

    public HipHopLevel getHipHopLevel() {
        return hipHopLevel;
    }

    public void setTrioLevel(TrioLevel trioLevel) {
        this.trioLevel = trioLevel;
    }

    public TrioLevel getTrioLevel() {
        return trioLevel;
    }

    public void setTrioAge(int trioAge) {
        this.trioAge = trioAge;
    }

    public int getTrioAge() {
        return trioAge;
    }

    public void setTrioPartners(String trioPartners) {
        this.trioPartners = trioPartners;
    }

    public String getTrioPartners() {
        return trioPartners;
    }

    public void setDiscoKidEvent(DiscoKidEvent discoKidEvent) {
        this.discoKidEvent = discoKidEvent;
    }

    public DiscoKidEvent getDiscoKidEvent() {
        return discoKidEvent;
    }

    public TwoDanceLevel getTwoDanceLevel() {
        return twoDanceLevel;
    }

    public void setTwoDanceLevel(TwoDanceLevel twoDanceLevel) {
        this.twoDanceLevel = twoDanceLevel;
    }

    public void setRegisteredForFunCouple(boolean registeredForFunCouple) {
        this.registeredForFunCouple = registeredForFunCouple;
    }

    public boolean isRegisteredForFunCouple() {
        return registeredForFunCouple;
    }

    public void setFunCouplePartner(String funCouplePartner) {
        this.funCouplePartner = funCouplePartner;
    }

    public String getFunCouplePartner() {
        return funCouplePartner;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", "+ age + " " + listedSchool;
    }

    public void setRegisteredForBigLittle(boolean registeredForBigLittle) {
        this.registeredForBigLittle = registeredForBigLittle;
    }

    public boolean isRegisteredForBigLittle() {
        return registeredForBigLittle;
    }

    public void setBigLittlePartner(String bigLittlePartner) {
        this.bigLittlePartner = bigLittlePartner;
    }

    public String getBigLittlePartner() {
        return bigLittlePartner;
    }

    public TheWorldsLevel getTheWorldsLevel() {
        return theWorldsLevel;
    }

    public void setTheWorldsLevel(TheWorldsLevel theWorldsLevel) {
        this.theWorldsLevel = theWorldsLevel;
    }

    public void setRegisteredForTeam(boolean registeredForTeam) {
        this.registeredForTeam = registeredForTeam;
    }

    public boolean isRegisteredForTeam() {
        return registeredForTeam;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }
}
