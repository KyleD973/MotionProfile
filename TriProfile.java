/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kyledematias
 */
public class TriProfile extends Profile {
    private double max_velocity, acceleration, distance, t0, t_half, t1;
    
    public TriProfile(double v_max, double accel, double dist){
        max_velocity = v_max;
        acceleration = accel;
        distance = dist;
        t0 = 0.0;
        t_half = Math.sqrt(0.5 * distance / acceleration);
        t1 = 2.0 * t_half;
    }
    
    public double getVelocityAtTime(double time){
        double v_exp;
        if(time == t_half)
                v_exp = acceleration * t_half;
        else if(time < t_half)
                v_exp = acceleration * time;
        else if(time > t_half)
                v_exp  = acceleration * (t1 - time);	
        else
            v_exp = 0;
        return v_exp;
    }
    
    public double getFinalTime(){
        return t1;
    }
    
    
}
