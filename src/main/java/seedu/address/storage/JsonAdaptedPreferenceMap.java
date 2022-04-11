package seedu.address.storage;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.client.PreferenceMap;


public class JsonAdaptedPreferenceMap {
    private final HashMap<String, String> preferenceMap;

    /**
     * Constructs a {@code JsonAdaptedPreferenceMap} with the given {@code HashMap}
     */
    @JsonCreator
    JsonAdaptedPreferenceMap(@JsonProperty("preferenceMap") HashMap<String, String> preferenceMap) {
        this.preferenceMap = preferenceMap;
    }

    public JsonAdaptedPreferenceMap(PreferenceMap source) {
        preferenceMap = source.getMap();
    }

    public PreferenceMap toModelType() {
        return new PreferenceMap().addAllPreferences(new PreferenceMap(preferenceMap));
    }


}
