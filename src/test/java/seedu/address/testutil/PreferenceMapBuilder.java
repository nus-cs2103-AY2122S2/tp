package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.client.PreferenceMap;

/**
 * A utility class to help with building PreferenceMap objects
 */
public class PreferenceMapBuilder {
    public static final String DEFAULT_PREFERENCE_CATEGORY_1 = "Drink";
    public static final String DEFAULT_PREFERENCE_VALUE_1 = "Bubble Tea";
    public static final String DEFAULT_PREFERENCE_CATEGORY_2 = "Food";
    public static final String DEFAULT_PREFERENCE_VALUE_2 = "Sushi";
    public static final String DEFAULT_PREFERENCE_CATEGORY_3 = "Music";
    public static final String DEFAULT_PREFERENCE_VALUE_3 = "Pop";

    private List<String> categoryList;
    private List<String> preferenceList;

    /**
     * Creates a {@code PreferenceMapBuilder} with the default details.
     */
    public PreferenceMapBuilder() {
        categoryList = new ArrayList<>(Arrays.asList(DEFAULT_PREFERENCE_CATEGORY_1, DEFAULT_PREFERENCE_CATEGORY_2,
                DEFAULT_PREFERENCE_CATEGORY_3));
        preferenceList = new ArrayList<>(Arrays.asList(DEFAULT_PREFERENCE_VALUE_1, DEFAULT_PREFERENCE_VALUE_2,
                DEFAULT_PREFERENCE_VALUE_3));
    }

    /**
     * Initializes the PreferenceMap builder with the data of {@code preferenceMapToCopy}
     */
    public PreferenceMapBuilder(PreferenceMap preferenceMapToCopy) {
        categoryList = preferenceMapToCopy.preferences.keySet().stream().collect(Collectors.toList());
        preferenceList = new ArrayList<>();
        for (String category: categoryList) {
            preferenceList.add(preferenceMapToCopy.preferences.get(category));
        }
    }

    /**
     * Adds a {@code Preference} to the {@code PreferenceMap} that we are building
     */
    public PreferenceMapBuilder withPreference(String category, String preference) {
        categoryList.add(category);
        preferenceList.add(preference);
        return this;
    }

    /**
     * Returns a PreferenceMap from the given {@code PreferenceMapBuilder}
     * @return
     */
    public PreferenceMap build() {
        PreferenceMap toReturn = new PreferenceMap();
        for (int i = 0; i < categoryList.size(); i++) {
            toReturn.addPreference(categoryList.get(i), preferenceList.get(i));
        }
        return toReturn;
    }
}
