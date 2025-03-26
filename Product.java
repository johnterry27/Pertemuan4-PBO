public class Product {
    private String stockCode;
    private String description;
    private double unitPrice;
    private int quantity;
    private String country;

    public Product(String stockCode, String description, double unitPrice, int quantity, String country) {
        this.stockCode = stockCode;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.country = country;
    }

    public String getStockCode() {
        return stockCode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCountry() {
        return country;
    }

    public double getTotalRevenue() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "StockCode='" + stockCode + '\'' +
                ", Description='" + description + '\'' +
                ", UnitPrice=" + unitPrice +
                ", Quantity=" + quantity +
                ", Country='" + country + '\'' +
                '}';
    }
}
