package org.howard.edu.lsp.assignment3;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class ProductDataWriter {
    private String filename;
    
    public ProductDataWriter(String filename) {
        this.filename = filename;
    }
    
    public void writeProducts(List<ProductRecord> products) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            // ↓↓↓ MAKE SURE HEADER INCLUDES PriceRange ↓↓↓
            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();
            
            for (ProductRecord product : products) {
                // ↓↓↓ MAKE SURE TO INCLUDE getPriceRange() IN THE OUTPUT ↓↓↓
                String line = String.format("%s,%s,%.2f,%s,%s",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getCategory(),
                    product.getPriceRange()); // ← THIS MUST BE HERE
                
                bw.write(line);
                bw.newLine();
                
                // Debug line to verify what's being written
                System.out.println("WRITING: " + line);
            }
            
        } catch (Exception e) {
            System.err.println("Error writing to output file: " + e.getMessage());
            throw new RuntimeException("Failed to write products to CSV", e);
        }
    }
    
    public String getFilename() {
        return filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }
}