package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class OverviewSelectionPane extends GridPane {
    private TextArea studentDetailsTextArea;
    private TextArea selectedModulesTextArea;
    private TextArea reservedModulesTextArea;
    private Button saveOverviewButton;

    public OverviewSelectionPane() {
        // Create text areas
        studentDetailsTextArea = new TextArea();
        selectedModulesTextArea = new TextArea();
        reservedModulesTextArea = new TextArea();

        // Set preferred size of text areas
        studentDetailsTextArea.setPrefSize(450, 150);
        selectedModulesTextArea.setPrefSize(100, 150);
        reservedModulesTextArea.setPrefSize(300, 150);

        // Create save button
        saveOverviewButton = new Button("Save Overview");

        // Add text areas and button to grid pane
        this.add(studentDetailsTextArea, 0, 0);
        this.add(selectedModulesTextArea, 1, 0);
        this.add(reservedModulesTextArea, 1, 1);
        this.add(saveOverviewButton, 0, 1);

        // Set padding and gaps
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
    }

    public TextArea getStudentDetailsTextArea() {
        return studentDetailsTextArea;
    }

    public TextArea getSelectedModulesTextArea() {
        return selectedModulesTextArea;
    }

    public TextArea getReservedModulesTextArea() {
        return reservedModulesTextArea;
    }

    public Button getSaveOverviewButton() {
        return saveOverviewButton;
    }
}