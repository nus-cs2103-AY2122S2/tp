package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Stack;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


class CommandManagerTest {
    private final ModelManager modelManager = new ModelManager();
    private final CommandManager commandManager = new CommandManager(modelManager);

    @Test
    public void constructor() {
        assertEquals(-1, commandManager.getCommandStackPointer());
        assertEquals(new Stack<Command>(), commandManager.getCommandStack());
        assertEquals(new ModelManager(), commandManager.getModel());
    }

    //todo: write better test cases
    @Test
    void insertCommand() throws CommandException {
        //insert all sorts of command
        //check if stack is the same
        Stack<Command> checkStack = new Stack<>();
        ModelManager modelManager = new ModelManager();
        CommandManager commandManager = new CommandManager(modelManager);

        //insert addCommand
        Person bob = new PersonBuilder().withName("Bob").build();
        commandManager.insertCommand(new AddCommand(bob));
        checkStack.push(new AddCommand(bob));
        assertEquals(checkStack, commandManager.getCommandStack());
        assertEquals(0, commandManager.getCommandStackPointer());
    }

    @Test
    void insertCommand_undo() throws CommandException {
        //insert undo command
        //check if stack is the same
        Stack<Command> checkStack = new Stack<>();
        ModelManager modelManager = new ModelManager();
        CommandManager commandManager = new CommandManager(modelManager);

        //insert undo Command
        commandManager.insertCommand(new UndoCommand());
        assertEquals(checkStack, commandManager.getCommandStack());
        assertEquals(-1, commandManager.getCommandStackPointer());
    }

    @Test
    void insertCommand_redo() throws CommandException {
        //insert undo command
        //check if stack is the same
        Stack<Command> checkStack = new Stack<>();
        ModelManager modelManager = new ModelManager();
        CommandManager commandManager = new CommandManager(modelManager);

        //insert undo Command
        commandManager.insertCommand(new RedoCommand());
        assertEquals(checkStack, commandManager.getCommandStack());
        assertEquals(-1, commandManager.getCommandStackPointer());
    }

    //todo: this seems odd
    @Test
    void refreshFutureCommands() throws CommandException {
        ModelManager modelManager = new ModelManager();
        CommandManager commandManager = new CommandManager(modelManager);

        Person bob = new PersonBuilder().withName("Bob").build();
        commandManager.insertCommand(new AddCommand(bob));

        commandManager.insertCommand(new HelpCommand());

        commandManager.refreshFutureCommands(0);

        assertEquals(1, commandManager.getCommandStackPointer());
    }

    //todo: do this test
    @Test
    void undo() throws CommandException {
        ModelManager modelManager = new ModelManager();
        CommandManager commandManager = new CommandManager(modelManager);

        //insert addCommand
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand command = new AddCommand(bob);
        commandManager.insertCommand(command);

        commandManager.undo();
    }

    @Test
    void redo() throws CommandException {
        ModelManager modelManager = new ModelManager();
        CommandManager commandManager = new CommandManager(modelManager);

        //insert addCommand
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand command = new AddCommand(bob);
        commandManager.insertCommand(command);

        commandManager.undo();
        commandManager.redo();
    }
}
