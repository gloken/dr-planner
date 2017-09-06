package no.ndf.konkurranse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gloken on 15.05.2016.
 */
public enum TwoDanceLevel {
    DELTAR_IKKE("Deltar ikke",Stream.of("Deltar ikke").collect(Collectors.toList())),
    OPEN("Åpen/Mester", Stream.of("Åpen klasse (rekrutt/litt øvet/mester)").collect(Collectors.toList())),
    CHAMP_ELITE("Champ/Elite", Stream.of("Champ/elite").collect(Collectors.toList()))
    ;

    private final String level;
    private final List<String> validKeys;

    TwoDanceLevel(String level, List<String> validKeys) {
        this.level = level;
        this.validKeys = validKeys;
    }

    public String getLevel() {
        return level;
    }

    public List<String> getValidKeys() {
        return validKeys;
    }

    public static TwoDanceLevel fromString(String level) {
        for (TwoDanceLevel twoDanceLevel : TwoDanceLevel.values()) {
            for (String key : twoDanceLevel.getValidKeys()) {
                if (key.equalsIgnoreCase(level)) {
                    return twoDanceLevel;
                }
            }
        }

        return TwoDanceLevel.DELTAR_IKKE;
    }
}
