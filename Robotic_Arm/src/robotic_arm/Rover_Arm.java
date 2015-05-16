package robotic_arm;

//make it a thread
public class Rover_Arm {

	public static final long POWER = 110;
	//define variables
	boolean ARM_ON =false;
	
	//instrument
	private String instrument;
	
	//angles
	private int shoulder_arm_angle_theta1;
	private int arm_wrist_angle_theta2;
	
	//degrees of freedom
	private int degree_1;
	private int degree_2;
	private int degree_3;
	private int degree_4;
	private int degree_5;
	
	//power
	private long power;
	//time
	private long time;
	
	//constructor
	
	public Rover_Arm(){
		
	}
	
	public Rover_Arm(String instrument,int angle1,int angle2, int degree_1, int degree_2, int degree_3, 
			int degree_4 , int degree_5, long time, long power ){
		
		//continue at home
		this.instrument = instrument;
		this.shoulder_arm_angle_theta1 = angle1;
		this.arm_wrist_angle_theta2 = angle2;
		this.degree_1 = degree_1;
		this.degree_2 = degree_2;
		this.degree_3 = degree_3;
		this.degree_4 = degree_4;
		this.degree_5 = degree_5;
		this.time = time;
		this.power = power;
	}
	
	

	
	//methods
		
	public Rover_Arm move(int instru, int theta1, int theta2, int deg_1,int deg_2,int deg_3, int deg_4, int deg_5){
		
		//store in a constructor for JSON purposes ?? i think so
		
		//check theta1
		int angle1 = theta1;
		//check theta2
		int angle2 = theta2;
		
		//select the instrument
		String instrument = "";
		switch(instru){
			case 1: instrument = "DRT";
			break;
			case 2: instrument = "MAHLI";
			break;
			case 3: instrument = "Drill";
			break;
			case 4: instrument = "APXS";
			break;
			case 5: instrument = "CHIMRA";
			break;
		}
		
		int degree_1 = deg_1;
		int degree_2 = deg_2;
		int degree_3 = deg_3;
		int degree_4 = deg_4;
		int degree_5 = deg_5;
		
		//power
		long power_watts = POWER;
		
		//time
		long move_time;
		if(degree_1 == 1){
			move_time = ARM_MOVEMENT_TIME(1000);
		}else if (degree_2 == 1){
			move_time = ARM_MOVEMENT_TIME(2000);
		}else if (degree_3 == 1){
			move_time = ARM_MOVEMENT_TIME(3000);
		}else if (degree_4 == 1){
			move_time = ARM_MOVEMENT_TIME(4000);
		}else{
			move_time = ARM_MOVEMENT_TIME(5000);
		}
				
		//set constructor
		Rover_Arm d = new Rover_Arm(instrument,angle1,angle2,degree_1,degree_2,degree_3,degree_4,degree_5,
				move_time,power_watts);
		
		d.setARM_ON(true);
		
		return d;
	}
	
	public long ARM_MOVEMENT_TIME(int deg){
		
		long currentTime = System.currentTimeMillis()/1000;
				
		//issue delay
		try {
			Thread.sleep((int)(Math.random()*(10000 - deg)) + deg);                
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
				
		long latterTime = System.currentTimeMillis()/1000;
		long arm_move_time = latterTime - currentTime;
				
		return arm_move_time;
	}
	
	public String INST_STATUS(){
		
		String int_status = "OFF";
		int [] instruments = {this.getDegree_1(), this.getDegree_2(),this.getDegree_3(),this.getDegree_4(),this.getDegree_5()}; 
	
		for(int i = 0; i < instruments.length; i++){
			
			if(instruments[i] == 1)
				int_status = "ON";
		}
		
		return int_status;
	}
	
	public String INST_IN_USE(){
		
		String instrument = "No Instrument in USE";
		int [] instruments = {this.getDegree_1(), this.getDegree_2(),this.getDegree_3(),this.getDegree_4(),this.getDegree_5()}; 
		
		for(int i = 0; i < instruments.length; i++){
			
			if(instruments[i] == 1)
			{
				switch(i){
					case 0: instrument = "DRT";
						break;
					case 1: instrument = "MAHLI";
						break;
					case 2: instrument = "Drill";
						break;
					case 3: instrument = "APXS";
						break;
					case 4: instrument = "CHIMRA";
						break;
				}
			}
		}
		
		
		return instrument;
	}
	
	public String ARM_STATUS(){
		
		if (isARM_ON() == false){
			return "OFF";
		}else{
			return "ON";
		}
	}
	
	public void turn_off_instruments(){
		
		this.setDegree_1(0);
		this.setDegree_2(0);
		this.setDegree_3(0);
		this.setDegree_4(0);
		this.setDegree_5(0);
	}
	
	public long ARM_PWR_ON(){
		
		//output it requires 10 seconds to turn on
		long currentTime = System.currentTimeMillis()/1000;
		
		//issue delay
		try {
		    Thread.sleep(10000);                
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		long latterTime = System.currentTimeMillis()/1000;
		long arm_boot_time = latterTime - currentTime;
		
		//set On to true;
		setARM_ON(true);
		
		return arm_boot_time;
	}
	

	public long ARM_PWR_OFF(){
		
		
		//set instruments off
		turn_off_instruments();
		
		//output it requires 10 seconds to turn on
		long currentTime = System.currentTimeMillis()/1000;
				
		//issue delay
		try {
			Thread.sleep(5000);                
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
				
		long latterTime = System.currentTimeMillis()/1000;
		long arm_off_time = latterTime - currentTime;
		
		//set On to false;
		setARM_ON(false);
		
		return arm_off_time;	
	}
	
	
	public long ARM_PWR_STOW(){
		
		//stowing time less than turn off
		long time = 2;
		//
		//issue delay
		try {
			Thread.sleep(5000);                
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		
		//set to stow values
		setArm_stow(time);
		
		return time;
	}
	
	public void setArm_stow(long time){
		
		this.setInstrument("ARM_PWR_STOW");
		this.setShoulder_arm_angle_theta1(20);
		this.setArm_wrist_angle_theta2(10);
		this.setDegree_1(0);
		this.setDegree_2(0);
		this.setDegree_3(0);
		this.setDegree_4(0);
		this.setDegree_5(0);
		this.setPower(ARM_POWER_VALUE());
		this.setTime(time);
	}
	
	public long ARM_POWER_VALUE(){
		
		long power = POWER;
		
		return power;
		
	}
	
	

	
	//one of the constraints... check if movement of the arm is allowed
	public boolean can_it_move(double tetha1,double tetha2,boolean camera_ON,double temperature){
		
		boolean can_it = false;
		
		//if the camera is on, then it won't move
		if(!camera_ON){	
			
			//check if the temperature is good enough to move the arm
			if(temperature > -110 && temperature < 50){
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
		
		return can_it;
	}
	
	

	//setters and getters

	public int getShoulder_arm_angle_theta1() {
		return shoulder_arm_angle_theta1;
	}

	public void setShoulder_arm_angle_theta1(int shoulder_arm_angle_theta1) {
		this.shoulder_arm_angle_theta1 = shoulder_arm_angle_theta1;
	}

	public int getArm_wrist_angle_theta2() {
		return arm_wrist_angle_theta2;
	}

	public void setArm_wrist_angle_theta2(int arm_wrist_angle_theta2) {
		this.arm_wrist_angle_theta2 = arm_wrist_angle_theta2;
	}

	public boolean isARM_ON() {
		return ARM_ON;
	}

	public void setARM_ON(boolean aRM_ON) {
		ARM_ON = aRM_ON;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public int getDegree_1() {
		return degree_1;
	}

	public void setDegree_1(int degree_1) {
		this.degree_1 = degree_1;
	}

	public int getDegree_2() {
		return degree_2;
	}

	public void setDegree_2(int degree_2) {
		this.degree_2 = degree_2;
	}

	public int getDegree_3() {
		return degree_3;
	}

	public void setDegree_3(int degree_3) {
		this.degree_3 = degree_3;
	}

	public int getDegree_4() {
		return degree_4;
	}

	public void setDegree_4(int degree_4) {
		this.degree_4 = degree_4;
	}

	public int getDegree_5() {
		return degree_5;
	}

	public void setDegree_5(int degree_5) {
		this.degree_5 = degree_5;
	}

	public long getPower() {
		return power;
	}

	public void setPower(long power) {
		this.power = power;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	
	
}
