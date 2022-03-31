package seedu.address.model.person.insights;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.Person;


/**
 * Encapsulates an insight.
 */
public class Insights {

    public abstract static class Insight {

        // instantiate flag
        private boolean initialized = false;

        /**
         * Returns an insight given a {@code Person} and {@code Model}.
         */
        public abstract Insight getInsight(Person person, Model model);

        /**
         * Returns the string representation to be shown in the UI.
         */
        public abstract String getAsString();

        protected void markInitialized() {
            this.initialized = true;
        }

        protected boolean isInitialized() {
            return this.initialized;
        }
    }

    /**
     * Statically constructs a MostRecentEventInsight object.
     */
    public static MostRecentEventInsight createMostRecentEventInsight(Person person, Model model) {
        requireAllNonNull(person, model);
        return new MostRecentEventInsight().getInsight(person, model);
    }

    /**
     * Constructs a NumEventsInsight object.
     */
    public static NumEventsInsight createNumEventsInsight(Person person, Model model) {
        requireAllNonNull(person, model);
        return new NumEventsInsight().getInsight(person, model);
    }

    /**
     * Statically constructs a NumLogsInsight object.
     */
    public static NumLogsInsight createNumLogsInsight(Person person, Model model) {
        requireAllNonNull(person, model);
        return new NumLogsInsight().getInsight(person, model);
    }

}

