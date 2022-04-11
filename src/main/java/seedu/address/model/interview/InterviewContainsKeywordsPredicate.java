package seedu.address.model.interview;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;

public class InterviewContainsKeywordsPredicate implements Predicate<Interview> {
    private List<String> keywords;

    public InterviewContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Interview interview) {

        for (String group : keywords) {
            boolean containsAllGroupTerms = true;
            ArgumentMultimap fields = ArgumentTokenizer.tokenize(
                    " " + group, PREFIX_DATE, PREFIX_TIME, PREFIX_NAME, PREFIX_JOB);

            List<String> dates = fields.getAllValues(PREFIX_DATE);
            List<String> times = fields.getAllValues(PREFIX_TIME);
            List<String> names = fields.getAllValues(PREFIX_NAME);
            List<String> jobs = fields.getAllValues(PREFIX_JOB);

            if (!dates.isEmpty() && !dates.stream().allMatch(d -> interview.getDate().toString().contains(d))) {
                containsAllGroupTerms = false;
            }

            if (!times.isEmpty() && !times.stream().allMatch(t-> interview.getTime().toString().contains(t))) {
                containsAllGroupTerms = false;
            }

            if (!names.isEmpty() && !names.stream().allMatch(n -> interview.getPerson().getName().contains(n))) {
                containsAllGroupTerms = false;
            }

            if (!jobs.isEmpty() && !jobs.stream().allMatch(j -> interview.getPerson().getJob().contains(j))) {
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
        if (!(other instanceof InterviewContainsKeywordsPredicate)) {
            return false;
        }

        // state check
        InterviewContainsKeywordsPredicate e = (InterviewContainsKeywordsPredicate) other;
        return keywords.equals(e.keywords);
    }
}
