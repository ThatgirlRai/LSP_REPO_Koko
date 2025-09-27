package org.howard.edu.lsp.assignment3;


import java.util.ArrayList;
import java.util.List;

public class ProductTransformer {
    
    public List<ProductRecord> transform(List<ProductRecord> products) {
        List<ProductRecord> transformedProducts = new ArrayList<>();
        
        for (ProductRecord product : products) {
            if (isValidProduct(product)) {
                ProductRecord transformed = transformProduct(product);
                
                // ↓↓↓ MAKE SURE THIS PART EXISTS AND WORKS ↓↓↓
                String priceRange = getPriceRange(transformed.getPrice());
                transformed.setPriceRange(priceRange); // ← THIS IS CRITICAL
                
                // Debug line to verify it's working
                System.out.println("DEBUG: " + transformed.getId() + " - " + 
                                 transformed.getName() + " - Price: " + transformed.getPrice() + 
                                 " - PriceRange: " + priceRange);
                
                transformedProducts.add(transformed);
            }
        }
        
        return transformedProducts;
    }
    
    private boolean isValidProduct(ProductRecord product) {
        if (product.getPrice() <= 0) {
            System.out.println("Skipping product " + product.getId() + " - invalid price: " + product.getPrice());
            return false;
        }
        
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            System.out.println("Skipping product " + product.getId() + " - missing name");
            return false;
        }
        
        if (product.getId() == null || product.getId().trim().isEmpty()) {
            System.out.println("Skipping product - missing ID");
            return false;
        }
        
        return true;
    }
    
    private ProductRecord transformProduct(ProductRecord original) {
        ProductRecord transformed = new ProductRecord();
        
        transformed.setId(original.getId());
        transformed.setName(original.getName().toUpperCase());
        transformed.setPrice(original.getPrice());
        transformed.setCategory(original.getCategory());
        // Note: priceRange is set separately after this method
        
        return transformed;
    }
    
    private String getPriceRange(double price) {
        if (price <= 10.00) return "Low";
        else if (price <= 100.00) return "Medium";
        else if (price <= 500.00) return "High";
        else return "Premium";
    }
}