import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 10314;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    String method = in.readLine();
                    switch (method) {
                        case "ADD": {
                            int lhs = Integer.parseInt(in.readLine());
                            int rhs = Integer.parseInt(in.readLine());
                            out.println(add(lhs, rhs));
                            break;
                        }
                        case "DIVIDE": {
                            int num = Integer.parseInt(in.readLine());
                            int denom = Integer.parseInt(in.readLine());
                            try {
                                out.println(divide(num, denom));
                            } catch (ArithmeticException e) {
                                out.println("ArithmeticException");
                            }
                            break;
                        }
                        case "ECHO": {
                            String message = in.readLine();
                            out.println(echo(message));
                            break;
                        }
                        default:
                            break;
                    }
                } 
            }
        } catch (IOException e) {
            
        }
    }

    // Do not modify any code below this line
    // --------------------------------------
    public static String echo(String message) {
        return "You said " + message + "!";
    }

    public static int add(int lhs, int rhs) {
        return lhs + rhs;
    }

    public static int divide(int num, int denom) {
        if (denom == 0)
            throw new ArithmeticException();

        return num / denom;
    }
}
