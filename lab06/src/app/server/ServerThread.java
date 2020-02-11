package app.server;

import app.main.Paczkomat;
import app.main.Package;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            String text;
            boolean createdPaczkomat = false;
            int paczkomatNumber=0;
            String senderName;

            do {
                text = reader.readLine();
                switch (text)
                {
                    case "1":
                        if (createdPaczkomat == false)
                        {
                            writer.println("Server: Wpisz nr paczkomatu. ktorym jestes\n");
                            text = reader.readLine();
                            paczkomatNumber = Integer.parseInt(text);
                            Paczkomat paczkomat = new Paczkomat(paczkomatNumber);
                            Server.paczkomats.add(paczkomat);
                            writer.println("Server: Utworzono paczkomat o numerze: " + paczkomat.getPaczkomatNumber());
                            System.out.println("dodano paczkomat " + paczkomatNumber);
                            writer.println("Server: nacisnij dowolny przycisk aby wrocic do menu");
                            createdPaczkomat = true;
                            break;
                        }
                        else
                        {
                            writer.println("Juz stworzyles paczkomat\n");
                            writer.println("Server: nacisnij dowolny przycisk aby wrocic do menu");
                            break;
                        }

                    case "2":
                        writer.println("Podaj swoje imie");
                        writer.println("");
                        senderName = reader.readLine();
                        writer.println("Podaj imie odbiorcy");
                        writer.println("");
                        String receiverName = reader.readLine();
                        writer.println("podaj zawartosc paczki");
                        writer.println("");
                        String contents = reader.readLine();
                        writer.println("Wpisz numer paczkomatu odbiorczego");
                        writer.println("");
                        Random rand = new Random();
                        int receiverPaczkomat = Integer.parseInt(reader.readLine());
                        if(Server.checkIfPaczkomatExists(receiverPaczkomat))
                        {
                            int packageNumber = rand.nextInt(10000);

                            try {
                                Package pack = new Package(senderName, receiverName, contents,
                                        packageNumber, paczkomatNumber, receiverPaczkomat);
                                Server.packages.add(pack);
                                writer.println("Dodano paczke");
                                System.out.println(paczkomatNumber + "nadal paczke" + packageNumber);
                                writer.println("Server: nacisnij dowolny przycisk aby wrocic do menu");
                            } catch (Exception e) {
                                e.printStackTrace();
                                writer.println("Blad przy dodawaniu paczki");
                                writer.println("Server: nacisnij dowolny przycisk aby wrocic do menu");
                            }
                            break;
                        }
                        else
                        {
                            writer.println("Blad przy dodawaniu paczki");
                            break;
                        }
                    case "3":
                        writer.println("wpisz nr paczki, ktora chcesz odebrac:");
                        writer.println("");
                        int packageNumber = Integer.parseInt(reader.readLine());
                        if(Server.checkIfPackageInPaczkomat(paczkomatNumber, packageNumber))
                        {
                            Server.pickUpPackage(packageNumber);
                            writer.println("odebrano paczke");
                            System.out.println(paczkomatNumber + "odebral paczke" + packageNumber);
                            break;
                        }
                        else
                        {
                            writer.println("Blad przy odbieraniu paczki");
                            writer.println("");
                            break;
                        }
                    case "4":
                        for (Paczkomat p : Server.paczkomats ) {
                            writer.println("Paczkomat nr: " + p.getPaczkomatNumber());
                        }
                        System.out.println(paczkomatNumber + " wyswietla paczkomaty");
                        writer.println("Server: nacisnij dowolny przycisk aby wrocic do menu");
                        break;
                    case "5":
                        for (Package p : Server.getPackagesInThisPaczkomat(paczkomatNumber)) {
                            writer.println("paczka nr:" + p.getPackageNumber());
                        }
                        System.out.println(paczkomatNumber + " wyswietla paczki");
                        break;
                    default:
                        writer.println("1 - utworz paczkomat");
                        writer.println("2 - nadaj paczke");
                        writer.println("3 - odbierz paczke");
                        writer.println("4 - wyswietl paczkomaty");
                        writer.println("5 - wyswietl paczki w tym paczkomacie");
                        break;
                }
                writer.println("");
            } while (!text.equals("bye"));
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}