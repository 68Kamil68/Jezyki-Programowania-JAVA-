package app.main;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ParcelLockerInterface extends Remote {
    public void putParcel(Parcel parcel) throws RemoteException, RegistrationException;
    public void takeParcel(String parcelNumber) throws RemoteException, RegistrationException;
    public int getId() throws RemoteException;
}
