package org.firstinspires.ftc.teamcode.Opmode.Blueprint;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import com.acmerobotics.dashboard.config.Config;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class PID_posi {
    public static double kp = 0 ;
    public static double ki = 0 ;
    public static double kd = 0 ;
    public static double kg = 0 ;

    private double previous_error, error, delta_error, integral, previous_time, delta_time, power_motor;
    private DcMotorEx motor1;
    private ElapsedTime time;

    public void init (HardwareMap hardwareMap){
        motor1 = hardwareMap.get(DcMotorEx.class, "Motor1");
        time = new ElapsedTime();
        time.startTime();
        previous_time = time.seconds();
        previous_error = 0;
    }
    public void PIDF (double target_posi){
        delta_time = time.seconds() - previous_time;
        error = target_posi - motor1.getCurrentPosition();
        delta_error = error - previous_error;
        integral += error*delta_time;

        power_motor = (kp*error) + (ki*integral) + (kd*(delta_error/delta_time)) + kg;
        previous_time = time.seconds();
        previous_error = error;
        set_posi(power_motor);
    }
    public void set_posi (double power){
        motor1.setPower(power);
    }
}