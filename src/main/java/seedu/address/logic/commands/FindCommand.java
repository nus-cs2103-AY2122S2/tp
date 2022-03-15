package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;


/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    public static final String MESSAGE_NO_PARAMETERS = "At least one field must be provided.";

    private final Predicate<Person> predicate;

    public FindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class FindPersonDescriptor {
        private List<Name> names;
        private List<Phone> phones;
        private List<Email> emails;
        private List<Address> addresses;


        public FindPersonDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public FindPersonDescriptor(FindPersonDescriptor toCopy) {
            setNames(toCopy.names);
            setPhones(toCopy.phones);
            setEmails(toCopy.emails);
            setAddresses(toCopy.addresses);
        }

        /**
         * Returns true if at least one field is searched for.
         */
        public boolean isAnyFieldPresent() {
            return CollectionUtil.isAnyNonNull(names, phones, emails, addresses);
        }

        public void setNames(List<Name> names) {
            this.names = names;
        }

        public Optional<List<Name>> getNames() {
            return Optional.ofNullable(names);
        }

        public Optional<List<String>> getStringNames() {
            return getNames().map(names ->
                    names.stream().map(name -> name.fullName).collect(Collectors.toList()));
        }

        public void setPhones(List<Phone> phones) {
            this.phones = phones;
        }

        public Optional<List<Phone>> getPhones() {
            return Optional.ofNullable(phones);
        }

        public Optional<List<String>> getStringPhones() {
            return getPhones().map(list ->
                    list.stream().map(name -> name.value).collect(Collectors.toList()));
        }

        public void setEmails(List<Email> emails) {
            this.emails = emails;
        }

        public Optional<List<Email>> getEmails() {
            return Optional.ofNullable(emails);
        }

        public Optional<List<String>> getStringEmails() {
            return getEmails().map(list ->
                    list.stream().map(name -> name.value).collect(Collectors.toList()));
        }

        public void setAddresses(List<Address> addresses) {
            this.addresses = addresses;
        }

        public Optional<List<Address>> getAddresses() {
            return Optional.ofNullable(addresses);
        }

        public Optional<List<String>> getStringAddresses() {
            return getAddresses().map(list ->
                    list.stream().map(name -> name.value).collect(Collectors.toList()));
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCommand.EditPersonDescriptor)) {
                return false;
            }

            // state check
            FindPersonDescriptor e = (FindPersonDescriptor) other;

            return getNames().equals(e.getNames())
                    && getPhones().equals(e.getPhones())
                    && getEmails().equals(e.getEmails())
                    && getAddresses().equals(e.getAddresses());
        }
    }
}


