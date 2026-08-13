package org.firstinspires.ftc.teamcode.Opmode.Blueprint;

import com.acmerobotics.dashboard.config.Config;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
@Config
public class PID_Turret {
    public static double kp = 0 ;
    public static double ki = 0 ;
    public static double kd = 0 ;
    public static double kf = 0 ;
    public static double kp_sec = 0 ;
    public static double ki_sec = 0 ;
    public static double kd_sec = 0 ;
    public static double kf_sec = 0 ;//feedforward
    public static double kg = 0 ;//gravity force
    public static double Max_angle = 120;
    public static double Min_angle = -120;

    private double previous_error, error, delta_error, integral, previous_time, delta_time, power_motor;
    private boolean Check_limit;
    private double Per_round = 0;
    private double gear_motor = 0;
    private double gear_turret = 0;
    private ElapsedTime time;

    public void init (ElapsedTime elapsedTime){
        time = elapsedTime;
        previous_time = time.seconds();
        previous_error = 0;
        Check_limit = false;
    }
    public double convert_current_to_degree(double motor_posi){
        return (motor_posi / Per_round) * 360 * gear_motor / gear_turret;
    }
    public double PIDF (double target_posi , double current_posi){
        delta_time = time.seconds() - previous_time;
        if (convert_current_to_degree(current_posi) > Max_angle){error = Max_angle - convert_current_to_degree(current_posi);}
        else if (convert_current_to_degree(current_posi) < Min_angle){error = Min_angle - convert_current_to_degree(current_posi);}
        else {error = target_posi - convert_current_to_degree(current_posi);}
        delta_error = error - previous_error;
        integral += error*delta_time;

        if (Math.abs(error) > 4) {
            if (Math.abs(error) > 30) {
                power_motor = (kp * error) + (ki * integral) + (kd * (delta_error / delta_time)) + (kf * Math.signum(error)) + kg;
            } else {
                power_motor = (kp_sec * error) + (ki_sec * integral) + (kd_sec * (delta_error / delta_time)) + (kf_sec * Math.signum(error)) + kg;
            }
        }

        previous_time = time.seconds();
        previous_error = error;
        return power_motor;
    }
}