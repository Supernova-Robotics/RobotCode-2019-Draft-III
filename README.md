# RobotCode 2019 Draft III


Using Command Based controlling system.

# Subsystems

## Chassis

 - six CAN Victor motor controllers for the chassis
 - two solenoids for lift-up

## Collector

 - \[in progress\]

## Arm

 - two PWM Spark motor controllers for the arm lift-up
 - one PWM Spark motor controller for the claw lift-up
 
 
# Pin Layouts

Chassis

chassis - motor_left_0 = VectorSPX(10)
chassis - motor_left_1 = VectorSPX(11)
chassis - motor_left_2 = VectorSPX(12)
chassis - motor_right_0 = VectorSPX(13)
chassis - motor_right_1 = VectorSPX(14)
chassis - motor_right_2 = VectorSPX(15)

Arm

arm_0 = TalonSRX(20)
arm_1 = TalonSRX(21)
claw = TalonSRX(22)

pusher = Solenoid(1)
claw = Solenoid(2, 3)

Hook

extender = Solenoid(4)
hook = Solenoid(5)

Intake
lift = Spark(1)
collector = Spark(2)