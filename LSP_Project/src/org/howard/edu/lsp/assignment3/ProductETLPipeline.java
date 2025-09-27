package org.howard.edu.lsp.assignment3;


import java.util.List;

public class ProductETLPipeline {
    public static void main(String[] args) {
        String inputFile = "data/products.csv";
        String outputFile = "data/transformed_products.csv";
        
        System.out.println("Starting ETL Pipeline...");
        
        try {
            // ==================== EXTRACT PHASE ====================
            CSVProductReader reader = new CSVProductReader(inputFile);
            List<ProductRecord> products = reader.readProducts();
            System.out.println("Extracted " + products.size() + " products");
            
            // ==================== TRANSFORM PHASE ====================
            ProductTransformer transformer = new ProductTransformer();
            List<ProductRecord> transformedProducts = transformer.transform(products);
            System.out.println("Transformed " + transformedProducts.size() + " products");
            
            // ==================== LOAD PHASE ====================
            ProductDataWriter writer = new ProductDataWriter(outputFile);
            writer.writeProducts(transformedProducts);
            System.out.println("Loaded results to " + outputFile);
            
            // ==================== SUMMARY ====================
            printSummary(products.size(), transformedProducts.size(), outputFile);
            
        } catch (Exception e) {
            System.err.println("ETL Pipeline failed: " + e.getMessage());
            System.err.println("Please ensure the input file exists and is properly formatted.");
        }
    }
    
    private static void printSummary(int inputCount, int outputCount, String outputFile) {
        System.out.println("=== ETL Pipeline Summary ===");
        System.out.println("Rows read from input: " + inputCount);
        System.out.println("Rows transformed: " + outputCount);
        System.out.println("Rows skipped: " + (inputCount - outputCount));
        System.out.println("Output file created: " + outputFile);
        System.out.println("Pipeline completed successfully!");
    }
}