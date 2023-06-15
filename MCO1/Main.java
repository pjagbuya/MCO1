import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



// added the ff
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    private static final String SORT_DIREC = "SortResult/";
    private static final String INSERT_DIREC = "Insertion/";
    private static final String SELECT_DIREC = "Selection/";
    private static final String MERGE_DIREC = "Merge/";
    private static final String RADIX_DIREC = "Radix/";
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
        String userInputC;
        int inputSortAlgo;

        userInputC = "y";
        while(userInputC.equalsIgnoreCase("y"))
        {
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
                sc.nextLine(); // remove newline

                if(inputSortAlgo > 4 || inputSortAlgo <= 0)
                    System.out.println("Error input!");

            }
    

            // Change this code to test different sorting algos
            // Sorts the records using insertion sort
            if (records != null){

                

                startTime = 0;
                endTime = 0;

                //Sort algo chosen
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

           

                


                Paint.turnOnYellow();
                System.out.println("Execution time of sorting least to greatest(top to bot): " + (endTime-startTime) +" ms");
                Paint.turnOffColor();

            } else{
                System.out.println("ERROR! Records are empty, please rerun and provide again");
            }
            

            md.writeToFile(filePath, records, inputSortAlgo);

            System.out.print("Run again? (Y/N): ");
            userInputC = sc.nextLine();

            // Cleanup section
            if(!userInputC.equalsIgnoreCase("y"))
                records = null;
        }
        System.out.print("Run again? (Y/N): ");





        // Code below commented for checking records via System print DO NOT USE FOR MORE THAN 100 DATA
        // for(Record r: test_records2){

        //     System.out.println(r.getIdNumber());
        // }
        
        // Writes back the sorted Records array


        // Cleanup section
        sc.close();
        


    }


    private void writeToFile(String filePath, Record[] records, int algoType){
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
        
        
        if(filePath.indexOf('\\') != -1){
            
            
            filePath = filePath.substring(filePath.lastIndexOf('\\')+1);
            
            
        } else if(filePath.indexOf('/') != -1){
            
            
            filePath = filePath.substring(filePath.lastIndexOf('/')+1);
        }
     

        if (filePath != null){
            // Put the results in a folder "SortResult" and the respective algo result
            switch(algoType){
                case 1:
                    newFilePath = SORT_DIREC + INSERT_DIREC + filePath;
                    break;
                case 2:
                    newFilePath = SORT_DIREC + SELECT_DIREC + filePath;
                    break;
                case 3:
                    newFilePath = SORT_DIREC + MERGE_DIREC + filePath;
                    break;
                case 4:
                    newFilePath = SORT_DIREC + RADIX_DIREC + filePath;
                    break;
                default:
                    newFilePath = SORT_DIREC + filePath;
            }
            // adds result label
            newFilePath = newFilePath.replace(".txt", "-result.txt");
    

            // Write to file
            try {

                


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
