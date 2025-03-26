import java.io.*;
import java.net.*;
import java.util.*;

public class Mavenproject1 {
    public static void main(String[] args) {
        String url = "https://drive.google.com/uc?export=download&id=14DWF2kG0hGD3hYJjAcsexOCGedVfrv3r";

        List<Product> productList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URI(url).toURL().openStream()))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) { // Skip header
                    firstLine = false;
                    continue;
                }
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                    String stockCode = values[1].trim();
                    String description = values[2].trim();
                    double unitPrice = Double.parseDouble(values[5].trim());  // Harus angka
                    int quantity = Integer.parseInt(values[3].trim());       // Harus angka
                    String country = values[7].trim();

                    Product product = new Product(stockCode, description, unitPrice, quantity, country);
                    productList.add(product);

                
            }

            System.out.println("Dataset berhasil dibaca! Jumlah produk: " + productList.size());
            productList.stream().limit(5).forEach(System.out::println);

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
