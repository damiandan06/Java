package view;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Module;

public class ReserveModulesPane extends GridPane {
    private ListView<Module> term1UnselectedModulesListView;
    private ListView<Module> term1SelectedModulesListView;
    private ListView<Module> term2UnselectedModulesListView;
    private ListView<Module> term2SelectedModulesListView;
    private Accordion termAccordion;

    public ReserveModulesPane() {

        // Create list views
        term1UnselectedModulesListView = new ListView<>();
        term1SelectedModulesListView = new ListView<>();
        term2UnselectedModulesListView = new ListView<>();
        term2SelectedModulesListView = new ListView<>();

        // Set preferred size of list views
        term1UnselectedModulesListView.setPrefSize(320, 100);
        term1SelectedModulesListView.setPrefSize(320, 100);
        term2UnselectedModulesListView.setPrefSize(320, 100);
        term2SelectedModulesListView.setPrefSize(320, 100);

        // Create TitledPane for Term 1
        TitledPane term1Pane = new TitledPane();
        term1Pane.setText("Term 1");

        VBox term1Box = new VBox();
        term1Box.getChildren().addAll(
                new Label("Term 1 Unselected Modules:"), term1UnselectedModulesListView,
                new Label("Term 1 Selected Modules:"), term1SelectedModulesListView,
                new Button("Add"), new Button("Remove"), new Button("Confirm"));

        // Add Term 1 pane to accordion control
        term1Pane.setContent(term1Box);

        // Create TitledPane for Term 2
        TitledPane term2Pane = new TitledPane();
        term2Pane.setText("Term 2");

        VBox term2Box = new VBox();
        term2Box.getChildren().addAll(
                new Label("Term 2 Unselected Modules:"), term2UnselectedModulesListView,
                new Label("Term 2 Selected Modules:"), term2SelectedModulesListView,
                new Button("Add"), new Button("Remove"), new Button("Confirm"));

        // Add Term 2 pane to accordion control
        term2Pane.setContent(term2Box);

        // Create Accordion control
        termAccordion = new Accordion();
        termAccordion.getPanes().addAll(term1Pane, term2Pane);

        this.getChildren().add(termAccordion);

        //styling
        this.setVgap(5);
        this.setHgap(5);
        this.setAlignment(Pos.CENTER);

        ColumnConstraints column0 = new ColumnConstraints();
        column0.setHalignment(HPos.RIGHT);

        this.getColumnConstraints().addAll(column0);

        // Display only one pane at a time
        termAccordion.setExpandedPane(term1Pane);










        }

    public void addUnselectedModuleToReserveListViewTerm1(Module unselectedModule) {
        term1UnselectedModulesListView.getItems().add(unselectedModule);

    }

    public void addUnselectedModuleToReserveListViewTerm2(Module unselectedModuleTerm2) {
        term2UnselectedModulesListView.getItems().add(unselectedModuleTerm2);


    }







}