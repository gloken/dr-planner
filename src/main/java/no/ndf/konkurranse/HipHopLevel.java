package no.ndf.konkurranse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gloken on 08.12.2015.
 */
public enum HipHopLevel {
    DELTAR_IKKE("Deltar ikke", Stream.of("Deltar ikke").collect(Collectors.toList())),
    BEGINNER("nybegynner", Stream.of("Hip Hop nybegynner", "Nybegynner").collect(Collectors.toList())),
    INTERMEDIATE("øvet", Stream.of("Hip Hop øvet", "Øvet").collect(Collectors.toList()));

    private final String level;
    private List<String> validKeys;

    HipHopLevel(String level, List<String> validKeys) {
        this.level = level;
        this.validKeys = validKeys;
    }

    public String getLevel() {
        return level;
    }

    public List<String> getValidKeys() {
        return validKeys;
    }

    public static HipHopLevel fromString(String level) {
        for (HipHopLevel hipHopLevel : HipHopLevel.values()) {
            for (String key : hipHopLevel.getValidKeys()) {
                if (key.equalsIgnoreCase(level)) {
                    return hipHopLevel;
                }
            }
        }

        return HipHopLevel.DELTAR_IKKE;
    }
}
