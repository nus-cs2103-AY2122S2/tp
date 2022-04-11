package seedu.address.ui;

import static java.util.Objects.isNull;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Company;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Pronoun;
import seedu.address.model.tag.Tag;

/**
 * Card that displays a persons particulars, including their name, pronouns, tags, and occupation details.
 */
public class ParticularsCard extends UiPart<Region> {
    private static final String FXML = "ParticularsCard.fxml";
    private final Logger logger = LogsCenter.getLogger(ParticularsCard.class);

    @FXML
    private Label name;

    @FXML
    private Label pronouns;

    @FXML
    private FlowPane tags;

    @FXML
    private Label occupation;

    /**
     * Creates a {@code ParticularsCard} with the given personal particulars.
     */
    public ParticularsCard(Name name, Set<Pronoun> pronouns, Set<Tag> tags, JobTitle job, Company company) {
        super(FXML);
        this.name.setText(name.toString());
        this.pronouns.setText(pronounsToString(pronouns));
        tags.forEach(tag -> this.tags.getChildren().add(new Label(tag.tagName)));

        if (!isNull(job) && !isNull(company)) {
            occupation.setText(job.jobTitle + ", " + company.company);
        } else if (!isNull(job)) {
            occupation.setText(job.jobTitle);
        } else if (!isNull(company)) {
            occupation.setText(company.company);
        }
    }

    /**
     * Converts a {@code Set} of pronouns into a human-readable {@code String}.
     *
     * @param pronouns Pronouns associated with a person.
     * @return Pronouns in string form.
     */
    public String pronounsToString(Set<Pronoun> pronouns) {
        if (!pronouns.isEmpty()) {
            StringBuilder pronounsBuilder = new StringBuilder().append("(");
            Iterator<Pronoun> iterator = pronouns.iterator();
            while (iterator.hasNext()) {
                Pronoun p = iterator.next();

                if (iterator.hasNext()) {
                    pronounsBuilder.append(p).append("/");
                } else {
                    pronounsBuilder.append(p).append(")");
                }
            }
            return pronounsBuilder.toString();
        } else {
            return "";
        }
    }
}
