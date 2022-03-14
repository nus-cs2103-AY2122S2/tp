package seedu.contax.logic.commands;

import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.model.AddressBook;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;
import seedu.contax.model.person.Person;
import seedu.contax.testutil.EditPersonDescriptorBuilder;
import seedu.contax.testutil.PersonBuilder;

public class ChainCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new Schedule(), new UserPrefs());
    }

    @Test
    public void execute_chainedList_success() {
        assertCommandSuccess(new ChainCommand(List.of(new ListCommand())), model,
                new CommandResult(ListCommand.MESSAGE_SUCCESS,
                GuiListContentType.PERSON), expectedModel);
    }

    @Test
    public void execute_chainedEditThenList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new Schedule(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        List<Command> commandList = new ArrayList<>();

        commandList.add(editCommand);
        commandList.add(new ListCommand());

        assertCommandSuccess(new ChainCommand(commandList),
                model, new CommandResult(ListCommand.MESSAGE_SUCCESS,
                GuiListContentType.PERSON), expectedModel);
    }
}
