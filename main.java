/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kyledematias
 */
public class main {
    public static void main (String[] args) throws java.lang.Exception
	{
            double v_max = 10.0;
            double dist = 50.0;
            double accel = 100.0;
            Profile profile1 = Profile.getProfile(v_max, accel, dist);
            double t_rand = 0.0;
            while(t_rand <= profile1.getFinalTime()){
                System.out.println(Math.abs(profile1.getVelocityAtTime(t_rand)));
                if(Math.abs(profile1.getVelocityAtTime(t_rand)) > v_max + 0.01){
                    System.out.print(Math.abs(profile1.getVelocityAtTime(t_rand)) + " " + t_rand);
                    throw new Exception("Velocity exceeded max");
                }
                t_rand+= 0.01;
            }
            
            System.out.print(profile1.getFinalTime());
             double t_rand2 = 0.0;
            while(t_rand2 <= profile1.getFinalTime()){
                if(Math.abs((profile1.getVelocityAtTime(t_rand2) - profile1.getVelocityAtTime(t_rand2 + 0.01)) / 0.01) 
                           > accel + 0.000001){
                    System.out.print(Math.abs((profile1.getVelocityAtTime(t_rand2) - profile1.getVelocityAtTime(t_rand2 + 0.01)) / 0.01) + " " + t_rand2);
                    throw new Exception("acceleration exceeded max");
                }
                System.out.println(Math.abs((profile1.getVelocityAtTime(t_rand2) - profile1.getVelocityAtTime(t_rand2 + 0.01)) / 0.01) + " " + t_rand2);
                t_rand2+= 0.01;
            }
        }
}
