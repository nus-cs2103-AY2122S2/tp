package seedu.contax.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import seedu.contax.logic.commands.CommandResult;
import seedu.contax.logic.commands.ImportCsvCommand;
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

public class CsvManager {
    private Model model;

    /**
     * Creates a CsvManager to import to specified {@code model}
     */
    public CsvManager(Model model) {
        this.model = model;
    }

    /**
     * Imports a given {@code IndexedCSvFile} to a {@code model}
     * @param csvFile File to be imported
     * @return CommandResult upon success
     * @throws IOException if reading from file fails
     */
    public CommandResult importCsv(IndexedCsvFile csvFile) throws IOException {
        BufferedReader importedCsv = new BufferedReader(new FileReader(csvFile.getFilePath()));
        String line = "";
        ArrayList<Integer> skippedLines = new ArrayList<>();
        int lineCounter = 0;
        //skip first line as it contains headers
        importedCsv.readLine();

        while ((line = importedCsv.readLine()) != null) {
            lineCounter++;
            String[] importedPerson = line.split(",");
            try {
                Person toAddPerson = personParser(importedPerson, csvFile);
                model.addPerson(toAddPerson);
                Set<Tag> tags = toAddPerson.getTags();
                for (Tag tag : tags) {
                    if (!model.hasTag(tag)) {
                        model.addTag(tag);
                    }
                }
            } catch (ParseException | DuplicatePersonException e) {
                skippedLines.add(lineCounter);
                continue;
            }
        }
        return outputStringBuilder(skippedLines);
    }

    private Person personParser(String[] importedPerson, IndexedCsvFile csvFile) throws ParseException {
        Name toAddName = ParserUtil.parseName(importedPerson[csvFile.getNamePositionIndex()]);
        Phone toAddPhone = ParserUtil.parsePhone(importedPerson[csvFile.getPhonePositionIndex()]);
        Email toAddEmail = ParserUtil.parseEmail(importedPerson[csvFile.getEmailPositionIndex()]);
        Address toAddAddress = ParserUtil.parseAddress(importedPerson[csvFile.getAddressPositionIndex()]);
        String[] tags = importedPerson[csvFile.getTagPositionIndex()].split(";");
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
            return new CommandResult(String.format("%s\n%s", ImportCsvCommand.MESSAGE_SUCCESS,
                    String.format(ImportCsvCommand.MESSAGE_SKIPPED_LINES, skippedLinesString)));
        } else {
            return new CommandResult(ImportCsvCommand.MESSAGE_SUCCESS);
        }
    }
}
