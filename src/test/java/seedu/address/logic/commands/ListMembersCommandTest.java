package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Membership;
import seedu.address.model.person.Person;
import seedu.address.model.person.util.PersonContainsKeywordsPredicate;
import seedu.address.model.person.util.PersonContainsMembershipPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListMembersCommand.
 */
public class ListMembersCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_noMembers_showsEmptyList() {
        String expectedMessage = Messages.MESSAGE_NO_PERSONS_FOUND_OVERVIEW;
        PersonContainsMembershipPredicate predicate = new PersonContainsMembershipPredicate(Membership.Tier.ALL);

        expectedModel.updateFilteredPersonList(new PersonContainsKeywordsPredicate(Arrays.asList("NONESTUFF")));

        assertCommandSuccess(new ListMembersCommand(predicate), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        // Gold test
        Membership member = new Membership("GOLD");
        Person person = model.getFilteredPersonList().get(0);
        Person personEdited = person.addMembership(member);
        model.setPerson(person, personEdited);

        person = expectedModel.getFilteredPersonList().get(0);
        personEdited = person.addMembership(member);
        expectedModel.setPerson(person, personEdited);
        expectedModel.updateFilteredPersonList(new PersonContainsKeywordsPredicate(Arrays.asList("GOLD")));

        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_FOUND_OVERVIEW, 1);

        PersonContainsMembershipPredicate predicate = new PersonContainsMembershipPredicate(Membership.Tier.GOLD);
        assertCommandSuccess(new ListMembersCommand(predicate), model, expectedMessage, expectedModel);
    }

    @Test
    void equals() {
        PersonContainsMembershipPredicate predicate1 = new PersonContainsMembershipPredicate(Membership.Tier.ALL);
        PersonContainsMembershipPredicate predicate2 = new PersonContainsMembershipPredicate(Membership.Tier.GOLD);

        ListMembersCommand command1 = new ListMembersCommand(predicate1);
        ListMembersCommand command2 = new ListMembersCommand(predicate1);
        ListMembersCommand command3 = new ListMembersCommand(predicate2);

        // Equals to itself
        assertTrue(command1.equals(command1));
        //Equals to other object with same predicate
        assertTrue(command1.equals(command2));
        // Not equals to other object with different predicate
        assertFalse(command1.equals(command3));
        // Other types
        assertFalse(command1.equals(1));
        assertFalse(command1.equals(null));
    }
}
