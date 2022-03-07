package seedu.contax.logic.commands;

import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.contax.model.ImportCsv;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.UserPrefs;
import seedu.contax.testutil.ImportCsvObjectBuilder;
import seedu.contax.testutil.PersonBuilder;

public class ImportCsvCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullImportCsv_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportCsvCommand(null));
    }

    @Test
    public void execute_contactsInFileAcceptedByModel_addSucessful() throws Exception {
        ImportCsv validCsvFormat = new ImportCsvObjectBuilder().build();
        ImportCsvCommand importCsvCommand = new ImportCsvCommand(validCsvFormat);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        PersonBuilder personBuilder1 = new PersonBuilder().withName("Person 1");
        personBuilder1.withPhone("12345678");
        personBuilder1.withEmail("example1@example.com");
        personBuilder1.withAddress("Example address 1");
        personBuilder1.withTags("tag1", "tag2");
        expectedModel.addPerson(personBuilder1.build());
        PersonBuilder personBuilder2 = new PersonBuilder().withName("Person 2");
        personBuilder2.withPhone("12345678");
        personBuilder2.withEmail("example2@example.com");
        personBuilder2.withAddress("Example address 2");
        personBuilder2.withTags("tag1");
        expectedModel.addPerson(personBuilder2.build());

        assertCommandSuccess(importCsvCommand, model, ImportCsvCommand.MESSAGE_SUCCESS, expectedModel);

    }
}
