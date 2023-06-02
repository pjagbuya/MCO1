import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.*;

import java.security.SecureRandom;

public class TextRandomizer{
    


    public static void main(String[] args){

        try{
            File myObj = new File("Names.txt");
            
            
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
            FileWriter myWriter = new FileWriter("Names.txt");
            Node[] nodeArray = generateInfoNodes();
            for(Node obj : nodeArray){
                myWriter.write(obj.toString() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

        nodeArray = null;
        myObj.close();


    }

    private static Node[] generateInfoNodes() throws FileNotFoundException, IOException{
        SecureRandom random = new SecureRandom();
        HashSet<Long> hs = new HashSet<Long>();
        Node[] nodeArray = new Node[100];
        long userID;
        String name;
        int randNum;

        

        while(hs.size() < 100){
            userID = random.nextLong(100, 999999);
            hs.add(userID);
            System.out.println(userID);
        }

        Iterator<Long> it = hs.iterator();
        System.out.print(it.hasNext());

        while (Node.getnCount() < hs.size() && it.hasNext()){
            randNum = random.nextInt(1, 93);
            FileInputStream fs= new FileInputStream("FirstNames.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            for(int i = 0; i < randNum; ++i)
                br.readLine();
            String lineIWant1 = br.readLine();

            fs = new FileInputStream("LastNames.txt");
            br = new BufferedReader(new InputStreamReader(fs));

            for(int i = 0; i < randNum; ++i)
                br.readLine();
            String lineIWant2 = br.readLine();

            name = lineIWant1 + " " + lineIWant2;
            


            nodeArray[Node.getnCount()] = new Node(name, it.next());
            System.out.println(nodeArray[Node.getnCount()-1].toString());
        }

        return nodeArray;
       







    }
}