package app.main;

import java.io.Serializable;

public class Parcel implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ContactData addresee;
    private ContactData sender;
    private String parcelNumber;
    private String payload;

    public void setAddress(ContactData addresee) {
        this.addresee = addresee;
    }
    public ContactData getSender() {
        return sender;
    }
    public void setSender(ContactData sender) {
        this.sender = sender;
    }
    public String getParcelNumber() {
        return parcelNumber;
    }
    public void setParcelNumber(String parcelNumber) {
        this.parcelNumber = parcelNumber;
    }
    public String getPayload() {
        return payload;
    }
    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Parcel(ContactData sender, ContactData addressee, String payload,
           String fromParcelLockerNumber, String toParcelLockerNumber){
        setSender(sender);
        setAddress(addressee);
        setPayload(payload);
    }
}
