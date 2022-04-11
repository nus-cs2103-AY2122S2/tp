package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PREFERENCE_CATEGORY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_CATEGORY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_VALUE;
import static seedu.address.testutil.PreferenceMapBuilder.DEFAULT_PREFERENCE_CATEGORY_1;
import static seedu.address.testutil.TypicalPreferenceMap.SNACK;
import static seedu.address.testutil.TypicalPreferenceMap.SPORTS;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.exceptions.InvalidPreferenceKeyException;
import seedu.address.testutil.PreferenceMapBuilder;

public class PreferenceMapTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PreferenceMap(null));
    }

    @Test
    public void constructor_empty_initializesEmptyPreferenceMap() {
        PreferenceMap emptyPreferenceMap = new PreferenceMap();
        assertTrue(emptyPreferenceMap.preferences.size() == 0);
    }

    @Test
    public void getMap() {
        PreferenceMap emptyPreferenceMap = new PreferenceMap();
        assertTrue(emptyPreferenceMap.getMap() == emptyPreferenceMap.preferences);
    }

    @Test
    public void test_hashCode() {
        PreferenceMap first = new PreferenceMap();
        PreferenceMap second = new PreferenceMap();
        assertTrue(first.hashCode() == second.hashCode());
    }

    @Test
    public void size() {
        PreferenceMap testSize = new PreferenceMap();
        assertTrue(testSize.size() == testSize.preferences.size());

        testSize.preferences.put(VALID_PREFERENCE_CATEGORY, VALID_PREFERENCE_VALUE);
        assertTrue(testSize.size() == testSize.preferences.size());
    }

    @Test
    public void addPreference() {
        PreferenceMap preferenceMapToAdd = new PreferenceMap();
        preferenceMapToAdd.addPreference(VALID_PREFERENCE_CATEGORY, VALID_PREFERENCE_VALUE);
        assertTrue(preferenceMapToAdd.preferences.get(VALID_PREFERENCE_CATEGORY).equals(VALID_PREFERENCE_VALUE));
    }

    @Test
    public void deletePreference() {
        PreferenceMap preferenceMapToDelete = new PreferenceMapBuilder().build();
        preferenceMapToDelete.deletePreference(DEFAULT_PREFERENCE_CATEGORY_1);
        assertFalse(preferenceMapToDelete.preferences.containsKey(DEFAULT_PREFERENCE_CATEGORY_1));

        assertThrows(InvalidPreferenceKeyException.class, () -> preferenceMapToDelete
                .deletePreference(INVALID_PREFERENCE_CATEGORY));
    }



    @Test
    public void test_toString() {
        // same values -> returns true;
        PreferenceMap preferenceMapCopy = new PreferenceMapBuilder(SNACK).build();
        assertTrue(SNACK.toString().equals(preferenceMapCopy.toString()));

        // same object -> returns true
        assertTrue(SNACK.toString().equals(SNACK.toString()));

        // different preference map -> returns false
        assertFalse(SNACK.toString().equals(SPORTS.toString()));

        // same category, different value -> returns false
        PreferenceMap editedPreferenceMap = new PreferenceMapBuilder()
                .withPreference("Sports", "Basketball")
                .build();
        assertFalse(SPORTS.toString().equals(editedPreferenceMap.toString()));

        // same value, different category -> returns false
        editedPreferenceMap = new PreferenceMapBuilder()
                .withPreference("Sport", "Basketball")
                .build();
        assertFalse(SPORTS.toString().equals(editedPreferenceMap.toString()));

        // different number of preferences -> returns false
        editedPreferenceMap = new PreferenceMapBuilder()
                .withPreference("Drink", "Beer")
                .withPreference("Sports", "Tennis")
                .build();
        assertFalse(SPORTS.toString().equals(editedPreferenceMap.toString()));

        // empty string -> "The client has no preferences"
        PreferenceMap emptyPreferenceMap = new PreferenceMap();
        System.out.println(emptyPreferenceMap.toString());
        assertTrue(emptyPreferenceMap.toString().equals(PreferenceMap.EMPTY_PREFERENCE_LIST_STRING));
    }

    @Test
    public void equals() {
        // same values -> returns true
        PreferenceMap preferenceMapCopy = new PreferenceMapBuilder(SNACK).build();
        assertTrue(SNACK.equals(preferenceMapCopy));

        // same object -> returns true
        assertTrue(SNACK.equals(SNACK));

        // null -> returns false
        assertFalse(SNACK.equals(null));

        // different type -> returns false
        assertFalse(SNACK.equals(5));

        // different preference map -> returns false
        assertFalse(SNACK.equals(SPORTS));

        // same category, different value -> returns false
        PreferenceMap editedPreferenceMap = new PreferenceMapBuilder()
                .withPreference("Sports", "Basketball")
                .build();
        assertFalse(SPORTS.equals(editedPreferenceMap));

        // same value, different category -> returns false
        editedPreferenceMap = new PreferenceMapBuilder()
                .withPreference("Sport", "Basketball")
                .build();
        assertFalse(SPORTS.equals(editedPreferenceMap));

        // different number of preferences -> returns false
        editedPreferenceMap = new PreferenceMapBuilder()
                .withPreference("Drink", "Beer")
                .withPreference("Sports", "Tennis")
                .build();
        assertFalse(SPORTS.equals(editedPreferenceMap));
    }
}
