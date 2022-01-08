public class Jishu01 {
    public static void main(String[] args) {
        int a = 40;
        int b = 9;
        int c = 10;
        
        int max = a;
        if (max < b) {max = b;}
        if (max < c) {max = c;}
        System.out.println(max);
    }
}
