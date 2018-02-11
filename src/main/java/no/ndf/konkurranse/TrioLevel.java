package no.ndf.konkurranse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gloken on 06.04.2016.
 */
public enum TrioLevel {
    DELTAR_IKKE("Deltar ikke", Stream.of("Deltar ikke").collect(Collectors.toList())),
    MESTER_CHAMP_ELITE("Mester/Champ/Elite", Stream.of("Mester/Champ/Elite", "Mester, Champ, Elite", "Mester/Champ/Elite m spott").collect(Collectors.toList()));

    private final String level;
    private final List<String> validKeys;

    TrioLevel(String level, List<String> validKeys) {
        this.level = level;
        this.validKeys = validKeys;
    }

    public String getLevel() {
        return level;
    }

    public List<String> getValidKeys() {
        return validKeys;
    }

    public static TrioLevel fromString(String level) {
        for (TrioLevel trioLevel : TrioLevel.values()) {
            for (String key : trioLevel.getValidKeys()) {
                if (key.equalsIgnoreCase(level)) {
                    return trioLevel;
                }
            }
        }

        return TrioLevel.DELTAR_IKKE;
    }
}
