
import java.util.ArrayList;

/**
 *
 * @author kyledematias
 */
public class TrapProfile extends Profile {
    private double distance , max_velocity, acceleration, t0, t1, t2, t3, start_velocity, end_velocity, d1, d2, d3; 
	
    public TrapProfile(double v_max, double accel, double dist, double start_vel, double end_vel){
        distance = dist;
        max_velocity = v_max;
        acceleration = accel;
        start_velocity = start_vel;
        end_velocity = end_vel;
        t0 = 0;
        t1 = (Math.abs(max_velocity) - Math.abs(start_velocity)) / accel;
        d1 = ((Math.abs(max_velocity) + Math.abs(start_velocity)) / 2.0) * t1;
        d3 = (Math.pow(max_velocity, 2.0) - Math.pow(end_velocity, 2.0)) / (2.0 * acceleration);
        d2 = Math.abs(distance) - d1 - d3;
        t2 = Math.abs(d2 / max_velocity) + t1;
        t3 = (Math.abs(end_velocity) - Math.abs(max_velocity)) / -acceleration + t2;
    }

    public double getFinalTime(){
        return t3;
    }

    public double getVelocityAtTime(double time){
        double v_exp = 0;
        if(time >= t1 && time <= t2){
            v_exp = Math.abs(max_velocity);
        }
        else if(time < t1 && time >= t0){
            v_exp = Math.abs(start_velocity) + acceleration * time;
        }
        else if(time > t2 && time <= t3){
            v_exp  = Math.abs(max_velocity) - acceleration * (time - t2);
        }
        if(distance >= 0.0){
            return v_exp;
        }
        else if(distance < 0.0){
            return -v_exp;
        }
        else{
            return 0.0;
        }    
    }
    
    public double getDistAtTime(double time){
        double dist = 0;
        if(time <= t1 && time >= t0){
            dist = (0.5 * getVelocityAtTime(time) * time); 
        }
        else if(time > t1 && time <= t2){
            dist = d1 + (max_velocity * (time - t1));//2.0 + 20*2.3
        }
        else if(time > t2 && time <= t3){
            dist  = d1 + (max_velocity * (t2 - t1))
                    + ((Math.pow(getVelocityAtTime(time), 2.0) - Math.pow(max_velocity, 2.0)) / (2.0 * -acceleration));
            //System.out.println(max_velocity * (time - t1));
        }
        else{
            dist = distance;
        }
        if(distance >= 0.0){
            return dist;
        }
        else if(distance < 0.0){
            return -dist;
        }
        else{
            return 0.0;
        }
    }
    
    //per 100 msec
    public ArrayList<Double> getDistances(){
        ArrayList<Double> distArr = new ArrayList<>();
        for(double i = 0.0; i < getFinalTime(); i += 0.05){
            distArr.add(new Double(getDistAtTime(i)));
        }
        return distArr;
    }
    
    public double getDist(){
        return distance;
    }
        
    public double getMaxVel(){
        return max_velocity;
    }
    
    public double getAccel(){
        return acceleration;
    }
    
    public double getStartVel(){
        return start_velocity;
    }
    
    public double getEndVel(){
        return end_velocity;
    }
}