/**
 *
 * @author kyledematias
 */
import java.util.ArrayList;
        
public abstract class Profile {
    
    public static Profile getVelProfile(double max_velocity, double acceleration, double distance, double start_vel, double end_vel){
        assert acceleration == 0.0 && max_velocity == start_vel && start_vel == end_vel: "Velocity must stay constant (ie. accel = 0)";
        if((Math.pow(max_velocity, 2.0) / acceleration) < Math.abs(distance) || acceleration == 0.0){
            System.out.println("trapezoidal");
            if(distance < 0.0){
                return new TrapProfile(-max_velocity, acceleration, distance, -start_vel, -end_vel);
            }
            else{
                return new TrapProfile(max_velocity, acceleration, distance, start_vel, end_vel);
            }
        }
        else {
            System.out.println("triangular");
            if(distance < 0.0){
                return new TriProfile(-max_velocity, acceleration, distance, -start_vel, -end_vel);
            }
            else{
                return new TriProfile(max_velocity, acceleration, distance, start_vel, end_vel);
            }
	}
    }
   
    public abstract double getFinalTime();
    public abstract double getVelocityAtTime(double time);
    public abstract double getDistAtTime(double time);
    public abstract ArrayList<Double> getDistances(); //each 100 ms increment
    public abstract double getDist();
    public abstract double getMaxVel();
    public abstract double getAccel();
    public abstract double getStartVel();
    public abstract double getEndVel();
}