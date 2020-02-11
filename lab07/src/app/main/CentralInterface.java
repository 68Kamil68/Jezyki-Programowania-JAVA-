package app.main;

import java.rmi.RemoteException;
import java.rmi.Remote;
import java.util.List;
import java.util.Set;

public interface CentralInterface extends Remote{

    public void register(String plNumber, ParcelLockerInterface plInterface) throws RemoteException, RegistrationException ;
    public void unregister(String plNumber, ParcelLockerInterface pli) throws RemoteException, RegistrationException ;
    public void registerInformer(InformerInterface iInterface) throws RemoteException, RegistrationException ;
    public void unregisterInformer() throws RemoteException, RegistrationException ;
    public int registerParcelLocker(ParcelLockerInterface parcelLockerInterface) throws RemoteException, RegistrationException ;
    public void unregisterParcelLocker(int id) throws RemoteException, RegistrationException ;
    public Boolean checkNumber(String plNumber) throws RemoteException;
    public ParcelLockerInterface getParcelLockerInterface(String plNumber) throws RemoteException, RegistrationException ;
    public InformerInterface getInformerInterface() throws RemoteException, RegistrationException ;
    public List<Integer> getAllParcelLockerNumbers() throws RemoteException;
    public List<String> getParcelsInParcelLocker(ParcelLockerInterface parcelLockerInterface) throws RemoteException, RegistrationException;

}
