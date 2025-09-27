package org.howard.edu.lsp.assignment3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVProductReader {
    private String filename;
    
    
    
    public CSVProductReader(String filename) {
        this.filename = filename;
    }
    
    public List<ProductRecord> readProducts() {
        List<ProductRecord> products = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;
            
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header row
                    continue;
                }
                
                String[] values = line.split(",");
                if (values.length >= 4) {
                    String id = values[0].trim();
                    String name = values[1].trim();
                    double price = Double.parseDouble(values[2].trim());
                    String category = values[3].trim();
                    
                    products.add(new ProductRecord(id, name, price, category));
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            throw new RuntimeException("Failed to read products from CSV", e);
        }
        
        return products;
    }
    
    public String getFilename() {
        return filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }
}