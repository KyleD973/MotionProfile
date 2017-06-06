/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kyledematias
 */
import java.util.ArrayList;

public class TriProfile extends Profile {
    private double max_velocity, acceleration, distance, t0, t_half, t1;
    
    public TriProfile(double v_max, double accel, double dist){
        max_velocity = v_max;
        acceleration = accel;
        distance = dist;
        t0 = 0.0;
        t_half = Math.sqrt(0.5 * Math.abs(distance) / acceleration);
        t1 = 2.0 * t_half;
    }
    
    public double getVelocityAtTime(double time){
        double v_exp;
        if(time == t_half)
            v_exp = acceleration * t_half;
        else if(time < t_half){
            v_exp = acceleration * time;
        }
        else if(time > t_half){
            v_exp  = acceleration * (t1 - time);
        }
        else
            v_exp = 0;
        return v_exp;
    }
    
    public double getDistAtTime(double time){
        double dist = 0;
        if(time == t_half)
            dist = (getVelocityAtTime(t_half) * t_half); 
        else if(time < t_half){
            dist = (0.5 * getVelocityAtTime(time) * time); 
        }
        else if(time > t_half && time <= t1){
            dist = (getVelocityAtTime(t_half) * t_half) +
                    (getVelocityAtTime(t_half) + getVelocityAtTime(time) / 2.0) * (time - t_half);
//(Math.pow((getVelocityAtTime(time)), 2.0) - Math.pow((getVelocityAtTime(t_half)), 2.0)) / (2.0 * -acceleration);
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
}
