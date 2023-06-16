import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Iterator;

import java.security.SecureRandom;

public class DataSetRandomizer{
    
    // The following are the folders where the randomizer gets data from and outputs to
    private static final String NAME_DIRECT = "Names/";
    private static final String OUTPUT_DIRECT = "OutputData/";
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String userFileName;
        String userFileNameSubTxt;
        File myObj;
        int dataSetSize;
        int dataSetCount;

        // Ask user fo rthe name of the file
        System.out.print("What is the name of the file: ");
        userFileName = sc.nextLine();

        // add the directory of the output
        userFileName = OUTPUT_DIRECT + userFileName;

        System.out.println();
        

        // by default the dataSetSize is 99, to activate the while loop;
        dataSetSize = 99;

        // ask user for valid data Set size
        while(dataSetSize < 100 || dataSetSize > 150000){
            System.out.print("How much entries will be in the data(100-150000): ");
            dataSetSize = sc.nextInt();

            if (dataSetSize < 100 || dataSetSize > 150000){
                System.out.println();
                System.out.println("Error! size is restricted to 100 - 150000");
            }
        }
        
        System.out.println("Info Given: " + userFileName + ", " + dataSetSize);

        // Passes the maybe the potential .txt substring if it contains oone
        if(userFileName.indexOf('.', 0) != -1)
            userFileNameSubTxt =userFileName.substring(userFileName.indexOf('.', 0), userFileName.length());
        else
            userFileNameSubTxt = "N/A";





        


        System.out.print("How many copies of this do you want: ");
        dataSetCount = sc.nextInt();
        sc.nextLine();
        int ind = 0;

        while(dataSetCount > 0){
            ind ++;

            // Check .txt substing if found, else appen a .txt
            if (userFileNameSubTxt.equals(".txt")){

                myObj = new File(userFileName.replace(".txt", "-"+ind +".txt"));

            } 
            else{
                myObj = new File(userFileName + ".txt");
                userFileName += ".txt";
            }
            try{
                
                
                
                if (myObj.createNewFile()){
                    System.out.println("File created: " + myObj.getName());
                    

                }
                else {

                    System.out.println("File already exists");
                }

                
            } catch (IOException e){
                System.out.println("An error occured");
                e.printStackTrace();
            }            
            // Write to file
            try {
                FileWriter myWriter = new FileWriter(userFileName);
                Node[] nodeArray = generateInfoNodes(dataSetSize);
                // Start by stating the size of the file
                myWriter.write(dataSetSize + "\n");

                // write line by line each object in the node
                for(Node obj : nodeArray){
                
                    myWriter.write(obj.toString() + "\n");
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");

                nodeArray = null;
            
            }
            
            catch (IOException e) 
            {
                System.out.println("An error occurred.");
                e.printStackTrace();
            
            }

            dataSetCount--;
        }
        
        
        
        

        sc.close();
        


    }

    private static Node[] generateInfoNodes(int nSize) throws FileNotFoundException, IOException{

        String tempReadLine;
        String lineIWant1;
        String lineIWant2;


        SecureRandom random = new SecureRandom();
        HashSet<Long> hs = new HashSet<Long>();
        Node[] nodeArray = new Node[nSize];
        long userID;
        String name;
        int randNum;
        
        
        FileInputStream fs = null;
        BufferedReader br = null;

        while(hs.size() < nSize){
           
            userID = random.nextInt(9999999);
            hs.add(userID);
            
        }

        Iterator<Long> it = hs.iterator();

        while (Node.getnCount() < hs.size() && it.hasNext()){

            randNum = random.nextInt(367);
            fs = new FileInputStream((NAME_DIRECT + "FirstNames.txt"));
            br = new BufferedReader(new InputStreamReader(fs));
            for(int i = 0; i < randNum; ++i)
                br.readLine();
            lineIWant1 = br.readLine();

            fs = new FileInputStream(NAME_DIRECT + "LastNames.txt");
            br = new BufferedReader(new InputStreamReader(fs));


            randNum = random.nextInt(447);
            for(int i = 0; i < randNum; ++i)
            {
                br.readLine();
            }
            
            // Reads the lin by the current line number
            tempReadLine = br.readLine();
            // If the same word, read something else
            if(tempReadLine.equals(lineIWant1)){

                // read line again
                lineIWant2 = br.readLine();
            }
            else{

                // continue with the value
                lineIWant2 = tempReadLine;
            }

            
            // ID + Name
            name = lineIWant1 + " " + lineIWant2;
            


            nodeArray[Node.getnCount()] = new Node(name, it.next());
            

            
        }

        if(br!= null){
            br.close();
        }
        
        return nodeArray;
       







    }
}