package seedu.address.testutil;

import static seedu.address.testutil.TypicalApplicants.ALICE;
import static seedu.address.testutil.TypicalApplicants.BOB;
import static seedu.address.testutil.TypicalApplicants.CARL;
import static seedu.address.testutil.TypicalApplicants.DANIEL;
import static seedu.address.testutil.TypicalPositions.JR_PROJECT_MANAGER;
import static seedu.address.testutil.TypicalPositions.JR_SOFTWARE_ENGINEER;
import static seedu.address.testutil.TypicalPositions.QA_ENGINEER;
import static seedu.address.testutil.TypicalPositions.SR_FE_SOFTWARE_ENGINEER;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.interview.Interview;


public class TypicalInterviews {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final Interview JR_SWE_INTERVIEW = new InterviewBuilder().withApplicant(ALICE)
            .withDate(LocalDateTime.parse("2022-01-01 12:00", FORMATTER)).withPosition(JR_SOFTWARE_ENGINEER)
            .build();

    public static final Interview SR_FE_INTERVIEW = new InterviewBuilder().withApplicant(BOB)
            .withDate(LocalDateTime.parse("2022-12-31 12:00", FORMATTER)).withPosition(SR_FE_SOFTWARE_ENGINEER)
            .build();

    public static final Interview JR_PROJECT_MANAGER_INTERVIEW = new InterviewBuilder().withApplicant(CARL)
            .withDate(LocalDateTime.parse("2023-01-01 12:00", FORMATTER)).withPosition(JR_PROJECT_MANAGER)
            .build();

    public static final Interview QA_ENGINEER_INTERVIEW = new InterviewBuilder().withApplicant(DANIEL)
            .withDate(LocalDateTime.parse("2023-12-31 12:00", FORMATTER)).withPosition(QA_ENGINEER)
            .build();

    public static List<Interview> getTypicalInterviews() {
        return new ArrayList<>(Arrays.asList(JR_SWE_INTERVIEW, SR_FE_INTERVIEW, JR_PROJECT_MANAGER_INTERVIEW,
                QA_ENGINEER_INTERVIEW));
    }
}
