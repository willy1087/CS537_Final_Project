package robotic_arm;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Rover_main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);

		int choice;
		
		Rover_Arm test = new Rover_Arm();
		
		do{
			
			System.out.println("What do you want to do");
			System.out.println("1. ARM_PWR_ON");
			System.out.println("2. ARM_PWR_OFF");
			System.out.println("3. INTST_MOVE_choices");
			System.out.println("4. ARM_POWER");
			System.out.println("5. ARM_STATUS");
			System.out.println("6. INST_STATUS");
			System.out.println("7. INST_IN_USE");
			System.out.println("8. ARM_PWR_STOW");
			System.out.println("9. EXIT");
			

			choice = in.nextInt();
		
			if(choice == 1){
				
				if(test.ARM_STATUS() == "ON"){
					System.out.println("The arm is already on");
				}else{
					System.out.println("Warming the arm. Please wait ....");
					System.out.println("The arm took " + test.ARM_PWR_ON()  + " seconds to turn on");
				}
			}else if (choice == 2){
				
				if(test.ARM_STATUS() == "OFF"){
					System.out.println("The arm is already off");
				}else{
					System.out.println("Turning off the arm. Please wait ....");
					System.out.println("The arm took " + test.ARM_PWR_OFF() + " seconds to turn off");
				}
				
			}else if (choice == 3){
				
				
				if(test.ARM_STATUS() == "ON"){
				
					int instrument = 0;
					while(instrument != 6){
						
						System.out.println("Select an instrument");
						System.out.println("1. DRT");
						System.out.println("2. MAHLI");
						System.out.println("3. Drill");
						System.out.println("4. APXS");
						System.out.println("5. CHIMRA");
						System.out.println("6. Exit");
						
						instrument = in.nextInt();
						
						if(instrument != 6){
							
							int deg_of_free_1 = 0;
							int deg_of_free_2 = 0;
							int deg_of_free_3 = 0;
							int deg_of_free_4 = 0;
							int deg_of_free_5 = 0;
							
							int theta1,theta2;
							
							System.out.println("Enter theta 1");
							theta1 = in.nextInt();
							
							System.out.println("Enter theta 2");
							theta2 = in.nextInt();
							
							switch(instrument){
								case 1: deg_of_free_1 = 1;
										break;
								case 2: deg_of_free_2 = 1;
										break;
								case 3: deg_of_free_3 = 1;
										break;
								case 4: deg_of_free_4 = 1;
										break;
								case 5: deg_of_free_5 = 1;
										break;
									
							}
							
							//store into method then display it
							System.out.println("Warming the arm to move. Please wait ....");
							test = test.move(instrument,theta1, theta2, deg_of_free_1,deg_of_free_2,deg_of_free_3,deg_of_free_4,deg_of_free_5);							
							System.out.println("command : " + test.getInstrument() + "_" + test.getShoulder_arm_angle_theta1() +
									"_" + test.getArm_wrist_angle_theta2() + "_" + test.getDegree_1() + "_" + test.getDegree_2() +
									"_" + test.getDegree_3() + "_" + test.getDegree_4() + "_" + test.getDegree_5() + " Successful");
							System.out.println("Time : " + test.getTime() + " seconds" );
							System.out.println("Power: " + test.getPower() + " watts");
							System.out.println("machine on? " + test.ARM_STATUS());
							
							
							//json code
							String myFilePath = "C:\\Users\\willy1087\\Documents\\Robotic_Arm\\ARM_COMMAND.json";
							
							// Gson is used to create a json object that is spaced nicely
							Gson gson = new GsonBuilder().setPrettyPrinting().create();

							// Instantiate the writer since we're writing to a JSON file.
							FileWriter writer = null;
							try {
								writer = new FileWriter(myFilePath);
							} catch (IOException e) {
								e.printStackTrace();
							}
							
							// Object is converted to a JSON String
							String jsonString = gson.toJson(test);
							
							// Write the file
							try {
								writer.write(jsonString);
							} catch (IOException e) {
								e.printStackTrace();
							}
							
							// Close the Writer
							try {
								writer.close();
							} catch (IOException e) {
								e.printStackTrace();
							}

							//end of json code
						}
						
						
					}
				
				}else{
					System.out.println("The ARM_STATUS is : " + test.ARM_STATUS());
					System.out.println("Please turn on the ARM");
				}
				
				
			}else if(choice == 4){
				
				System.out.println("The Arm's power is : " + test.ARM_POWER_VALUE() + " watts ");
			}else if (choice ==5){
				
				System.out.println("The Arm is currently: " + test.ARM_STATUS());
			}
			else if (choice == 6){
				
				System.out.println("The instrument is currently: " + test.INST_STATUS());
			}
			else if (choice == 7){
				
				System.out.println("Name of instrument in use: " + test.INST_IN_USE());
			}
			else if (choice == 8){
				
				if(test.ARM_STATUS() == "OFF"){
					System.out.println("The arm is off");
				}else{
					System.out.println("The Arm got stowed for " + test.ARM_PWR_STOW() + " seconds");
					System.out.println("command : " + test.getInstrument() + "_" + test.getShoulder_arm_angle_theta1() +
							"_" + test.getArm_wrist_angle_theta2() + "_" + test.getDegree_1() + "_" + test.getDegree_2() +
							"_" + test.getDegree_3() + "_" + test.getDegree_4() + "_" + test.getDegree_5() + " Successful");
					
					
					//json code
					String myFilePath = "C:\\Users\\willy1087\\Documents\\Robotic_Arm\\ARM_COMMAND.json";
					
					// Gson is used to create a json object that is spaced nicely
					Gson gson = new GsonBuilder().setPrettyPrinting().create();

					// Instantiate the writer since we're writing to a JSON file.
					FileWriter writer = null;
					try {
						writer = new FileWriter(myFilePath);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					// Object is converted to a JSON String
					String jsonString = gson.toJson(test);
					
					// Write the file
					try {
						writer.write(jsonString);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					// Close the Writer
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

					//end of json code
					
					
				}
			}
			
			
			System.out.println();
			
		}while(choice != 9);
		
		in.close();
		
	}

}
