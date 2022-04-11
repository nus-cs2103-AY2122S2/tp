package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.applicant.AddApplicantCommand;
import seedu.address.logic.commands.applicant.DeleteApplicantCommand;
import seedu.address.logic.commands.applicant.EditApplicantCommand;
import seedu.address.logic.commands.applicant.EditApplicantCommand.EditApplicantDescriptor;
import seedu.address.logic.commands.applicant.ListApplicantCommand;
import seedu.address.logic.commands.help.HelpCommand;
import seedu.address.logic.commands.interview.ListInterviewCommand;
import seedu.address.logic.commands.position.ListPositionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Applicant;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.ApplicantUtil;
import seedu.address.testutil.EditApplicantDescriptorBuilder;

public class HireLahParserTest {

    private final HireLahParser parser = new HireLahParser();

    @Test
    public void parseCommand_addApplicant() throws Exception {
        Applicant applicant = new ApplicantBuilder().build();
        AddApplicantCommand command = (AddApplicantCommand) parser.parseCommand(ApplicantUtil.getAddCommand(applicant));
        assertEquals(new AddApplicantCommand(applicant), command);
    }

    @Test
    public void parseCommand_deleteApplicant() throws Exception {
        DeleteApplicantCommand command = (DeleteApplicantCommand) parser.parseCommand(
                DeleteApplicantCommand.COMMAND_WORD + " -a " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteApplicantCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_editApplicant() throws Exception {
        Applicant applicant = new ApplicantBuilder().build();
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(applicant).build();
        EditApplicantCommand command =
                (EditApplicantCommand) parser.parseCommand(EditApplicantCommand.COMMAND_WORD + " -a "
                + INDEX_FIRST.getOneBased() + " " + ApplicantUtil.getEditApplicantDescriptorDetails(descriptor));
        assertEquals(new EditApplicantCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " help") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListApplicantCommand.COMMAND_WORD + " -a") instanceof ListApplicantCommand);
        assertTrue(parser.parseCommand(ListApplicantCommand.COMMAND_WORD + " -i") instanceof ListInterviewCommand);
        assertTrue(parser.parseCommand(ListApplicantCommand.COMMAND_WORD + " -p") instanceof ListPositionCommand);
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
