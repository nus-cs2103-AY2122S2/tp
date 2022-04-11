package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAGE;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;

public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        for (String group : keywords) {
            boolean containsAllGroupTerms = true;
            ArgumentMultimap fields = ArgumentTokenizer.tokenize(" " + group, PREFIX_NAME,
                    PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_JOB, PREFIX_STAGE);

            List<String> names = fields.getAllValues(PREFIX_NAME);
            List<String> phone = fields.getAllValues(PREFIX_PHONE);
            List<String> emails = fields.getAllValues(PREFIX_EMAIL);
            List<String> addresses = fields.getAllValues(PREFIX_ADDRESS);
            List<String> jobs = fields.getAllValues(PREFIX_JOB);
            List<String> stages = fields.getAllValues(PREFIX_STAGE);

            if (!names.isEmpty() && !names.stream().allMatch(n -> person.getName().contains(n))) {
                containsAllGroupTerms = false;
            }

            if (!phone.isEmpty() && !phone.stream().allMatch(p -> person.getPhone().contains(p))) {
                containsAllGroupTerms = false;
            }

            if (!emails.isEmpty() && !emails.stream().allMatch(e -> person.getEmail().contains(e))) {
                containsAllGroupTerms = false;
            }

            if (!addresses.isEmpty() && !addresses.stream().allMatch(a -> person.getAddress().contains(a))) {
                containsAllGroupTerms = false;
            }

            if (!jobs.isEmpty() && !jobs.stream().allMatch(j -> person.getJob().contains(j))) {
                containsAllGroupTerms = false;
            }

            if (!stages.isEmpty() && !stages.stream().allMatch(s -> person.getStage().contains(s))) {
                containsAllGroupTerms = false;
            }

            if (containsAllGroupTerms) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonContainsKeywordsPredicate)) {
            return false;
        }

        // state check
        PersonContainsKeywordsPredicate e = (PersonContainsKeywordsPredicate) other;
        return keywords.equals(e.keywords);
    }
}
