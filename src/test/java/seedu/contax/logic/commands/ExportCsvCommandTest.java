package seedu.contax.logic.commands;

import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;
import seedu.contax.testutil.PersonBuilder;

public class ExportCsvCommandTest {
    private static final String PERSON1_NAME = "Person 1";
    private static final String PERSON1_PHONE = "12345678";
    private static final String PERSON1_EMAIL = "example1@example.com";
    private static final String PERSON1_ADDRESS = "Example address 1";

    private static final String PERSON2_NAME = "Person 2";
    private static final String PERSON2_PHONE = "12345678";
    private static final String PERSON2_EMAIL = "example2@example.com";
    private static final String PERSON2_ADDRESS = "Example address 2";

    private static final String VALID_FILEPATH = "./src/test/data/ExportCsvTest/ValidAddressBook.csv";

    private Model model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());

    @Test
    public void execute_validPersonsInAddressBook_exportSuccessful() {
        //Build model with Person 1 and 2
        PersonBuilder personBuilder1 = new PersonBuilder().withName(PERSON1_NAME)
                .withPhone(PERSON1_PHONE).withEmail(PERSON1_EMAIL)
                .withAddress(PERSON1_ADDRESS).withTags("tag1", "tag2");
        model.addPerson(personBuilder1.build());
        PersonBuilder personBuilder2 = new PersonBuilder().withName(PERSON2_NAME)
                .withPhone(PERSON2_PHONE).withEmail(PERSON2_EMAIL)
                .withAddress(PERSON2_ADDRESS).withTags("tag1");
        model.addPerson(personBuilder2.build());

        Model expectedModel = new ModelManager(model.getAddressBook(), new Schedule(), new UserPrefs());

        assertCommandSuccess(new ExportCsvCommand(VALID_FILEPATH), model,
                new CommandResult(ExportCsvCommand.MESSAGE_SUCCESS), expectedModel);
    }

    @Test
    public void execute_noPersonsInAddressBook_exportSuccessful() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new Schedule(), new UserPrefs());
        assertCommandSuccess(new ExportCsvCommand(VALID_FILEPATH), model,
                new CommandResult(ExportCsvCommand.MESSAGE_SUCCESS), expectedModel);
    }

}
