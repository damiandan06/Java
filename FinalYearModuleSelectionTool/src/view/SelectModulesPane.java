package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import model.Module;

public class SelectModulesPane extends GridPane {
    private ListView<Module> term1UnselectedModulesListView;
    private ListView<Module> term2UnselectedModulesListView;
    private ListView<Module> yearLongSelectedModulesListView;
    private ListView<Module> term1SelectedModulesListView;
    private ListView<Module> term2SelectedModulesListView;
    private Label term1CreditsLabel;
    private Label term2CreditsLabel;
    private Button term1AddButton;
    private Button term1RemoveButton;
    private Button term2AddButton;
    private Button term2RemoveButton;
    private Button resetButton;
    private Button submitButton;

    private int term1Credits;
    private int term2Credits;

    public SelectModulesPane() {

        term1CreditsLabel = new Label("Term 1 Credits: 0");
        term2CreditsLabel = new Label("Term 2 Credits: 0");


        //styling
        this.setVgap(5);
        this.setHgap(5);
        this.setAlignment(Pos.CENTER);

        ColumnConstraints column0 = new ColumnConstraints();
        column0.setHalignment(HPos.RIGHT);

        this.getColumnConstraints().addAll(column0);


        term1UnselectedModulesListView = new ListView<>();
        term2UnselectedModulesListView = new ListView<>();
        yearLongSelectedModulesListView = new ListView<>();
        term1SelectedModulesListView = new ListView<>();
        term2SelectedModulesListView = new ListView<>();

        term1UnselectedModulesListView.setPrefSize(320, 150);
        term2UnselectedModulesListView.setPrefSize(320, 150);
        yearLongSelectedModulesListView.setPrefSize(200, 100);
        term1SelectedModulesListView.setPrefSize(320, 150);
        term2SelectedModulesListView.setPrefSize(320, 150);

        term1CreditsLabel = new Label("Term 1 Credits: 0");
        term2CreditsLabel = new Label("Term 2 Credits: 0");
        term1AddButton = new Button("Add");
        term1RemoveButton = new Button("Remove");
        term2AddButton = new Button("Add");
        term2RemoveButton = new Button("Remove");
        resetButton = new Button("Reset");
        submitButton = new Button("Submit");

        // Unselected modules
        this.add(new Label("Term 1 Unselected Modules:"), 0, 0);
        this.add(term1UnselectedModulesListView, 0, 1);

        // Term 1 Add/Remove buttons
        this.add(new Label("Term 1"), 0, 3);
        this.add(term1AddButton, 1, 3);
        this.add(term1RemoveButton, 2, 3);

        this.add(new Label("Term 2 Unselected Modules:"), 0, 4);
        this.add(term2UnselectedModulesListView, 0, 5);


        // Selected modules

        this.add(new Label("Term 1 Selected Modules:"), 3, 0);
        this.add(term1SelectedModulesListView, 3, 1);

        // Term 2 Add/Remove buttons
        this.add(new Label("Term 2"), 0, 6);
        this.add(term2AddButton, 1, 6);
        this.add(term2RemoveButton, 2, 6);

        this.add(new Label("Term 2 Selected Modules:"), 3, 4);
        this.add(term2SelectedModulesListView, 3, 5);



        this.add(new Label("Year Long Selected Modules:"), 3, 7);
        this.add(yearLongSelectedModulesListView, 3, 8);


        //display credits


        this.add(term1CreditsLabel, 0, 8);
        this.add(term2CreditsLabel, 2, 8);


        // Reset and Submit buttons
        this.add(resetButton, 0, 10);
        this.add(submitButton, 1, 10);



    }
    // Methods to retrieve selected modules from unselected list views via Controller's Selection Models instance

    public Module getTerm1SelectedModule() {
        return term1UnselectedModulesListView.getSelectionModel().getSelectedItem();
    }

    public Module getTerm2SelectedModule(){
        return term2UnselectedModulesListView.getSelectionModel().getSelectedItem();
    }



    public Module getTerm1UnselectedModule() {
        return term1SelectedModulesListView.getSelectionModel().getSelectedItem();
    }

    public Module getTerm2UnselectedModule() {
        return term2SelectedModulesListView.getSelectionModel().getSelectedItem();
    }


    // Methods to add selected module to selected modules lists view

    public void addModuleToTerm1SelectedListView(Module module) {
        term1SelectedModulesListView.getItems().add(module);
    }

    public void addModuleToTerm2SelectedListView(Module module) {

        term2SelectedModulesListView.getItems().add(module);
    }





    // Methods to add unselected module to unselected modules lists view
    public void addModuleToTerm1UnselectedListView(Module module) {
        term1UnselectedModulesListView.getItems().add(module);
    }

    public void addModuleToTerm2UnselectedListView(Module module) {
        term2UnselectedModulesListView.getItems().add(module);
    }


    // Method to add year long module to selected modules list view

    public void addModuleToYearLongSelectedListView(Module module) {
        yearLongSelectedModulesListView.getItems().add(module);
    }
















    //write method to clear selected modules without removing mandatory modules for each term
    public void clearSelectedModules() {


        term1SelectedModulesListView.getItems().clear();
        term2SelectedModulesListView.getItems().clear();
        yearLongSelectedModulesListView.getItems().clear();
    }



    public void clearUnselectedModules() {
        term1UnselectedModulesListView.getItems().clear();
        term2UnselectedModulesListView.getItems().clear();
    }


    public void removeSelectedModule() {
        term1SelectedModulesListView.getItems().remove(term1SelectedModulesListView.getSelectionModel().getSelectedItem());
        term2SelectedModulesListView.getItems().remove(term2SelectedModulesListView.getSelectionModel().getSelectedItem());
    }





    // Methods to add event handlers to buttons
    public void addTerm1SelectedModuleHandler(EventHandler<ActionEvent> handler) {
        term1AddButton.setOnAction(handler);
    }

    public void addTerm2SelectedModuleHandler(EventHandler<ActionEvent> handler) {
        term2AddButton.setOnAction(handler);
    }

    public void removeTerm1UnselectedModuleHandler(EventHandler<ActionEvent> handler) {
        term1RemoveButton.setOnAction(handler);
    }

    public void removeTerm2UnselectedModuleHandler(EventHandler<ActionEvent> handler){
        term2RemoveButton.setOnAction(handler);
    }

    public void addSubmitStudentProfileHandler(EventHandler<ActionEvent> handler){
    submitButton.setOnAction(handler);
    }

    public void addResetStudentProfileHandler(EventHandler<ActionEvent>handler) {
        resetButton.setOnAction(handler);



    }


    // Method to remove modules from  List views
    public void removeModuleFromTerm1UnselectedListView(Module selectedModule) {
        term1UnselectedModulesListView.getItems().remove(selectedModule);
    }


    public void removeModuleFromTerm2UnselectedListView(Module selectedModule) {
        term2UnselectedModulesListView.getItems().remove(selectedModule);
    }

    public void removeModuleFromTerm1SelectedListView(Module unselectedModule) {
        term1SelectedModulesListView.getItems().remove(unselectedModule);

    }


    public void removeModuleFromTerm2SelectecListView(Module unselectedModule) {
        term2SelectedModulesListView.getItems().remove(unselectedModule);
    }


    public void addSubmitSelectedModulesHandler(EventHandler<ActionEvent> handler) {
        submitButton.setOnAction(handler);
    }

    public ListView<Module> getTerm1SelectedModulesListView() {
        return term1SelectedModulesListView;
    }

    public ListView<Module> getTerm2SelectedModulesListView() {
        return term2SelectedModulesListView;
    }

    public ListView<Module> getTerm1UnselectedModulesListView() {
        return term1UnselectedModulesListView;
    }

    public ListView<Module> getTerm2UnselectedModulesListView() {
        return term2UnselectedModulesListView;
    }



}














