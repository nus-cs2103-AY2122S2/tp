package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.logic.parser.ParserUtil;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.ImportCsv;
import seedu.contax.model.Model;
import seedu.contax.model.person.Address;
import seedu.contax.model.person.Email;
import seedu.contax.model.person.Name;
import seedu.contax.model.person.Person;
import seedu.contax.model.person.Phone;
import seedu.contax.model.tag.Tag;

public class ImportCsvCommand extends Command {
    public static final String COMMAND_WORD = "importcsv";
    public static final String MESSAGE_USAGE = "to be entered";
    public static final String MESSAGE_NO_FILE_FOUND = "file not found";


    private final ImportCsv toImport;

    /**
     * Creates an ImportCsvCommand to import with specified {@code params}
     */
    public ImportCsvCommand(ImportCsv params) {
        requireNonNull(params);
        this.toImport = params;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        //firstly process file
        try {
            String line = "";
            BufferedReader importedCsv = new BufferedReader(new FileReader(toImport.getFilePath()));
            //skip first line by default as it contains headers
            importedCsv.readLine();
            while ((line = importedCsv.readLine()) != null) {
                String[] importedPerson = line.split(",");
                try {
                    Name toAddName = ParserUtil.parseName(importedPerson[toImport.getNamePositionIndex()]);
                    Phone toAddPhone = ParserUtil.parsePhone(importedPerson[toImport.getPhonePositionIndex()]);
                    Email toAddEmail = ParserUtil.parseEmail(importedPerson[toImport.getEmailPositionIndex()]);
                    Address toAddAddress = ParserUtil.parseAddress(importedPerson[toImport.getAddressPositionIndex()]);
                    String[] tags = importedPerson[toImport.getTagPositionIndex()].split(";");
                    Set<Tag> toAddTag = ParserUtil.parseTags(Arrays.asList(tags));
                    Person toAddPerson = new Person(toAddName, toAddPhone, toAddEmail, toAddAddress, toAddTag);
                    model.addPerson(toAddPerson);
                } catch (ParseException e) {
                    continue;
                }
            }
            return new CommandResult("imported success");
        } catch (IOException e) {
            throw new CommandException(MESSAGE_NO_FILE_FOUND);
        }

        //use params to read the file

        //load the content into the current list
    }
}
