package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.OrderingUtil.Ordering;
import seedu.address.commons.core.SearchTypeUtil;
import seedu.address.commons.core.SearchTypeUtil.SearchType;
import seedu.address.logic.commands.AddCompanyCommand;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.ArchiveAllCommand;
import seedu.address.logic.commands.ArchiveCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteAllCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCompanyCommand;
import seedu.address.logic.commands.EditCompanyCommand.EditCompanyDescriptor;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCompanyCommand;
import seedu.address.logic.commands.FindEventCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCompanyCommand;
import seedu.address.logic.commands.ListEventCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.commands.SortCompanyCommand;
import seedu.address.logic.commands.SortEventCommand;
import seedu.address.logic.commands.SortPersonCommand;
import seedu.address.logic.commands.UnarchiveAllCommand;
import seedu.address.logic.commands.UnarchiveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entry.Company;
import seedu.address.model.entry.Event;
import seedu.address.model.entry.Person;
import seedu.address.model.entry.predicate.CompanyContainsKeywordsPredicate;
import seedu.address.model.entry.predicate.EventContainsKeywordsPredicate;
import seedu.address.model.entry.predicate.PersonContainsKeywordsPredicate;
import seedu.address.testutil.CompanyBuilder;
import seedu.address.testutil.EditCompanyDescriptorBuilder;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EntryUtil;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addCompany() throws Exception {
        Company company = new CompanyBuilder().build();
        AddCompanyCommand command = (AddCompanyCommand) parser.parseCommand(EntryUtil.getAddCompanyCommand(company));
        assertEquals(new AddCompanyCommand(company), command);
    }

    @Test
    public void parseCommand_addPerson() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand command = (AddPersonCommand) parser.parseCommand(EntryUtil.getAddPersonCommand(person));
        assertEquals(new AddPersonCommand(person), command);
    }

    @Test
    public void parseCommand_addEvent() throws Exception {
        Event event = new EventBuilder().build();
        AddEventCommand command = (AddEventCommand) parser.parseCommand(EntryUtil.getAddEventCommand(event));
        assertEquals(new AddEventCommand(event), command);
    }

    @Test
    public void parseCommand_archive() throws Exception {
        ArchiveCommand command = (ArchiveCommand) parser.parseCommand(
                ArchiveCommand.COMMAND_WORD + " " + INDEX_FIRST_ENTRY.getOneBased());
        assertEquals(new ArchiveCommand(INDEX_FIRST_ENTRY), command);
    }

    @Test
    public void parseCommand_archiveAll() throws Exception {
        assertTrue(parser.parseCommand(ArchiveAllCommand.COMMAND_WORD) instanceof ArchiveAllCommand);
        assertTrue(parser.parseCommand(ArchiveAllCommand.COMMAND_WORD + " 3") instanceof ArchiveAllCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_ENTRY.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_ENTRY), command);
    }

    @Test
    public void parseCommand_deleteAll() throws Exception {
        assertTrue(parser.parseCommand(DeleteAllCommand.COMMAND_WORD) instanceof DeleteAllCommand);
        assertTrue(parser.parseCommand(DeleteAllCommand.COMMAND_WORD + " 3") instanceof DeleteAllCommand);
    }

    @Test
    public void parseCommand_editCompany() throws Exception {
        Company company = new CompanyBuilder().build();
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder(company).build();
        EditCompanyCommand command = (EditCompanyCommand) parser.parseCommand(EditCompanyCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ENTRY.getOneBased() + " " + EntryUtil.getEditCompanyDescriptorDetails(descriptor));
        assertEquals(new EditCompanyCommand(INDEX_FIRST_ENTRY, descriptor), command);
    }

    @Test
    public void parseCommand_editPerson() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditPersonCommand command = (EditPersonCommand) parser.parseCommand(EditPersonCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ENTRY.getOneBased() + " " + EntryUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditPersonCommand(INDEX_FIRST_ENTRY, descriptor), command);
    }

    @Test
    public void parseCommand_editEvent() throws Exception {
        Event company = new EventBuilder().build();
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(company).build();
        EditEventCommand command = (EditEventCommand) parser.parseCommand(EditEventCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ENTRY.getOneBased() + " " + EntryUtil.getEditEventDescriptorDetails(descriptor));
        assertEquals(new EditEventCommand(INDEX_FIRST_ENTRY, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findCompany() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        String test = FindCompanyCommand.COMMAND_WORD + " n/ " + keywords.stream().collect(Collectors.joining(" "));
        FindCompanyCommand command = (FindCompanyCommand) parser.parseCommand(test);

        CompanyContainsKeywordsPredicate predicate = new CompanyContainsKeywordsPredicate(
                keywords, List.<String>of(""), SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.UNARCHIVED_ONLY));
        assertEquals(new FindCompanyCommand(predicate), command);
    }

    @Test
    public void parseCommand_findPerson() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        String test = FindPersonCommand.COMMAND_WORD + " n/ " + keywords.stream().collect(Collectors.joining(" "));
        FindPersonCommand command = (FindPersonCommand) parser.parseCommand(
                test);

        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(
                keywords, List.of(""), List.of(""),
                SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.UNARCHIVED_ONLY)
        );
        assertEquals(new FindPersonCommand(predicate), command);
    }

    @Test
    public void parseCommand_findEvent() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        String test = FindEventCommand.COMMAND_WORD + " n/ " + keywords.stream().collect(Collectors.joining(" "));
        FindEventCommand command = (FindEventCommand) parser.parseCommand(test);

        EventContainsKeywordsPredicate predicate = new EventContainsKeywordsPredicate(
                keywords, List.of(""), null, null, List.of(""), List.of(""), List.of(""),
                SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.UNARCHIVED_ONLY));
        assertEquals(new FindEventCommand(predicate), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listCompany() throws Exception {
        ListCompanyCommand commandNoArgs = (ListCompanyCommand) parser.parseCommand(ListCompanyCommand.COMMAND_WORD);
        ListCompanyCommand commandWithArgs = (ListCompanyCommand) parser.parseCommand(
                ListCompanyCommand.COMMAND_WORD + " s/unarchived"
        );

        assertEquals(new ListCompanyCommand(SearchType.UNARCHIVED_ONLY), commandNoArgs);
        assertEquals(new ListCompanyCommand(SearchType.UNARCHIVED_ONLY), commandWithArgs);
    }

    @Test
    public void parseCommand_listPerson() throws Exception {
        ListPersonCommand commandNoArgs = (ListPersonCommand) parser.parseCommand(ListPersonCommand.COMMAND_WORD);
        ListPersonCommand commandWithArgs = (ListPersonCommand) parser.parseCommand(
                ListPersonCommand.COMMAND_WORD + " s/archived"
        );

        assertEquals(new ListPersonCommand(SearchType.UNARCHIVED_ONLY), commandNoArgs);
        assertEquals(new ListPersonCommand(SearchType.ARCHIVED_ONLY), commandWithArgs);
    }

    @Test
    public void parseCommand_listEvent() throws Exception {
        ListEventCommand commandNoArgs = (ListEventCommand) parser.parseCommand(ListEventCommand.COMMAND_WORD);
        ListEventCommand commandWithArgs = (ListEventCommand) parser.parseCommand(
                ListEventCommand.COMMAND_WORD + " s/all"
        );

        assertEquals(new ListEventCommand(SearchType.UNARCHIVED_ONLY), commandNoArgs);
        assertEquals(new ListEventCommand(SearchType.ALL), commandWithArgs);
    }

    @Test
    public void parseCommand_sortCompany() throws Exception {
        SortCompanyCommand commandNoArgs = (SortCompanyCommand) parser.parseCommand(SortCompanyCommand.COMMAND_WORD);
        SortCompanyCommand commandWithArgs = (SortCompanyCommand) parser.parseCommand(
                SortCompanyCommand.COMMAND_WORD + " s/unarchived o/ascending");

        assertEquals(new SortCompanyCommand(SearchType.UNARCHIVED_ONLY, Ordering.ASCENDING), commandNoArgs);
        assertEquals(new SortCompanyCommand(SearchType.UNARCHIVED_ONLY, Ordering.ASCENDING), commandWithArgs);
    }

    @Test
    public void parseCommand_sortPerson() throws Exception {
        SortPersonCommand commandNoArgs = (SortPersonCommand) parser.parseCommand(SortPersonCommand.COMMAND_WORD);
        SortPersonCommand commandWithArgs = (SortPersonCommand) parser.parseCommand(
                SortPersonCommand.COMMAND_WORD + " s/archived o/descending");

        assertEquals(new SortPersonCommand(SearchType.UNARCHIVED_ONLY, Ordering.ASCENDING), commandNoArgs);
        assertEquals(new SortPersonCommand(SearchType.ARCHIVED_ONLY, Ordering.DESCENDING), commandWithArgs);
    }

    @Test
    public void parseCommand_sortEvent() throws Exception {
        SortEventCommand commandNoArgs = (SortEventCommand) parser.parseCommand(SortEventCommand.COMMAND_WORD);
        SortEventCommand commandWithArgs = (SortEventCommand) parser.parseCommand(
                SortEventCommand.COMMAND_WORD + " s/all o/descending");

        assertEquals(new SortEventCommand(SearchType.UNARCHIVED_ONLY, Ordering.ASCENDING), commandNoArgs);
        assertEquals(new SortEventCommand(SearchType.ALL, Ordering.DESCENDING), commandWithArgs);
    }

    @Test
    public void parseCommand_unarchive() throws Exception {
        UnarchiveCommand command = (UnarchiveCommand) parser.parseCommand(
                UnarchiveCommand.COMMAND_WORD + " " + INDEX_FIRST_ENTRY.getOneBased());
        assertEquals(new UnarchiveCommand(INDEX_FIRST_ENTRY), command);
    }

    @Test
    public void parseCommand_unarchiveAll() throws Exception {
        assertTrue(parser.parseCommand(UnarchiveAllCommand.COMMAND_WORD) instanceof UnarchiveAllCommand);
        assertTrue(parser.parseCommand(UnarchiveAllCommand.COMMAND_WORD + " 3") instanceof UnarchiveAllCommand);
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
