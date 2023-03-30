package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class FinalYearOptionsRootPane extends BorderPane {

	private CreateStudentProfilePane cspp;
	private SelectModulesPane smp;

	private ReserveModulesPane rmp;
	private FinalYearOptionsMenuBar mstmb;

	private OverviewSelectionPane ost;
	private TabPane tp;

	public FinalYearOptionsRootPane() {
		//create tab pane and disable tabs from being closed
		tp = new TabPane();
		tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		//create panes
		cspp = new CreateStudentProfilePane();
		smp = new SelectModulesPane();  // create the select module pane
		rmp = new ReserveModulesPane();  // create the reserve module pane
		ost = new OverviewSelectionPane();  // create the overview selection tab


		//create tabs with panes added
		Tab t1 = new Tab("Create Profile", cspp);
		Tab t2 = new Tab("Select Modules", smp);  // create a new tab for select module pane
		Tab t3 = new Tab("Reserve Modules", rmp);  // create a new tab for reserve module pane
		Tab t4 = new Tab("Overview", ost);  // create a new tab for overview selection tab

		//add tabs to tab pane
		tp.getTabs().addAll(t1, t2, t3, t4);  // add both tabs to the tab pane

		//create menu bar
		mstmb = new FinalYearOptionsMenuBar();

		//add menu bar and tab pane to this root pane
		this.setTop(mstmb);
		this.setCenter(tp);
	}





	// methods allowing sub-containers to be accessed by the controller.
	public CreateStudentProfilePane getCreateStudentProfilePane() {
		return cspp;
	}

	public SelectModulesPane getSelectModulesPane() {
		return smp;
	}

	public ReserveModulesPane getReserveModulesPane() { return rmp; }

	public OverviewSelectionPane getOverviewSelectionPane() { return ost; }



	public FinalYearOptionsMenuBar getModuleSelectionToolMenuBar() {
		return mstmb;
	}

	// method to allow the controller to change tabs
	public void changeTab(int index) {
		tp.getSelectionModel().select(index);
	}
}
