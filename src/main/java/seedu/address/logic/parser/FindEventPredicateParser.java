package seedu.address.logic.parser;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindEventCommand;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventInfoContainsKeywordsPredicate;
import seedu.address.model.event.EventNameContainsKeywordsPredicate;
import seedu.address.model.event.EventParticipantsContainsKeywordsPredicate;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.CcaContainsKeywordsPredicateAnd;
import seedu.address.model.person.EducationContainsKeywordsPredicateAnd;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.InternshipContainsKeywordsPredicateAnd;
import seedu.address.model.person.ModuleContainsKeywordsPredicateAnd;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FindEventPredicateParser {
    /**
     * Parses a FindEventDescriptor into a single predicate that is true if any of the predicates in each field
     * of the descriptor are satisfied.
     *
     * @param eventDescriptor an object describing the predicate list for each field.
     * @return a FindCommand to be executed.
     */
    public FindEventCommand parse(FindEventCommand.FindEventDescriptor eventDescriptor) {
        List<Predicate<Event>> predicateList = new ArrayList<>();

        eventDescriptor.getStringNames().ifPresent(names ->
                predicateList.add(new EventNameContainsKeywordsPredicate(names)));
        eventDescriptor.getStringInformations().ifPresent(infos ->
                predicateList.add(new EventInfoContainsKeywordsPredicate(infos)));
        eventDescriptor.getStringParticipants().ifPresent(parts ->
                predicateList.add(new EventParticipantsContainsKeywordsPredicate(parts)));

        Predicate<Event> predicate = predicateList.stream().reduce(x->false, Predicate::or);

        return new FindEventCommand(predicate);
    }
}
