package seedu.address.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Description;
import seedu.address.model.position.Position;
import seedu.address.model.position.PositionName;
import seedu.address.model.position.PositionOpenings;

/**
 * Panel containing the list of interviews.
 */
public class InterviewListPanel extends UiPart<Region> {
    private static final String FXML = "InterviewListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PositionListPanel.class);

    @FXML
    private ListView<Interview> interviewListView;

    /**
     * Creates a {@code ApplicantListPanel} with the given {@code ObservableList}.
     */
    public InterviewListPanel(ObservableList<Interview> interviewList) {
        super(FXML);

        Position tempPos1 = new Position(
                new PositionName("Senior Software Developer"),
                new Description("More than 5 years experience"),
                new PositionOpenings("1"),
                new HashSet<>());

        Applicant tempApp1 = new Applicant(
                new Name("Bobby Liu"),
                new Phone("91234567"),
                new Email("bobbyboy@gmail.com"),
                new Address("12 Jurong West #03-45 Singapore 682930"),
                new HashSet<>()
        );

        Position tempPos2 = new Position(
                new PositionName("Backend Engineer Intern"),
                new Description("More than 5 years experience"),
                new PositionOpenings("1"),
                new HashSet<>());

        Applicant tempApp2 = new Applicant(
                new Name("Christian Fernando"),
                new Phone("83749234"),
                new Email("christian_fernando@yahoo.com"),
                new Address("12 Jurong West #03-45 Singapore 682930"),
                new HashSet<>()
        );

        List<Interview> tempList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        tempList.add(new Interview(tempApp1, LocalDateTime.parse("2022-03-18 13:00", formatter), tempPos1));
        tempList.add(new Interview(tempApp2, LocalDateTime.parse("2023-03-22 16:00", formatter), tempPos2));
        ObservableList<Interview> intList = FXCollections.observableList(tempList);

        interviewListView.setItems(intList); // TO REPLACE WITH REAL LIST
        interviewListView.setCellFactory(listView -> new InterviewListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Position} using a {@code PositionCard}.
     */
    class InterviewListViewCell extends ListCell<Interview> {
        @Override
        protected void updateItem(Interview interview, boolean empty) {
            super.updateItem(interview, empty);

            if (empty || interview == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new InterviewCard(interview, getIndex() + 1).getRoot());
            }
        }
    }
}
