package no.ndf.konkurranse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gloken on 08.12.2015.
 */
public enum DoubleLevel {
    DELTAR_IKKE("Deltar ikke", Stream.of("Deltar ikke").collect(Collectors.toList())),
    OPEN("Åpen klasse", Stream.of("Åpen klasse", "Åpen klasse (rekrutt/litt øvet)", "Åpen klasse (rekrutt+litt øvet)").collect(Collectors.toList())),
    MESTER("Mester", Stream.of("Mester").collect(Collectors.toList())),
    CHAMP_ELITE("Champ/Elite", Stream.of("Champ/Elite", "Champ", "Elite", "Champion/Elite").collect(Collectors.toList())),
    UNKNOWN("Ikke oppgitt ferdighetsklasse", Stream.of("-").collect(Collectors.toList()));

    private final String level;
    private List<String> validKeys;

    DoubleLevel(String level, List<String> validKeys) {
        this.level = level;
        this.validKeys = validKeys;
    }

    public List<String> getValidKeys() {
        return validKeys;
    }

    public String getLevel() {
        return level;
    }

    public static DoubleLevel fromString(String level) {
        for (DoubleLevel doubleLevel : values()) {
            for (String key : doubleLevel.getValidKeys()) {
                if (key.equalsIgnoreCase(level)) {
                    return doubleLevel;
                }
            }
        }

        return UNKNOWN;
    }
}
