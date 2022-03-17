package seedu.address.model.person;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.module.Module;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsModulePredicate implements Predicate<Person> {
    // May possibly change to a list of modules
    private final Module module;

    public NameContainsModulePredicate(String module) {
        this.module = new Module(module);
    }

    @Override
    public boolean test(Person person) {
        // instead of converting to stream, it is simply a module now
        // so, to do the "test", we only need to check if module is within the set of modules
        Set<Module> modules = person.getModules();
        return modules.contains(module);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsModulePredicate // instanceof handles nulls
                && module.equals(((NameContainsModulePredicate) other).module)); // state check
    }

}
