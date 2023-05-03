import java.io.*;
import java.net.Socket;

public class Client {

    /**
     * This method name and parameters must remain as-is
     */
    public static int add(int lhs, int rhs) {
        return sendRequest("ADD", lhs, rhs);
    }

    /**
     * This method name and parameters must remain as-is
     */
    public static int divide(int num, int denom) {
        return sendRequest("DIVIDE", num, denom);
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static String echo(String message) {
        return sendRequest("ECHO", message);
    }

    private static int sendRequest(String method, int... args) {
        try (Socket socket = new Socket("localhost", PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
    
            out.println(method);
            for (int arg : args) {
                out.println(arg);
            }
    
            String response = in.readLine();
    
            if (method.equals("DIVIDE") && response.equals("ArithmeticException")) {
                throw new ArithmeticException();
            }
    
            return Integer.parseInt(response);
        } catch (IOException e) {
            System.err.println("Error connecting to the server: " + e.getMessage());
            return -1;
        }
    }
    
    private static String sendRequest(String method, String message) {
        try (Socket socket = new Socket("localhost", PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(method);
            out.println(message);

            return in.readLine();
        } catch (IOException e) {
            System.err.println("Error connecting to the server: " + e.getMessage());
            return "";
        }
    }

    // Do not modify any code below this line
    // --------------------------------------
    String server = "localhost";
    public static final int PORT = 10314;

    public static void main(String... args) {
        // All of the code below this line must be uncommented
        // to be successfully graded.
        System.out.print("Testing... ");

        if (add(2, 4) == 6)
            System.out.print(".");
        else
            System.out.print("X");

        try {
            divide(1, 0);
            System.out.print("X");
        }
        catch (ArithmeticException x) {
            System.out.print(".");
        }

        if (echo("Hello").equals("You said Hello!"))
            System.out.print(".");
        else
            System.out.print("X");
        
        System.out.println(" Finished");
    }
}