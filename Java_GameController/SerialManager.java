package project;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

public class SerialManager {
    private SerialPort serialPort;
    private static final int BAUD = 9600; // Baud rate for serial communication

    // Opens the serial port with specific settings
    public void open(String portName) throws SerialPortException {
        serialPort = new SerialPort(portName);
        serialPort.openPort();
        serialPort.setParams(BAUD, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
    }
  
    // Reads a line from the serial port with timeout
    public String readLine(int timeoutMillis) throws SerialPortException, SerialPortTimeoutException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            byte[] buf = serialPort.readBytes(1, timeoutMillis); // Read 1 byte
            char c = (char) (buf[0] & 0xFF); // Convert byte to character
            if (c == '\n') break;           // End of line
            if (c != '\r') sb.append(c);    // Ignore carriage return
        }
        return sb.toString();               // Return complete line
    }

    // Sends a line to the serial port
    public void writeLine(String line) throws SerialPortException {
        serialPort.writeBytes((line + "\n").getBytes());
    }

    // Closes the serial port
    public void close() throws SerialPortException {
        if (serialPort != null && serialPort.isOpened()) {
            serialPort.closePort();
        }
    }
}
