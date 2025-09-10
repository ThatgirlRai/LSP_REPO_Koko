
package org.howard.edu.lsp.assignment2;


import java.io.*;
import java.util.*;


public class ETLPipeline {
	/**
	 * Name: Sarai Prentice
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	String inputPath = "data/products.csv";
	String outputPath="data/transformed_products.csv";
	
	//reads CSV file 
	List<String[]> data = readCSV(inputPath);
	//if there is a file error exits
	if (data==null) return; 
	
	List<String[]> transformedData = transformData(data);
	
	writeCSV(transformedData, "data/transformed_products.csv");
	printSummary(data.size(), transformedData.size(),outputPath);
	
	/* DEBUG These lines were used during development to verify file creation
	File outputFileCheck = new File(outputPath);
    if (outputFileCheck.exists()) {
        System.out.println("DEBUG: Output file exists at: " + outputFileCheck.getAbsolutePath());
        System.out.println("DEBUG: File size: " + outputFileCheck.length() + " bytes");
    } else {
        System.out.println("DEBUG: Output file was NOT created!");
    	}*/ 
	}
	
	
	
	private static List<String[]> readCSV(String filename){
		List<String[]> data = new ArrayList<>();  
	                      
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			
			while((line = reader.readLine()) != null) {
				String[] row = line.
			split(",");
			data.add(row);  			}
			
			reader.close();
			
		}catch (IOException e) { 
			System.out.println("Error reading file: " + e.getMessage());
			return null;
		}
	return data;
	
	}
	
	private static List<String[]> transformData(List<String[]> data){
		List<String[]> result = new ArrayList<>();
		
		result.add(new String[] {"ProductID","Name","Price","Category","PriceRange"});
		
		if (data.size() <= 1) {
            return result;
        }
        
        // Process each data row (skip header row at index 0)
        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);
            if (row.length < 4) {
                continue; // Skip invalid rows
            }
            
            String productID = row[0];
            String name = row[1].toUpperCase(); // 1. Convert to uppercase
            String category = row[3];
            double price = Double.parseDouble(row[2]);
            
            // 2. Apply 10% discount for Electronics
            if ("Electronics".equals(category)) {
                price = price * 0.9;
                // Round to 2 decimals using Math.round
                price = Math.round(price * 100.0) / 100.0;
                
                // 3. Recategorize if price > 500 after discount
                if (price > 500.00) {
                    category = "Premium Electronics";
                }
            }
            
            // 4. Determine price range
            String priceRange = getPriceRange(price);
            
            // Format price to 2 decimal places
            String formattedPrice = String.format("%.2f", price);
            
            result.add(new String[]{
                productID,
                name,
                formattedPrice,
                category,
                priceRange
            });
        }
        
        return result;
    }
    
    // Helper method for price range categorization
    private static String getPriceRange(double price) {
        if (price <= 10.00) {
            return "Low";
        } else if (price <= 100.00) {
            return "Medium";
        } else if (price <= 500.00) {
            return "High";
        } else {
            return "Premium";
        }
    }
    
    // LOAD: Write to CSV using plain Java I/O
    private static boolean writeCSV(List<String[]> data, String filename) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            
            for (String[] row : data) {
                // Join array elements with commas
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    if (i > 0) {
                        line.append(",");
                    }
                    line.append(row[i]);
                }
                writer.println(line.toString());
            }
            /*
            writer.close();
            System.out.println("DEBUG: Successfully wrote to: " + new File(filename).getAbsolutePath());
            System.out.println("DEBUG: File should be in: " + new File(filename).getAbsolutePath());*/
            return true; 
           
            
            } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }
    
    // Print run summary
    private static void printSummary(int inputRows, int outputRows, String outputFile) {
        System.out.println("=== ETL Pipeline Summary ===");
        System.out.println("Rows read from input: " + (inputRows - 1)); // exclude header
        System.out.println("Rows transformed: " + (outputRows - 1)); // exclude header
        System.out.println("Rows skipped: " + ((inputRows - 1) - (outputRows - 1)));
        System.out.println("Output file created: " + outputFile);
        System.out.println("Pipeline completed successfully!");
    }
	
 // Check if output file was created
   
}
