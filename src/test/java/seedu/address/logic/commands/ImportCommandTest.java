package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ImportCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void check_duplicate_person() {
        List<Person> personList = new ArrayList<>();
        personList.add(new PersonBuilder().withName("Alice Pauline").build());
        System.out.println(personList.get(0));

        ImportCommand testCommand = new ImportCommand(personList);
        assertThrows(CommandException.class, () -> testCommand.execute(model));
    }
}
