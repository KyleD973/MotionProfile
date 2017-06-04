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
        
public abstract class Profile {
    
    public static Profile getVelProfile(double max_velocity, double acceleration, double distance){
        if((Math.pow(max_velocity, 2.0) / acceleration) < distance){
            System.out.println("trapezoidal");
            return new TrapProfile(max_velocity, acceleration, distance);
        }
        else {
            System.out.println("triangular");
            return new TriProfile(max_velocity, acceleration, distance);
	}
    }
   
    public abstract double getFinalTime();
    public abstract double getVelocityAtTime(double time);
    public abstract double getDistAtTime(double time);
    public abstract ArrayList<Double> getDistances(); //each 100 ms increment
    public abstract double getDist();
    public abstract double getMaxVel();
    public abstract double getAccel();
}