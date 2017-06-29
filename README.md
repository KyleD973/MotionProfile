# Motion Profile for Generic Robot Drive Systems
### A Java Implementation of Motion Profiles :100: :fire:
##### **Implemented June 2017**

*Imagine a car at rest that needs to move to point A from your current position. Now think about what it does when the driver steps on the gas pedal and letting it coast with constant velocity for a couple minutes then steps on the breaks until it goes to a halt. Can you tell me how far it travelled at 1s? 5s? 2000s? How about its velocity? Can you generate a graph of its movement?*

## Purpose:
- Generate a motion profile (sequence of positions and velocities from point A to point B) with a few constraints:
    - velocity may not exceed a specified maximum
    - acceleration may not exceed a specified maximum and must be constant from accelerating to decelerating mode
    - the path must be a continuous function
    - integrating velocity must yield position
    - profile must yield a distance approximate to the distance inputted by the user
    - start and end velocities are integrated, meaning a profile is not stuck to just starting and ending at rest
- This repository contains motion profile drive controller formulae to be ported to an FRC robot
- This will mostly be used for autonomous movements
- Team 973 The Greybots first implemented this for the 2017 season to be used in the 15-second autonomous movements
- This can lowkey be used for physics problems

## How does it work? (Methodology)
- To construct a motion profile, the following values must be taken as parameters: ```max velocity, acceleration, distance, start and end velocities.``` 
- From simple kinematics equations and geometry/calculus, we would be able to construct a motion profile, trapezoidal or triangular, that we can use for a simple robot drive subsystems.
- A singular motion profile will have an`accelerating` mode which will be the first thing the robot will do from start velocity to ramp up to the max velocity, then a `coast` mode is simply for 0 acceleration and robot is at max velocity which is only exclusive for trapezoidal profiled, and finally a `decelerating` mode which will mark the end of the profile and the object must decelerate until it reaches end velocity.
- There will be two kinds of motion profiles: `trapezoidal` and `triangular`. In order to distinguish between them, a formula was developed to determine whether the distance travelled by reaching the max velocity from start velocity then decelerating to end_velocity (minimum distance travelled) is just enough for the distance inputted by the user. If it not enough, then it is a `trapezoidal` profile. If it does not need to reach max velocity, then it is a `triangular` profile.

## How to use this?
```
git clone git@github.com:KyleD973/MotionProfile.git
cd MotionProfile
```
Install [Netbeans](https://netbeans.org) or [Eclipse](http://www.eclipse.org) or any IDE softwares that can compile in Java
Open the repo in the installed software and compile, run project
**Now you can play with motion profiles!**

**If you are porting:**
Just make sure that you are transposing the right variable names and such so that no bugs will be a detriment to your robot program development.

## Scopes and Limitations
- This project was done specifically for FRC robots and done by imagining a moving object in a perfect "physics" world (not accounting for friction and air resistance, etc.)
- This can probably do rotational motion but I have to check on that cause it might be a different formula but same concept
- Graphical representation of the Profile object is not yet supported but not needed for the basic purpose

## Acknowledgements
This was heavily inspired from our 973 College Student Mentor Andrew Nelson.
Reference: [Trap Profile] (https://github.com/yabberyabber/jupyter_notebooks/blob/master/trap%20profile%20with%20optional%20rampup%20and%20follow-through.ipynb)
