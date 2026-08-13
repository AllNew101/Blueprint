package org.firstinspires.ftc.teamcode.Opmode.Blueprint;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class FSM_Turret {
    public enum Current_State{
        Idle_state,
        Lock
    }
    DcMotorEx motor1;
    ElapsedTime time;
    Current_State current_state;
    PID_Turret PID;
    Distance distance;

    public void init (HardwareMap hardwareMap, ElapsedTime elapsedTime){
        PID = new PID_Turret();
        time = elapsedTime;

        motor1 = hardwareMap.get(DcMotorEx.class, "Motor1");
    }

    public void command(Current_State state){
        current_state = state;
    }

    public void update_state(double target_theta){
        switch (current_state){
            case Idle_state:
                PID.PIDF (0, motor1.getCurrentPosition());
                break;
            case Lock :
                PID.PIDF(target_theta, motor1.getCurrentPosition());
                break;


        }
    }
}
