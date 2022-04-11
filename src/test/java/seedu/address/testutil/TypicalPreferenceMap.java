package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.client.PreferenceMap;

/**
 * A utility class containing a list of {@code PreferenceMap} objects to be used in tests
 */
public class TypicalPreferenceMap {

    public static final PreferenceMap SPORTS = new PreferenceMapBuilder()
            .withPreference("Sports", "Tennis")
            .build();

    public static final PreferenceMap SNACK = new PreferenceMapBuilder()
            .withPreference("Snack", "Sushi")
            .build();


    private TypicalPreferenceMap() {} // prevents instantiation

    public static List<PreferenceMap> getTypicalPreferenceMaps() {
        return new ArrayList<>(Arrays.asList(SPORTS, SNACK));
    }

}
