package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import seedu.address.model.person.Person;

/**
 * Compares {@code Person} objects according to one or more attributes.
 */
public class PersonComparator implements Comparator<Person> {
    private final List<Comparator<Person>> comparators;

    /**
     * Constructs an {@code PersonComparator}.
     *
     * @param comparators The list of comparators that will be used to compare {@code Person} objects
     * in the specified order.
     */
    public PersonComparator(List<Comparator<Person>> comparators) {
        requireNonNull(comparators);

        if (comparators.isEmpty()) {
            throw new IllegalArgumentException("comparators cannot be empty");
        }

        this.comparators = comparators;
    }

    @Override
    public int compare(Person person1, Person person2) {
        assert (!comparators.isEmpty());
        Comparator<Person> combinedComparator = comparators.stream().reduce(Comparator::thenComparing).get();
        return combinedComparator.compare(person1, person2);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonComparator // instanceof handles nulls
                && comparators.equals(((PersonComparator) other).comparators)); // state check
    }
}
