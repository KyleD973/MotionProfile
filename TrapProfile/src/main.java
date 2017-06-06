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
    public static boolean checkDist(Profile profileObj){
        double totalDist = 0;
        for(double i = 0.0; i <= profileObj.getFinalTime(); i += 1.0){
            totalDist += 0.5 * (profileObj.getVelocityAtTime(i) - profileObj.getVelocityAtTime(i - 0.1)) * (0.1);
        }
        return Math.abs(totalDist - profileObj.getDist()) < 0.05; 
    }
    
    public static boolean exceedsMaxVelocity(Profile profileObj){
        // Velocity constraints test
        boolean doesExceed = false;
        for(double t_rand = 0.0; t_rand <= profileObj.getFinalTime(); t_rand+= 0.01){
            doesExceed = (Math.abs(profileObj.getVelocityAtTime(t_rand)) > profileObj.getMaxVel() + 0.01);
            if(doesExceed){
                System.out.println("Exceeds max velocity");
            }
        }
        return doesExceed;
    }
    
    public static boolean exceedsAccel(Profile profileObj){
        boolean doesExceed = false;
        for(double t_rand = 0.0;t_rand <= profileObj.getFinalTime(); t_rand+= 0.01){
            doesExceed = Math.abs((profileObj.getVelocityAtTime(t_rand) - profileObj.getVelocityAtTime(t_rand + 0.01)) / 0.01) 
                       > profileObj.getAccel() + 0.000001;
            if(doesExceed){
                System.out.println("Exceeds acceleration");
            }
        }
        return doesExceed;
    }
    
    public static boolean checkExtremes(Profile profileObj){
        return profileObj.getVelocityAtTime(0.0) == 0.0 
                && profileObj.getVelocityAtTime(profileObj.getFinalTime()) == 0.0;
    }
    
    public static void main (String[] args) throws java.lang.Exception
	{
            double v_max = 90.0;
            double dist = 50.0;
            double accel = 100.0;
            Profile profile1 = Profile.getVelProfile(v_max, accel, dist);
            
            System.out.println(profile1.getDistAtTime(profile1.getFinalTime()));//profile1.getFinalTime()));
            System.out.println(profile1.getDistances());
            System.out.println(profile1.getVelocityAtTime(profile1.getFinalTime()));
            System.out.println(checkExtremes(profile1) + " " + exceedsAccel(profile1) + exceedsAccel(profile1) + checkDist(profile1));
        }
}
