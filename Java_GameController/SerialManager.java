package project;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

public class SerialManager {
    private SerialPort serialPort;
    private static final int BAUD = 9600;

    public void open(String portName) throws SerialPortException {
        serialPort = new SerialPort(portName);
        serialPort.openPort();
        serialPort.setParams(BAUD, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
    }
  
    public String readLine(int timeoutMillis) throws SerialPortException, SerialPortTimeoutException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            byte[] buf = serialPort.readBytes(1, timeoutMillis);
            char c = (char) (buf[0] & 0xFF);
            if (c == '\n') break;
            if (c != '\r') sb.append(c);
        }
        return sb.toString();
    }

    public void writeLine(String line) throws SerialPortException {
        serialPort.writeBytes((line + "\n").getBytes());
    }

    public void close() throws SerialPortException {
        if (serialPort != null && serialPort.isOpened()) {
            serialPort.closePort();
        }
    }
}