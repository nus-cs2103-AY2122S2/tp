package seedu.address.model.person.insights;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

public abstract class Insight {

    public abstract Insight getInsight(Person person, Model model);
    public abstract String getAsString();

}

