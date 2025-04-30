# Motion Maze

**Motion Maze** is a motion-controlled maze game using a Lilypad Arduino, sensors, and a Java-based game engine. The player moves by tilting the controller, and real-time feedback is provided via LEDs, sound, and a servo motor.

---

## Hardware Used

- Lilypad Arduino
- MPU6050 Accelerometer
- PIR Motion Sensor
- Push Button
- RGB LED
- Buzzer
- Servo Motor
- ZigBee Module (for wireless connection)

---

## Wiring (to Lilypad)

| Component     | Lilypad Pin |
|---------------|-------------|
| MPU6050       | SDA → A4, SCL → A5 |
| Motion Sensor | D2          |
| Button        | D3          |
| RGB LED       | D6 (Red), D9 (Green), D10 (Blue) |
| Buzzer        | D11         |
| Servo         | D12         |
| ZigBee        | RX → D4, TX → D5 |
| Power         | 3.3V/5V + GND |

---

## How to Run

### Arduino:
1. Upload `MotionMazeController.ino` to Lilypad using Arduino IDE.
2. Open Serial Monitor to confirm MPU6050 is working.

### Java:
1. Open the Java project in Eclipse.
2. In `SerialManager.java`, select the correct COM port.
3. Run `MainGame.java`.

---

## Controls

- **Tilt**: Move player
- **Motion sensor**: Adds obstacles
- **Button**: Start/Restart game
- **LED + Buzzer**: Feedback
- **Servo**: Moves during gameplay

---

## Design Patterns

- **Observer Pattern**
- **State Pattern**

---

## AI Use

ChatGPT helped with writing and explaining code. Not used in report writing.

---

## Demo

[YouTube Link Here]
