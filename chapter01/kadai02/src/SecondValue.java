public class SecondValue {

    public int theSecond(int a, int b, int c, int d) {
        int max = max(max(a, b), max(c, d));
        int min = min(min(a, b), min(c, d));
        if (a == max) {a = min;}
        if (b == max) {b = min;}
        if (c == max) {c = min;}
        if (d == max) {d = min;}
        return max(max(a, b), max(c, d));
    }

    public int min(int v1, int v2) {
        if (v1 <= v2) {
            return v1;
        } else {
            return v2;
        }
    }

    public int max(int v1, int v2) {
        if (v1 <= v2) {
            return v2;
        } else {
            return v1;
        }
    }

    public void printResult(int a, int b, int c, int d) {
        System.out.println(String.format("Second Value(%d,%d,%d,%d) = %d", 
                                        a, b, c, d, theSecond(a, b, c, d)));
    }

    public static void main(String[] args) throws Exception {
        SecondValue sv = new SecondValue();

        // 4”‚ª‚·‚×‚ÄˆÙ‚È‚éê‡
        sv.printResult(1,2,3,4);
        sv.printResult(3,4,1,2);
        sv.printResult(4,3,2,1);
        sv.printResult(3,4,1,2);

        // 2”‚ª“™‚µ‚¢
        sv.printResult(1,1,3,4);
        sv.printResult(2,3,3,4);
        sv.printResult(2,3,4,4);
        sv.printResult(3,3,4,4);
        sv.printResult(4,3,1,1);
        sv.printResult(4,3,3,2);
        sv.printResult(4,4,3,2);
        sv.printResult(4,4,3,3);
        
        // 3”‚ª“™‚µ‚¢
        sv.printResult(3,4,4,4);
        sv.printResult(3,3,3,4);
        sv.printResult(4,3,4,4);
        sv.printResult(3,3,4,3);

        // 4”‚ª‚·‚×‚Ä“™‚µ‚¢
        sv.printResult(3,3,3,3);

        // •‰”
        sv.printResult(-3,-3,-2,-2);
    }
}
