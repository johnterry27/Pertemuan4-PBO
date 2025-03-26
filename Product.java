class Product {
    // Atribut produk
    private String stockCode;  // Kode unik produk
    private String description;  // Deskripsi produk
    private double unitPrice;  // Harga per unit produk
    private int quantity;  // Jumlah produk yang dibeli
    private String country;  // Negara tempat produk dibeli

    // Konstruktor untuk inisialisasi objek Product
    public Product(String stockCode, String description, double unitPrice, int quantity, String country) {
        this.stockCode = stockCode;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.country = country;
    }

    // Getter untuk mendapatkan StockCode produk
    public String getStockCode() {
        return stockCode;
    }

    // Getter untuk mendapatkan harga satuan produk
    public double getUnitPrice() {
        return unitPrice;
    }

    // Getter untuk mendapatkan jumlah produk yang terjual
    public int getQuantity() {
        return quantity;
    }

    // Getter untuk mendapatkan negara tempat pembelian
    public String getCountry() {
        return country;
    }

    // Metode untuk menghitung total pendapatan dari produk ini
    public double getTotalRevenue() {
        return unitPrice * quantity;
    }

    // Override metode toString untuk menampilkan data produk dalam bentuk teks
    @Override
    public String toString() {
        return "StockCode: " + stockCode + ", Description: " + description + ", UnitPrice: " + unitPrice + ", Quantity: " + quantity + ", Country: " + country;
    }
}
