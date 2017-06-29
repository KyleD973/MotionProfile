/**
 *
 * @author kyledematias
 */
import java.util.ArrayList;

public class TriProfile extends Profile {
    private double max_velocity, acceleration, distance, t0, t_half, t1, start_velocity, end_velocity, cap_velocity;
    
    public TriProfile(double v_max, double accel, double dist, double start_vel, double end_vel){
        max_velocity = v_max;
        acceleration = accel;
        distance = dist;
        start_velocity = start_vel;
        end_velocity = end_vel;
        cap_velocity = Math.sqrt(Math.abs(acceleration * distance) + (Math.pow(start_velocity, 2.0) + Math.pow(end_velocity, 2.0)) / 2.0);
        t0 = 0.0;
        t_half = Math.abs(cap_velocity - start_velocity) / acceleration;
        t1 = (Math.abs(end_velocity) - Math.abs(cap_velocity)) / acceleration + t_half;
        assert (Math.abs(cap_velocity) + Math.abs(end_velocity)) / 2.0 * t1 < Math.abs(dist) + 1.0 : "Overconstrained profile";
    }
    
    public double getVelocityAtTime(double time){
        double v_exp;
        if(time == t_half)
            v_exp = acceleration * t_half + start_velocity;
        else if(time < t_half){
            v_exp = acceleration * time + start_velocity;
        }
        else if(time > t_half){
            v_exp  = cap_velocity - acceleration * (time - t_half);
        }
        else
            v_exp = 0;
        if(acceleration > 0.0 && max_velocity == start_velocity && max_velocity == end_velocity){
            System.out.println("Over Constrained Profile");
            v_exp = max_velocity;
        }
        return v_exp;
    }
    
    public double getDistAtTime(double time){
        double dist = 0;
        if(time == t_half)
            dist = (getVelocityAtTime(t_half) * t_half); 
        else if(time < t_half){
            dist = (0.5 * getVelocityAtTime(time) * time); 
        }
        else if(time > t_half && time < t1){
            dist = (getVelocityAtTime(t_half) * t_half) +
                    (getVelocityAtTime(t_half) + getVelocityAtTime(time) / 2.0) * (time - t_half);
        }
        else{
            dist = distance;
        }
        if(distance < 0.0){
            return -dist;
        }
        else{
            return dist;
        }
    }
    
    //per 100 msec
    public ArrayList<Double> getDistances(){
        ArrayList<Double> distArr = new ArrayList<>();
        for(double i = 0.0; i < getFinalTime(); i += 0.1){
            distArr.add(new Double(getDistAtTime(i)));
        }
        return distArr;
    }
    
    public double getFinalTime(){
        return t1;
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
