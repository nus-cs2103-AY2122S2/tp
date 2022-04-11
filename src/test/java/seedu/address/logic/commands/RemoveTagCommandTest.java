package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Cca;
import seedu.address.model.person.Education;
import seedu.address.model.person.Internship;
import seedu.address.model.person.Module;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

class RemoveTagCommandTest {
    private static final String EDUCATION_STRING = "NUS";
    private static final String EDUCATION_STRING_2 = "NTU";
    private static final String INTERNSHIP_STRING = "Twitter";
    private static final String INTERNSHIP_STRING_2 = "Google";
    private static final String MODULE_STRING = "CS2040S";
    private static final String MODULE_STRING_2 = "CS3230";
    private static final String CCA_STRING = "Greyhats";
    private static final String CCA_STRING_2 = "NUSHackers";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final List<Tag> emptyList = new ArrayList<>();
    private final List<Tag> eduList = Arrays.asList(new Education(EDUCATION_STRING));
    private final List<Tag> eduList2 = Arrays.asList(new Education(EDUCATION_STRING_2));
    private final List<Tag> internshipList = Arrays.asList(new Internship(INTERNSHIP_STRING));
    private final List<Tag> internshipList2 = Arrays.asList(new Internship(INTERNSHIP_STRING_2));
    private final List<Tag> moduleList = Arrays.asList(new Module(MODULE_STRING));
    private final List<Tag> moduleList2 = Arrays.asList(new Module(MODULE_STRING_2));
    private final List<Tag> ccaList = Arrays.asList(new Cca(CCA_STRING));
    private final List<Tag> ccaList2 = Arrays.asList(new Cca(CCA_STRING_2));

    @Test
    void execute_validIndexUnfilteredList_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        RemoveTagCommand removeTagCommand =
                new RemoveTagCommand(INDEX_FIRST_PERSON, eduList, emptyList, emptyList, emptyList);
        String expectedMessage = String.format(RemoveTagCommand.MESSAGE_ARGUMENTS,
                personToEdit.getName(),
                "[]", personToEdit.getInternships(), personToEdit.getModules(), personToEdit.getCcas());

        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(),
                emptyList, personToEdit.getInternships(), personToEdit.getModules(), personToEdit.getCcas());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(removeTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        RemoveTagCommand firstCommand = new RemoveTagCommand(INDEX_FIRST_PERSON,
                eduList, internshipList, moduleList, ccaList);
        RemoveTagCommand secondCommand = new RemoveTagCommand(INDEX_FIRST_PERSON,
                eduList2, internshipList2, moduleList2, ccaList2);
        RemoveTagCommand thirdCommand = new RemoveTagCommand(INDEX_SECOND_PERSON,
                eduList, internshipList, moduleList, ccaList);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        RemoveTagCommand firstCommandCopy = new RemoveTagCommand(INDEX_FIRST_PERSON,
                eduList, internshipList, moduleList, ccaList);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommand.equals(thirdCommand));

        // same person, different tags -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
