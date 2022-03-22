package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.common.Name;
import seedu.address.model.person.Person;

/**
 * Represents a command with hidden internal logic and the ability to be executed,
 * specifically with the requirement that it supports INDEX and NAME based access to persons
 * in Amigos.
 */
public abstract class ByIndexByNameCommand extends Command {

    public static final String MESSAGE_INVALID_INDEX = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
    public static final String MESSAGE_PERSON_NOT_FOUND = Messages.MESSAGE_INVALID_PERSON_NAME;

    /**
     * Gets a person by name from the model. Assumes that for a given name, there is
     * exactly one person with the name.
     *
     * @throws CommandException if name provided does not exist in the model.
     */
    public static Person getPersonByName(Model model, Name name) throws CommandException {
        requireAllNonNull(model, name);
        // find person with same name
        List<Person> personsToEdit = model.getAddressBook()
                .getPersonList().stream()
                .filter(p -> p.hasName(name))
                .collect(Collectors.toList());

        // if person not found, throw an error
        if (personsToEdit.size() < 1) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }

        // assumes exactly one person with a given name, assertion to check
        assert (personsToEdit.size() == 1);

        return personsToEdit.get(0);
    }

    /**
     * Gets a person from the model by his index in the filtered list.
     *
     * @throws CommandException if index provided is out of bounds.
     */
    public static Person getPersonByFilteredIndex(Model model, Index index) throws CommandException {
        requireAllNonNull(model, index);

        // get list of persons from model
        List<Person> lastShownList = model.getFilteredPersonList();

        // ensure that index is valid
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }

        return lastShownList.get(index.getZeroBased());
    }
}
