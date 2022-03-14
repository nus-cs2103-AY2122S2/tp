package seedu.contax.ui.onboarding;

import seedu.contax.logic.parser.AddressBookParser;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.Model;
import seedu.contax.model.person.Person;
import seedu.contax.model.util.SampleDataUtil;


/**
 * This class provides utilities functions for the onboarding window.
 */
public class OnboardingUtil {

    /**
     * Populates the given Model with the sample data
     * @param model the Model to be populated
     */
    public static void populateWithSample(Model model) {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();
        for (Person p : samplePersons) {
            model.addPerson(p);
        }
    }

    /**
     * Returns the last Person in the given Model
     * @param model the Model to be searched
     * @return Person object of the last person
     */
    public static Person getLatestPerson(Model model) {
        int lastIndex = model.getFilteredPersonList().size() - 1;
        lastIndex = lastIndex < 0 ? 0 : lastIndex;
        return model.getFilteredPersonList().get(lastIndex);
    }

    /**
     * Returns the name string of the last person in the given Model
     * @param model the UniquePersonList to be searched
     * @return name of the last person
     */
    public static String getLatestPersonName(Model model) {
        return getLatestPerson(model).getName().toString();
    }


}
