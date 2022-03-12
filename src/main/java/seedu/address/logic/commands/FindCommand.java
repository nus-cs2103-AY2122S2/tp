package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.Model;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.FieldContainsKeywordsPredicate;
import seedu.address.model.person.predicates.InsurancePackageContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagsContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose fields contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: FIELD KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alice bob charlie";

    private final FieldContainsKeywordsPredicate predicate;

    public FindCommand(FieldContainsKeywordsPredicate predicate) {
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
     * Stores the fields to find the person with. Each non-empty field predicate will be used to set the
     * filteredPersons list.
     */
    public static class FindPersonDescriptor {
        private NameContainsKeywordsPredicate namePredicate;
        private PhoneContainsKeywordsPredicate phonePredicate;
        private EmailContainsKeywordsPredicate emailPredicate;
        private AddressContainsKeywordsPredicate addressPredicate;
        private InsurancePackageContainsKeywordsPredicate insurancePackagePredicate;
        private TagsContainsKeywordsPredicate tagsPredicate;

        public FindPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public FindPersonDescriptor(FindPersonDescriptor toCopy) {
            setNamePredicate(toCopy.namePredicate);
            setPhonePredicate(toCopy.phonePredicate);
            setEmailPredicate(toCopy.emailPredicate);
            setInsurancePackagePredicate(toCopy.insurancePackagePredicate);
            setAddressPredicate(toCopy.addressPredicate);
            setTagsPredicate(toCopy.tagsPredicate);
        }

        /**
         * Returns true if at least one field is applied to find
         */
        public boolean isAnyFieldFind() {
            return CollectionUtil.isAnyNonNull(namePredicate, phonePredicate,
                    emailPredicate, insurancePackagePredicate, addressPredicate, tagsPredicate);
        }

        public void setNamePredicate(NameContainsKeywordsPredicate namePredicate) {
            this.namePredicate = namePredicate;
        }

        public Optional<NameContainsKeywordsPredicate> getNamePredicate() {
            return Optional.ofNullable(namePredicate);
        }

        public void setPhonePredicate(PhoneContainsKeywordsPredicate phonePredicate) {
            this.phonePredicate = phonePredicate;
        }

        public Optional<PhoneContainsKeywordsPredicate> getPhonePredicate() {
            return Optional.ofNullable(phonePredicate);
        }

        public void setEmailPredicate(EmailContainsKeywordsPredicate emailPredicate) {
            this.emailPredicate = emailPredicate;
        }

        public Optional<EmailContainsKeywordsPredicate> getEmailPredicate() {
            return Optional.ofNullable(emailPredicate);
        }

        public void setAddressPredicate(AddressContainsKeywordsPredicate addressPredicate) {
            this.addressPredicate = addressPredicate;
        }

        public Optional<AddressContainsKeywordsPredicate> getAddressPredicate() {
            return Optional.ofNullable(addressPredicate);
        }

        public void setInsurancePackagePredicate(InsurancePackageContainsKeywordsPredicate insurancePackagePredicate) {
            this.insurancePackagePredicate = insurancePackagePredicate;
        }

        public Optional<InsurancePackageContainsKeywordsPredicate> getInsurancePackagePredicate() {
            return Optional.ofNullable(insurancePackagePredicate);
        }

        public void setTagsPredicate(TagsContainsKeywordsPredicate tagsPredicate) {
            this.tagsPredicate = tagsPredicate;
        }

        public Optional<TagsContainsKeywordsPredicate> getTagsPredicate() {
            return Optional.ofNullable(tagsPredicate);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FindPersonDescriptor)) {
                return false;
            }

            // state check
            FindPersonDescriptor e = (FindPersonDescriptor) other;

            return getNamePredicate().equals(e.getNamePredicate())
                    && getPhonePredicate().equals(e.getPhonePredicate())
                    && getEmailPredicate().equals(e.getEmailPredicate())
                    && getInsurancePackagePredicate().equals(e.getInsurancePackagePredicate())
                    && getAddressPredicate().equals(e.getAddressPredicate())
                    && getTagsPredicate().equals(e.getTagsPredicate());
        }
    }
}
