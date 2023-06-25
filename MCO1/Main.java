import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    private static final String SORT_DIREC = "SortResult/";
    private static final String INSERT_DIREC = "Insertion/";
    private static final String SELECT_DIREC = "Selection/";
    private static final String MERGE_DIREC = "Merge/";
    private static final String RADIX_DIREC = "Radix/";
    private static final String FOLDER_DIREC = "stockData";

    private static final int NS_AND_MS_BOUND = 1000;

    private static int countFiles(final File folder){
        int count;
        count = 0;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                countFiles(folder);
            } else {
                count++;
            }
        }

        return count;
    }

    private static void readFolders(final File folder, int inputSortAlgo, String folderName, String inputAllowWrite){
        Main md = new Main();
        long startTime;
        long endTime;
        long sumTimes;
        long aveTimes;

        Record[] records;
        SortingAlgorithms SA = new SortingAlgorithms();
        FileReader fr = new FileReader();
        long[] execTimes;

        int i;
        int dataSize;
        int arrSize;

        arrSize = countFiles(folder);
        dataSize = 0;
        execTimes = new long[arrSize];
        i=0;
        
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                readFolders(fileEntry, inputSortAlgo, folderName, inputAllowWrite);
            } else {
                records = fr.readFile(folderName + "/" + fileEntry.getName());

                try{
                    File f = new File(folderName + "/" + fileEntry.getName());
                    Scanner scFile = new Scanner(f);
                    dataSize = scFile.nextInt();
                    
                    scFile.close();

                }catch (FileNotFoundException e){
                    System.err.println("File not found.");
                    e.printStackTrace();
                }

                if (records != null){
                    startTime = 0;
                    endTime = 0;

                    //Sort algo chosen
                    switch(inputSortAlgo){
                        case 1:
                            if(dataSize > NS_AND_MS_BOUND){
                                // Record start time
                                startTime = System.currentTimeMillis();
                                SA.insertionSort(records, records.length);
                                // Record end time
                                endTime = System.currentTimeMillis();
                            }else{
                                // Record start time
                                startTime = System.nanoTime();
                                SA.insertionSort(records, records.length);
                                // Record end time
                                endTime = System.nanoTime();
                            }
                            break;

                        case 2:
                            if(dataSize > NS_AND_MS_BOUND){
                                // Record start time
                                startTime = System.currentTimeMillis();
                                SA.selectionSort(records, records.length);
                                // Record end time
                                endTime = System.currentTimeMillis();
                            }else{
                                // Record start time
                                startTime = System.nanoTime();
                                SA.selectionSort(records, records.length);
                                // Record end time
                                endTime = System.nanoTime();
                            }
                            break;
                        case 3:
                            if(dataSize > NS_AND_MS_BOUND){
                                // Record start time
                                startTime = System.currentTimeMillis();
                                
                                SA.mergeSort(records, 0, records.length-1);

                                // Record end time
                                endTime = System.currentTimeMillis();
                            }else{
                                // Record start time
                                startTime = System.nanoTime();
                                SA.mergeSort(records, 0, records.length-1);
                                // Record end time
                                endTime = System.nanoTime();
                            }
                            break;
                        case 4:                
                            if(dataSize > NS_AND_MS_BOUND){
                                // Record start time
                                startTime = System.currentTimeMillis();
                                SA.radixSort(records, records.length);
                                // Record end time
                                endTime = System.currentTimeMillis();
                            }else{
                                // Record start time
                                startTime = System.nanoTime();
                                SA.radixSort(records, records.length);
                                // Record end time
                                endTime = System.nanoTime();
                            }
                            break;
                    }

                    Paint.turnOffColor();
                    // Display each execution time
                    if(dataSize > NS_AND_MS_BOUND) {
                        System.out.println(Paint.paintTextYellow(endTime-startTime+" ms") );
                    } else {
                        System.out.println(Paint.paintTextYellow(endTime-startTime+" ns"));
                    }
                    
                    execTimes[i] = endTime-startTime;
                    i++;
                } else {
                    System.out.println("ERROR! Records are empty, please rerun and provide again");
                }

                if(inputAllowWrite.equalsIgnoreCase("y"))
                    md.writeToFile(folderName + "/" + fileEntry.getName(), records, inputSortAlgo);
            }
        }

        sumTimes = 0;
        for(i=0; i < arrSize; i++){
            sumTimes += execTimes[i];
        }
    
        if(arrSize != 0)
            aveTimes = sumTimes/arrSize;
        else
            aveTimes = 0;

        System.out.println();
        Paint.turnOnCyan();
        if(dataSize > NS_AND_MS_BOUND){
            System.out.println("For a data size of " + Paint.paintTextGreen(dataSize+"") +", the average execution time is: " + Paint.paintTextCyan(aveTimes+" ms"));
        }else{
            System.out.println("For a data size of " + Paint.paintTextGreen(dataSize+"") +", the average execution time is: " + Paint.paintTextCyan(aveTimes+" ns"));
        }
    }

    public static void main(String[] args) {
        int i;
        long endTime;
        long startTime; 

        String userInputAllowWrite;
        String filePath;
        String filePathSubTxt;
        String userInputC;

        int dataSize;
        int inputSortAlgo;
        int arrSize;
        int loopSize;
        int inputTestType;
        
        long[] execTimes;
        long sumTimes;
        long aveTimes;

        Scanner sc = new Scanner(System.in);

        Main md = new Main();
        FileReader fr = new FileReader();
        SortingAlgorithms SA = new SortingAlgorithms();

        userInputC = "y";
        while(userInputC.equalsIgnoreCase("y"))
        {   
            inputTestType = 0;
            while (inputTestType == 0) {
                System.out.println("What type of testing: ");
                System.out.println("[1] - Run a file repeatedly");
                System.out.println("[2] -  Run all files in a folder");
                System.out.print("Input: ");

                inputTestType = sc.nextInt();
                sc.nextLine();

                if (inputTestType != 1 && inputTestType != 2) {     
                    Paint.turnOnOrange();
                    System.out.println("ERROR! Please input 1 or 2");
                    Paint.turnOffColor();
                }
            }
            
            System.out.println();
            if (inputTestType == 2) {
                inputSortAlgo = 0;
                while (inputSortAlgo <= 0 || inputSortAlgo > 4) {
                    System.out.println("Choose from the following: ");
                    System.out.println("[1] - Insertion Sort");
                    System.out.println("[2] - Selection Sort");
                    System.out.println("[3] - Merge Sort");
                    System.out.println("[4] - Radix Sort");

                    inputSortAlgo = sc.nextInt();
                    sc.nextLine(); // remove newline

                    if (inputSortAlgo > 4 || inputSortAlgo <= 0)
                        System.out.println("Error input!");
                }
                System.out.println("Folder name location of the files to be read: " + FOLDER_DIREC);
                
                File fFolder = new File(FOLDER_DIREC);
                System.out.print("Do you want to also write the result sorted result into SortResult Folder? (Y/N): ");
                userInputAllowWrite = sc.nextLine();
                
                readFolders(fFolder, inputSortAlgo, FOLDER_DIREC, userInputAllowWrite);

                System.out.println();

                System.out.print("Continue? (Y/N): ");
                if (sc.nextLine().equalsIgnoreCase("N")) {
                    break;
                }
            } else if(inputTestType == 1) {
                System.out.print("Enter the relative filepath of your file, should end with '.txt': ");
                filePath = sc.nextLine();

                // This section just checks if you have .txt or not
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
                while (inputSortAlgo <= 0 || inputSortAlgo > 4) {
                    System.out.println("Choose from the following: ");
                    System.out.println("[1] - Insertion Sort");
                    System.out.println("[2] - Selection Sort");
                    System.out.println("[3] - Merge Sort");
                    System.out.println("[4] - Radix Sort");

                    inputSortAlgo = sc.nextInt();
                    sc.nextLine(); // remove newline

                    if (inputSortAlgo > 4 || inputSortAlgo <= 0)
                        System.out.println("Error input!");
                }

                File f = new File(filePath);

                System.out.print("How many times do we execute this sort algo: ");
                arrSize = sc.nextInt();
                sc.nextLine(); //get newline
                execTimes = new long[arrSize];
                i = 0;

                dataSize = 0;
                try {
                    Scanner scFile = new Scanner(f);
                    dataSize = scFile.nextInt();
                    scFile.close();
                } catch (FileNotFoundException e) {
                    System.err.println("File not found.");
                    e.printStackTrace();
                }

                System.out.println("Execution time of sorting least to greatest(top to bot): ");
                loopSize = arrSize;
                while(loopSize > 0){
                    // Reset original position of records
                    records = fr.readFile(filePath);

                    // Change this code to test different sorting algos
                    if (records != null) {
                        startTime = 0;
                        endTime = 0;

                        //Sort algo chosen
                        switch(inputSortAlgo){
                            case 1:
                                if(dataSize > NS_AND_MS_BOUND){
                                    // Record start time
                                    startTime = System.currentTimeMillis();
                                    SA.insertionSort(records, records.length);
                                    // Record end time
                                    endTime = System.currentTimeMillis();
                                }else{
                                    // Record start time
                                    startTime = System.nanoTime();
                                    SA.insertionSort(records, records.length);
                                    // Record end time
                                    endTime = System.nanoTime();
                                }
                                break;

                            case 2:
                                if(dataSize > NS_AND_MS_BOUND){
                                    // Record start time
                                    startTime = System.currentTimeMillis();
                                    SA.selectionSort(records, records.length);
                                    // Record end time
                                    endTime = System.currentTimeMillis();
                                }else{
                                    // Record start time
                                    startTime = System.nanoTime();
                                    SA.selectionSort(records, records.length);
                                    // Record end time
                                    endTime = System.nanoTime();
                                }
                                break;
                            case 3:
                                if(dataSize > NS_AND_MS_BOUND){
                                    // Record start time
                                    startTime = System.currentTimeMillis();
                                    SA.mergeSort(records,0, records.length-1);
                                    endTime = System.currentTimeMillis();
                                    // Record end time
                                }else{
                                    // Record start time
                                    startTime = System.nanoTime();
                                    SA.mergeSort(records,0, records.length-1);
                                    // Record end time
                                    endTime = System.nanoTime();
                                }

                                break;
                            case 4:                
                                if(dataSize > NS_AND_MS_BOUND){
                                    // Record start time
                                    startTime = System.currentTimeMillis();
                                    SA.radixSort(records, records.length);
                                    // Record end time
                                    endTime = System.currentTimeMillis();
                                }else{
                                    // Record start time
                                    startTime = System.nanoTime();
                                    SA.radixSort(records, records.length);
                                    // Record end time
                                    endTime = System.nanoTime();
                                }
                                break;
                        }

                        // Display each execution time
                        if(dataSize > NS_AND_MS_BOUND) {
                            System.out.println(Paint.paintTextYellow(endTime-startTime+" ms") );
                        } else {
                            System.out.println(Paint.paintTextYellow(endTime-startTime+" ns"));
                        }
                         
                        execTimes[i] = endTime-startTime;
                    } else {
                        System.out.println("ERROR! Records are empty, please rerun and provide again");
                    }
  
                    i++;
                    loopSize -= 1;
                }

                sumTimes = 0;
                for(i=0; i < arrSize; i++){
                    sumTimes += execTimes[i];
                }
                aveTimes = sumTimes/arrSize;
                System.out.println();
                Paint.turnOnCyan();

                switch(inputSortAlgo){
                    case 1:
                        System.out.println("Insertion Sort");
                        break;
                    case 2:
                        System.out.println("Selection Sort");
                        break;
                    case 3:
                        System.out.println("Merge Sort");
                        break;
                    case 4:
                        System.out.println("Radix Sort");
                        break;
                }
                Paint.turnOffColor();
                if(dataSize > NS_AND_MS_BOUND){
                    System.out.println("The average execution time is: " + aveTimes + " ms");
                }else{
                    System.out.println("The average execution time is: " + aveTimes + " ns");
                }
               
                System.out.println();
                
                md.writeToFile(filePath, records, inputSortAlgo);

                System.out.print("Run again? (Y/N): ");
                userInputC = sc.nextLine();

                // Cleanup section
                if(!userInputC.equalsIgnoreCase("y"))
                    records = null;
            }
        }
        
        // Code below commented for checking records via System print DO NOT USE FOR MORE THAN 100 DATA
        // for(Record r: test_records2){

        //     System.out.println(r.getIdNumber());
        // }

        // Cleanup section
        sc.close();
    }

    private void writeToFile(String filePath, Record[] records, int algoType){
        int dataSetSize = 0;
        File givenFile = new File(filePath);
        String newFilePath;

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
