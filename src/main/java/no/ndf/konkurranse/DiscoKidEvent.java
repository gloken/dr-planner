package no.ndf.konkurranse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gloken on 11.05.2016.
 */
public enum DiscoKidEvent {
    DELTAR_IKKE("Deltar ikke", Stream.of("Deltar ikke").collect(Collectors.toList())),
    SINGLE_ONLY("Kun singel", Stream.of("Kun singel").collect(Collectors.toList())),
    SLOW_ONLY("Kun slow", Stream.of("Kun slow").collect(Collectors.toList())),
    SINGLE_AND_SLOW("Både singel og slow", Stream.of("Både singel og slow").collect(Collectors.toList()));

    private final String event;
    private final List<String> validKeys;

    DiscoKidEvent(String event, List<String> validKeys) {
        this.event = event;
        this.validKeys = validKeys;
    }

    public String getEvent() {
        return event;
    }

    public static DiscoKidEvent fromString(String event) {
        for (DiscoKidEvent discoKidEvent : values()) {
            for (String validKey : discoKidEvent.validKeys) {
                if (validKey.equalsIgnoreCase(event)) {
                    return discoKidEvent;
                }
            }
        }

        return DELTAR_IKKE;
    }
}
