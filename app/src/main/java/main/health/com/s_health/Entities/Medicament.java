package main.health.com.s_health.Entities;

/**
 * Created by ElyesL on 20/11/2015.
 */
public class Medicament {
    private int id;
    private String label;
    private int quantity;
    private boolean isAvailable;
    private int barCode;
    private String expiryDate;
    private String urlImg;

    public Medicament(int id, String label, int quantity, boolean isAvailable, int barCode, String expiryDate, String urlImg) {
        this.id = id;
        this.label = label;
        this.quantity = quantity;
        this.isAvailable = isAvailable;
        this.barCode = barCode;
        this.expiryDate = expiryDate;
        this.urlImg = urlImg;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public Medicament() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getBarCode() {
        return barCode;
    }

    public void setBarCode(int barCode) {
        this.barCode = barCode;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", quantity=" + quantity +
                ", isAvailable=" + isAvailable +
                ", barCode=" + barCode +
                ", expiryDate='" + expiryDate + '\'' +
                '}';
    }
}
