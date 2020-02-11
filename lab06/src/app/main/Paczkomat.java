package app.main;

import java.util.List;

public class Paczkomat {
    private int paczkomatNumber;
    private List<Package> packages;

    public Paczkomat(int paczkomatNumber)
    {
        this.paczkomatNumber = paczkomatNumber;
    }

    public void addPackage(Package p)
    {
        packages.add(p);
    }

    public int getPaczkomatNumber() {
        return paczkomatNumber;
    }
}
