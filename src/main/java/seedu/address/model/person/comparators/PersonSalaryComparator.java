
package seedu.address.model.person.comparators;

import java.util.Comparator;

import seedu.address.model.person.Person;
import seedu.address.model.person.Salary;

public class PersonSalaryComparator extends PersonFlagComparator implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        int flagCompare = super.compare(person1, person2);
        if (flagCompare == 0) {
            Salary person1Salary = person1.getSalary();
            Salary person2Salary = person2.getSalary();
            return person1Salary.compare(person2Salary);
        } else {
            return flagCompare;
        }
    }
}
