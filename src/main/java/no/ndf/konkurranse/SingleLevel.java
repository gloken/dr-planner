package no.ndf.konkurranse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gloken on 08.12.2015.
 */
public enum SingleLevel {
    DELTAR_IKKE("Deltar ikke", Stream.of("Deltar ikke").collect(Collectors.toList())),
    REKRUTT("Rekrutt", Stream.of("Rekrutt").collect(Collectors.toList())),
    LITT_OVET("Litt øvet", Stream.of("Litt øvet").collect(Collectors.toList())),
    MESTER("Mester", Stream.of("Mester").collect(Collectors.toList())),
    CHAMP("Champ", Stream.of("Champ").collect(Collectors.toList())),
    ELITE("Elite", Stream.of("Elite").collect(Collectors.toList()))
    ;

    private final String level;
    private final List<String> validKeys;

    SingleLevel(String level, List<String> validKeys) {
        this.level = level;
        this.validKeys = validKeys;
    }

    public String getLevel() {
        return level;
    }

    public List<String> getValidKeys() {
        return validKeys;
    }

    public static SingleLevel fromString(String level) {
        for (SingleLevel singleLevel : SingleLevel.values()) {
            for (String key : singleLevel.getValidKeys()) {
                if (key.equalsIgnoreCase(level)) {
                    return singleLevel;
                }
            }
        }

        return SingleLevel.DELTAR_IKKE;
    }
}
