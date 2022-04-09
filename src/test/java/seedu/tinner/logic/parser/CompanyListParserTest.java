package seedu.tinner.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tinner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tinner.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.tinner.testutil.Assert.assertThrows;
import static seedu.tinner.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.tinner.testutil.TypicalIndexes.INDEX_FIRST_ROLE;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.tinner.commons.core.index.Index;
import seedu.tinner.logic.commands.AddCompanyCommand;
import seedu.tinner.logic.commands.AddRoleCommand;
import seedu.tinner.logic.commands.ClearCommand;
import seedu.tinner.logic.commands.DeleteCompanyCommand;
import seedu.tinner.logic.commands.DeleteRoleCommand;
import seedu.tinner.logic.commands.EditCompanyCommand;
import seedu.tinner.logic.commands.EditCompanyCommand.EditCompanyDescriptor;
import seedu.tinner.logic.commands.EditRoleCommand;
import seedu.tinner.logic.commands.EditRoleCommand.EditRoleDescriptor;
import seedu.tinner.logic.commands.ExitCommand;
import seedu.tinner.logic.commands.FavouriteCompanyCommand;
import seedu.tinner.logic.commands.FindCommand;
import seedu.tinner.logic.commands.HelpCommand;
import seedu.tinner.logic.commands.ListCommand;
import seedu.tinner.logic.commands.ListFavouriteCommand;
import seedu.tinner.logic.commands.SetReminderWindowCommand;
import seedu.tinner.logic.commands.UnfavouriteCompanyCommand;
import seedu.tinner.logic.parser.exceptions.ParseException;
import seedu.tinner.model.company.Company;
import seedu.tinner.model.company.CompanyNameContainsKeywordsPredicate;
import seedu.tinner.model.role.Role;
import seedu.tinner.model.role.RoleNameContainsKeywordsPredicate;
import seedu.tinner.testutil.CompanyBuilder;
import seedu.tinner.testutil.CompanyUtil;
import seedu.tinner.testutil.EditCompanyDescriptorBuilder;
import seedu.tinner.testutil.EditRoleDescriptorBuilder;
import seedu.tinner.testutil.RoleBuilder;
import seedu.tinner.testutil.RoleUtil;

public class CompanyListParserTest {

    private final CompanyListParser parser = new CompanyListParser();

    @Test
    public void parseCommand_addCompany() throws Exception {
        Company company = new CompanyBuilder().build();
        AddCompanyCommand command = (AddCompanyCommand) parser.parseCommand(CompanyUtil.getAddCompanyCommand(company));
        assertEquals(new AddCompanyCommand(company), command);
    }

    @Test
    public void parseCommand_addRole() throws Exception {
        Role role = new RoleBuilder().build();
        Index firstIndex = Index.fromOneBased(1);
        AddRoleCommand command = (AddRoleCommand) parser.parseCommand(RoleUtil.getAddRoleCommand(role, firstIndex));
        assertEquals(new AddRoleCommand(firstIndex, role), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ParserUtil.MESSAGE_EXTRANEOUS_PARAMETER), () ->
                        parser.parseCommand(ClearCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_deleteCompany() throws Exception {
        DeleteCompanyCommand command = (DeleteCompanyCommand) parser.parseCommand(
                DeleteCompanyCommand.COMMAND_WORD + " " + INDEX_FIRST_COMPANY
                        .getOneBased());
        assertEquals(new DeleteCompanyCommand(INDEX_FIRST_COMPANY
        ), command);
    }

    @Test
    public void parseCommand_deleteRole() throws Exception {
        DeleteRoleCommand command = (DeleteRoleCommand) parser.parseCommand(
                DeleteRoleCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_COMPANY.getOneBased() + " " + INDEX_FIRST_ROLE.getOneBased());
        assertEquals(new DeleteRoleCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_ROLE), command);
    }

    @Test
    public void parseCommand_editCompany() throws Exception {
        Company company = new CompanyBuilder().build();
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder(company).build();
        EditCompanyCommand command = (EditCompanyCommand) parser.parseCommand(
                EditCompanyCommand.COMMAND_WORD + " " + INDEX_FIRST_COMPANY
                .getOneBased() + " " + CompanyUtil.getEditCompanyDescriptorDetails(descriptor));
        assertEquals(new EditCompanyCommand(INDEX_FIRST_COMPANY, descriptor), command);
    }

    @Test
    public void parseCommand_editRole() throws Exception {
        Role role = new RoleBuilder().build();
        EditRoleDescriptor descriptor = new EditRoleDescriptorBuilder(role).build();
        EditRoleCommand command = (EditRoleCommand) parser.parseCommand(
                EditRoleCommand.COMMAND_WORD + " " + INDEX_FIRST_COMPANY
                        .getOneBased() + " " + INDEX_FIRST_ROLE.getOneBased() + " " + RoleUtil
                        .getEditRoleDescriptorDetails(descriptor));
        assertEquals(new EditRoleCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_ROLE, descriptor), command);
    }

    @Test
    public void parseCommand_favouriteCompany() throws Exception {
        FavouriteCompanyCommand command = (FavouriteCompanyCommand) parser.parseCommand(
                FavouriteCompanyCommand.COMMAND_WORD + " " + INDEX_FIRST_COMPANY
                        .getOneBased());
        assertEquals(new FavouriteCompanyCommand(INDEX_FIRST_COMPANY), command);
    }

    @Test
    public void parseCommand_unfavouriteCompany() throws Exception {
        UnfavouriteCompanyCommand command = (UnfavouriteCompanyCommand) parser.parseCommand(
                UnfavouriteCompanyCommand.COMMAND_WORD + " " + INDEX_FIRST_COMPANY
                        .getOneBased());
        assertEquals(new UnfavouriteCompanyCommand(INDEX_FIRST_COMPANY), command);
    }

    @Test
    public void parseCommand_setReminderWindow() throws Exception {
        SetReminderWindowCommand command = (SetReminderWindowCommand) parser.parseCommand(
                SetReminderWindowCommand.COMMAND_WORD + " " + 1);
        assertEquals(new SetReminderWindowCommand(1), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ParserUtil.MESSAGE_EXTRANEOUS_PARAMETER), () ->
                        parser.parseCommand(ExitCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_listFavourite() throws Exception {
        assertTrue(parser.parseCommand(ListFavouriteCommand.COMMAND_WORD) instanceof ListFavouriteCommand);
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ParserUtil.MESSAGE_EXTRANEOUS_PARAMETER), () ->
                        parser.parseCommand(ListFavouriteCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(FindCommand.COMMAND_WORD + " c/"
                + String.join(" ", keywords) + " r/" + String.join(" ", keywords));
        assertEquals(new FindCommand(new CompanyNameContainsKeywordsPredicate(keywords, keywords),
                new RoleNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ParserUtil.MESSAGE_EXTRANEOUS_PARAMETER), () ->
                        parser.parseCommand(HelpCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ParserUtil.MESSAGE_EXTRANEOUS_PARAMETER), () ->
                        parser.parseCommand(ClearCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand(
                "unknownCommand"));
    }
}
