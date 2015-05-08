package robotic_arm;
import java.util.Scanner;

public class Rover_main {

	public static void main(String[] args) {
		
		boolean ON = false;
		Scanner in = new Scanner(System.in);

		//set a starting position
		Rover_Arm position = new Rover_Arm();
		//from rover end points (the corners of the rover)
		Rover_Arm one;
		Rover_Arm two;
		
		int choice;
		
		do{
			
			System.out.println("What do you want to do");
			System.out.println("1. TURN ON");
			System.out.println("2. TURN OFF");
			System.out.println("3. MOVE ARM");
			System.out.println("4. EXIT");
			

			choice = in.nextInt();
		
			if(choice == 1){
			
				ON = true;
				//getting the corners coordinates from rover (This is suppose to come from latitude group)
				one = new Rover_Arm(1,2,3);
				two = new Rover_Arm(3,2,1);
			
		
				//set the position of the arm and set true for ARM_ON (meaning the arm is on)
				position.Position(one, two,true);
			
			}else if (choice == 2){
				
				//still thinking more about it on this part like adding time for example
				//if the arm is in a certain position and I want to turn it off then I will do this
				//but I am suppose to give the time when the arm returns to its initial position...
				ON = false;
				one = new Rover_Arm();
				two = new Rover_Arm();
				position.Position(one, two, ON);
				
			}else if (choice == 3){
				
				//preventing to move if the device is not on
				if(ON != true){
					System.out.println("Foo you haven't turn this thing on... turn in on");
					continue;
				}else{
					
					//asking the questions for new location of the arm
					double joint_y,joint_z,wrist_y,wrist_z;
					double tetha1,tetha2;
					double temperature;
					
					System.out.println("Enter a temperature between -110 and 50 Celcius");
					temperature = in.nextDouble();
					
					System.out.println("Specify new joint Y coordinate since shoulder arm X coordinate is fixed with 1.1: ");
					joint_y = in.nextDouble();
					System.out.println("Specify new joint Z coordinate since shoulder arm X coordinate is fixed with 1,1: ");
					joint_z = in.nextDouble();
					
					System.out.println("Specify angle tetha1: ");
					tetha1 = in.nextDouble();
					System.out.println("Specify angle tetha2: ");
					tetha2 = in.nextDouble();
					
					System.out.println("Specify new wrist Y coordinate since arm wrist X coordinate is fixed with 1.1: ");
					wrist_y = in.nextDouble();
					System.out.println("Specify new wrist Z coordinate since arm wrist X coordinate is fixed with 1.1: ");
					wrist_z = in.nextDouble();
					
					
					//checking if it can move
					
					//the false value is for the camera. if the camera is off then false otherwise true
					//adding a dummy temperature to see if it can do it
					if(position.can_it_move(wrist_y,wrist_z,joint_y,joint_z,tetha1,tetha2,false,temperature)){
						
						System.out.println("Yes you can");
						//move it
						//the actual method will go here (i will have to create a method on Rover_arm and pass all the parameters)
						position.newPosition(wrist_y, wrist_z, joint_y, joint_z, tetha1, tetha2);
						

					}else{
						//try again
						System.out.println("Can not move it, try again");
						continue;

					}
				}
			}
			
			
			if(choice != 4){
				System.out.println("The position of the shoulder arm is at x: " + position.getInitial_x());
				System.out.println("The position of the shoulder arm is at y: " + position.getInitial_y());
				System.out.println("The position of the shoulder arm is at z: " + position.getInitial_z());
				System.out.println();
				System.out.println("The initial tetha1 angle (starting at shoulder) position is : " + position.getShoulder_arm_angle_theta1());
				System.out.println();
				System.out.println("The position of the joint arm is at x : " + position.getJoint_x());
				System.out.println("The position of the joint arm is at y : " + position.getJoint_y());
				System.out.println("The position of the joint arm is at z : " + position.getJoint_z());
				System.out.println();
				System.out.println("The initial tetha2 angle (starting at joint) position is : " + position.getArm_wrist_angle_theta2());
				System.out.println();
				System.out.println("The position of the wrist arm is at x : " + position.getWrist_x());
				System.out.println("The position of the wrist arm is at y : " + position.getWrist_y());
				System.out.println("The position of the wrist arm is at z : " + position.getWrist_z());
				
				System.out.println();
				System.out.println("Summary");
				System.out.println("====================================");
				System.out.println("shoulder: (" + position.getInitial_x() + "," + position.getInitial_y() + "," + position.getInitial_y() + ")");
				System.out.println("Angle tetha 1: " + position.getShoulder_arm_angle_theta1());
				System.out.println();
				System.out.println("joint: (" + position.getJoint_x() + "," + position.getJoint_y() + "," + position.getJoint_z() + ")");
				System.out.println("Angle tetha 2: " + position.getArm_wrist_angle_theta2());
				System.out.println();
				System.out.println("wrist: (" + position.getWrist_x() + "," + position.getWrist_y() + "," + position.getWrist_z() + ")");
				
			}
			
			System.out.println();
			
		}while(choice != 4);
		
		in.close();
		
	}

}
