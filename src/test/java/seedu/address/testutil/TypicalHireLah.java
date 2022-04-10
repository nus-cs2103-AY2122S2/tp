package seedu.address.testutil;

import static seedu.address.testutil.TypicalApplicants.getTypicalApplicants;
import static seedu.address.testutil.TypicalInterviews.getTypicalInterviews;
import static seedu.address.testutil.TypicalPositions.getTypicalPositions;

import seedu.address.model.HireLah;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;


public class TypicalHireLah {
    /**
     * Returns an {@code HireLah} with all the typical persons and interviews.
     */
    public static HireLah getTypicalHireLah() {
        HireLah ab = new HireLah();
        for (Applicant applicant : getTypicalApplicants()) {
            ab.addApplicant(applicant);
        }

        for (Interview interview : getTypicalInterviews()) {
            ab.addInterview(interview);
        }

        for (Position position : getTypicalPositions()) {
            ab.addPosition(position);
        }

        return ab;
    }
}
