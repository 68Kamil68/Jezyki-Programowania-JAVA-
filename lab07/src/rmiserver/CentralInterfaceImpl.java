package rmiserver;

import app.main.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class CentralInterfaceImpl implements CentralInterface {
    int lockerId = 10;
    private List<String> parcels;
    private InformerInterface informerInterface;
    private Map<Integer, ParcelLockerInterface> parcelLockers;
    private Map<String, ParcelLockerInterface> parcelsInLockers;

    public CentralInterfaceImpl()
    {
        this.parcelLockers = new TreeMap<>();
        this.parcelsInLockers = new TreeMap<>();
        this.parcels = new ArrayList<>();
    }

    @Override
    public void register(String plNumber, ParcelLockerInterface plInterface) throws RemoteException, RegistrationException {
        checkNumber(plNumber);
       // checkIfParcelExists(plNumber);
        try {
            parcels.add(plNumber);
            parcelsInLockers.put(plNumber, plInterface);
            informerInterface.putMessage("wyslano paczke nr: " + plNumber);
        }
        catch (Exception e)
        {
            throw new RegistrationException("Nie udalo sie zarejestrowac paczki");
        }
    }

    @Override
    public List<Integer> getAllParcelLockerNumbers() throws RemoteException
    {
        List<Integer> numbers = new ArrayList<>();
        for (Integer i: parcelLockers.keySet())
        {
            numbers.add(i);
        }
        return numbers;
    }

    @Override
    public List<String> getParcelsInParcelLocker(ParcelLockerInterface parcelLockerInterface) throws RemoteException, RegistrationException {
        List<String> parcelNumbers = new ArrayList<>();
        for (String p:parcels) {
            if(getParcelLockerInterface(p).equals(parcelLockerInterface))
            {
                parcelNumbers.add(p);
            }
        }
        return parcelNumbers;
    }

    public static void registerCentral() throws Exception
    {
        Registry registry = LocateRegistry.createRegistry(1111);
        CentralInterfaceImpl centralInterface = new CentralInterfaceImpl();
        CentralInterface centralInterface1 = (CentralInterface) UnicastRemoteObject.exportObject(centralInterface, 0);
        registry.bind("Central", centralInterface1);
        System.out.println(registry.toString());
    }

    @Override
    public void unregister(String plNumber, ParcelLockerInterface pli) throws RemoteException, RegistrationException {
        if(parcels.contains(plNumber) && parcelsInLockers.get(plNumber).equals(pli))
        {
            try {
                parcelsInLockers.remove(plNumber);
                parcels.remove(plNumber);
                this.informerInterface.putMessage("Odebrano paczke:" + plNumber);
            }
            catch (Exception e)
            {
                this.informerInterface.putMessage("Nie udalo sie odebrac paczki" + plNumber);
                throw new RegistrationException("Nie udalo sie wyrejestrowac paczki");
            }
        }
        else
        {
            this.informerInterface.putMessage("Nie udalo sie odebrac paczki " + plNumber);
        }
        }

    @Override
    public void registerInformer(InformerInterface iInterface) throws RemoteException, RegistrationException {
        this.informerInterface = iInterface;
    }

    @Override
    public void unregisterInformer() throws RemoteException, RegistrationException {
        this.informerInterface = null;
    }

    @Override
    public int registerParcelLocker(ParcelLockerInterface parcelLockerInterface) throws RemoteException, RegistrationException {
        System.out.println("jd");
        this.lockerId++;
        if(!parcelLockers.containsValue(parcelLockerInterface))
        {
            parcelLockers.put(lockerId, parcelLockerInterface);
            this.informerInterface.putMessage("Zarejestrowano paczkomat:" + lockerId);
            return this.lockerId;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public void unregisterParcelLocker(int id) throws RemoteException, RegistrationException {
        if(parcelLockers.containsKey(id))
        {
            parcelLockers.remove(id);
            this.informerInterface.putMessage("Wyrejestrowano paczkomat:" + id);
        }
        else
        {
            throw new RegistrationException("Nie udalo sie wyrejesttrowac paczkomatu");
        }
    }


    @Override
    public Boolean checkNumber(String plNumber) throws RemoteException {
        boolean flag = false;
        try {
            int d = Integer.parseInt(plNumber);
        }
        catch (Exception e)
        {
            throw new RemoteException("Not a number");
        }
        return flag;
    }

    public Boolean checkIfParcelExists(String plNumber)
    {
        boolean flag = false;
        if(!parcels.isEmpty())
        {
            for (String p: parcels) {
                if(p == plNumber)
                {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    @Override
    public ParcelLockerInterface getParcelLockerInterface(String plNumber) throws RemoteException, RegistrationException {
        checkNumber(plNumber);
        ParcelLockerInterface pli = null;
        try {
            pli = parcelsInLockers.get(plNumber);
        }
        catch (Exception e)
        {
            throw new RegistrationException("Nie udalo sie odnalezc takiej paczki");
        }
        return pli;
    }

    @Override
    public InformerInterface getInformerInterface() throws RemoteException, RegistrationException {
        return this.informerInterface;
    }
}
