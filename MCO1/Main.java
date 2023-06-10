import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// added the ff
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        // TODO: Use this method to run your experiments.

        Main md = new Main();

        FileReader fr = new FileReader();

        // Declare sorting algorithm
        SortingAlgorithms SA = new SortingAlgorithms();


        Scanner sc = new Scanner(System.in);

        

        String filePath;
        String filePathSubTxt;

        System.out.print("Enter the filepath of your file, should end with '.txt': ");
        filePath = sc.nextLine();

        /**
         * This section just checks if you have .txt or not
         */
        // Passes the maybe the potential .txt substring if it contains oone
        if(filePath.indexOf('.', 0) != -1)
            filePathSubTxt =filePath.substring(filePath.indexOf('.', 0), filePath.length());
        else
            filePathSubTxt = "N/A";

        // Check .txt substing if found, else appen a .txt
        if ( !filePathSubTxt.equals(".txt") ){
            filePath += ".txt";
        }
        
        Record[] records = fr.readFile(filePath);


        // Change this code to test different sorting algos
        // Sorts the records using insertion sort
        if (records != null){
            SA.insertionSort(records, records.length);
        }
        

        md.writeToFile(filePath, records);
        

        // Code below commented for checking records via System print DO NOT USE FOR MORE THAN 100 DATA
        // for(Record r: test_records2){

        //     System.out.println(r.getIdNumber());
        // }
        
        // Writes back the sorted Records array


        // Cleanup section
        sc.close();
        records = null;


    }


    public void writeToFile(String filePath, Record[] records){
        int dataSetSize;
        
        File givenFile = new File(filePath);

        dataSetSize = 0;


        try{
            Scanner sc = new Scanner(givenFile);

            dataSetSize = sc.nextInt();

            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            e.printStackTrace();

        }
        

        

        if (filePath != null){
             

            // Write to file
            try {
                FileWriter myWriter = new FileWriter(filePath);
                

                // Start by stating the size of the file
                myWriter.write(dataSetSize + "\n");



                // write line by line each object in the node
                for(Record record : records){
                    if (record != null){
                        myWriter.write(record.getIdNumber() + record.getName() + "\n");
                    }
                    
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }


        
       
    }
}
