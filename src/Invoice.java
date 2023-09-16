public class Invoice {
    int PartNumber;
    String PartDescription;
    int Quantity;
    double price;

    public Invoice(int partNumber, String partDescription, int quantity, double price) {
        PartNumber = partNumber;
        PartDescription = partDescription;
        Quantity = quantity;
        this.price = price;
    }

    public int getPartNumber() {
        return PartNumber;
    }

    public void setPartNumber(int partNumber) {
        PartNumber = partNumber;
    }

    public String getPartDescription() {
        return PartDescription;
    }

    public void setPartDescription(String partDescription) {
        PartDescription = partDescription;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "PartNumber=" + PartNumber +
                ", PartDescription='" + PartDescription + '\'' +
                ", Quantity=" + Quantity +
                ", price=" + price +
                '}';
    }
}
