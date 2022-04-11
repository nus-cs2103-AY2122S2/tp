package seedu.address.model.client;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.exceptions.InvalidPreferenceKeyException;

public class PreferenceMap {

    // static values
    public static final String EMPTY_PREFERENCE_LIST_STRING = "The client has no preferences.";
    public final HashMap<String, String> preferences;

    /**
     * Construct a {@code PreferenceMap}.
     */
    public PreferenceMap() {
        this.preferences = new HashMap<>();
    }

    /**
     * Constructs a {@code PreferenceMap} with the given {@code HashMap} of preferences
     * @param preferences
     */
    public PreferenceMap(HashMap<String, String> preferences) {
        requireNonNull(preferences);
        this.preferences = preferences;
    }

    /**
     * Adds the preference to the specified {@code Client}
     * @param key
     * @param value
     */
    public void addPreference(String key, String value) {
        requireNonNull(key, value);
        preferences.put(key, value);
    }

    /**
     * Deletes the preference of the specified {@code key} from the Client
     * @param key - the key to be deleted from the client's preferences
     *
     */
    public void deletePreference(String key) throws InvalidPreferenceKeyException {
        requireNonNull(key);
        if (!preferences.containsKey(key)) {
            throw new InvalidPreferenceKeyException();
        }
        preferences.remove(key);
    }

    public HashMap<String, String> getMap() {
        return this.preferences;
    }

    public ObservableList<Map.Entry<String, String>> asObservableList() {
        return FXCollections.observableArrayList(new ArrayList<>(preferences.entrySet()));
    }

    /**
     * Add all the preferences in the previous PreferenceMap to this PreferenceMap
     */
    public PreferenceMap addAllPreferences(PreferenceMap preferenceMap) {
        this.preferences.putAll(preferenceMap.preferences);
        return this;
    }

    @Override
    public String toString() {
        String result = "";

        if (preferences.isEmpty()) {
            return EMPTY_PREFERENCE_LIST_STRING;
        }

        for (String str: preferences.keySet()) {
            result += String.format("%s: %s\n", str, preferences.get(str));
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PreferenceMap
            && preferences.equals(((PreferenceMap) other).preferences));
    }

    @Override
    public int hashCode() {
        return preferences.hashCode();
    }

    public int size() {
        return preferences.size();
    }
}
