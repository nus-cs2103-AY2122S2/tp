package seedu.address.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents a class that provide insights based on data.
 */
public class DataAnalyzer {
    private static final List<Tag> POSITION_TAGS = List.of(new Tag("PG"), new Tag("SG"),
            new Tag("SF"), new Tag("PF"), new Tag("C"));
    private static final String MESSAGE_BALANCED_TEAM =
            "Well done! Seems like the number of players in each position are balanced! ";
    private static final String MESSAGE_UNBALANCED_TEAM =
            "It seems like your club is short of %s players. Consider recruiting more of them. ";
    private static final String MESSAGE_UNCLASSIFIED_PLAYERS =
            "Meanwhile, you still have %d unclassified players. "
            + "Please tag them by their position so that MyGM can have a better\nunderstanding of your club.";

    /**
     * Analyzes the number of players by position.
     *
     * @param persons List of players.
     * @return String message indicating the suggestion given by MyGM.
     */
    public static String analyzePlayerPosition(ObservableList<Person> persons) {
        Map<Tag, Integer> positions = new HashMap<>();
        for (Tag tag : POSITION_TAGS) {
            positions.put(tag, 0);
        }

        int numOfUnclassifiedPlayers = 0;

        for (Person p : persons) {
            boolean isClassified = false;

            for (Tag tag : POSITION_TAGS) {
                if (p.getTags().contains(tag)) {
                    positions.replace(tag, positions.get(tag) + 1);
                    isClassified = true;
                }
            }

            if (!isClassified) {
                numOfUnclassifiedPlayers++;
            }
        }

        double averagePlayers = positions.values().stream().reduce(0, (x, y) -> x + y) / ((double) 5);

        List<Tag> lackingPlayers = new ArrayList<Tag>();

        for (Tag tag : POSITION_TAGS) {
            if (positions.get(tag) < averagePlayers - 1 || positions.get(tag) < 2) {
                lackingPlayers.add(tag);
            }
        }

        String result = "";

        if (lackingPlayers.isEmpty()) {
            result += MESSAGE_BALANCED_TEAM;
        } else {
            String lackingPositions = "";
            for (int i = 0; i < lackingPlayers.size(); i++) {
                String lackingPosition = lackingPlayers.get(i).tagName;
                if (i > 1 && i == lackingPlayers.size() - 1) {
                    lackingPositions += " and ";
                } else if (i > 0) {
                    lackingPositions += ", ";
                }

                lackingPositions += lackingPosition;
            }

            result += String.format(MESSAGE_UNBALANCED_TEAM, lackingPositions);
        }

        if (numOfUnclassifiedPlayers == 0) {
            return result;
        } else {
            result += String.format(MESSAGE_UNCLASSIFIED_PLAYERS, numOfUnclassifiedPlayers);
        }

        return result;
    }


}
