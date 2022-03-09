package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EDUCATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EDUCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERNSHIP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERNSHIP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.TagCommand.MESSAGE_ARGUMENTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.TagCommandParser;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;


/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class TagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        final List<Tag> educations = TagCommandParser.convertToList("", "education");
        final List<Tag> ccas = TagCommandParser.convertToList("", "cca");
        final List<Tag> internships = TagCommandParser.convertToList("", "internship");
        final List<Tag> modules = TagCommandParser.convertToList("", "module");

        assertCommandFailure(new TagCommand(INDEX_FIRST_PERSON, educations, internships, modules, ccas), model,
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_PERSON.getOneBased(),
                        educations, internships, modules, ccas));

    }

    @Test
    public void equals() {
        final List<Tag> educations = TagCommandParser.convertToList(VALID_EDUCATION_AMY, "education");
        final List<Tag> ccas = TagCommandParser.convertToList(VALID_CCA_AMY, "cca");
        final List<Tag> internships = TagCommandParser.convertToList(VALID_INTERNSHIP_AMY, "internship");
        final List<Tag> modules = TagCommandParser.convertToList(VALID_MODULE_AMY, "module");

        final TagCommand standardCommand = new TagCommand(INDEX_FIRST_PERSON, educations,
                internships, modules, ccas);

        // same values -> return true
        TagCommand commandWithSomeValue = new TagCommand(INDEX_FIRST_PERSON, educations,
                internships, modules, ccas);
        assertTrue(standardCommand.equals(commandWithSomeValue));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> return false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new TagCommand(INDEX_SECOND_PERSON, educations,
                internships, modules, ccas)));

        final List<Tag> bobEducations = TagCommandParser.convertToList(VALID_EDUCATION_BOB, "education");
        final List<Tag> bobInternships = TagCommandParser.convertToList(VALID_INTERNSHIP_BOB, "internship");
        final List<Tag> bobModules = TagCommandParser.convertToList(VALID_MODULE_BOB, "module");
        final List<Tag> bobCcas = TagCommandParser.convertToList(VALID_CCA_BOB, "cca");

        // different tags -> returns false
        assertFalse(standardCommand.equals(new TagCommand(INDEX_FIRST_PERSON, bobEducations,
                bobInternships, bobModules, bobCcas)));
    }
}
