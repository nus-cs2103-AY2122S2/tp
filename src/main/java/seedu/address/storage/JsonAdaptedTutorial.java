package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.AssessmentResultsList;
import seedu.address.model.assessment.UniqueAssessmentList;
import seedu.address.model.attendance.AttendanceList;
import seedu.address.model.tutorial.Day;
import seedu.address.model.tutorial.Time;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.tutorial.Venue;

/**
 * Jackson-friendly version of {@link Tutorial}.
 */
class JsonAdaptedTutorial {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutorial's %s field is missing!";
    public static final String MISSING_ASSESSMENT = "The assessment, %s, is missing from storage"
            + "so its results cannot be retrieved.";
    public static final String INVALID_ATTENDANCE_WEEKS = "The attendance list has been incorrectly "
            + "modified and cannot be retrieved.";

    private final String tutorialName;
    private final String venue;
    private final String day;
    private final String time;
    private final String weeks;
    private final JsonAdaptedAttendanceList attendanceList;
    private final List<JsonAdaptedAssessmentResults> assessmentResultsList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTutorial} with the given tutorial details.
     */
    @JsonCreator
    public JsonAdaptedTutorial(@JsonProperty("tutorialName") String tutorialName, @JsonProperty("venue") String venue,
                             @JsonProperty("day") String day, @JsonProperty("time") String time,
                               @JsonProperty("weeks") String weeks,
                               @JsonProperty("attendanceList") JsonAdaptedAttendanceList attendanceList,
                               @JsonProperty("assessmentResultsList") List<JsonAdaptedAssessmentResults> results) {
        this.tutorialName = tutorialName;
        this.venue = venue;
        this.day = day;
        this.time = time;
        this.weeks = weeks;
        this.attendanceList = attendanceList;
        if (results != null) {
            this.assessmentResultsList.addAll(results);
        }
    }

    /**
     * Converts a given {@code Tutorial} into this class for Jackson use.
     */
    public JsonAdaptedTutorial(Tutorial source) {
        tutorialName = source.getTutorialName().name;
        venue = source.getVenue().value;
        day = source.getDay().day;
        time = source.getTime().time;
        weeks = String.valueOf(source.getWeeks());
        attendanceList = new JsonAdaptedAttendanceList(source.getAttendanceList());
        assessmentResultsList.addAll(source.getUnmodifiableAssessmentResultsList()
                .stream()
                .map(JsonAdaptedAssessmentResults::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted tutorial object into the model's {@code Tutorial} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tutorial.
     */
    public Tutorial toModelType(UniqueAssessmentList assessmentList) throws IllegalValueException {
        if (tutorialName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TutorialName.class.getSimpleName()));
        }
        if (!TutorialName.isValidTutorialName(tutorialName)) {
            throw new IllegalValueException(TutorialName.MESSAGE_CONSTRAINTS);
        }
        final TutorialName modelTutorialName = new TutorialName(tutorialName);

        if (venue == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Venue.class.getSimpleName()));
        }
        if (!Venue.isValidVenue(venue)) {
            throw new IllegalValueException(Venue.MESSAGE_CONSTRAINTS);
        }
        final Venue modelVenue = new Venue(venue);

        if (day == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName()));
        }
        if (!Day.isValidDay(day)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        final Day modelDay = new Day(day);

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(time)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelTime = new Time(time);

        if (weeks == null) {
            throw new IllegalValueException("Weeks field is missing!");
        }
        if (!weeks.chars().allMatch(Character::isDigit) || !(Integer.parseInt(weeks) > 0)
                || !(Integer.parseInt(weeks) <= 60)) {
            throw new IllegalValueException("Weeks must be numeric and range from 1-60");
        }
        final int modelWeeks = Integer.parseInt(weeks);

        final AttendanceList modelAttendanceList = attendanceList.toModelType();
        if (modelWeeks != modelAttendanceList.getWeeks()) {
            throw new IllegalValueException(INVALID_ATTENDANCE_WEEKS);
        }

        final AssessmentResultsList modelAssessmentResultsList = new AssessmentResultsList(modelTutorialName);
        for (JsonAdaptedAssessmentResults assessmentResults : assessmentResultsList) {
            AssessmentName assessmentName = assessmentResults.getAssessmentName();
            if (!assessmentList.containsByName(assessmentName)) {
                throw new IllegalValueException(MISSING_ASSESSMENT);
            }
            // the full mark of the assessment is passed into toModelType() to check that all
            // student results have a score that is not larger than the full marks
            modelAssessmentResultsList.add(assessmentResults.toModelType(
                    assessmentList.getByName(assessmentName).getFullMark()));
        }

        return new Tutorial(modelTutorialName, modelVenue, modelDay, modelTime,
                modelWeeks, modelAttendanceList, modelAssessmentResultsList);
    }
}
