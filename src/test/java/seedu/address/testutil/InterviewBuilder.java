package seedu.address.testutil;

import static seedu.address.testutil.TypicalApplicants.ALICE;
import static seedu.address.testutil.TypicalPositions.JR_SOFTWARE_ENGINEER;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;


public class InterviewBuilder {

    public static final Applicant DEFAULT_APPLICANT = ALICE;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final LocalDateTime DEFAULT_DATE = LocalDateTime.parse("2022-01-01 12:00", FORMATTER);
    public static final Position DEFAULT_POSITION = JR_SOFTWARE_ENGINEER;

    // Data fields
    private Applicant applicant;
    private LocalDateTime date;
    private Position position;

    /**
     * Creates a {@code InterviewBuilder} with the default details.
     */
    public InterviewBuilder() {
        applicant = DEFAULT_APPLICANT;
        date = DEFAULT_DATE;
        position = DEFAULT_POSITION;
    }

    /**
     * Initializes the InterviewBuilder with the data of {@code interviewToCopy}.
     */
    public InterviewBuilder(Interview interviewToCopy) {
        applicant = interviewToCopy.getApplicant();
        date = interviewToCopy.getDate();
        position = interviewToCopy.getPosition();
    }

    /**
     * Sets the {@code applicant} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withApplicant(Applicant interviewApplicant) {
        this.applicant = interviewApplicant;
        return this;
    }

    /**
     * Sets the {@code date} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    /**
     * Sets the {@code position} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withPosition(Position position) {
        this.position = position;
        return this;
    }

    public Interview build() {
        return new Interview(applicant, date, position);
    }
}
