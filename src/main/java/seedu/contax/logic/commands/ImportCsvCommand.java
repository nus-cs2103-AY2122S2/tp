package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_FILE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TAG;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.logic.parser.ParserUtil;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.IndexedCsvFile;
import seedu.contax.model.Model;
import seedu.contax.model.person.Address;
import seedu.contax.model.person.Email;
import seedu.contax.model.person.Name;
import seedu.contax.model.person.Person;
import seedu.contax.model.person.Phone;
import seedu.contax.model.person.exceptions.DuplicatePersonException;
import seedu.contax.model.tag.Tag;

public class ImportCsvCommand extends Command {
    public static final String COMMAND_WORD = "importcsv";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports contacts from CSV file"
            + "Parameters: "
            + PREFIX_FILE + "FILEPATH "
            + "[" + PREFIX_NAME + "NAME_POSITION]"
            + "[" + PREFIX_PHONE + "PHONE_POSITION]"
            + "[" + PREFIX_EMAIL + "EMAIL_POSITION]"
            + "[" + PREFIX_ADDRESS + "ADDRESS_POSITION]"
            + "[" + PREFIX_TAG + "TAG_POSITION]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FILE + "/data/file.csv";
    public static final String MESSAGE_NO_FILE_FOUND = "File not found: %s";
    public static final String MESSAGE_SUCCESS = "Imported successfully";
    public static final String MESSAGE_SKIPPED_LINES = "Lines skipped (either bad formatting or duplicates): %s";

    private final IndexedCsvFile toImport;

    /**
     * Creates an ImportCsvCommand to import with specified {@code params}
     */
    public ImportCsvCommand(IndexedCsvFile params) {
        requireNonNull(params);
        this.toImport = params;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        //process file
        try {
            String line = "";
            ArrayList<Integer> skippedLines = new ArrayList<>();
            int lineCounter = 0;
            BufferedReader importedCsv = new BufferedReader(new FileReader(toImport.getFilePath()));

            //skip first line by default as it contains headers
            importedCsv.readLine();

            while ((line = importedCsv.readLine()) != null) {
                lineCounter++;
                String[] importedPerson = line.split(",");
                try {
                    Person toAddPerson = personParser(importedPerson);
                    model.addPerson(toAddPerson);
                } catch (ParseException | DuplicatePersonException e) {
                    skippedLines.add(lineCounter);
                    continue;
                }
            }
            return outputStringBuilder(skippedLines);
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_NO_FILE_FOUND, toImport.getFilePath()));
        }
    }
    private Person personParser(String[] importedPerson) throws ParseException {
        Name toAddName = ParserUtil.parseName(importedPerson[toImport.getNamePositionIndex()]);
        Phone toAddPhone = ParserUtil.parsePhone(importedPerson[toImport.getPhonePositionIndex()]);
        Email toAddEmail = ParserUtil.parseEmail(importedPerson[toImport.getEmailPositionIndex()]);
        Address toAddAddress = ParserUtil.parseAddress(importedPerson[toImport.getAddressPositionIndex()]);
        String[] tags = importedPerson[toImport.getTagPositionIndex()].split(";");
        Set<Tag> toAddTag = ParserUtil.parseTags(Arrays.asList(tags));

        return new Person(toAddName, toAddPhone, toAddEmail, toAddAddress, toAddTag);
    }

    private CommandResult outputStringBuilder(ArrayList<Integer> skippedLines) {
        if (skippedLines.size() > 0) {
            String skippedLinesString = "";
            for (int i = 0; i < skippedLines.size(); i++) {
                skippedLinesString += skippedLines.get(i);
                if (i != skippedLines.size() - 1) {
                    skippedLinesString += ", ";
                }
            }
            return new CommandResult(String.format("%s\n%s", MESSAGE_SUCCESS,
                    String.format(MESSAGE_SKIPPED_LINES, skippedLinesString)));
        } else {
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCsvCommand // instanceof handles nulls
                && toImport.equals(((ImportCsvCommand) other).toImport));
    }
}
