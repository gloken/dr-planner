package no.ndf.konkurranse.rest.groups;

/**
 * Created by gloken on 10.02.2016.
 */
public enum AgeGroupDescription {
    AGE_UNDER_6("u/6 År", 0, 6),
    AGE_UNDER_8("u/8 År", 0, 8),
    AGE_UNDER_10("u/10 År", 0, 10),
    AGE_UNDER_12("u/12 År", 0, 12),
    AGE_UNDER_13("u/13 År", 0, 13),
    AGE_6_8("6-8 År", 6, 8),
    AGE_8_10("8-10 År", 8, 10),
    AGE_10_12("10-12 År", 10, 12),
    AGE_12_14("12-14 År", 12, 14),
    AGE_14_16("14-16 År", 14, 16),
    AGE_16_18("16-18 År", 16, 18),
    AGE_OVER_12("o/12 År", 12, 99),
    AGE_OVER_13("o/13 År", 13, 99),
    AGE_OVER_14("o/14 År", 14, 99),
    AGE_OVER_16("o/16 År", 16, 99),
    AGE_OVER_18("o/18 År", 18, 99)
    ;

    private final String description;
    private final int lowAge;
    private final int highAge;

    AgeGroupDescription(String description, int lowAge, int highAge) {
        this.description = description;
        this.lowAge = lowAge;
        this.highAge = highAge;
    }
}
