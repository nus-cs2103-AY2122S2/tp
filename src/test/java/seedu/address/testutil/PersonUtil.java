package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.team.SkillSet;
import seedu.address.model.team.Team;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_GITHUB_USERNAME + person.getGithubUsername().value + " ");
        person.getTeams().stream().forEach(
            s -> sb.append(PREFIX_TEAM + s.teamName + " ")
        );
        person.getSkillSet().getSkillSetInStream().forEach(
            s -> sb.append(PREFIX_SKILL + s.skillName + "_" + s.skillProficiency + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getGithubUsername().ifPresent(address ->
                sb.append(PREFIX_GITHUB_USERNAME).append(address.value).append(" "));
        if (descriptor.getTeams().isPresent()) {
            Set<Team> teams = descriptor.getTeams().get();
            if (teams.isEmpty()) {
                sb.append(PREFIX_TEAM).append(" ");
            } else {
                teams.forEach(s -> sb.append(PREFIX_TEAM).append(s.teamName).append(" "));
            }
        }
        if (descriptor.getSkillSet().isPresent()) {
            SkillSet skillSet = descriptor.getSkillSet().get();
            if (skillSet.getSkillSet().isEmpty()) {
                sb.append(PREFIX_SKILL);
            } else {
                skillSet.getSkillSet().forEach(s -> sb.append(PREFIX_SKILL).append(s.skillName).append("_")
                        .append(s.skillProficiency).append(" "));
            }
        }
        return sb.toString();
    }
}
