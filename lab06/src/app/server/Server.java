package app.server;

import app.main.Paczkomat;
import app.main.Package;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class Server {
    public static List<Paczkomat> paczkomats = new ArrayList<>();
    public static List<Package> packages = new ArrayList<>();


    public static boolean checkIfPaczkomatExists(int paczkomatNumber) {
        boolean flag = false;
        for (Paczkomat p : paczkomats) {
            if (p.getPaczkomatNumber() == paczkomatNumber) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static List<Package> getPackagesInThisPaczkomat(int paczkomatNumber)
    {
        List<Package> packagesInThisPaczkomat = new ArrayList<>();
        for (Package p: packages) {
            if(paczkomatNumber == p.getReceiverPaczkomat())
            {
                packagesInThisPaczkomat.add(p);
            }
        }
        return packagesInThisPaczkomat;
    }

    public static boolean checkIfPackageInPaczkomat(int paczkomatNumber, int packageNumber)
    {
        boolean flag = false;
        for (Package p: getPackagesInThisPaczkomat(paczkomatNumber)) {
            if (p.getPackageNumber() == packageNumber)
            {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static void pickUpPackage(int packageNumber)
    {
        packages.removeIf(p -> p.getPackageNumber() == packageNumber);
    }



    public static void main(String[] args) {
        if (args.length < 1) return;

        int port = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                new ServerThread(socket).start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}