package seedu.address.testutil;

import java.util.Random;

import seedu.address.model.procedure.Cost;
import seedu.address.model.procedure.Date;
import seedu.address.model.procedure.Information;

/**
 * Holds typical details that can make up a Procedure. Date field has 3 additional separate components to account for
 * AddProcCommandTest (autosort variant). For example, the dates in the first index are earlier than the dates in
 * the second index. This is true for the second index with respect to the third index.
 */

public class TypicalRandomProcedureDetails {

    public static final Information[] EXAMPLE_INFORMATION = {
        new Information("Apple Inc."), new Information("Google Inc."), new Information("Singtel Inc.")
    };
    public static final Cost[] EXAMPLE_COST = {
        new Cost("10.00"), new Cost("23.23"), new Cost("76.10")
    };
    public static final Date[][] EXAMPLE_DATE_SEPARATED_BY_PERIOD = {
            {new Date("29/02/2012 10:00"), new Date("10/10/2015 13:45"), new Date("02/03/2013 09:00")},
            {new Date("25/12/2015 16:00"), new Date("01/02/2016 17:12"), new Date("03/11/2018 12:00")},
            {new Date("12/12/2018 09:00"), new Date("29/02/2020 11:10"), new Date("03/03/2022 11:01")}
    };

    private static Random rand = new Random();
    private static final Date EARLY_DATE = EXAMPLE_DATE_SEPARATED_BY_PERIOD[0][rand.nextInt(3)];
    private static final Date NEUTRAL_DATE = EXAMPLE_DATE_SEPARATED_BY_PERIOD[1][rand.nextInt(3)];
    private static final Date FUTURE_DATE = EXAMPLE_DATE_SEPARATED_BY_PERIOD[2][rand.nextInt(3)];

    public TypicalRandomProcedureDetails() {
    }

    Information getInfo() {
        int randInt = rand.nextInt(3);
        return EXAMPLE_INFORMATION[randInt];
    }

    Cost getCost() {
        int randInt = rand.nextInt(3);
        return EXAMPLE_COST[randInt];
    }

    Random getRand() {
        return rand;
    }

    /**
     * Returns a random array of dates that have been pre-sorted.
     *
     * @return Date array containing random dates for Procedure generation.
     */

    Date[] getDates() {
        return new Date[]{EARLY_DATE, NEUTRAL_DATE, FUTURE_DATE};
    }
}
