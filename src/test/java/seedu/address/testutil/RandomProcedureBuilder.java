package seedu.address.testutil;

import seedu.address.model.procedure.Completion;
import seedu.address.model.procedure.Cost;
import seedu.address.model.procedure.Date;
import seedu.address.model.procedure.Information;
import seedu.address.model.procedure.Procedure;

/**
 * Builds random Procedure objects.
 */
public class RandomProcedureBuilder {

    private Date[] dateList;
    private Information info;
    private Date date;
    private Cost cost;
    private Completion hasCompleted;
    private boolean dateCheck = false;
    private TypicalRandomProcedureDetails procedureDetails = new TypicalRandomProcedureDetails();

    public RandomProcedureBuilder() {
    }

    /**
     * Returns a new random Procedure out of the details selected from TypicalRandomProcedureDetails. Guarantees that
     * when called during the test, it will return three procedures that are sorted by date.
     *
     * @param i Integer to assist in selecting dates from dateList
     * @return A new random Procedure
     */

    public Procedure buildRandomProcedure(int i) {
        info = procedureDetails.getInfo();
        cost = procedureDetails.getCost();
        hasCompleted = new Completion("false");
        if (!dateCheck) {
            dateList = procedureDetails.getDates();
            dateCheck = true;
        }
        date = dateList[i];
        return new Procedure(info, date, cost, hasCompleted);
    }
}
