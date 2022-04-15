package seedu.address.logic.commands.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.task.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sort the list of persons in the address book by total strengths in descending order and display to the user.
 */
public class SortStrengthCommand extends Command {

    public static final String COMMAND_WORD = "sort-strength";

    public static final String MESSAGE_SUCCESS = "Sorted all persons by most strengths";

    /**
     * Comparator that compares two Person objects by their total strengths.
     * If total strengths of two Person objects are equal, then compare by least total weaknesses
     */
    protected static final Comparator<Person> BY_STRENGTHS = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            //if total strengths are equal, order by least weaknesses
            if (o1.getStrengths().size() == o2.getStrengths().size()) {
                return o1.getWeaknesses().size() - o2.getWeaknesses().size();
            } else if (o1.getStrengths().size() > o2.getStrengths().size()) {
                return -1;
            } else {
                return 1;
            }
        }
    };

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getUnfilteredPersonList();
        List<Person> newPersonList = new ArrayList<>(lastShownPersonList);
        AddressBook newAddressBook = new AddressBook();

        if (newPersonList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_EMPTY_PERSON_LIST);
        }

        newPersonList.sort(BY_STRENGTHS);
        for (Person p : newPersonList) {
            newAddressBook.addPerson(p);
        }
        model.setAddressBook(new AddressBook(newAddressBook)); //update address book with new sorted book
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
