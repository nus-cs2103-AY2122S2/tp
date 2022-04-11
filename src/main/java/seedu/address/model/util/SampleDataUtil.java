package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.LinkyTime;
import seedu.address.model.ReadOnlyLinkyTime;
import seedu.address.model.meeting.IsRecurring;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDateTime;
import seedu.address.model.meeting.MeetingDuration;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.MeetingUrl;
import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code LinkyTime} with sample data.
 */
public class SampleDataUtil {
    public static Meeting[] getSampleMeetings() {
        return new Meeting[] {
            new Meeting(
                new MeetingName("Lecture"),
                new MeetingUrl("https://legit-uni.zoom.us/j/3442dfkj99221?pwd=F3ejfa99221"),
                new MeetingDateTime("18-03-2022 1400"),
                new MeetingDuration("2"),
                new Module("CS2103T"),
                new IsRecurring("Y"),
                getTagSet("recorded", "lecturequiz")
            ),
            new Meeting(
                new MeetingName("Weekly Meeting"),
                new MeetingUrl("https://legit-uni.zoom.us/j/3442138ukj99221?pwd=F3ejfa99221"),
                new MeetingDateTime("14-03-2022 1800"),
                new MeetingDuration("2"),
                new Module("CS2103T"),
                new IsRecurring("Y"),
                getTagSet("tp")
            ),
            new Meeting(
                new MeetingName("Tutorial"),
                new MeetingUrl("https://legit-uni.zoom.us/j/3abc922qwdu1?pwd=F3a99rgb221"),
                new MeetingDateTime("17-03-2022 1300"),
                new MeetingDuration("1"),
                new Module("CS2103T"),
                new IsRecurring("Y"),
                getTagSet("participation")
            ),
            new Meeting(
                new MeetingName("PE Dry Run"),
                new MeetingUrl("https://legit-uni.zoom.us/j/3442dfkj99221?pwd=F3ejfa99221"),
                new MeetingDateTime("01-04-2022 1345"),
                new MeetingDuration("1.5"),
                new Module("CS2103T"),
                new IsRecurring("N"),
                getTagSet("graded")
            ),
            new Meeting(
                new MeetingName("PE Exam"),
                new MeetingUrl("https://legit-uni.zoom.us/j/3442dfkj99221?pwd=F3ejfa99221"),
                new MeetingDateTime("16-04-2022 1345"),
                new MeetingDuration("2.5"),
                new Module("CS2103T"),
                new IsRecurring("N"),
                getTagSet("graded")
            ),
            new Meeting(
                new MeetingName("Lecture"),
                new MeetingUrl("https://legit-uni.zoom.us/j/93sd28jvnk2921?pwd=F3a9als7421"),
                new MeetingDateTime("16-03-2022 1000"),
                new MeetingDuration("2"),
                new Module("CS2106"),
                new IsRecurring("Y"),
                getTagSet("recorded")
            ),
            new Meeting(
                new MeetingName("Tutorial"),
                new MeetingUrl("https://legit-uni.zoom.us/j/1abaslk849y2?pwd=F3jdf12mvf21"),
                new MeetingDateTime("16-03-2022 1400"),
                new MeetingDuration("1"),
                new Module("CS2106"),
                new IsRecurring("Y"),
                getTagSet()
            ),
            new Meeting(
                new MeetingName("Lab"),
                new MeetingUrl("https://legit-uni.zoom.us/j/1a2398sllab49y2?pwd=F3129fmvf21"),
                new MeetingDateTime("14-02-2022 1300"),
                new MeetingDuration("1"),
                new Module("CS2106"),
                new IsRecurring("N"),
                getTagSet()
            ),
            new Meeting(
                new MeetingName("Midterm"),
                new MeetingUrl("https://legit-uni.zoom.us/j/93sd28jvnk2921?pwd=F3a9als7421"),
                new MeetingDateTime("12-03-2022 1300"),
                new MeetingDuration("2"),
                new Module("CS2106"),
                new IsRecurring("N"),
                getTagSet("exams", "graded", "openbook")
            ),
            new Meeting(
                new MeetingName("Tutorial"),
                new MeetingUrl("https://legit-uni.zoom.us/j/1awe2398sll3249y2?pwd=F312sdf9fmvf21"),
                new MeetingDateTime("15-03-2022 1200"),
                new MeetingDuration("2"),
                new Module("CS2101"),
                new IsRecurring("Y"),
                getTagSet("participation")
            ),
            new Meeting(
                new MeetingName("OP2 Presentation"),
                new MeetingUrl("https://legit-uni.zoom.us/j/1awe23sll3249y2?pwd=F312sdf9fmvf21"),
                new MeetingDateTime("01-04-2022 1600"),
                new MeetingDuration("2"),
                new Module("CS2101"),
                new IsRecurring("N"),
                getTagSet("presentation")
            ),
            new Meeting(
                new MeetingName("OP1 Presentation"),
                new MeetingUrl("https://legit-uni.zoom.us/j/1awe23sll3249y2?pwd=F312sdf9fmvf21"),
                new MeetingDateTime("08-02-2022 1600"),
                new MeetingDuration("2"),
                new Module("CS2101"),
                new IsRecurring("N"),
                getTagSet("presentation")
            ),
            new Meeting(
                new MeetingName("Lecture"),
                new MeetingUrl("https://legit-uni.zoom.us/j/1awell3242?pwd=F31a12sdf9fmvf21"),
                new MeetingDateTime("18-03-2022 1200"),
                new MeetingDuration("2"),
                new Module("ST2334"),
                new IsRecurring("Y"),
                getTagSet("notrecorded")
            ),
            new Meeting(
                new MeetingName("Tutorial"),
                new MeetingUrl("https://legit-uni.zoom.us/j/1awll3242?pwd=F312sdfdf9fmvf21"),
                new MeetingDateTime("15-03-2022 1000"),
                new MeetingDuration("2"),
                new Module("GES1035"),
                new IsRecurring("Y"),
                getTagSet()
            ),
            new Meeting(
                new MeetingName("Group Presentation"),
                new MeetingUrl("https://legit-uni.zoom.us/j/1awll3242?pwd=F312sdfdf9fmvf21"),
                new MeetingDateTime("22-02-2022 1000"),
                new MeetingDuration("2"),
                new Module("GES1035"),
                new IsRecurring("N"),
                getTagSet("graded", "presentation")
            ),
            new Meeting(
                new MeetingName("Final Quiz"),
                new MeetingUrl("https://legit-uni.zoom.us/j/1awll3242?pwd=F312sdfdf9fmvf21"),
                new MeetingDateTime("13-04-2022 2000"),
                new MeetingDuration("1"),
                new Module("GES1035"),
                new IsRecurring("N"),
                getTagSet("graded", "exams", "openbook")
            ),
            new Meeting(
                new MeetingName("Weekly Check In"),
                new MeetingUrl("https://legit-company.zoom.us/j/1awll323242?pwd=F312sdgfdfdf9fmvf21"),
                new MeetingDateTime("02-04-2022 1400"),
                new MeetingDuration("0.5"),
                new Module("Internship"),
                new IsRecurring("Y"),
                getTagSet()
            )
        };
    }

    public static Module[] getSampleModules() {
        return new Module[] {
            new Module("CS2103T"),
            new Module("CS2101"),
            new Module("CS2106"),
            new Module("ST2334"),
            new Module("GES1035"),
            new Module("Internship")
        };
    }

    public static ReadOnlyLinkyTime getSampleLinkyTime() {
        final LinkyTime sampleLinkyTime = new LinkyTime();
        for (final Meeting sampleMeeting : getSampleMeetings()) {
            sampleLinkyTime.addMeeting(sampleMeeting);
        }
        for (final Module sampleModule : getSampleModules()) {
            sampleLinkyTime.addModule(sampleModule);
        }
        return sampleLinkyTime;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
