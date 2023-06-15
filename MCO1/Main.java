import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



// added the ff
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    private static final String SORT_DIREC = "SortResult/";
    
    public static void main(String[] args) {
        // TODO: Use this method to run your experiments.


        long endTime;
        long startTime; 

        Main md = new Main();

        FileReader fr = new FileReader();

        // Declare sorting algorithm
        SortingAlgorithms SA = new SortingAlgorithms();


        Scanner sc = new Scanner(System.in);

        

        String filePath;
        String filePathSubTxt;
        int inputSortAlgo;

        System.out.print("Enter the relative filepath of your file, should end with '.txt': ");
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


        System.out.println();


        inputSortAlgo = 0;
        while(inputSortAlgo <= 0 || inputSortAlgo > 4){

            System.out.println("Choose from the following: ");
            System.out.println("[1] - Insertion Sort");
            System.out.println("[2] - Selection Sort");
            System.out.println("[3] - Merge Sort");
            System.out.println("[4] - Radix Sort");

            inputSortAlgo = sc.nextInt();

            if(inputSortAlgo > 4 || inputSortAlgo <= 0)
                System.out.println("Error input!");

        }
 

        // Change this code to test different sorting algos
        // Sorts the records using insertion sort
        if (records != null){

            

            startTime = 0;
            endTime = 0;
            switch(inputSortAlgo){
                case 1:
                    // Record start time
                    startTime = System.currentTimeMillis();
                    SA.insertionSort(records, records.length);
                    // Record end time
                    endTime = System.currentTimeMillis();

                    break;

                case 2:
                    // Record start time
                    startTime = System.currentTimeMillis();
                    SA.selectionSort(records, records.length);
                    // Record end time
                    endTime = System.currentTimeMillis();

                    break;
                case 3:
                    // Record start time
                    startTime = System.currentTimeMillis();
                    // missing one more parameter
                    //SA.mergeSort(records, records.length);
                    // Record end time
                    endTime = System.currentTimeMillis();

                    break;
                case 4:                
                    // Record start time
                    startTime = System.currentTimeMillis();
                    SA.radixSort(records, records.length);
                    // Record end time
                    endTime = System.currentTimeMillis();

                    break;
            }

            // Sorting algo to choose
            SA.insertionSort(records, records.length);

            


            Paint.turnOnYellow();
            System.out.println("Execution time of sorting least to greatest(top - bot): " + (endTime-startTime) +" ms");
            Paint.turnOffColor();

        } else{
            System.out.println("ERROR! Records are empty, please rerun and provide again");
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


    private void writeToFile(String filePath, Record[] records){
        int dataSetSize;
        
        File givenFile = new File(filePath);
    

        String newFilePath;

        dataSetSize = 0;


        try{
            Scanner sc = new Scanner(givenFile);

            dataSetSize = sc.nextInt();

            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            e.printStackTrace();

        }
        
        System.out.println(filePath);
        if(filePath.indexOf('\\') != -1){
            System.out.println(filePath.lastIndexOf('\\'));
            
            filePath = filePath.substring(filePath.lastIndexOf('\\')+1);
            
            
        } else if(filePath.indexOf('/') != -1){
            System.out.println(filePath.lastIndexOf('/'));
            
            filePath = filePath.substring(filePath.lastIndexOf('/')+1);
        }

        if (filePath != null){
             

            // Write to file
            try {
                // Put the results in a folder "SortResult"
                newFilePath = SORT_DIREC + filePath;

                // File writer will now store it in "SortResult"
                FileWriter myWriter = new FileWriter(newFilePath);
                

                // Start by stating the size of the file
                myWriter.write(dataSetSize + "\n");



                // write line by line each object in the node
                for(Record record : records){
                    if (record != null){
                        myWriter.write(record.getIdNumber() + record.getName() + "\n");
                    }
                    
                }
                myWriter.close();

                Paint.turnOnGreen();
                System.out.println("Successfully wrote to the file.");
                Paint.turnOffColor();

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }


        
       
    }
}
