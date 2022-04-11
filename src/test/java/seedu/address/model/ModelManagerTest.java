package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.exceptions.UnknownEntityException;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;
import seedu.address.testutil.TAssistBuilder;
import seedu.address.testutil.TypicalAssessments;
import seedu.address.testutil.TypicalClassGroups;
import seedu.address.testutil.TypicalModules;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TAssist(), new TAssist(modelManager.getTAssist()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTAssistFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTAssistFilePath(Paths.get("new/address/book/file/path"));
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
    public void setTAssistFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTAssistFilePath(null));
    }

    @Test
    public void setTAssistFilePath_validPath_setsTAssistFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setTAssistFilePath(path);
        assertEquals(path, modelManager.getTAssistFilePath());
    }

    @Test
    public void hasEntity_nullEntity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEntity(null));
    }

    @Test
    public void deleteEntity_nullEntity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteEntity(null));
    }

    @Test
    public void hasStudent_studentNotInTAssist_returnsFalse() {
        assertFalse(modelManager.hasEntity(ALICE));
    }

    @Test
    public void hasStudent_studentInTAssist_returnsTrue() {
        modelManager.addEntity(ALICE);
        assertTrue(modelManager.hasEntity(ALICE));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void getStudentList() {
        modelManager.addEntity(ALICE);
        modelManager.addEntity(BENSON);
        Predicate<Student> filter = (Student student) -> {
            return student.equals(ALICE);
        };
        modelManager.updateFilteredStudentList(filter);

        // Test filtered list
        ObservableList<Student> filteredStudents = modelManager.getFilteredStudentList();
        assertTrue(filteredStudents.contains(ALICE));
        assertFalse(filteredStudents.contains(BENSON));

        // Test unfiltered list
        ObservableList<Student> unfilteredStudents = modelManager.getUnfilteredStudentList();
        assertTrue(unfilteredStudents.contains(ALICE));
        assertTrue(unfilteredStudents.contains(BENSON));
    }

    @Test
    public void deleteStudent() {
        modelManager.addEntity(ALICE);
        assertTrue(modelManager.hasEntity(ALICE));
        modelManager.deleteEntity(ALICE);
        assertFalse(modelManager.hasEntity(ALICE));
    }

    @Test
    public void hasClassGroup_classGroupNotInTAssist_returnsFalse() {
        assertFalse(modelManager.hasEntity(TypicalClassGroups.CS2101G09));
    }

    @Test
    public void hasClassGroup_classGroupInTAssist_returnsTrue() {
        modelManager.addEntity(TypicalClassGroups.CS2101G09);
        assertTrue(modelManager.hasEntity(TypicalClassGroups.CS2101G09));
    }

    @Test
    public void getFilteredClassGroupList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredClassGroupList().remove(0));
    }

    @Test
    public void getClassGroupList() {
        ClassGroup classGroup1 = TypicalClassGroups.CS2101G09;
        ClassGroup classGroup2 = TypicalClassGroups.CS2103TT13;
        modelManager.addEntity(classGroup1);
        modelManager.addEntity(classGroup2);
        Predicate<ClassGroup> filter = (ClassGroup classGroup) -> {
            return classGroup.equals(classGroup1);
        };
        modelManager.updateFilteredClassGroupList(filter);

        // Test filtered list
        ObservableList<ClassGroup> filteredClassGroups = modelManager.getFilteredClassGroupList();
        assertTrue(filteredClassGroups.contains(classGroup1));
        assertFalse(filteredClassGroups.contains(classGroup2));

        // Test unfiltered list
        ObservableList<ClassGroup> unfilteredClassGroups = modelManager.getUnfilteredClassGroupList();
        assertTrue(unfilteredClassGroups.contains(classGroup1));
        assertTrue(unfilteredClassGroups.contains(classGroup2));
    }

    @Test
    public void deleteClassGroup() {
        ClassGroup classGroup = TypicalClassGroups.CS2101G09;
        modelManager.addEntity(classGroup);
        assertTrue(modelManager.hasEntity(classGroup));
        modelManager.deleteEntity(classGroup);
        assertFalse(modelManager.hasEntity(classGroup));
    }

    @Test
    public void getFilteredModulesList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredModuleList().remove(0));
    }

    @Test
    public void hasModule_moduleNotInTAssist_returnsFalse() {
        TaModule module = TypicalModules.getModule(TypicalModules.CS2101);
        assertFalse(modelManager.hasEntity(module));
    }

    @Test
    public void hasModule_moduleInTAssist_returnsTrue() {
        TaModule module = TypicalModules.getModule(TypicalModules.CS2101);
        modelManager.addEntity(module);
        assertTrue(modelManager.hasEntity(module));
    }

    @Test
    public void getModuleList() {
        TaModule module1 = TypicalModules.getModule(TypicalModules.CS2101);
        TaModule module2 = TypicalModules.getModule(TypicalModules.CS2103T);
        modelManager.addEntity(module1);
        modelManager.addEntity(module2);
        Predicate<TaModule> filter = (TaModule module) -> {
            return module.equals(module1);
        };
        modelManager.updateFilteredModuleList(filter);

        // Test filtered list
        ObservableList<TaModule> filteredModules = modelManager.getFilteredModuleList();
        assertTrue(filteredModules.contains(module1));
        assertFalse(filteredModules.contains(module2));

        // Test unfiltered list
        ObservableList<TaModule> unfilteredModules = modelManager.getUnfilteredModuleList();
        assertTrue(unfilteredModules.contains(module1));
        assertTrue(unfilteredModules.contains(module2));
    }

    @Test
    public void deleteModule() {
        TaModule module = TypicalModules.getModule(TypicalModules.CS2101);
        modelManager.addEntity(module);
        assertTrue(modelManager.hasEntity(module));
        modelManager.deleteEntity(module);
        assertFalse(modelManager.hasEntity(module));
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredModuleList().remove(0));
    }

    @Test
    public void getFilteredAssessmentsList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredAssessmentList().remove(0));
    }

    @Test
    public void hasModule_assessmentNotInTAssist_returnsFalse() {
        assertFalse(modelManager.hasEntity(TypicalAssessments.CS2101_PARTICIPATION_WITH_ATTEMPT));
    }

    @Test
    public void hasAssessment_moduleInTAssist_returnsTrue() {
        modelManager.addEntity(TypicalAssessments.CS2101_PARTICIPATION_WITH_ATTEMPT);
        assertTrue(modelManager.hasEntity(TypicalAssessments.CS2101_PARTICIPATION_WITH_ATTEMPT));
    }

    @Test
    public void getFilteredAssessmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredAssessmentList().remove(0));
    }

    @Test
    public void getAssessmentList() {
        Assessment assessment1 = TypicalAssessments.CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT;
        Assessment assessment2 = TypicalAssessments.CS2040_LAB2_NO_ATTEMPT;
        modelManager.addEntity(assessment1);
        modelManager.addEntity(assessment2);
        Predicate<Assessment> filter = (Assessment assessment) -> {
            return assessment.equals(assessment1);
        };
        modelManager.updateFilteredAssessmentList(filter);

        // Test filtered list
        ObservableList<Assessment> filteredAssessments = modelManager.getFilteredAssessmentList();
        assertTrue(filteredAssessments.contains(assessment1));
        assertFalse(filteredAssessments.contains(assessment2));

        // Test unfiltered list
        ObservableList<Assessment> unfilteredAssessments = modelManager.getUnfilteredAssessmentList();
        assertTrue(unfilteredAssessments.contains(assessment1));
        assertTrue(unfilteredAssessments.contains(assessment2));
    }

    @Test
    public void deleteAssessment() {
        Assessment assessment = TypicalAssessments.CS2040_LAB2_NO_ATTEMPT;
        modelManager.addEntity(assessment);
        assertTrue(modelManager.hasEntity(assessment));
        modelManager.deleteEntity(assessment);
        assertFalse(modelManager.hasEntity(assessment));
    }

    @Test
    public void equals() {
        TAssist tassist = new TAssistBuilder().withStudent(ALICE).withStudent(BENSON).build();
        TAssist differentTAssist = new TAssist();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(tassist, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(tassist, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different tassist -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTAssist, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(tassist, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStudentList(PREDICATE_SHOW_ALL);

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredClassGroupList(PREDICATE_SHOW_ALL);

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredModuleList(PREDICATE_SHOW_ALL);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTAssistFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(tassist, differentUserPrefs)));
    }
}
