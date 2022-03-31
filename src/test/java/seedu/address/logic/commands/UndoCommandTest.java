package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class UndoCommandTest {

    private static final StackUndoRedo EMPTY_STACK = new StackUndoRedo();

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final DeleteCommand deleteCommandOne = new DeleteCommand(INDEX_FIRST_PERSON);
    private final DeleteCommand deleteCommandTwo = new DeleteCommand(INDEX_FIRST_PERSON);

    @Test
    public void execute_undoOnEmptyStack_failed() {

        //Create new model
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // Undo Command
        UndoCommand undoCommand = new UndoCommand();

        //execute undo command, expects a command failure
        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_USAGE_FAILURE);

    }
    @Test
    public void execute_undoOnNonEmptyStack_success() throws CommandException {

        StackUndoRedo undoRedoStack = prepareStack(
                Arrays.asList(deleteCommandOne, deleteCommandTwo), Collections.emptyList());
        UndoCommand undoCommand = new UndoCommand();
        undoCommand.setData(undoRedoStack);

        deleteCommandOne.execute(model);
        deleteCommandTwo.execute(model);

        // multiple commands in undoStack
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person firstPerson = expectedModel.getFilteredPersonList().get(0);
        model.deletePerson(firstPerson);
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_USAGE_SUCCESS, expectedModel);

        // single command in undoStack
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_USAGE_SUCCESS, expectedModel);

        // no command in undoStack
        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_USAGE_FAILURE);

    }

    public static StackUndoRedo prepareStack(List<RedoableCommand> undoElements,
                                             List<RedoableCommand> redoElements) {
        StackUndoRedo undoRedoStack = new StackUndoRedo();
        undoElements.forEach(undoRedoStack::push);

        Collections.reverse(redoElements);
        redoElements.forEach(undoRedoStack::push);
        redoElements.forEach(unused -> undoRedoStack.popUndo());

        return undoRedoStack;
    }
}
