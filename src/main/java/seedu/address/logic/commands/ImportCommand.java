package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.Type.PERSON;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE =
            "Imports all job applicants data from csv file. Accepts absolute or relative path.\n"
            + "The csv file needs to contain the same fields as the 'add [p]' command.\n"
            + "Parameters: filepath to csv file\n"
            + "Example: import ../../past_data.csv";

    private List<Person> persons;

    /**
     * Constructor for Import Command.
     *
     * @param persons List of applicants to be added.
     */
    public ImportCommand(List<Person> persons) {
        requireNonNull(persons);
        this.persons = persons;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // checks if any persons is already in address book.
        for (int i = 0; i < persons.size(); i++) {
            if (model.hasPerson(persons.get(i))) {
                int entryNum = i + 1;
                throw new CommandException("Entry " + entryNum + " is already in address book.\nAborting now.");
            }
        }

        persons.forEach(model::addPerson);
        return new CommandResult("Added " + persons.size() + " applicants to address book.", getType());
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        persons.forEach(person -> s.append(person.toString() + "\n"));
        return s.toString().strip();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ImportCommand)) {
            return false;
        }

        // state check
        ImportCommand e = (ImportCommand) other;
        return this.toString().equals(e.toString());
    }

    @Override
    public Type getType() {
        return PERSON;
    }
}
