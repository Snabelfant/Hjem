package dag.hjem.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dag on 23.02.2015.
 */
public enum TimeDirection {
    AFTER("Avreise"),
    BEFORE("Ankomst"),;
    private final String name;

    private TimeDirection(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public static List<TimeDirection> asList() {
        return Arrays.asList(AFTER, BEFORE);
    }

}
