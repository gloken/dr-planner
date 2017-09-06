package no.ndf.konkurranse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gloken on 08.12.2015.
 */
public enum SlowLevel {
    DELTAR_IKKE("Deltar ikke", Stream.of("Deltar ikke").collect(Collectors.toList())),
    OPEN("Åpen klasse", Stream.of("Åpen klasse", "Åpen klasse (rekrutt/litt øvet)").collect(Collectors.toList())),
    MESTER("Mester", Stream.of("Mester").collect(Collectors.toList())),
    CHAMP("Champ", Stream.of("Champ").collect(Collectors.toList())),
    ELITE("Elite", Stream.of("Elite").collect(Collectors.toList()));

    private final String level;
    private List<String> validKeys;

    SlowLevel(String level, List<String> validKeys) {
        this.level = level;
        this.validKeys = validKeys;
    }

    public String getLevel() {
        return level;
    }

    public List<String> getValidKeys() {
        return validKeys;
    }

    public static SlowLevel fromString(String level) {
        for (SlowLevel slowLevel : SlowLevel.values()) {
            for (String key : slowLevel.getValidKeys()) {
                if (key.equalsIgnoreCase(level)) {
                    return slowLevel;
                }
            }
        }

        return SlowLevel.DELTAR_IKKE;
    }
}
