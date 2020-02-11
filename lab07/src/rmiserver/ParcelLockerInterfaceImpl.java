package rmiserver;

import app.main.CentralInterface;
import app.main.Parcel;
import app.main.ParcelLockerInterface;
import app.main.RegistrationException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.AlreadyBoundException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParcelLockerInterfaceImpl implements ParcelLockerInterface {
    private int id;
    private List<Parcel> parcels;
    public CentralInterface centralInterface;
    public ParcelLockerInterface parcelLockerInterface;

    public ParcelLockerInterfaceImpl()
    {
        parcels = new ArrayList<>();
    }

    @Override
    public void putParcel(Parcel parcel) throws RemoteException, RegistrationException {
        Random rand = new Random();
        parcel.setParcelNumber((String.valueOf(rand.nextInt(10000))));
        //Registry registry = LocateRegistry.getRegistry();
        centralInterface.register(parcel.getParcelNumber(), this);
        parcels.add(parcel);
    }

    @Override
    public void takeParcel(String parcelNumber) throws RemoteException, RegistrationException {
        centralInterface.unregister(parcelNumber, this);
    }


    public void registerToCentral() throws Exception {
        //UnicastRemoteObject.unexportObject(this, true);
        Registry registry = LocateRegistry.getRegistry("localhost", 1111);
        centralInterface = (CentralInterface) registry.lookup("Central");
        BufferedReader readline = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Podaj port: ");
        int port = Integer.parseInt(readline.readLine());
        parcelLockerInterface = (ParcelLockerInterface) UnicastRemoteObject.exportObject(this, port);
        System.out.println("jd");
        id = centralInterface.registerParcelLocker(parcelLockerInterface); //TUTAJ ZMIENIC NA THIS JAKBY CO
        System.out.println("jd");
        if (id != 0)
        {
            registry.bind("ParcelLocker" + id, parcelLockerInterface);
        }
    }

    public void unregisterFromCentral() throws RemoteException, NotBoundException, RegistrationException
    {
        Registry registry = LocateRegistry.getRegistry("localhost", 1111);
        CentralInterface centralInterface = (CentralInterface) registry.lookup("Central");
        centralInterface.unregisterParcelLocker(id);
    }

    @Override
    public int getId() {
        return id;
    }


}
