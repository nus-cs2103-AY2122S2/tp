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
    public static final String SKIP_CSV_FILEPATH = "./src/test/data/ImportCsvTest/SkipLineContaXFormat.csv";

    private static final String PERSON1_NAME = "Person 1";
    private static final String PERSON1_PHONE = "12345678";
    private static final String PERSON1_EMAIL = "example1@example.com";
    private static final String PERSON1_ADDRESS = "Example address 1";

    private static final String PERSON2_NAME = "Person 2";
    private static final String PERSON2_PHONE = "12345678";
    private static final String PERSON2_EMAIL = "example2@example.com";
    private static final String PERSON2_ADDRESS = "Example address 2";

    private static final String PERSON5_NAME = "Person 5";
    private static final String PERSON5_PHONE = "+659111111";
    private static final String PERSON5_EMAIL = "example5@example.com";
    private static final String PERSON5_ADDRESS = "Example address 5";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullImportCsv_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportCsvCommand(null));
    }

    @Test
    public void execute_validPersonsInFile_addSucessful() throws Exception {
        //Valid CSV file, adds successfully to list
        ImportCsv validCsvFormat = new ImportCsvObjectBuilder().build();
        ImportCsvCommand importCsvCommand = new ImportCsvCommand(validCsvFormat);

        //Build expecting model with Person 1 and 2
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        PersonBuilder personBuilder1 = new PersonBuilder().withName(PERSON1_NAME)
                .withPhone(PERSON1_PHONE).withEmail(PERSON1_EMAIL)
                .withAddress(PERSON1_ADDRESS).withTags("tag1", "tag2");
        expectedModel.addPerson(personBuilder1.build());
        PersonBuilder personBuilder2 = new PersonBuilder().withName(PERSON2_NAME)
                .withPhone(PERSON2_PHONE).withEmail(PERSON2_EMAIL)
                .withAddress(PERSON2_ADDRESS).withTags("tag1");
        expectedModel.addPerson(personBuilder2.build());

        assertCommandSuccess(importCsvCommand, model, ImportCsvCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_someBadFormatPersonsInFile_badFormatSkipped() throws Exception {
        //CSV contains Person 3 and Person 4 with invalid params, these two are skipped
        ImportCsv validCsvFormat = new ImportCsvObjectBuilder(SKIP_CSV_FILEPATH).build();
        ImportCsvCommand importCsvCommand = new ImportCsvCommand(validCsvFormat);

        //Build expecting model with Person 1, 2 and 5
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        PersonBuilder personBuilder1 = new PersonBuilder().withName(PERSON1_NAME)
                .withPhone(PERSON1_PHONE).withEmail(PERSON1_EMAIL)
                .withAddress(PERSON1_ADDRESS).withTags("tag1", "tag2");
        expectedModel.addPerson(personBuilder1.build());
        PersonBuilder personBuilder2 = new PersonBuilder().withName(PERSON2_NAME)
                .withPhone(PERSON2_PHONE).withEmail(PERSON2_EMAIL)
                .withAddress(PERSON2_ADDRESS).withTags("tag1");
        expectedModel.addPerson(personBuilder2.build());
        PersonBuilder personBuilder5 = new PersonBuilder().withName(PERSON5_NAME)
                .withPhone(PERSON5_PHONE).withEmail(PERSON5_EMAIL)
                .withAddress(PERSON5_ADDRESS).withTags("tag2", "tag3");
        expectedModel.addPerson(personBuilder5.build());

        assertCommandSuccess(importCsvCommand, model, String.format("%s\n%s", ImportCsvCommand.MESSAGE_SUCCESS,
                String.format(ImportCsvCommand.MESSAGE_SKIPPED_LINES, "3, 4")), expectedModel);
    }

    @Test
    public void execute_duplicatePersonsImported_duplicatesSkipped() throws Exception {
        //build a model with the person 1 already inside, then try importing.
        //result should be only person 2 imported

        //Use csv file with valid Person 1 and Person 2
        ImportCsv validCsvFormat = new ImportCsvObjectBuilder().build();
        ImportCsvCommand importCsvCommand = new ImportCsvCommand(validCsvFormat);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        //Build initial model with person 1 inside
        PersonBuilder personBuilder1 = new PersonBuilder().withName(PERSON1_NAME)
                .withPhone(PERSON1_PHONE).withEmail(PERSON1_EMAIL)
                .withAddress(PERSON1_ADDRESS).withTags("tag1", "tag2");
        model.addPerson(personBuilder1.build());

        //Build expecting model with person 1 and 2
        expectedModel.addPerson(personBuilder1.build());
        PersonBuilder personBuilder2 = new PersonBuilder().withName(PERSON2_NAME)
                .withPhone(PERSON2_PHONE).withEmail(PERSON2_EMAIL)
                .withAddress(PERSON2_ADDRESS).withTags("tag1");
        expectedModel.addPerson(personBuilder2.build());

        assertCommandSuccess(importCsvCommand, model, String.format("%s\n%s", ImportCsvCommand.MESSAGE_SUCCESS,
                String.format(ImportCsvCommand.MESSAGE_SKIPPED_LINES, "1")), expectedModel);
    }
}
