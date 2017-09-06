package no.ndf.konkurranse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gloken on 08.12.2015.
 */
public enum SlowDoubleLevel {
    DELTAR_IKKE("Deltar ikke", Stream.of("Deltar ikke").collect(Collectors.toList())),
    AAPEN("Åpen klasse (rekrutt/litt øvet)", Stream.of("Åpen klasse (rekrutt/litt øvet)", "Åpen klasse (rekrutt+litt øvet)").collect(Collectors.toList())),
    MESTER_CHAMP_ELITE("Mester/Champ/Elite", Stream.of("Mester/Champ/Elite", "Mester", "Champ/Elite", "Mester/Champ/Elite (spott)").collect(Collectors.toList())),
    ;

    private final String level;
    private final List<String> validKeys;

    SlowDoubleLevel(String level, List<String> validKeys) {
        this.level = level;
        this.validKeys = validKeys;
    }

    public String getLevel() {
        return level;
    }

    public static SlowDoubleLevel fromString(String level) {
        for (SlowDoubleLevel slowDoubleLevel : values()) {
            for (String validKey : slowDoubleLevel.validKeys) {
                if (validKey.equalsIgnoreCase(level)) {
                    return slowDoubleLevel;
                }
            }
        }

        return DELTAR_IKKE;
    }
}
