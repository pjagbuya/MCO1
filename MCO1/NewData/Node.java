public class Node{
    private String sName;
    private long nNum;
    private static int nCount = 0;
    
    public Node(String s, long n){
        sName = s;
        nNum = n;
        nCount++;

    }

    public void setName(String s){

        sName = s;
    }


    public static int getnCount() {
        return nCount;
    }

    public void setNum(long n){
        nNum = n;
    }

    public static void resetCount(){
        nCount = 0;
    }

    @Override
    public String toString(){

        return nNum + " " + sName;

    }

}