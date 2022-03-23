package seedu.contax.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALONE;
import static seedu.contax.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.AddAppointmentCommand;
import seedu.contax.logic.commands.AddPersonCommand;
import seedu.contax.logic.commands.AddTagCommand;
import seedu.contax.logic.commands.AppointmentsBetweenCommand;
import seedu.contax.logic.commands.BatchCommand;
import seedu.contax.logic.commands.ChainCommand;
import seedu.contax.logic.commands.ClearCommand;
import seedu.contax.logic.commands.Command;
import seedu.contax.logic.commands.DeleteAppointmentCommand;
import seedu.contax.logic.commands.DeletePersonCommand;
import seedu.contax.logic.commands.DeleteTagCommand;
import seedu.contax.logic.commands.EditAppointmentCommand;
import seedu.contax.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.contax.logic.commands.EditPersonCommand;
import seedu.contax.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.contax.logic.commands.EditTagCommand;
import seedu.contax.logic.commands.EditTagCommand.EditTagDescriptor;
import seedu.contax.logic.commands.ExitCommand;
import seedu.contax.logic.commands.ExportCsvCommand;
import seedu.contax.logic.commands.FindByTagCommand;
import seedu.contax.logic.commands.FindPersonCommand;
import seedu.contax.logic.commands.FreeBetweenCommand;
import seedu.contax.logic.commands.HelpCommand;
import seedu.contax.logic.commands.ImportCsvCommand;
import seedu.contax.logic.commands.ListAppointmentCommand;
import seedu.contax.logic.commands.ListPersonCommand;
import seedu.contax.logic.commands.ListTagCommand;
import seedu.contax.logic.commands.RangeCommand;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.IndexedCsvFile;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.person.NameContainsKeywordsPredicate;
import seedu.contax.model.person.Person;
import seedu.contax.model.person.TagNameContainsKeywordsPredicate;
import seedu.contax.model.tag.Tag;
import seedu.contax.testutil.AppointmentBuilder;
import seedu.contax.testutil.AppointmentUtil;
import seedu.contax.testutil.DateInputUtil;
import seedu.contax.testutil.EditAppointmentDescriptorBuilder;
import seedu.contax.testutil.EditPersonDescriptorBuilder;
import seedu.contax.testutil.EditTagDescriptorBuilder;
import seedu.contax.testutil.ImportCsvObjectBuilder;
import seedu.contax.testutil.PersonBuilder;
import seedu.contax.testutil.PersonUtil;
import seedu.contax.testutil.TagBuilder;
import seedu.contax.testutil.TagUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand command = (AddPersonCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddPersonCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
                DeletePersonCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeletePersonCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditPersonCommand command = (EditPersonCommand) parser.parseCommand(EditPersonCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditPersonCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPersonCommand command = (FindPersonCommand) parser.parseCommand(
                FindPersonCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindPersonCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListPersonCommand.COMMAND_WORD) instanceof ListPersonCommand);
        assertTrue(parser.parseCommand(ListPersonCommand.COMMAND_WORD + " 3") instanceof ListPersonCommand);
    }

    // Tag management commands
    @Test
    public void parseCommand_addTag() throws Exception {
        Tag tag = new TagBuilder().build();
        AddTagCommand command = (AddTagCommand) parser.parseCommand(TagUtil.getAddTagCommand(tag));
        assertEquals(command, new AddTagCommand(tag));
    }

    @Test
    public void parseCommand_listTag() throws Exception {
        assertTrue(parser.parseCommand(ListTagCommand.COMMAND_WORD) instanceof ListTagCommand);
    }

    @Test
    public void parseCommand_deleteTag() throws Exception {
        Index firstIndex = Index.fromOneBased(1);
        String deleteCommand = String.format("%s %s", DeleteTagCommand.COMMAND_WORD, 1);
        DeleteTagCommand command = (DeleteTagCommand) parser.parseCommand(deleteCommand);
        assertEquals(command, new DeleteTagCommand(firstIndex));
    }

    @Test
    public void parseCommand_editTag() throws Exception {
        Index index = Index.fromOneBased(1);
        Tag tag = new TagBuilder().build();
        EditTagDescriptor editTagDescriptor = new EditTagDescriptorBuilder(tag).build();
        EditTagCommand command = (EditTagCommand) parser.parseCommand(EditTagCommand.COMMAND_WORD + " 1 "
                + "t/clients");
        assertEquals(command, new EditTagCommand(index, editTagDescriptor));
    }

    @Test
    public void parseCommand_findByTag() throws Exception {
        TagNameContainsKeywordsPredicate predicate = new TagNameContainsKeywordsPredicate("friends");
        FindByTagCommand command = (FindByTagCommand) parser.parseCommand(FindByTagCommand.COMMAND_WORD
                + " t/friends");
        assertEquals(command, new FindByTagCommand(predicate));
    }

    // Appointment related commands
    @Test
    public void parseCommand_addAppointment() throws Exception {
        Appointment appointment = new AppointmentBuilder(APPOINTMENT_ALONE).build();
        AddAppointmentCommand command = (AddAppointmentCommand) parser.parseCommand(AppointmentUtil
                .getAddCommand(appointment, null));
        assertEquals(new AddAppointmentCommand(appointment, null), command);
    }

    @Test
    public void parseCommand_listAppointments() throws Exception {
        assertTrue(parser.parseCommand(ListAppointmentCommand.COMMAND_WORD) instanceof ListAppointmentCommand);
    }

    @Test
    public void parseCommand_editAppointment() throws Exception {
        EditAppointmentDescriptor editDescriptor = new EditAppointmentDescriptorBuilder()
                .withName("Name Change").build();
        Command command = parser.parseCommand(EditAppointmentCommand.COMMAND_WORD
                + " " + Index.fromOneBased(1).getOneBased() + " "
                + AppointmentUtil.getAppointmentEditDescriptorDetails(editDescriptor));
        assertTrue(command instanceof EditAppointmentCommand);
        assertEquals(new EditAppointmentCommand(Index.fromOneBased(1), editDescriptor), command);
    }

    @Test
    public void parseCommand_deleteAppointment() throws Exception {
        assertTrue(parser.parseCommand(DeleteAppointmentCommand.COMMAND_WORD + " 1")
                instanceof DeleteAppointmentCommand);
    }

    @Test
    public void parseCommand_appointmentsBetween() throws Exception {
        LocalDateTime refDateTime = APPOINTMENT_ALONE.getStartDateTime();
        assertTrue(parser.parseCommand(AppointmentsBetweenCommand.COMMAND_WORD
                + DateInputUtil.getDateRangeInput(refDateTime, refDateTime.plusMinutes(50)))
                instanceof AppointmentsBetweenCommand);
    }

    @Test
    public void parseCommand_freeBetween() throws Exception {
        LocalDateTime refDateTime = APPOINTMENT_ALONE.getStartDateTime();
        assertTrue(parser.parseCommand(FreeBetweenCommand.COMMAND_WORD
                + DateInputUtil.getDateRangeInput(refDateTime, refDateTime.plusMinutes(50))
                + " " + PREFIX_DURATION + "1")
                instanceof FreeBetweenCommand);
    }

    //Import/Export csv tests
    @Test
    public void parseCommand_importCsv() throws Exception {
        IndexedCsvFile indexedCsvFile = new ImportCsvObjectBuilder().build();
        ImportCsvCommand command = (ImportCsvCommand)
                parser.parseCommand(ImportCsvObjectBuilder.getImportCsvCommand(indexedCsvFile));
        assertEquals(new ImportCsvCommand(indexedCsvFile), command);
    }

    @Test
    public void parseCommand_exportCsv() throws Exception {
        assertTrue(parser.parseCommand(ExportCsvCommand.COMMAND_WORD) instanceof ExportCsvCommand);
    }

    @Test
    public void parseCommand_chainCommand() throws Exception {
        assertTrue(parser.parseCommand(ChainCommand.COMMAND_WORD + " " + ListPersonCommand.COMMAND_WORD
                + " && " + ListPersonCommand.COMMAND_WORD) instanceof ChainCommand);
    }

    @Test
    public void parseCommand_rangeCommand() throws Exception {
        assertTrue(parser.parseCommand(RangeCommand.COMMAND_WORD + " " + DeletePersonCommand.COMMAND_WORD
                + " from/1 to/2") instanceof RangeCommand);
    }

    @Test
    public void parseCommand_batchCommand() throws Exception {
        assertTrue(parser.parseCommand(BatchCommand.COMMAND_WORD + " " + DeletePersonCommand.COMMAND_WORD
                + " by/phone =/123") instanceof BatchCommand);
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
