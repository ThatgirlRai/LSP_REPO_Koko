package org.howard.edu.lsp.assignment3;



public class ProductRecord {
    private String id;
    private String name;
    private double price;
    private String category;
    private String priceRange; 
    
    public ProductRecord() {}
    
    public ProductRecord(String id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    @Override
    public String toString() {
        return String.format("ProductRecord{id='%s', name='%s', price=%.2f, category='%s'}", 
                           id, name, price, category);
    }
    public String getPriceRange() { 
        return priceRange; 
    }
    
    public void setPriceRange(String priceRange) { 
        this.priceRange = priceRange; 
    }
}