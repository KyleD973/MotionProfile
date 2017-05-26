/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kyledematias
 */
public class TrapProfile extends Profile {
    private double distance , max_velocity, acceleration, t0, t1, t2, t3; 
	
    public TrapProfile(double v_max, double accel, double dist){
        distance = dist;
        max_velocity = v_max;
        acceleration = accel;
        t0 = 0;
        t1 = v_max / accel;
        t2 = Math.abs((dist - (v_max * t1)) / v_max + t1);
        t3 = t2 + t1;
    }

    public double getFinalTime(){
        return t3;
    }

    public double getVelocityAtTime(double time){
        double v_exp;
        if(time >= t1 && time <= t2){
            v_exp = max_velocity;
        }
        else if(time < t1){
            v_exp = acceleration * time;
        }
        else if(time > t2){
            v_exp  = acceleration * (t3 - time);
        }
        else{
            v_exp = 0;
        }
        return v_exp;
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