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
    private double distance;
	private double max_velocity;
	private double acceleration;
	private double t0, t1, t2, t3; 
	
	public TrapProfile(double dist, double v_max, double accel){
		distance = dist;
		max_velocity = v_max;
		acceleration = accel;
		t0 = 0;
		t1 = v_max / accel;
		t2 = (dist - (v_max * t1)) / v_max + t1;
		t3 = t2 + t1;
	}
	
        public double getFinalTime(){
            return t3;
        }
        
	public double getVelocityAtTime(double time){
		double v_exp;
                if(time >= t1 && time <= t2)
                        v_exp = max_velocity;
                else if(time < t1)
                        v_exp = acceleration * time;
                else if(time > t2)
                        v_exp  = acceleration * (t3 - time);
                else
                    v_exp = 0;
		return v_exp;
	}
}