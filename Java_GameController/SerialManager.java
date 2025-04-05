import java.io.*;
import gnu.io.*;

public class SerialManager {
    private GameEngine engine;
    private CommPortIdentifier portId;
    private SerialPort serialPort;
    private InputStream input;
    private OutputStream output;

    public SerialManager(GameEngine engine) {
        this.engine = engine;
    }

    public void initialize() {
        try {
            portId = CommPortIdentifier.getPortIdentifier("COM3"); // check later
            serialPort = (SerialPort) portId.open("MotionMaze", 2000);
            serialPort.setSerialPortParams(9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            input = serialPort.getInputStream();
            output = serialPort.getOutputStream();

            new Thread(this::listen).start();
        } catch (Exception e) {
            System.out.println("Serial Initialization Error: " + e.getMessage());
        }
    }

    private void listen() {
        try {
            while (true) {
                if (input.available() > 0) {
                    int dir = input.read();
                    System.out.println("Received Direction Code: " + dir);

                    if (dir >= 1 && dir <= 4) {
                        engine.updatePlayerPosition(dir);

                        // Example feedback logic (can be customized)
                        if (engine.getPlayer().x > 250 && engine.getPlayer().y > 250) {
                            sendFeedback(5); // Simulate obstacle hit
                        }

                        if (engine.getPlayer().x == 100 && engine.getPlayer().y == 100) {
                            sendFeedback(6); // Simulate power-up
                        }
                    } else if (dir == 7) {
                        engine.resetGame(); // If Arduino sends reset request
                        System.out.println("Reset signal received from Arduino.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Serial Listening Error: " + e.getMessage());
        }
    }

    // Sends feedback signal to Arduino
    public void sendFeedback(int code) {
        try {
            if (output != null) {
                output.write(code);
                output.flush();
                System.out.println("Sent Feedback Code: " + code);
            }
        } catch (IOException e) {
            System.out.println("Error Sending Feedback: " + e.getMessage());
        }
    }
}
