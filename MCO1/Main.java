public class Main {
    public static void main(String[] args) {
        // TODO: Use this method to run your experiments.

        FileReader fr = new FileReader();

        Record[] records = fr.readFile("C:\\Users\\Paul Josef\\Downloads\\MCO1\\data\\almostsorted.txt");


        for(Record r: records){

            System.out.println(r.getIdNumber());
        }
    }
}
