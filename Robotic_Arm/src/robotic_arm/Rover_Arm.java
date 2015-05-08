
package robotic_arm;
//make it a thread
public class Rover_Arm {

	//in meters
	public static final double ARM_LENGTH_SHOULDER_ARM = 1.1;
	public static final double ARM_LENGTH_ARM_WRIST = 1.1;
	public static final double MINIMUM_JOINT_Z_HEIGHT = 1;

	//in cm
	public static final double TURRET_WIDE_LENGHT = 0.60;
	

	//define variables
	boolean ARM_ON =false;
	
	
	//initial variables shoulder from given x, y, and z
	private double initial_x,initial_y,initial_z;
	
	//joint x,y,z
	private double joint_x,joint_y,joint_z;
	
	//wrist x,y,z
	private double wrist_x,wrist_y,wrist_z;
	
	//angles
	private double shoulder_arm_angle_theta1;
	private double arm_wrist_angle_theta2;
		
	//constructor
	
	public Rover_Arm(){
		
	}
	
	public Rover_Arm(double initial_x, double initial_y, double initial_z){
		this.initial_x = initial_x;
		this.initial_y = initial_y;
		this.initial_z = initial_z;
	}
	
	
	//methods
	
	public Rover_Arm newPosition(double wrist_y, double wrist_z,double joint_y,double joint_z ,double tetha1,double tetha2){
		
		
		//setting angle tetha1
		this.setShoulder_arm_angle_theta1(tetha1);
		
		
		//setting the joints
		
		//set joint x
		this.setJoint_x(this.getInitial_x()+ ARM_LENGTH_SHOULDER_ARM);
		//set joint y
		this.setJoint_y(joint_y);
		//set joint z
		this.setJoint_z(joint_z);
		
		
		//setting angle tethat2
		this.setArm_wrist_angle_theta2(tetha2);
		
		//setting the wrists
		
		//set wrist x
		this.setWrist_x(this.getJoint_x()+ ARM_LENGTH_ARM_WRIST);
		//set wrist y
		this.setWrist_y(wrist_y);
		//set wrist z
		this.setWrist_z(wrist_z);
		
		return this;
		
		
	}
	
	//one of the constraints... check if movement of the arm is allowed
	public boolean can_it_move(double wrist_y, double wrist_z,double joint_y,double joint_z ,double tetha1,double tetha2,
			boolean camera_ON,double temperature){
		
		boolean can_it = false;
		
		//if the camera is on, then it won't move
		if(!camera_ON){	
			
			//check if the temperature is good enough to move the arm
			if(temperature > -110 && temperature < 50){
			
				//if the position of the wrist is less than 60 cm from the ground
				if(!(wrist_z < 0.60)){
					
					//if joint z is greater than the shoulder position + ARM_LENGTH_SHOULDER_ARM
					if(!(joint_z > this.getInitial_z() + ARM_LENGTH_SHOULDER_ARM )){
			
						//if joint z is lower than the minimum height then it cannot move
						if(!(joint_z < MINIMUM_JOINT_Z_HEIGHT)){
						//if angle tetha1 is greater than 70 degrees (might hit ground) 
						
							if(!(tetha1 > 70)){
								
								//or tetha1 is less than 45 degree (might hit rover)
								if(!(tetha1 < 45)){
									
									//tetha2 is greater than 290 degrees then it cannot move
									if(!(tetha2 > 290)){
										can_it = true;
									}
								}
							}
						}
					}
				}
			}

		}
		
		return can_it;
	}
	
	public Rover_Arm Position(Rover_Arm one,Rover_Arm two, boolean ARM_ON){
		
		double actual_X = (one.getInitial_x() + two.getInitial_x())/2;
		double actual_Y = (one.getInitial_y() + two.getInitial_y())/2;
		double actual_Z = (one.getInitial_z() + two.getInitial_z())/2;
		
		
		this.setInitial_x(actual_X);
		this.setInitial_y(actual_Y);
		this.setInitial_z(actual_Z);
		
		
		//if the arm is on
		if(ARM_ON){
			//angles in degrees
			double tetha1 = 1;
			double tetha2 = 10; 
		
			//start at this angle
			this.setArm_wrist_angle_theta2(tetha1);
			this.setShoulder_arm_angle_theta1(tetha2);
			
			//default joint values
			this.setJoint_x(actual_X - ARM_LENGTH_SHOULDER_ARM );
			this.setJoint_y(actual_Y);
			this.setJoint_z(actual_Z);
			
			//default wrist location
			this.setWrist_x(actual_X);
			this.setWrist_y(actual_Y);
			this.setWrist_z(actual_Z + TURRET_WIDE_LENGHT);
			
			
		//if arm is off	
		}else{
			
			//setting the angle to default
			this.setArm_wrist_angle_theta2(10);
			this.setShoulder_arm_angle_theta1(0);
			
			//default joint values
			this.setJoint_x(actual_X - ARM_LENGTH_SHOULDER_ARM );
			this.setJoint_y(actual_Y);
			this.setJoint_z(actual_Z);
			
			//default wrist location
			this.setWrist_x(actual_X);
			this.setWrist_y(actual_Y);
			this.setWrist_z(actual_Z + TURRET_WIDE_LENGHT);
			
		}
		//set the arm and wrist
		
		return this;
	}


	//setters and getters
	public double getInitial_x() {
		return initial_x;
	}


	public void setInitial_x(double initial_x) {
		this.initial_x = initial_x;
	}


	public double getInitial_y() {
		return initial_y;
	}


	public void setInitial_y(double initial_y) {
		this.initial_y = initial_y;
	}


	public double getInitial_z() {
		return initial_z;
	}


	public void setInitial_z(double initial_z) {
		this.initial_z = initial_z;
	}

	public double getShoulder_arm_angle_theta1() {
		return shoulder_arm_angle_theta1;
	}

	public void setShoulder_arm_angle_theta1(double shoulder_arm_angle_theta1) {
		this.shoulder_arm_angle_theta1 = shoulder_arm_angle_theta1;
	}

	public double getArm_wrist_angle_theta2() {
		return arm_wrist_angle_theta2;
	}

	public void setArm_wrist_angle_theta2(double arm_wrist_angle_theta2) {
		this.arm_wrist_angle_theta2 = arm_wrist_angle_theta2;
	}

	public double getJoint_x() {
		return joint_x;
	}

	public void setJoint_x(double joint_x) {
		this.joint_x = joint_x;
	}

	public double getJoint_y() {
		return joint_y;
	}

	public void setJoint_y(double joint_y) {
		this.joint_y = joint_y;
	}

	public double getJoint_z() {
		return joint_z;
	}

	public void setJoint_z(double joint_z) {
		this.joint_z = joint_z;
	}

	public double getWrist_x() {
		return wrist_x;
	}

	public void setWrist_x(double wrist_x) {
		this.wrist_x = wrist_x;
	}

	public double getWrist_y() {
		return wrist_y;
	}

	public void setWrist_y(double wrist_y) {
		this.wrist_y = wrist_y;
	}

	public double getWrist_z() {
		return wrist_z;
	}

	public void setWrist_z(double wrist_z) {
		this.wrist_z = wrist_z;
	}
	
	
	
}
