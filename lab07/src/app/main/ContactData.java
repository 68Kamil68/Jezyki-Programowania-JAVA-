package app.main;


import java.io.Serializable;
import java.rmi.Remote;

public class ContactData implements Serializable {
    private String name;
    private String telephone;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public ContactData(String name, String telephone) {
        setName(name);
        setTelephone(telephone);
    }
}