package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FORCED_DELETE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_FIRST_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.meeting.AddMeetingCommand;
import seedu.address.logic.commands.meeting.AddMeetingCommand.AddMeetingDescriptor;
import seedu.address.logic.commands.meeting.DeleteMeetingCommand;
import seedu.address.logic.commands.meeting.EditMeetingCommand;
import seedu.address.logic.commands.meeting.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.commands.meeting.ListMeetingCommand;
import seedu.address.logic.commands.meeting.OpenMeetingCommand;
import seedu.address.logic.commands.module.AddModuleCommand;
import seedu.address.logic.commands.module.DeleteModuleCommand;
import seedu.address.logic.commands.module.EditModuleCommand;
import seedu.address.logic.commands.module.EditModuleCommand.EditModuleDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;
import seedu.address.testutil.meeting.AddMeetingDescriptorBuilder;
import seedu.address.testutil.meeting.EditMeetingDescriptorBuilder;
import seedu.address.testutil.meeting.MeetingBuilder;
import seedu.address.testutil.meeting.MeetingUtil;
import seedu.address.testutil.module.EditModuleDescriptorBuilder;
import seedu.address.testutil.module.ModuleBuilder;
import seedu.address.testutil.module.ModuleUtil;

/**
 * Contains unit test for LinkyTimeParser
 * This file is an adapted version of AddressBook's AddressBookParser and is currently missing certain functions
 * due to missing implementation. Please refer to the obsolete package for reference.
 */
public class LinkyTimeParserTest {
    private final LinkyTimeParser parser = new LinkyTimeParser();

    @Test
    public void parseCommand_add() throws Exception {
        final Meeting meeting = new MeetingBuilder().build();

        final Index firstModule = Index.fromZeroBased(0);
        final AddMeetingDescriptor addMeetingDescriptor = new AddMeetingDescriptorBuilder(meeting)
                .withModule(firstModule).build();
        final AddMeetingCommand command = (AddMeetingCommand) parser.parseCommand(
                MeetingUtil.getAddMeetingCommand(meeting));
        assertEquals(new AddMeetingCommand(addMeetingDescriptor), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        final Meeting meeting = new MeetingBuilder().build();
        final Index firstModule = Index.fromZeroBased(0);
        final EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(meeting)
              .withModule(firstModule).build();
        final EditMeetingCommand command = (EditMeetingCommand) parser.parseCommand(EditMeetingCommand.COMMAND_WORD
                + " " + INDEX_FIRST_MEETING.getOneBased() + " "
                + MeetingUtil.getEditMeetingDescriptorDetails(descriptor));
        assertEquals(new EditMeetingCommand(INDEX_FIRST_MEETING, descriptor), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_open() throws Exception {
        final OpenMeetingCommand command = (OpenMeetingCommand) parser.parseCommand(
                OpenMeetingCommand.COMMAND_WORD + " " + INDEX_FIRST_MEETING.getOneBased());
        assertEquals(new OpenMeetingCommand(INDEX_FIRST_MEETING), command);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        final DeleteMeetingCommand command = (DeleteMeetingCommand) parser.parseCommand(
                DeleteMeetingCommand.COMMAND_WORD + " " + INDEX_FIRST_MEETING.getOneBased());
        assertEquals(new DeleteMeetingCommand(INDEX_FIRST_MEETING), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListMeetingCommand.COMMAND_WORD) instanceof ListMeetingCommand);
        assertTrue(parser.parseCommand(ListMeetingCommand.COMMAND_WORD + " 3") instanceof ListMeetingCommand);
    }

    @Test
    public void parseCommand_addModule() throws Exception {
        final Module module = new ModuleBuilder().build();
        final AddModuleCommand command = (AddModuleCommand) parser.parseCommand(ModuleUtil.getAddModuleCommand(module));
        assertEquals(new AddModuleCommand(module), command);
    }

    @Test
    public void parseCommand_editModule() throws Exception {
        final Module module = new ModuleBuilder().build();
        final EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(module).build();
        final EditModuleCommand command = (EditModuleCommand) parser.parseCommand(EditModuleCommand.COMMAND_WORD
                + " " + INDEX_FIRST_MODULE.getOneBased() + " " + ModuleUtil.getEditModuleDescriptorDetails(descriptor));
        assertEquals(new EditModuleCommand(INDEX_FIRST_MODULE, descriptor), command);
    }

    @Test
    public void parseCommand_deleteModule() throws Exception {
        final DeleteModuleCommand command = (DeleteModuleCommand) parser.parseCommand(
                DeleteModuleCommand.COMMAND_WORD + " " + INDEX_FIRST_MODULE.getOneBased());
        assertEquals(new DeleteModuleCommand(INDEX_FIRST_MODULE, false), command);
    }

    @Test
    public void parseCommand_deleteModuleForced() throws Exception {
        final DeleteModuleCommand command = (DeleteModuleCommand) parser.parseCommand(
                DeleteModuleCommand.COMMAND_WORD + " " + INDEX_FIRST_MODULE.getOneBased()
                        + " " + PREFIX_FORCED_DELETE);
        assertEquals(new DeleteModuleCommand(INDEX_FIRST_MODULE, true), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
