package application.controller;

/**
 * @author Austin Moore
 * 
 * MainController.java, has initialize method 
 * runTask() to create thread to call update santa in PersonTask.java
 */

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import application.model.Person;
import application.model.PersonTask;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class MainController implements Initializable  {

	ArrayList<Person> people = new ArrayList<>();
	ArrayList<Circle> circles = new ArrayList<>();
	ArrayList<Circle> nameList = new ArrayList<>();
	
	PersonTask pTask = new PersonTask();
	
	@FXML
	private AnchorPane rootPane;
	
	@FXML
	private Label currActivity;
	
	@FXML
	private Label currTime;
	
	@FXML
	private Circle NicoleCircle, JaneCircle, EmmetCircle, KassandraCircle, DiegoCircle, LeonCircle, RowdyCircle, LeannaCircle;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		people = Person.loadPersons("data.csv");
		currActivity.setText("Please click an icon to show status...");
		currTime.setText(":");
		nameList.add(NicoleCircle);
		nameList.add(JaneCircle);
		nameList.add(EmmetCircle);
		nameList.add(KassandraCircle);
		nameList.add(DiegoCircle);
		nameList.add(LeonCircle);
		nameList.add(RowdyCircle);
		nameList.add(LeannaCircle);

		// add circles to AnchorPane
		rootPane.getChildren().addAll(circles);
		
		PersonTask pTask = new PersonTask();
		pTask.runColorTask(people, circles, rootPane, currActivity);
		
		// run task to move santa to next circle
		runTask();
		
	}
	
	public void runTask() {
		
		try {
			
			Thread th = new Thread( new Task(){                // put the task in its own thread
				
				int i = 0;
				@Override
				protected String call() throws Exception {
					
					Calendar c = Calendar.getInstance();
					currTime.setText("Last updated: " + ((c.get(Calendar.WEEK_OF_YEAR) / 4) - 1) + "/" + c.get(Calendar.DATE) + ", " + c.get(Calendar.YEAR) + " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND));
					
					System.out.println("Task starting...");
					String status = "";
					Thread.sleep(10000);
					
					// loop to until at last person
					while (i < people.size()) {
						
						status = "";
						
							Platform.runLater(new Runnable() {
					            @Override
					            public void run() {
					            
					            	// code to run
					            	int r = pTask.santaNext();

					            	
					            	if (r == 0) {
					            		
					            		circles.get(i).setFill(Color.BLACK);
					            		nameList.get(i).setFill(Color.BLACK);
					            		people.get(i).setActivity("Asleep");
					            		Calendar c = Calendar.getInstance();
										currTime.setText("Last updated: " + ((c.get(Calendar.WEEK_OF_YEAR) / 4) - 1) + "/" + c.get(Calendar.DATE) + ", " + c.get(Calendar.YEAR) + " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND));
					            		
					            		if (currActivity.getText().contains(people.get(i).getName())) {
					            			people.get(i).setActivity("asleep");
					            			currActivity.setText("");
					            		} else { 
					            			people.get(i).setActivity("Asleep");
					            		}
					            		

					            		i++;
					            		
					            	}
					            	
					            	
					            }
				     
				        });
						Thread.sleep(10000);
					}
					
					System.out.println("Task ending...");
					return status;
				}
			});													
			
			// init and run the new thread
			th.setDaemon(true);									
			th.start();
			Thread.sleep(1000);
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
	}


}
