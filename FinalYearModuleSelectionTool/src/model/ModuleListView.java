package view;

import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class ModuleListView extends VBox {
    private ListView<String> listView;

    public ModuleListView() {
        // Create list view
        listView = new ListView<>();

        // Set preferred size of list view
        listView.setPrefSize(50, 50);

        // Add list view to VBox
        this.getChildren().add(listView);

        // Set padding and gaps
        this.setPadding(new Insets(10));
        this.setSpacing(10);

        // Set VBox to fill available space both horizontally and vertically
        this.setFillWidth(true);
        this.setFillHeight(true);
    }

    private void setFillHeight(boolean b) {
    }

    public ListView<String> getListView() {
        return listView;
    }
}