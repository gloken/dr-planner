package no.ndf.konkurranse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gloken on 08.12.2015.
 */
public enum TheWorldsLevel {
    DELTAR_IKKE("Deltar ikke", Stream.of("Deltar ikke").collect(Collectors.toList())),
    OPEN("Litt øvet/Mester/Champ", Stream.of("Litt øvet/Mester/Champ").collect(Collectors.toList())),
    ELITE("Elite", Stream.of("Elite").collect(Collectors.toList()))
    ;

    private final String level;
    private final List<String> validKeys;

    TheWorldsLevel(String level, List<String> validKeys) {
        this.level = level;
        this.validKeys = validKeys;
    }

    public String getLevel() {
        return level;
    }

    public List<String> getValidKeys() {
        return validKeys;
    }

    public static TheWorldsLevel fromString(String level) {
        for (TheWorldsLevel theWorldsLevel : TheWorldsLevel.values()) {
            for (String key : theWorldsLevel.getValidKeys()) {
                if (key.equalsIgnoreCase(level)) {
                    return theWorldsLevel;
                }
            }
        }

        return TheWorldsLevel.DELTAR_IKKE;
    }
}
