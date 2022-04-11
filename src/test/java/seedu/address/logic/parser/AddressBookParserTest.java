package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_INDEX_DESC_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_MANAGER_DESC_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_NAME_DESC_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_NAME_DESC_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.PREMIUM_DESC_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NAME_INVESTMENT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.ClientBuilder.DEFAULT_LAST_CONTACTED;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalPolicies.INSURANCE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.commands.AddPreferenceCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ContactedCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteNoteCommand;
import seedu.address.logic.commands.DeletePreferenceCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditClientDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.TutorialCommand;
import seedu.address.logic.commands.meeting.CloseMeetingCommand;
import seedu.address.logic.commands.policy.AddPolicyCommand;
import seedu.address.logic.commands.policy.DeletePolicyCommand;
import seedu.address.logic.commands.policy.EditPolicyCommand;
import seedu.address.logic.commands.policy.EditPolicyCommand.EditPolicyDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Client;
import seedu.address.model.client.LastContacted;
import seedu.address.model.client.Name;
import seedu.address.model.client.NameContainsKeywordsPredicate;
import seedu.address.model.client.Note;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.ClientUtil;
import seedu.address.testutil.EditClientDescriptorBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Client client = new ClientBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ClientUtil.getAddCommand(client));
        assertEquals(new AddCommand(client), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_CLIENT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Client client = new ClientBuilder().build();
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(client).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CLIENT.getOneBased() + " " + ClientUtil.getEditClientDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_CLIENT, descriptor), command);
    }

    @Test
    public void parseCommand_contacted() throws Exception {
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder().build();
        descriptor.setLastContacted(new LastContacted(DEFAULT_LAST_CONTACTED));
        ContactedCommand command = (ContactedCommand) parser.parseCommand(ContactedCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CLIENT.getOneBased() + " " + ClientUtil.getEditClientDescriptorDetails(descriptor));
        assertEquals(new ContactedCommand(INDEX_FIRST_CLIENT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_tutorial() throws Exception {
        assertTrue(parser.parseCommand(TutorialCommand.COMMAND_WORD) instanceof TutorialCommand);
        assertTrue(parser.parseCommand(TutorialCommand.COMMAND_WORD + " 3") instanceof TutorialCommand);
    }

    @Test
    public void parseCommand_addPolicy() throws Exception {
        String userInput = String.format("%s %s %s %s %s %s",
                AddPolicyCommand.COMMAND_WORD,
                INDEX_FIRST_CLIENT.getOneBased(),
                POLICY_NAME_DESC_INSURANCE,
                COMPANY_DESC_INSURANCE,
                POLICY_MANAGER_DESC_INSURANCE,
                PREMIUM_DESC_INSURANCE
        );
        AddPolicyCommand addPolicyCommand = new AddPolicyCommand(INDEX_FIRST_CLIENT, INSURANCE);
        assertEquals(parser.parseCommand(userInput), addPolicyCommand);
    }

    @Test
    public void parseCommand_editPolicy() throws Exception {
        String userInput = String.format("%s %s %s %s",
                EditPolicyCommand.COMMAND_WORD,
                INDEX_FIRST_CLIENT.getOneBased(),
                POLICY_INDEX_DESC_INSURANCE,
                POLICY_NAME_DESC_INVESTMENT
        );

        EditPolicyDescriptor editPolicyDescriptor =
                new EditPolicyDescriptor();
        editPolicyDescriptor.setName(new Name(VALID_POLICY_NAME_INVESTMENT));

        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_CLIENT,
                editPolicyDescriptor);
        assertEquals(parser.parseCommand(userInput), editPolicyCommand);
    }

    @Test
    public void parseCommand_deletePolicy() throws Exception {
        String userInput = String.format("%s %s %s",
                DeletePolicyCommand.COMMAND_WORD,
                INDEX_FIRST_CLIENT.getOneBased(),
                POLICY_INDEX_DESC_INSURANCE
        );

        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_CLIENT);
        assertEquals(parser.parseCommand(userInput), deletePolicyCommand);
    }

    @Test
    public void parseCommand_closeMeeting() throws Exception {
        String userInput = CloseMeetingCommand.COMMAND_WORD + " 1";
        assertEquals(parser.parseCommand(userInput), new CloseMeetingCommand(INDEX_FIRST_CLIENT));
    }

    @Test
    public void parseCommand_addNote() throws Exception {
        String userInput = AddNoteCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased() + " nt/Note";
        EditClientDescriptor editClientDescriptor = new EditClientDescriptor();
        editClientDescriptor.setNote(new Note("Note"));
        assertEquals(parser.parseCommand(userInput), new AddNoteCommand(INDEX_FIRST_CLIENT, editClientDescriptor));
    }

    @Test
    public void parseCommand_deleteNote() throws Exception {
        String userInput = DeleteNoteCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased();
        EditClientDescriptor editClientDescriptor = new EditClientDescriptor();
        editClientDescriptor.setNote(new Note(""));
        assertEquals(parser.parseCommand(userInput), new DeleteNoteCommand(INDEX_FIRST_CLIENT, editClientDescriptor));
    }

    @Test
    public void parseCommand_addPreference() throws Exception {
        String userInput = AddPreferenceCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CLIENT.getOneBased()
                + " cat/Drink pref/Coke";
        assertEquals(parser.parseCommand(userInput),
                new AddPreferenceCommand(INDEX_FIRST_CLIENT, "Drink", "Coke"));
    }

    @Test
    public void parseCommand_deletePreference() throws Exception {
        String userInput = DeletePreferenceCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CLIENT.getOneBased()
                + " cat/Drink";
        assertEquals(parser.parseCommand(userInput),
                new DeletePreferenceCommand(INDEX_FIRST_CLIENT, "Drink"));
    }

    @Test
    public void parseCommand_sort() throws Exception {
        String userInput = SortCommand.COMMAND_WORD;
        assertEquals(parser.parseCommand(userInput), new SortCommand("", false));
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
