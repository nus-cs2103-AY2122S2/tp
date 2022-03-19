package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Edits the details of an existing person in the address book.
 */
public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Copy the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final List<Prefix> prefixes;

    /**
     * @param index of the person in the filtered person list to edit
     * @param prefixes details to copy the person with
     */
    public CopyCommand(Index index, List<Prefix> prefixes) {
        requireNonNull(index);
        requireNonNull(prefixes);

        this.index = index;
        this.prefixes = prefixes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToCopy = lastShownList.get(index.getZeroBased());

        List<String> copiedFields = copyFields(personToCopy, prefixes);
        System.out.println(copiedFields);
        return new CommandResult(copiedFields.toString(), false, false, false, true);
    }

    private List<String> copyFields(Person personToCopy, List<Prefix> prefixes) {
        List<String> copiedFields = new ArrayList<>();
        for (Prefix prefix : prefixes) {
            copiedFields.add(getPersonField(personToCopy, prefix));
        }
        return copiedFields;
    }

    private String getPersonField(Person person, Prefix prefix) {
        if (prefix.equals(PREFIX_NAME)) {
            return person.getName().toString();

        } else if (prefix.equals(PREFIX_PHONE)) {
            return person.getPhone().toString();

        } else if (prefix.equals(PREFIX_EMAIL)) {
            return person.getEmail().toString();

        } else if (prefix.equals(PREFIX_ADDRESS)) {
            return person.getAddress().toString();

        } else if (prefix.equals(PREFIX_STATUS)) {
            return person.getStatus().toString();

        } else if (prefix.equals(PREFIX_MODULE)) {
            return person.getModules().toString();

        } else {
            return "";
        }
    }
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CopyCommand)) {
            return false;
        }

        // state check
        CopyCommand e = (CopyCommand) other;
        return index.equals(e.index)
                && prefixes.equals(e.prefixes);
    }
}
