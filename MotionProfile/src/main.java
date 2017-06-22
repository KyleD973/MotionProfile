/**
 *
 * @author kyledematias
 */
public class main {
    public static boolean checkDist(Profile profileObj){
        double totalDist = 0;
        double r = 0.01;
        if(profileObj instanceof TrapProfile){
            for(double i = 0.0; i < profileObj.getFinalTime() - r; i += r){
                totalDist += 0.5 * (profileObj.getVelocityAtTime(i) + profileObj.getVelocityAtTime(i + r)) * (r);
                //System.out.println(totalDist);
            }
        }
        else if(profileObj instanceof TriProfile){
            for(double i = 0.0; i < profileObj.getFinalTime() - r; i += r){
                totalDist += 0.5 * (profileObj.getVelocityAtTime(i) + profileObj.getVelocityAtTime(i + r)) * (r);
                //System.out.println(totalDist);
            }
        }
        return Math.abs(totalDist - profileObj.getDist()) < 1.0; 
    }
    
    public static boolean exceedsMaxVelocity(Profile profileObj){
        // Velocity constraints test
        boolean doesExceed = false;
        for(double t_rand = 0.0; t_rand < profileObj.getFinalTime() - 0.0005; t_rand+= 0.0005){
            if(profileObj.getDist() > 0.0){
                doesExceed = (Math.abs(profileObj.getVelocityAtTime(t_rand)) > profileObj.getMaxVel() + 0.5);
                //System.out.println(Math.abs(profileObj.getVelocityAtTime(t_rand)));
            }
            else if(profileObj.getDist() < 0.0){
                doesExceed = (Math.abs(profileObj.getVelocityAtTime(t_rand)) < profileObj.getMaxVel() + 0.1);
            }
            if(doesExceed){
                t_rand = Integer.MAX_VALUE;
                System.out.println("Exceeds max velocity");
            }
        }
        return doesExceed;
    }
    
    public static boolean exceedsAccel(Profile profileObj){
        boolean doesExceed = false;
        for(double t_rand = 0.0;t_rand < profileObj.getFinalTime() - 0.01; t_rand+= 0.01){
            doesExceed = Math.abs((profileObj.getVelocityAtTime(t_rand + 0.01) - profileObj.getVelocityAtTime(t_rand)) / 0.01) 
                       > profileObj.getAccel() + 0.1;
            if(doesExceed){
                t_rand = Integer.MAX_VALUE;
                System.out.println("Exceeds acceleration");
            }
        }
        return doesExceed;
    }
    
    public static boolean checkExtremes(Profile profileObj){
        return profileObj.getVelocityAtTime(0.0) == profileObj.getStartVel() 
                && profileObj.getVelocityAtTime(profileObj.getFinalTime()) == profileObj.getEndVel();
    }
    
    public static void main (String[] args) throws java.lang.Exception
	{
            double v_max = 30.0;
            double dist = 100.0;
            double accel = 20.0;
            double start_vel = 10.0;
            double end_vel = 20.0;
            Profile profile1 = Profile.getVelProfile(v_max, accel, dist, start_vel, end_vel);
            
            System.out.println(profile1.getFinalTime());
            System.out.println(profile1.getDistAtTime(profile1.getFinalTime()));
            System.out.println(profile1.getDistances());
            System.out.println(profile1.getVelocityAtTime(profile1.getFinalTime()));
            System.out.println(checkExtremes(profile1) + " " + exceedsMaxVelocity(profile1) + exceedsAccel(profile1) + checkDist(profile1));
        }
}
