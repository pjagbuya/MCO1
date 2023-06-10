public class Node{
    private String sName;
    private long nNum;
    private static int nCount;
    
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

    @Override
    public String toString(){

        return nNum + " " + sName;

    }

}