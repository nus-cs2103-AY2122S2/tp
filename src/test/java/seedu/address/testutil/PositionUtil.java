package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUM_OPENINGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;

import seedu.address.logic.commands.position.AddPositionCommand;
import seedu.address.model.position.Position;

/**
 * A utility class for Position.
 */
public class PositionUtil {
    /**
     * Returns an add command string for adding the {@code position}.
     */
    public static String getAddPositionCommand(Position position) {
        return AddPositionCommand.COMMAND_WORD + " -p " + getPositionDetails(position);
    }

    /**
     * Returns the part of command string for the given {@code position}'s details.
     */
    public static String getPositionDetails(Position position) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_POSITION + position.getPositionName().positionName + " ");
        sb.append(PREFIX_NUM_OPENINGS + position.getPositionOpenings().getCount().toString() + " ");
        sb.append(PREFIX_DESCRIPTION + position.getDescription().descriptionText + " ");
        position.getRequirements().stream().forEach(r -> sb.append(PREFIX_REQUIREMENT + r.requirementText + " "));
        return sb.toString();
    }
}
