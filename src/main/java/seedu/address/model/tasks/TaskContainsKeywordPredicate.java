package seedu.address.model.tasks;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFORMATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;

public class TaskContainsKeywordPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TaskContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        for (String group : keywords) {
            boolean containsAllGroupTerms = true;
            ArgumentMultimap fields = ArgumentTokenizer.tokenize(" " + group,
                    PREFIX_HEADER, PREFIX_INFORMATION, PREFIX_DATE, PREFIX_TIME);

            List<String> headers = fields.getAllValues(PREFIX_HEADER);
            List<String> infos = fields.getAllValues(PREFIX_INFORMATION);
            List<String> dates = fields.getAllValues(PREFIX_DATE);
            List<String> times = fields.getAllValues(PREFIX_TIME);

            if (!headers.isEmpty() && !headers.stream().allMatch(h -> task.getHeader().contains(h))) {
                containsAllGroupTerms = false;
            }

            if (!infos.isEmpty() && !infos.stream().allMatch(t -> task.getInformation().contains(t))) {
                containsAllGroupTerms = false;
            }

            if (!dates.isEmpty() && !dates.stream().allMatch(d -> task.getDate().toString().contains(d))) {
                containsAllGroupTerms = false;
            }

            if (!times.isEmpty() && !times.stream().allMatch(t-> task.getTime().toString().contains(t))) {
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
        if (!(other instanceof TaskContainsKeywordPredicate)) {
            return false;
        }

        // state check
        TaskContainsKeywordPredicate e = (TaskContainsKeywordPredicate) other;
        return keywords.equals(e.keywords);
    }
}
