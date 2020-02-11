package app.server;

import java.net.*;
import java.io.*;


public class Client {


    public static void main(String[] args) {
        if (args.length < 2) return;

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(hostname, port)) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            String text;

            do {
//                System.out.println("1 - utworz paczkomat");
//                System.out.println("2 - nadaj paczke");
//                System.out.println("3 - odbierz paczke");
//                System.out.println("4 - wyswietl paczkomat");

                BufferedReader readline = new BufferedReader(new InputStreamReader(System.in));
                text = readline.readLine();
                writer.println(text);
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String line;
                while ((line = reader.readLine()) != null || line != "")
                {
                    if(line.isEmpty())
                    {
                        break;
                    }
                    else
                    {
                        System.out.println(line);
                    }

                }

            } while (!text.equals("bye"));

            socket.close();

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}