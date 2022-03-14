package seedu.contax.storage;

import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.contax.logic.commands.ExportCsvCommand;
import seedu.contax.logic.commands.ImportCsvCommand;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.AddressBook;
import seedu.contax.model.IndexedCsvFile;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;
import seedu.contax.testutil.ImportCsvObjectBuilder;
import seedu.contax.testutil.PersonBuilder;

public class CsvManagerTest {
    private static final String PERSON1_NAME = "Person 1";
    private static final String PERSON1_PHONE = "12345678";
    private static final String PERSON1_EMAIL = "example1@example.com";
    private static final String PERSON1_ADDRESS = "Example address 1";

    private static final String PERSON2_NAME = "Person 2";
    private static final String PERSON2_PHONE = "12345678";
    private static final String PERSON2_EMAIL = "example2@example.com";
    private static final String PERSON2_ADDRESS = "Example address 2";

    private static final String PERSON3_NAME = "Person 3";
    private static final String PERSON3_PHONE = "12345678";
    private static final String PERSON3_EMAIL = "example3@example.com";
    private static final String PERSON3_ADDRESS = "Example address, with comma";

    private static final String EXISTING_FILEPATH = "./src/test/data/ExportCsvTest/ValidAddressBook.csv";
    private static final String NONEXISTANT_FILEPATH = "./src/test/data/ExportCsvTest/nonExistentFile.csv";
    private static final String COMMA_FILEPATH = "./src/test/data/ExportCsvTest/addressWithComma.csv";
    private static final String NOTAG_FILEPATH = "./src/test/data/ExportCsvTest/noTags.csv";

    private Model model = new ModelManager(new AddressBook(), new Schedule(), new UserPrefs());


    @Test
    public void exportThenImportCsv_validAddressBook_success() throws CommandException {
        //Regular flow with existing file
        //Build model with Person 1 and 2
        PersonBuilder personBuilder1 = new PersonBuilder().withName(PERSON1_NAME)
                .withPhone(PERSON1_PHONE).withEmail(PERSON1_EMAIL)
                .withAddress(PERSON1_ADDRESS).withTags("tag1", "tag2");
        model.addPerson(personBuilder1.build());
        PersonBuilder personBuilder2 = new PersonBuilder().withName(PERSON2_NAME)
                .withPhone(PERSON2_PHONE).withEmail(PERSON2_EMAIL)
                .withAddress(PERSON2_ADDRESS).withTags("tag1");
        model.addPerson(personBuilder2.build());

        ExportCsvCommand exportCsvCommand = new ExportCsvCommand(EXISTING_FILEPATH);
        exportCsvCommand.execute(model);
        IndexedCsvFile importBack = new ImportCsvObjectBuilder(EXISTING_FILEPATH).build();
        ImportCsvCommand importCsvCommand = new ImportCsvCommand(importBack);
        //Build empty resultant model
        Model resultantModel = new ModelManager(new AddressBook(), new Schedule(), new UserPrefs());

        assertCommandSuccess(importCsvCommand, resultantModel, ImportCsvCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void exportThenImportCsv_nonExistentOutputFile_success() throws CommandException {
        //If file does not already exist, it should automatically make a file
        //Build model with Person 1 and 2
        PersonBuilder personBuilder1 = new PersonBuilder().withName(PERSON1_NAME)
                .withPhone(PERSON1_PHONE).withEmail(PERSON1_EMAIL)
                .withAddress(PERSON1_ADDRESS).withTags("tag1", "tag2");
        model.addPerson(personBuilder1.build());
        PersonBuilder personBuilder2 = new PersonBuilder().withName(PERSON2_NAME)
                .withPhone(PERSON2_PHONE).withEmail(PERSON2_EMAIL)
                .withAddress(PERSON2_ADDRESS).withTags("tag1");
        model.addPerson(personBuilder2.build());

        ExportCsvCommand exportCsvCommand = new ExportCsvCommand(NONEXISTANT_FILEPATH);
        exportCsvCommand.execute(model);
        IndexedCsvFile importBack = new ImportCsvObjectBuilder(NONEXISTANT_FILEPATH).build();
        ImportCsvCommand importCsvCommand = new ImportCsvCommand(importBack);
        //Build empty resultant model
        Model resultantModel = new ModelManager(new AddressBook(), new Schedule(), new UserPrefs());

        assertCommandSuccess(importCsvCommand, resultantModel, ImportCsvCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void exportThenImport_addressHaveComma_success() throws CommandException {
        //Test to ensure that any potential commas in the address field is still able to export and import
        //Build model with Person 1 and 3
        PersonBuilder personBuilder1 = new PersonBuilder().withName(PERSON1_NAME)
                .withPhone(PERSON1_PHONE).withEmail(PERSON1_EMAIL)
                .withAddress(PERSON1_ADDRESS).withTags("tag1", "tag2");
        model.addPerson(personBuilder1.build());
        PersonBuilder personBuilder3 = new PersonBuilder().withName(PERSON3_NAME)
                .withPhone(PERSON3_PHONE).withEmail(PERSON3_EMAIL)
                .withAddress(PERSON3_ADDRESS).withTags("tag1");
        model.addPerson(personBuilder3.build());

        ExportCsvCommand exportCsvCommand = new ExportCsvCommand(COMMA_FILEPATH);
        exportCsvCommand.execute(model);
        IndexedCsvFile importBack = new ImportCsvObjectBuilder(COMMA_FILEPATH).build();
        ImportCsvCommand importCsvCommand = new ImportCsvCommand(importBack);
        //Build empty resultant model
        Model resultantModel = new ModelManager(new AddressBook(), new Schedule(), new UserPrefs());

        assertCommandSuccess(importCsvCommand, resultantModel, ImportCsvCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void exportThenImport_noTags_success() throws CommandException {
        //Test to ensure contacts with no tags are properly imported/exported
        //Build model with Person 1 and 2
        PersonBuilder personBuilder1 = new PersonBuilder().withName(PERSON1_NAME)
                .withPhone(PERSON1_PHONE).withEmail(PERSON1_EMAIL)
                .withAddress(PERSON1_ADDRESS);
        model.addPerson(personBuilder1.build());
        PersonBuilder personBuilder2 = new PersonBuilder().withName(PERSON2_NAME)
                .withPhone(PERSON2_PHONE).withEmail(PERSON2_EMAIL)
                .withAddress(PERSON2_ADDRESS);
        model.addPerson(personBuilder2.build());

        ExportCsvCommand exportCsvCommand = new ExportCsvCommand(NOTAG_FILEPATH);
        exportCsvCommand.execute(model);
        IndexedCsvFile importBack = new ImportCsvObjectBuilder(NOTAG_FILEPATH).build();
        ImportCsvCommand importCsvCommand = new ImportCsvCommand(importBack);
        //Build empty resultant model
        Model resultantModel = new ModelManager(new AddressBook(), new Schedule(), new UserPrefs());

        assertCommandSuccess(importCsvCommand, resultantModel, ImportCsvCommand.MESSAGE_SUCCESS, model);
    }
}
