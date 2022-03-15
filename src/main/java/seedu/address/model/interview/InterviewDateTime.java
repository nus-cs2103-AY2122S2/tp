/*package seedu.address.model.interview;

import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InterviewDateTime {
    public static final String MESSAGE_CONSTRAINTS =
            "Date and Time should be specified in the format dd-MM-yyyy HH:mm";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private final LocalDateTime interviewDateTime;
    private final String dateTime;

    public InterviewDateTime(String dateTime) {
        this.d
    }
    public boolean isValid(String interviewDateTime){
        try {
            formattedDateTime = LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_DATETIME);
        }
    }
}*/
