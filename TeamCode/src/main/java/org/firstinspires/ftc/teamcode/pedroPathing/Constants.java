package org.firstinspires.ftc.teamcode.pedroPathing;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
public class Constants {
    public static double forwardPodY = 0.0;
    public static double strafePodX = 0.0;
    public static double breaking = 1;
    public static double breaking_start = 1;

    public static FollowerConstants followerConstants = new FollowerConstants()
            .forwardZeroPowerAcceleration(0.0)
            .lateralZeroPowerAcceleration(0.0)
            .useSecondaryTranslationalPIDF(true)
            .useSecondaryHeadingPIDF(true)
            .useSecondaryDrivePIDF(true)
            .translationalPIDFCoefficients(new PIDFCoefficients(0,0,0,0))
            .secondaryTranslationalPIDFCoefficients(new PIDFCoefficients(0,0,0,0))
            .headingPIDFCoefficients(new PIDFCoefficients(0,0,0,0))
            .secondaryHeadingPIDFCoefficients(new PIDFCoefficients(0,0,0,0))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0,0,0,0,0))
            .secondaryDrivePIDFCoefficients(new FilteredPIDFCoefficients(0,0,0,0,0))
            .centripetalScaling(0.05)
            .automaticHoldEnd(false)
            .mass(10.0);// น้ำหนักหุ่น

    public static MecanumConstants driveConstants = new MecanumConstants()
            .staticFrictionCoefficient(0.0)
            .maxPower(1)
            ///////////////
            .rightFrontMotorName("rightFront")
            .rightRearMotorName("rightRear")
            .leftRearMotorName("leftRear")
            .leftFrontMotorName("leftFront")
            ///////////////
            .xVelocity(0.0)
            .yVelocity(0.0)
            .useBrakeModeInTeleOp(true)
            .useVoltageCompensation(true)
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD);

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(forwardPodY)
            .strafePodX(strafePodX)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("odo")
            .yawScalar(1.0)
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, breaking, breaking_start);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pinpointLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .build();
    }
}