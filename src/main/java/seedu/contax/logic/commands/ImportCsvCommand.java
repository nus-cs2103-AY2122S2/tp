package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    public static final String MESSAGE_NO_FILE_FOUND = "File not found: ";
    public static final String MESSAGE_SUCCESS = "Imported successfully";
    public static final String MESSAGE_SKIPPED_LINES = "Lines skipped due to invalid formatting: %s";


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
        //process file
        try {
            String line = "";
            ArrayList<Integer> skippedLines = new ArrayList<Integer>();
            int lineCounter = 0;
            BufferedReader importedCsv = new BufferedReader(new FileReader(toImport.getFilePath()));

            //skip first line by default as it contains headers
            importedCsv.readLine();
            while ((line = importedCsv.readLine()) != null) {
                lineCounter++;
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
                    skippedLines.add(lineCounter);
                    continue;
                }
            }
            if (skippedLines.size() > 0) {
                String skippedLinesString = "";
                for (int i = 0; i < skippedLines.size(); i++) {
                    skippedLinesString += skippedLines.get(i);
                    if (i != skippedLines.size() - 1) {
                        skippedLinesString += " ";
                    }
                }

                return new CommandResult(String.format("%s %s", MESSAGE_SUCCESS,
                        String.format(MESSAGE_SKIPPED_LINES, skippedLinesString)));
            } else {
                return new CommandResult(MESSAGE_SUCCESS);
            }
        } catch (IOException e) {
            throw new CommandException(MESSAGE_NO_FILE_FOUND);
        }

        //use params to read the file

        //load the content into the current list
    }
}
