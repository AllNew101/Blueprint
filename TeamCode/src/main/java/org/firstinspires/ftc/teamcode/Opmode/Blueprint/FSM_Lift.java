package org.firstinspires.ftc.teamcode.Opmode.Blueprint;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class FSM_Lift {
    enum Current_State{
        a,
        b,
        c,
        d
    }

    ElapsedTime time;
    DcMotorEx motor1;
    DcMotorEx motor2;
    public Current_State current_state = Current_State.a ;


    public void init (HardwareMap hardwareMap){
        motor1 = hardwareMap.get(DcMotorEx.class, "Motor1");
        motor2 = hardwareMap.get(DcMotorEx.class, "Motor2");
        time = new ElapsedTime();
        time.startTime();
    }

    public void command (Current_State Lift_state){
        current_state = Lift_state;
        //
    }

    public void update_state (){
        switch (current_state){
            case a:
                //
                break;
            case b:
                //
                break;
            case c:
                //
                break;
            case d:
                //
                break;
            default:
                //

        }
    }
}
