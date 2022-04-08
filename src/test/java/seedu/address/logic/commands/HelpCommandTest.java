package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.HELP_MESSAGE_ADD;
import static seedu.address.logic.commands.HelpCommand.HELP_MESSAGE_CLEAR;
import static seedu.address.logic.commands.HelpCommand.HELP_MESSAGE_DELETE;
import static seedu.address.logic.commands.HelpCommand.HELP_MESSAGE_EDIT;
import static seedu.address.logic.commands.HelpCommand.HELP_MESSAGE_EXIT;
import static seedu.address.logic.commands.HelpCommand.HELP_MESSAGE_FILTER;
import static seedu.address.logic.commands.HelpCommand.HELP_MESSAGE_FILTERTEAM;
import static seedu.address.logic.commands.HelpCommand.HELP_MESSAGE_FIND;
import static seedu.address.logic.commands.HelpCommand.HELP_MESSAGE_LIST;
import static seedu.address.logic.commands.HelpCommand.HELP_MESSAGE_REDO;
import static seedu.address.logic.commands.HelpCommand.HELP_MESSAGE_SHOW;
import static seedu.address.logic.commands.HelpCommand.HELP_MESSAGE_SKILL;
import static seedu.address.logic.commands.HelpCommand.HELP_MESSAGE_SORT;
import static seedu.address.logic.commands.HelpCommand.HELP_MESSAGE_TEAM;
import static seedu.address.logic.commands.HelpCommand.HELP_MESSAGE_UNDO;
import static seedu.address.logic.commands.HelpCommand.HELP_MESSAGE_UNTEAM;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(""), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_add_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE_ADD, false, false);
        assertCommandSuccess(new HelpCommand("add"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_delete_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE_DELETE, false, false);
        assertCommandSuccess(new HelpCommand("delete"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_find_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE_FIND, false, false);
        assertCommandSuccess(new HelpCommand("find"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_filter_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE_FILTER, false, false);
        assertCommandSuccess(new HelpCommand("filter"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_list_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE_LIST, false, false);
        assertCommandSuccess(new HelpCommand("list"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_skill_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE_SKILL, false, false);
        assertCommandSuccess(new HelpCommand("skill"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_team_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE_TEAM, false, false);
        assertCommandSuccess(new HelpCommand("team"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_unteam_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE_UNTEAM, false, false);
        assertCommandSuccess(new HelpCommand("unteam"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_sort_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE_SORT, false, false);
        assertCommandSuccess(new HelpCommand("sort"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_clear_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE_CLEAR, false, false);
        assertCommandSuccess(new HelpCommand("clear"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE_EXIT, false, false);
        assertCommandSuccess(new HelpCommand("exit"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_edit_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE_EDIT, false, false);
        assertCommandSuccess(new HelpCommand("edit"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_undo_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE_UNDO, false, false);
        assertCommandSuccess(new HelpCommand("undo"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_redo_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE_REDO, false, false);
        assertCommandSuccess(new HelpCommand("redo"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_show_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE_SHOW, false, false);
        assertCommandSuccess(new HelpCommand("show"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_filterteam_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE_FILTERTEAM, false, false);
        assertCommandSuccess(new HelpCommand("filterteam"), model, expectedCommandResult, expectedModel);
    }

}
