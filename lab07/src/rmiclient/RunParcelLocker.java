package rmiclient;

import app.main.ContactData;
import app.main.Parcel;
import rmiserver.ParcelLockerInterfaceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

public class RunParcelLocker {

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ParcelLockerInterfaceImpl parcelL = new ParcelLockerInterfaceImpl();
        parcelL.registerToCentral();
        BufferedReader readline = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("1 - nadaj paczke");
            System.out.println("2 - odbierz paczke");
            System.out.println("3 - wyswietl paczkomaty");
            System.out.println("4 - wyswietl paczki w tym paczkomacie");
            System.out.println("5 - wyrejestruj paczkomat");
            String choice = readline.readLine();
            switch (choice) {
                case "1":
                    System.out.println("Wpisz swoje imie: ");
                    String nameSender = br.readLine();
                    System.out.println("Wpisz swoj nr telefonu: ");
                    String telephoneSender = br.readLine();
                    ContactData sender = new ContactData(nameSender, telephoneSender);
                    System.out.println("Wpisz imie odbiorcy: ");
                    String nameReceiver = br.readLine();
                    System.out.println("Wpisz numer telefonu odbiorcy: ");
                    String telephoneReceiver = br.readLine();
                    ContactData receiver = new ContactData(nameReceiver, telephoneReceiver);
                    System.out.println("Co zawiera paczcka? ");
                    String payload = br.readLine();
                    System.out.println("Podaj nr paczkomatu, do ktorego chcesz wyslac paczke: ");
                    String toParcelLockerNumbr = br.readLine();
                    Parcel parcel = new Parcel(sender, receiver, payload, String.valueOf(parcelL.getId()), toParcelLockerNumbr);
                    parcelL.putParcel(parcel);
                    break;
                case "2":
                    System.out.println("Wpisz nr paczki, ktora chcesz odebrac: ");
                    String parcelNumber = br.readLine();
                    parcelL.takeParcel(parcelNumber);
                    break;
                case "3":
                    List<Integer> numbers = parcelL.centralInterface.getAllParcelLockerNumbers();
                    for (Integer n: numbers)
                    {
                        System.out.println("Paczkomat nr: " + n);
                    }
                    break;
                case "4":
                    List<String> parcelNums = parcelL.centralInterface.getParcelsInParcelLocker(parcelL);
                    for (String pn: parcelNums)
                    {
                        System.out.println("paczka nr:" + pn);
                    }
                    break;
                case "5":
                    parcelL.unregisterFromCentral();
                    return;
                default:
                    break;
            }
        }
    }


}
