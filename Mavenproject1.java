import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;

public class Mavenproject1 {
    public static void main(String[] args) {
        // URL dataset yang akan diunduh dan dibaca
        String url = "https://drive.google.com/uc?export=download&id=14DWF2kG0hGD3hYJjAcsexOCGedVfrv3r";

        // Menggunakan Collections API untuk menyimpan dan menganalisis data
        List<Product> productList = new ArrayList<>(); // Menyimpan daftar produk
        Set<String> uniqueCountries = new HashSet<>(); // Menyimpan daftar unik negara
        Map<String, Integer> productSalesCount = new HashMap<>(); // Menghitung total produk terjual berdasarkan StockCode
        Map<String, Double> revenuePerCountry = new HashMap<>(); // Menghitung total pendapatan per negara
        Map<String, Product> productByStockCode = new HashMap<>(); // Menyimpan produk berdasarkan StockCode

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URI(url).toURL().openStream()))) {
            String line;
            boolean firstLine = true; // Untuk melewati baris header

            while ((line = br.readLine()) != null) {
                if (firstLine) { // Melewati baris pertama (header)
                    firstLine = false;
                    continue;
                }
                
                // Memisahkan data berdasarkan koma, dengan mempertimbangkan tanda kutip
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                try {
                    // Mengambil nilai dari dataset
                    String stockCode = values[1].trim(); // Kode produk
                    String description = values[2].trim(); // Deskripsi produk
                    double unitPrice = Double.parseDouble(values[5].trim()); // Harga satuan
                    int quantity = Integer.parseInt(values[3].trim()); // Jumlah produk terjual
                    String country = values[7].trim(); // Negara pembelian

                    // Membuat objek Product baru
                    Product product = new Product(stockCode, description, unitPrice, quantity, country);
                    productList.add(product); // Menambahkan ke daftar produk

                    // Menambahkan negara ke dalam Set untuk mendapatkan daftar negara unik
                    uniqueCountries.add(country);

                    // Menghitung total produk terjual berdasarkan StockCode
                    productSalesCount.put(stockCode, productSalesCount.getOrDefault(stockCode, 0) + quantity);

                    // Menghitung total pendapatan berdasarkan negara
                    revenuePerCountry.put(country, revenuePerCountry.getOrDefault(country, 0.0) + product.getTotalRevenue());

                    // Menyimpan produk berdasarkan StockCode (produk terakhir yang ditemukan akan disimpan)
                    productByStockCode.put(stockCode, product);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Skipping invalid row: " + line); // Menampilkan pesan jika ada baris yang error
                }
            }

            // Menampilkan ringkasan hasil dari analisis data
            System.out.println("============================================================");
            System.out.println("Dataset berhasil dibaca! Jumlah produk: " + productList.size());
            System.out.println("============================================================");

            // Menampilkan 5 negara unik pertama
            System.out.println("Daftar Negara Unik (5 Pertama):");
            uniqueCountries.stream().limit(5).forEach(System.out::println);
            System.out.println("============================================================");

            // Menampilkan 5 produk pertama berdasarkan total penjualan per StockCode
            System.out.println("Total Produk Terjual Berdasarkan StockCode (5 Pertama):");
            productSalesCount.entrySet().stream().limit(5).forEach(entry -> 
                System.out.println("StockCode: " + entry.getKey() + " | Total Terjual: " + entry.getValue()));
            System.out.println("============================================================");

            // Menampilkan 5 negara pertama berdasarkan total pendapatan
            System.out.println("Total Pendapatan Per Negara (5 Pertama):");
            revenuePerCountry.entrySet().stream().limit(5).forEach(entry -> 
                System.out.println("Negara: " + entry.getKey() + " | Pendapatan: " + entry.getValue()));
            System.out.println("============================================================");

            // Menampilkan 5 produk pertama berdasarkan StockCode
            System.out.println("Produk Berdasarkan StockCode (5 Pertama):");
            productByStockCode.entrySet().stream().limit(5).forEach(entry -> 
                System.out.println("StockCode: " + entry.getKey() + " | Produk: " + entry.getValue()));
            System.out.println("============================================================");

            // Menampilkan contoh 5 produk pertama dari daftar produk
            System.out.println("Contoh 5 Produk Dari Daftar:");
            productList.stream().limit(5).forEach(System.out::println);
               System.out.println("============================================================");

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace(); // Menampilkan error jika terjadi kesalahan dalam membaca file
        }
    }
}
