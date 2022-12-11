package application.model;

import java.util.ArrayList;

/**
 * @author Austin Moore (ccj314)
 * UTSA CS 3443 - Lab 6
 * Fall 2022
 * 
 * PersonTask.java class, has methods to move santa to next person 1/3 times called
 * has task that updates dots on map based off info in Person ArrayList
 */

import java.util.Random;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Austin Moore
 * 
 * PersonTask.java, method to run thread that loads colored circles
 * santaNext method to randomly move santa to the next person
 */

public class PersonTask {
	
	Random random = new Random();
	
	// thread loads and colors circles for each person
		public void runColorTask(ArrayList<Person> people, ArrayList<Circle> circles, AnchorPane rootPane, Label currActivity) {
				
				try {
					
					Thread th = new Thread( new Task(){                // put the task in its own thread
						
						@Override
						protected String call() throws Exception {
							
							System.out.println("Color Task starting...");
							String status = "";
								
								status = "";
								
									Platform.runLater(new Runnable() {
							            @Override
							            public void run() {
							            	
							        		
							        		// create circles for each person, add to circles arraylist
							        		for (int i = 0; i < people.size(); i++) {
							        			
							        			Circle circle = new Circle();
							        			circle.setLayoutX((people.get(i).getLatitude() * 4/3));
							        			circle.setLayoutY((people.get(i).getLongitude() * 4/3) + 100);
							        			circle.setRadius(5);
							        			String color = people.get(i).getColor();
							        			Color c = Color.web(color);
							        			final int j = i;
							        			circle.setOnMouseClicked((click) -> currActivity.setText(people.get(j).getName() + " is currently: " + people.get(j).getActivity()));
							        			circle.setFill(c);
							        			
							        			circles.add(circle);
							        			
							        		}
		
							        		// add circles to AnchorPane
							        		rootPane.getChildren().addAll(circles);
							            	
							            }
						     
						        });
							
							
							System.out.println("Color Task ending...");
							return status;
						}
					});													
					
					// init and run the new thread
					th.setDaemon(true);									
					th.start();
					Thread.sleep(1);
					
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
				
			}
	
	public int santaNext() {
		
		int r = random.nextInt(3) + 0;
		
		System.out.println(r);
		
		if (r == 0) {
			
			// change currperson to black, change santa location to next person
			
			
		} 
		
		return r;
		
	}

}
