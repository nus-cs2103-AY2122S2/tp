package unibook.ui.listpanels;

import static unibook.ui.util.CustomListChangeListeners.addIndexedListChangeListener;
import static unibook.ui.util.CustomPaneListFiller.fillPaneFromList;

import java.util.function.BiFunction;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import unibook.commons.core.LogsCenter;
import unibook.model.module.Module;
import unibook.ui.UiPart;
import unibook.ui.cards.ModuleCard;

/**
 * Panel containing the list of modules.
 */
public class ModuleListPanel extends UiPart<Region> {
    private static final String FXML = "listpanels/ModuleListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ModuleListPanel.class);

    private ObservableList<Module> moduleList;

    @FXML
    private VBox moduleListView;

    /**
     * Creates a {@code ModuleListPanel} with the given {@code ObservableList}.
     */
    public ModuleListPanel(ObservableList<Module> moduleList) {
        super(FXML);
        this.moduleList = moduleList;

        BiFunction<Module, Integer, Node> cardCreator = new BiFunction<Module, Integer, Node>() {
            @Override
            public Node apply(Module module, Integer index) {
                return new ModuleCard(module, index + 1).getRoot();
            }
        };

        fillPaneFromList(moduleListView, this.moduleList, cardCreator);

        addIndexedListChangeListener(moduleListView, this.moduleList, cardCreator);

    }
}
