package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.position.Position;

public class TypicalPositions {

    public static final Position JR_SOFTWARE_ENGINEER = new PositionBuilder()
            .withPositionName("Junior Software Engineer").withDescription("Flexible role. Must be hardworking.")
            .withPositionOpenings("6").withRequirements("New graduate", "2nd class and above", "Java").build();
    public static final Position SR_FE_SOFTWARE_ENGINEER = new PositionBuilder()
            .withPositionName("Senior Front-end Software Engineer")
            .withDescription("In-charged of managing an entire frontend feature.")
            .withPositionOpenings("2").withRequirements(">=5 years of experience",
                    "Experienced with React", "Expert in JavaScript").build();
    public static final Position JR_PROJECT_MANAGER = new PositionBuilder()
            .withPositionName("Junior Project Manager")
            .withDescription("Manage and ensure smooth project workflow. Flexible with working overtime.")
            .withPositionOpenings("4").withRequirements(">2 years exp in e-commerce",
                    "Basic programming skills", "Good communicator").build();
    public static final Position QA_ENGINEER = new PositionBuilder()
            .withPositionName("Quality Assurance Engineer")
            .withDescription("Performance testing to ensure SLA is met.")
            .withPositionOpenings("1").withRequirements(">2 years exp",
                    "Creative thinker", "Excellent debugger").build();

    private TypicalPositions() {} // prevents instantiation

    public static List<Position> getTypicalPositions() {
        return new ArrayList<>(Arrays.asList(JR_SOFTWARE_ENGINEER, SR_FE_SOFTWARE_ENGINEER,
                JR_PROJECT_MANAGER, QA_ENGINEER));
    }
}
