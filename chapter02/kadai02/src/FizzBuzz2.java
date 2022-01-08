public class FizzBuzz2 {

    public int parseInput(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("出力範囲を指定してください");
            throw new Exception();
        }
        int ret;
        try {
            ret = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("出力範囲は整数で指定してください");
            throw e;
        }
        if (ret <= 4) {
            System.out.println("出力範囲は 5 以上の整数で指定してください");
            throw new Exception();
        }
        return ret;
    }

    public boolean isMultiple(int dividend, int divisor) {
        return (dividend - (dividend / divisor) * divisor) == 0;
    }

    public void fizzBuzz(int high) {
        for (int i = 1; i <= high; i++) {
            if (isMultiple(i, 15)) {
                System.out.print("FizzBuzz");
            } else if (isMultiple(i, 3)) {
                System.out.print("Fizz");
            } else if (isMultiple(i, 5)) {
                System.out.print("Buzz");
            } else {
                System.out.print(i);
            }
            System.out.print(",");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        int high = 0;
        FizzBuzz2 fb = new FizzBuzz2();
        try {
            high = fb.parseInput(args);
        } catch (Exception e) {
            System.exit(1);
        }
        fb.fizzBuzz(high);
    }
}
