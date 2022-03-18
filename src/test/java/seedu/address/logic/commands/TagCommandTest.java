package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Cca;
import seedu.address.model.person.Education;
import seedu.address.model.person.Internship;
import seedu.address.model.person.Module;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class TagCommandTest {
    private static final String EDUCATION_STRING = "NUS";
    private static final String INTERNSHIP_STRING = "Facebook";
    private static final String MODULE_STRING = "CS1101S";
    private static final String CCA_STRING = "Basketball";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final List<Tag> emptyList = new ArrayList<>();
    private final List<Tag> eduList = Arrays.asList(new Education(EDUCATION_STRING));
    private final List<Tag> internshipList = Arrays.asList(new Internship(INTERNSHIP_STRING));
    private final List<Tag> moduleList = Arrays.asList(new Module(MODULE_STRING));
    private final List<Tag> ccaList = Arrays.asList(new Cca(CCA_STRING));

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        TagCommand tagCommand = new TagCommand(outOfBoundIndex, emptyList, emptyList, emptyList, emptyList);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexUnfilteredListAllMissing_failure() {
        TagCommand tagCommand = new TagCommand(INDEX_FIRST_PERSON, emptyList, emptyList, emptyList, emptyList);

        assertCommandFailure(tagCommand, model, TagCommand.MESSAGE_NO_PARAMETERS);
    }

    @Test
    public void execute_validIndexUnfilteredListEduMissing_success() {
        Person personToTag = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());

        TagCommand tagCommandEduEmpty = new TagCommand(
                INDEX_THIRD_PERSON, emptyList, internshipList, moduleList, ccaList);

        String expectedMessageEdu = String.format(TagCommand.MESSAGE_ARGUMENTS,
                personToTag.getName(),
                "[]", "[" + INTERNSHIP_STRING + "]", "[" + MODULE_STRING + "]", "[" + CCA_STRING + "]");

        Person editedPersonEdu = new Person(
                personToTag.getName(), personToTag.getPhone(), personToTag.getEmail(), personToTag.getAddress(),
                emptyList, internshipList, moduleList, ccaList);

        ModelManager expectedModelEduEmpty = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModelEduEmpty.setPerson(personToTag, editedPersonEdu);

        assertCommandSuccess(tagCommandEduEmpty, model, expectedMessageEdu, expectedModelEduEmpty);
    }

    @Test
    public void execute_validIndexUnfilteredListInternshipMissing_success() {
        Person personToTag = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());

        TagCommand tagCommandInternshipEmpty = new TagCommand(
                INDEX_THIRD_PERSON, eduList, emptyList, moduleList, ccaList);

        String expectedMessageInternship = String.format(TagCommand.MESSAGE_ARGUMENTS,
                personToTag.getName(),
                "[" + EDUCATION_STRING + "]", "[]", "[" + MODULE_STRING + "]", "[" + CCA_STRING + "]");

        Person editedPersonInternship = new Person(
                personToTag.getName(), personToTag.getPhone(), personToTag.getEmail(), personToTag.getAddress(),
                eduList, emptyList, moduleList, ccaList);

        ModelManager expectedModelInternshipEmpty = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModelInternshipEmpty.setPerson(personToTag, editedPersonInternship);

        assertCommandSuccess(tagCommandInternshipEmpty, model, expectedMessageInternship, expectedModelInternshipEmpty);
    }

    @Test
    public void execute_validIndexUnfilteredListModuleMissing_success() {
        Person personToTag = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());

        TagCommand tagCommandModuleEmpty = new TagCommand(
                INDEX_THIRD_PERSON, eduList, internshipList, emptyList, ccaList);
        String expectedMessageModule = String.format(TagCommand.MESSAGE_ARGUMENTS,
                personToTag.getName(),
                "[" + EDUCATION_STRING + "]", "[" + INTERNSHIP_STRING + "]", "[]", "[" + CCA_STRING + "]");

        Person editedPersonModule = new Person(
                personToTag.getName(), personToTag.getPhone(), personToTag.getEmail(), personToTag.getAddress(),
                eduList, internshipList, emptyList, ccaList);

        ModelManager expectedModelModuleEmpty = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModelModuleEmpty.setPerson(personToTag, editedPersonModule);

        assertCommandSuccess(tagCommandModuleEmpty, model, expectedMessageModule, expectedModelModuleEmpty);
    }

    @Test
    public void execute_validIndexUnfilteredListCcaMissing_success() {
        Person personToTag = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        TagCommand tagCommandCcaEmpty = new TagCommand(
                INDEX_THIRD_PERSON, eduList, internshipList, moduleList, emptyList);

        String expectedMessageCca = String.format(TagCommand.MESSAGE_ARGUMENTS,
                personToTag.getName(),
                "[" + EDUCATION_STRING + "]", "[" + INTERNSHIP_STRING + "]", "[" + MODULE_STRING + "]", "[]");

        Person editedPersonCca = new Person(
                personToTag.getName(), personToTag.getPhone(), personToTag.getEmail(), personToTag.getAddress(),
                eduList, internshipList, moduleList, emptyList);

        ModelManager expectedModelCcaEmpty = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModelCcaEmpty.setPerson(personToTag, editedPersonCca);

        assertCommandSuccess(tagCommandCcaEmpty, model, expectedMessageCca, expectedModelCcaEmpty);
    }

    @Test
    public void equals() {
        Module moduleTag = new Module("CS2103T");
        Module moduleTag2 = new Module("CS2101");

        // same object -> returns true
        assertTrue(moduleTag.equals(moduleTag));

        // same values -> returns true
        Module moduleTagCopy = new Module("CS2103T");
        assertTrue(moduleTag.equals(moduleTagCopy));

        // different types -> returns false
        assertFalse(moduleTag.equals(1));

        // null -> returns false
        assertFalse(moduleTag.equals(null));

        // different tag -> returns false
        assertFalse(moduleTag.equals(moduleTag2));
    }
}
