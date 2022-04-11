package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.model.DisplayType;
import seedu.address.model.Model;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.StudentIdContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.model.person.TutorialContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose attributes contains any of the argument keywords by prefix.
 * Keyword matching is case insensitive.
 */
public class FindByPrefixCommand extends FindCommand {

    public static final String COMMAND_WORD = "find";

    //to be edited
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose details contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicateName;
    private final PhoneContainsKeywordsPredicate predicatePhone;
    private final EmailContainsKeywordsPredicate predicateEmail;
    private final AddressContainsKeywordsPredicate predicateAddress;
    private final TagContainsKeywordsPredicate predicateTags;
    private final StudentIdContainsKeywordsPredicate predicateStudentId;
    private final TutorialContainsKeywordsPredicate predicateTutorialName;

    /**
     * Finds and lists all persons in address book whose name contains any of the argument keywords.
     * Partial keyword matching is allowed but matching starts from the first letter.
     * Keyword matching is case insensitive.
     */
    public FindByPrefixCommand(NameContainsKeywordsPredicate predicateName,
                               PhoneContainsKeywordsPredicate predicatePhone,
                               EmailContainsKeywordsPredicate predicateEmail,
                               AddressContainsKeywordsPredicate predicateAddress,
                               TagContainsKeywordsPredicate predicateTags,
                               StudentIdContainsKeywordsPredicate predicateStudentId,
                               TutorialContainsKeywordsPredicate predicateTutorialName) {
        super();
        this.predicateName = predicateName;
        this.predicatePhone = predicatePhone;
        this.predicateEmail = predicateEmail;
        this.predicateAddress = predicateAddress;
        this.predicateTags = predicateTags;
        this.predicateStudentId = predicateStudentId;
        this.predicateTutorialName = predicateTutorialName;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Person> all = new ArrayList<>();

        // find all with matching name
        model.updateFilteredPersonList(predicateName);
        all.addAll(model.getFilteredPersonList());

        // find all with matching phone
        model.updateFilteredPersonList(predicatePhone);
        all.addAll(model.getFilteredPersonList());

        // find all with matching email
        model.updateFilteredPersonList(predicateEmail);
        all.addAll(model.getFilteredPersonList());

        // find all with matching address
        model.updateFilteredPersonList(predicateAddress);
        all.addAll(model.getFilteredPersonList());

        // find all with matching tag in tags
        model.updateFilteredPersonList(predicateTags);
        all.addAll(model.getFilteredPersonList());

        // find all with matching studentId
        model.updateFilteredStudentList(predicateStudentId);
        all.addAll(model.getFilteredPersonList());

        // find all with matching tutorialName
        model.updateFilteredPersonList(predicateTutorialName);
        all.addAll(model.getFilteredPersonList());

        // filter out duplicate contacts
        List<Person> allNoDupes = new ArrayList<>(new LinkedHashSet<>(all));
        model.setFilteredPersonsMultiPredList(allNoDupes);

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                        model.getFilteredPersonsMultiPredList().size()), false, false, DisplayType.FINDBYPREFIX);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindByPrefixCommand // instanceof handles nulls
                && predicateName.equals(((FindByPrefixCommand) other).predicateName)
                && predicateTags.equals(((FindByPrefixCommand) other).predicateTags)
                && predicateEmail.equals(((FindByPrefixCommand) other).predicateEmail)
                && predicateAddress.equals(((FindByPrefixCommand) other).predicateAddress)
                && predicateTutorialName.equals(((FindByPrefixCommand) other).predicateTutorialName)
                && predicateStudentId.equals(((FindByPrefixCommand) other).predicateStudentId));
    }


}
