package org.firstinspires.ftc.teamcode.Opmode.Blueprint;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class FSM_Turret {

    DcMotorEx motor1;
    ElapsedTime time;

    public void init (HardwareMap hardwareMap, ElapsedTime elapsedTime){
        time = elapsedTime;

        motor1 = hardwareMap.get(DcMotorEx.class, "Motor1");

    }
}
