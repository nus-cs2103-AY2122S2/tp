package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonAdaptedLesson {
    private final String name;
    private final String subject;
    private final String address;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("name") String name, @JsonProperty("subject") String subject,
             @JsonProperty("address") String address, @JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.name = name;
        this.subject = subject;
        this.address = address;
        if (students != null) {
            this.students.addAll(students);
        }
    }
}
