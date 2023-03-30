package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Course;
import model.Module;
import model.RunPlan;
import model.StudentProfile;
import view.*;

public class FinalYearOptionsController {

	//fields to be used throughout class
	private FinalYearOptionsRootPane view;
	private StudentProfile model;
	
	private CreateStudentProfilePane cspp;
	private FinalYearOptionsMenuBar mstmb;

	private SelectModulesPane smp;

	private ReserveModulesPane rmp;

	private OverviewSelectionPane osp;

	public FinalYearOptionsController(StudentProfile model, FinalYearOptionsRootPane view) {
		//initialise view and model fields
		this.view = view;
		this.model = model;

		
		//initialise view subcontainer fields
		cspp = view.getCreateStudentProfilePane();
		mstmb = view.getModuleSelectionToolMenuBar();
		smp = view.getSelectModulesPane();
		rmp = view.getReserveModulesPane();
		osp = view.getOverviewSelectionPane();

		//add courses to combobox in create student profile pane using the buildModulesAndCourses helper method below
		cspp.addCourseDataToComboBox(buildModulesAndCourses());

		//attach event handlers to view using private helper method
		this.attachEventHandlers();

	}

	
	//helper method - used to attach event handlers
	private void attachEventHandlers() {
		//attach an event handlers
		cspp.addCreateStudentProfileHandler(new CreateStudentProfileHandler());
		smp.addTerm1SelectedModuleHandler(new AddTerm1SelectedModuleHandler());
		smp.addTerm2SelectedModuleHandler(new AddTerm2SelectedModuleHandler());
		smp.removeTerm1UnselectedModuleHandler(new RemoveTerm1SelectedModuleHandler());
		smp.removeTerm2UnselectedModuleHandler(new RemoveTerm2SelectedModuleHandler());
		smp.addResetStudentProfileHandler(new ResetSelectedModulesHandler());

		smp.addSubmitSelectedModulesHandler(new SubmitSelectedModulesHandler());











		//write event handler to Create Student Profile Handler - captures the details of the student and stores them
		//within the data model (including their selected course).


		//attach an event handler to the menu bar that closes the application
		mstmb.addExitHandler(e -> System.exit(0));


	}
	
	//event handler (currently empty), which can be used for creating a profile
	private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			//get the student details from the view and store them in the model
			model.setStudentPnumber(view.getCreateStudentProfilePane().getStudentPnumber());
			model.setStudentName(view.getCreateStudentProfilePane().getStudentName());
			model.setStudentEmail(view.getCreateStudentProfilePane().getStudentEmail());
			model.setStudentCourse(view.getCreateStudentProfilePane().getSelectedCourse());
			model.setSubmissionDate(view.getCreateStudentProfilePane().getStudentDate());

			// Populate the Select Modules Pane with relevant modules
			fillSelectModulesPane();

			view.changeTab(1);


		}


	}

	private class ResetSelectedModulesHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			smp.clearSelectedModules();
			smp.clearUnselectedModules();
			fillSelectModulesPane();

		}
	}














	private class AddTerm1SelectedModuleHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			// Get the selected module from the view
			Module selectedModule = smp.getTerm1SelectedModule();


			// if (selectedModule == null) {
			//				return;
			//			}

			// Add the selected module to the selected modules list view in the model
			model.addSelectedModule(selectedModule);

			// Update the view
			smp.removeModuleFromTerm1UnselectedListView(selectedModule);
			smp.addModuleToTerm1SelectedListView(selectedModule);
		}
	}
	private class AddTerm2SelectedModuleHandler implements EventHandler<ActionEvent> {
		public void handle (ActionEvent e) {

			Module selectedModule = smp.getTerm2SelectedModule();

			model.addSelectedModule(selectedModule);

			smp.removeModuleFromTerm2UnselectedListView(selectedModule);
			smp.addModuleToTerm2SelectedListView(selectedModule);



		}

	}


	private class RemoveTerm1SelectedModuleHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			Module unselectedModule = smp.getTerm1UnselectedModule();

			model.addSelectedModule(unselectedModule);

			smp.removeModuleFromTerm1SelectedListView(unselectedModule);
			smp.addModuleToTerm1UnselectedListView(unselectedModule);

		}

	}

	private class RemoveTerm2SelectedModuleHandler implements EventHandler <ActionEvent> {

		public void handle (ActionEvent e) {

			Module unselectedModule = smp.getTerm2UnselectedModule();

			model.addSelectedModule(unselectedModule);

			smp.removeModuleFromTerm2SelectecListView(unselectedModule);
			smp.addModuleToTerm2UnselectedListView(unselectedModule);


		}
	}

	private class SubmitSelectedModulesHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {

			// Add all selected modules from Term 1 and Term 2 to the model
			for (Module selectedModuleTerm1 : smp.getTerm1SelectedModulesListView().getItems()) {
				model.addSelectedModule(selectedModuleTerm1);
			}

			for (Module selectedModuleTerm2 : smp.getTerm2SelectedModulesListView().getItems()) {
				model.addSelectedModule(selectedModuleTerm2);
			}

			// Populate the reserve modules pane with unselected modules
			for (Module unselectedModuleTerm1 : smp.getTerm1UnselectedModulesListView().getItems()) {
				rmp.addUnselectedModuleToReserveListViewTerm1(unselectedModuleTerm1);
			}

			for (Module unselectedModuleTerm2 : smp.getTerm2UnselectedModulesListView().getItems()) {
				rmp.addUnselectedModuleToReserveListViewTerm2(unselectedModuleTerm2);
			}

			view.changeTab(2);
		}
	}















	private void fillSelectModulesPane() {
		String selectedCourse = model.getStudentCourse().getCourseName();

		for (Module module : model.getStudentCourse().getAllModulesOnCourse()) {
			// Skip the "Information Technology Services Practice" module for the Software Engineering course
			if (selectedCourse.equals("Software Engineering") && module.getModuleName().equals("Information Technology Services Practice")) {
				continue;
			}

			// Check if the module is mandatory for the selected course
			boolean isMandatory = false;
			if (selectedCourse.equals("Software Engineering") &&
					(module.getModuleName().equals("Systems Building: Methods") ||
							module.getModuleName().equals("Development Project") ||
							module.getModuleName().equals("Rigorous Systems"))) {
				isMandatory = true;
			} else if (selectedCourse.equals("Computer Science") &&
					(module.getModuleName().equals("Systems Building: Methods") ||
							module.getModuleName().equals("Development Project"))) {
				isMandatory = true;
			}

			// Add "Development Project" module to the year-long selected ListView for both courses
			if (module.getModuleName().equals("Development Project")) {
				smp.addModuleToYearLongSelectedListView(module);
				continue;
			}

			// Add mandatory and optional modules to the respective term ListViews based on their delivery
			switch (module.getDelivery()) {
				case TERM_1:
					if (isMandatory) {
						smp.addModuleToTerm1SelectedListView(module);
					} else {
						smp.addModuleToTerm1UnselectedListView(module);
					}
					break;
				case TERM_2:
					if (isMandatory) {
						smp.addModuleToTerm2SelectedListView(module);
					} else {
						smp.addModuleToTerm2UnselectedListView(module);
					}
					break;
				case YEAR_LONG:
					smp.addModuleToYearLongSelectedListView(module);
					break;
			}
		}
	}





	//helper method - builds modules and course data and returns courses within an array
	private Course[] buildModulesAndCourses() {
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, RunPlan.TERM_1);
		Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, RunPlan.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, RunPlan.TERM_2);	
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, RunPlan.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, RunPlan.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, RunPlan.TERM_1);	
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, RunPlan.TERM_2);	
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, RunPlan.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, RunPlan.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, RunPlan.TERM_2);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, RunPlan.TERM_1);
		Module ctec3911 = new Module("CTEC3911", "Mobile Application Development", 15, false, RunPlan.TERM_1);
		Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, RunPlan.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, RunPlan.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Computer Ethics and Privacy", 15, false, RunPlan.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, RunPlan.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, RunPlan.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, RunPlan.TERM_2);


		Course compSci = new Course("Computer Science");
		compSci.addModuleToCourse(imat3423);
		compSci.addModuleToCourse(ctec3451);
		compSci.addModuleToCourse(ctec3902_CompSci);
		compSci.addModuleToCourse(ctec3110);
		compSci.addModuleToCourse(ctec3605);
		compSci.addModuleToCourse(ctec3606);
		compSci.addModuleToCourse(ctec3410);
		compSci.addModuleToCourse(ctec3904);
		compSci.addModuleToCourse(ctec3905);
		compSci.addModuleToCourse(ctec3906);
		compSci.addModuleToCourse(ctec3911);
		compSci.addModuleToCourse(imat3410);
		compSci.addModuleToCourse(imat3406);
		compSci.addModuleToCourse(imat3611);
		compSci.addModuleToCourse(imat3613);
		compSci.addModuleToCourse(imat3614);
		compSci.addModuleToCourse(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModuleToCourse(imat3423);
		softEng.addModuleToCourse(ctec3451);
		softEng.addModuleToCourse(ctec3902_SoftEng);
		softEng.addModuleToCourse(ctec3110);
		softEng.addModuleToCourse(ctec3605);
		softEng.addModuleToCourse(ctec3606);
		softEng.addModuleToCourse(ctec3410);
		softEng.addModuleToCourse(ctec3904);
		softEng.addModuleToCourse(ctec3905);
		softEng.addModuleToCourse(ctec3906);
		softEng.addModuleToCourse(ctec3911);
		softEng.addModuleToCourse(imat3410);
		softEng.addModuleToCourse(imat3406);
		softEng.addModuleToCourse(imat3611);
		softEng.addModuleToCourse(imat3613);
		softEng.addModuleToCourse(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}

}
