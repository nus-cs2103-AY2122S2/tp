package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindCommand.FindPersonDescriptor;
import seedu.address.model.person.AddressContainsKeywordsPredicateOr;
import seedu.address.model.person.CcaContainsKeywordsPredicateOr;
import seedu.address.model.person.EducationContainsKeywordsPredicateOr;
import seedu.address.model.person.EmailContainsKeywordsPredicateOr;
import seedu.address.model.person.InternshipContainsKeywordsPredicateOr;
import seedu.address.model.person.ModuleContainsKeywordsPredicateOr;
import seedu.address.model.person.NameContainsKeywordsPredicateOr;
import seedu.address.model.person.Person;
import seedu.address.model.person.PhoneContainsKeywordsPredicateOr;

public class FindOrPredicateParser {


    /**
     * Parses a FindPersonDescriptor into a single predicate that is true if any of the predicates in each field
     * of the descriptor are satisfied. A field is satisfied if any item from the list appears. The combined predicate
     * is satisfied if any of the fields are satisfied.
     *
     * @param personDescriptor an object describing the predicte list for each field.
     * @return a FindCommand to be executed.
     */
    public FindCommand parse(FindPersonDescriptor personDescriptor) {
        List<Predicate<Person>> predicateList = new ArrayList<>();

        personDescriptor.getStringNames().ifPresent(names ->
                predicateList.add(new NameContainsKeywordsPredicateOr(names)));
        personDescriptor.getStringPhones().ifPresent(phones ->
                predicateList.add(new PhoneContainsKeywordsPredicateOr(phones)));
        personDescriptor.getStringEmails().ifPresent(emails ->
                predicateList.add(new EmailContainsKeywordsPredicateOr(emails)));
        personDescriptor.getStringAddresses().ifPresent(list ->
                predicateList.add(new AddressContainsKeywordsPredicateOr(list)));

        personDescriptor.getStringEducations().ifPresent(list ->
                predicateList.add(new EducationContainsKeywordsPredicateOr(list)));
        personDescriptor.getStringInternships().ifPresent(list ->
                predicateList.add(new InternshipContainsKeywordsPredicateOr(list)));
        personDescriptor.getStringModules().ifPresent(list ->
                predicateList.add(new ModuleContainsKeywordsPredicateOr(list)));
        personDescriptor.getStringCcas().ifPresent(list ->
                predicateList.add(new CcaContainsKeywordsPredicateOr(list)));

        Predicate<Person> predicate = predicateList.stream().reduce(x->false, Predicate::or);

        return new FindCommand(predicate);
    }
}
