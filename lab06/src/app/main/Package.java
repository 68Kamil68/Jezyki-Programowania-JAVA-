package app.main;

public class Package {
    private String senderName;
    private String receiverName;
    private String contents;
    private int packageNumber;
    private int senderPaczkomat;
    private int receiverPaczkomat;

    public Package(String senderName, String receiverName, String contents, int packageNumber, int senderPaczkomat, int receiverPaczkomat)
    {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.contents = contents;
        this.packageNumber = packageNumber;
        this.senderPaczkomat = senderPaczkomat;
        this.receiverPaczkomat = receiverPaczkomat;
    }

    public int getReceiverPaczkomat() {
        return receiverPaczkomat;
    }

    public int getPackageNumber() {
        return packageNumber;
    }
}

