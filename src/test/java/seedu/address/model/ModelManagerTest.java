package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.ALICE;
import static seedu.address.testutil.TypicalApplicants.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.applicant.ApplicantNamePredicate;
import seedu.address.testutil.HireLahBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new HireLah(), new HireLah(modelManager.getHireLah()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setHireLahFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setHireLahFilePath(Paths.get("new/address/book/file/path"));
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
    public void setHireLahFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setHireLahFilePath(null));
    }

    @Test
    public void setHireLahFilePath_validPath_setsHireLahFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setHireLahFilePath(path);
        assertEquals(path, modelManager.getHireLahFilePath());
    }

    @Test
    public void hasApplicant_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasApplicant(null));
    }

    @Test
    public void hasApplicant_applicantNotInHireLah_returnsFalse() {
        assertFalse(modelManager.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantInHireLah_returnsTrue() {
        modelManager.addApplicant(ALICE);
        assertTrue(modelManager.hasApplicant(ALICE));
    }

    @Test
    public void getFilteredApplicantList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredApplicantList().remove(0));
    }

    @Test
    public void equals() {
        HireLah hireLah = new HireLahBuilder().withApplicant(ALICE).withApplicant(BENSON).build();
        HireLah differentHireLah = new HireLah();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(hireLah, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(hireLah, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different hireLah -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentHireLah, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredApplicantList(new ApplicantNamePredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(hireLah, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setHireLahFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(hireLah, differentUserPrefs)));
    }
}
