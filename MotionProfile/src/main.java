/**
 *
 * @author kyledematias
 */
public class main {
    public static void checkDist(Profile profileObj){
        double totalDist = 0;
        double r = 0.01;
        if(profileObj instanceof TrapProfile){
            for(double i = 0.0; i < profileObj.getFinalTime() - r; i += r){
                totalDist += 0.5 * (profileObj.getVelocityAtTime(i) + profileObj.getVelocityAtTime(i + r)) * (r);
                System.out.println(totalDist);
            }
        }
        else if(profileObj instanceof TriProfile){
            for(double i = 0.0; i < profileObj.getFinalTime() - r; i += r){
                totalDist += 0.5 * (profileObj.getVelocityAtTime(i) + profileObj.getVelocityAtTime(i + r)) * (r);
                System.out.println(totalDist);
            }
        }
        if(profileObj.getDist() >= 0.0){
            assert Math.abs((Math.abs(totalDist) - Math.abs(profileObj.getDist()))) >= 1.0: "Distance error"; 
        }
        else if(profileObj.getDist() < 0.0){
            assert Math.abs((Math.abs(totalDist) - Math.abs(profileObj.getDist()))) <= 1.0: "Distance error"; 
        }
    }
    
    public static void exceedsMaxVelocity(Profile profileObj){
        // Velocity constraints test
        for(double t = 0.0; t < profileObj.getFinalTime() - 0.0005; t+= 0.0005){
            if(profileObj.getDist() >= 0.0){
                assert (Math.abs(profileObj.getVelocityAtTime(t)) < profileObj.getMaxVel() + 0.5): "Exceeds MaxVel";
            }
            else if(profileObj.getDist() < 0.0){
                assert (Math.abs(profileObj.getVelocityAtTime(t)) < -profileObj.getMaxVel() + 0.5): "Exceeds MaxVel";
            }
            //System.out.println(Math.abs(profileObj.getVelocityAtTime(t)));
        }
    }
    
    public static void exceedsAccel(Profile profileObj){
        for(double t = 0.0;t < profileObj.getFinalTime() - 0.01; t+= 0.01){
            assert Math.abs((profileObj.getVelocityAtTime(t + 0.01) - profileObj.getVelocityAtTime(t)) / 0.01) 
                       < profileObj.getAccel() + 0.1: "Exceeds Accel";
        }
    }
    
    public static void checkExtremes(Profile profileObj){
        if(profileObj.getDist() >= 0.0){
            assert profileObj.getVelocityAtTime(0.0) == profileObj.getStartVel() 
                    && profileObj.getVelocityAtTime(profileObj.getFinalTime()) == profileObj.getEndVel(): "Initial and Final Velocities not matching";
        }
        else if(profileObj.getDist() >= 0.0){
            assert profileObj.getVelocityAtTime(0.0) == -profileObj.getStartVel() 
                    && profileObj.getVelocityAtTime(profileObj.getFinalTime()) == -profileObj.getEndVel(): "Initial and Final Velocities not matching";
        }
    }
    
    public static void main (String[] args) throws java.lang.Exception
	{
            Profile profile1 = Profile.getVelProfile(40, 10, -200, 10, 20);
            checkDist(profile1);
            checkExtremes(profile1);
            exceedsMaxVelocity(profile1);
            exceedsAccel(profile1);
           /* int i = 0;
            for(double v_max = 5.0; v_max <= 10.0; v_max++){
                for(double accel = 5.0; accel <= 10.0; accel++){
                    for(double dist = 5.0; dist <= 10.0; dist++){
                        for(double start_vel = 5.0; start_vel <= 10.0; start_vel++){
                            for(double end_vel = 5.0; end_vel <= 10.0; end_vel++){
                                Profile profile1 = Profile.getVelProfile(v_max, accel, dist, start_vel, end_vel);
                                checkDist(profile1);
                                checkExtremes(profile1);
                                exceedsMaxVelocity(profile1);
                                exceedsAccel(profile1);
                            }
                        }
                    }
                }
            } */     
        }
}
