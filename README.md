FOR HW 2 - ETL PIPELINE

Assumptions

- The input CSV file (products.csv) is located in the data/ directory relative to the project root

- The first row of the input file is a header row that should not be transformed/ edited 

- All product names can be safely converted to uppercase

- Prices are represented as decimal numbers

Design Notes
As someone famliar  C++ I first planned basic stricture of the program using C++ concepts  and then converted  to java using W3Schools(cited below )
- THe program follows a linear ETL pipeline structure 
  - Extract: Reads CSV data from data/products.csv
  - Transform: Applies required transformations (uppercase, discounts, categorization, price ranges)
  - Load: Writes transformed data to data/transformed_products.csv

How to Run
1.Ensure Java is installed 
2.place/ confirm that products.csv is on the data/ directory at the project root 
3. The output file transformes_products.csv will be created in the data/ directory 




AI Usage Documentation
I used AI assistance primarily to bridge the gap between my C++ knowledge and Java implementation requirements. The AI helped with Java-specific syntax, library imports, and exception handling patterns that differ from C++ approaches.

Prompts
"How can i implement  code for it to handle errors gracefully "
REspone : The result was to use Try- catch blocks  something i had no prior knowldgeof, I im[lemented this in my code.
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
Another promt i had gave it was when my code was when I was unable to confirm that my code was creating the transformed_products.csv . I asked Deepseek to:  
"help me confirm that the file was being created" so i could troubleshoot why is was not showing in the directory.
It gave me  a Debug as the result :
File outputFileCheck = new File(outputPath);
    if (outputFileCheck.exists()) {
        System.out.println("DEBUG: Output file exists at: " + outputFileCheck.getAbsolutePath());
        System.out.println("DEBUG: File size: " + outputFileCheck.length() + " bytes");
    } else {
        System.out.println("DEBUG: Output file was NOT created!");
    	}
	}



EXTERNAL INTERNET SOURCES 
external source usage : W3 Schools 
Link: https://www.w3schools.com/java/java_syntax.asp
Usage:referenced for basic java syntax, and proper use of import statements . 
