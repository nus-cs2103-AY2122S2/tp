package woofareyou.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static woofareyou.model.Model.PREDICATE_SHOW_ALL_PETS;
import static woofareyou.testutil.Assert.assertThrows;
import static woofareyou.testutil.TypicalPets.BOBA;
import static woofareyou.testutil.TypicalPets.PIZZA;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import woofareyou.commons.core.GuiSettings;
import woofareyou.model.pet.NameContainsKeywordsPredicate;
import woofareyou.testutil.PetBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new PetBook(), new PetBook(modelManager.getPetBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setPetBookFilePath(Paths.get("pet/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setPetBookFilePath(Paths.get("new/pet/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setPetBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPetBookFilePath(null));
    }

    @Test
    public void setPetBookFilePath_validPath_setsPetBookFilePath() {
        Path path = Paths.get("pet/book/file/path");
        modelManager.setPetBookFilePath(path);
        assertEquals(path, modelManager.getPetBookFilePath());
    }

    @Test
    public void hasPet_nullPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPet(null));
    }

    @Test
    public void hasPet_petNotInWoofAreYou_returnsFalse() {
        assertFalse(modelManager.hasPet(BOBA));
    }

    @Test
    public void hasPet_petInWoofAreYou_returnsTrue() {
        modelManager.addPet(BOBA);
        assertTrue(modelManager.hasPet(BOBA));
    }

    @Test
    public void getFilteredPetList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPetList().remove(0));
    }

    @Test
    public void equals() {
        PetBook petBook = new PetBookBuilder().withPet(BOBA).withPet(PIZZA).build();
        PetBook differentPetBook = new PetBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(petBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(petBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different petBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentPetBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = BOBA.getName().fullName.split("\\s+");
        modelManager.updateFilteredPetList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(petBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPetList(PREDICATE_SHOW_ALL_PETS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setPetBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(petBook, differentUserPrefs)));
    }
}
