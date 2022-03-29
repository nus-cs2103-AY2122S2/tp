package manageezpz.logic.commands;

import static manageezpz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static manageezpz.logic.commands.CommandTestUtil.showPersonAtIndex;
import static manageezpz.testutil.TypicalIndexes.INDEX_FIRST;
import static manageezpz.testutil.TypicalPersons.getTypicalAddressBookEmployees;

import org.junit.jupiter.api.Test;

import manageezpz.model.Model;
import manageezpz.model.ModelManager;
import manageezpz.model.UserPrefs;

class ListEmployeeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookEmployees(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookEmployees(), new UserPrefs());

    @Test
    void listEmployeesCommand_notFilteredList_showSameList() {
        assertCommandSuccess(new ListEmployeeCommand(), model, ListEmployeeCommand.MESSAGE_ALL_SUCCESS,
                expectedModel);
    }

    @Test
    void listEmployeesCommand_listIsFiltered_showEverything() {
        showPersonAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListEmployeeCommand(), model, ListEmployeeCommand.MESSAGE_ALL_SUCCESS,
                expectedModel);
    }
}
