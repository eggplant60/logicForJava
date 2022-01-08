public class FizzBuzz1 {

    public int parseInput(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("�o�͔͈͂��w�肵�Ă�������");
            throw new Exception();
        }
        int ret;
        try {
            ret = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("�o�͔͈͂͐����Ŏw�肵�Ă�������");
            throw e;
        }
        if (ret <= 4) {
            System.out.println("�o�͔͈͂� 5 �ȏ�̐����Ŏw�肵�Ă�������");
            throw new Exception();
        }
        return ret;
    }

    public void fizzBuzz(int high) {
        for (int i = 1; i <= high; i++) {
            if ((i % 3 == 0) && (i % 5 == 0)) {
                System.out.print("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.print("Fizz");
            } else if (i % 5 == 0) {
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
        FizzBuzz1 fb = new FizzBuzz1();
        try {
            high = fb.parseInput(args);
        } catch (Exception e) {
            System.exit(1);
        }
        fb.fizzBuzz(high);
    }
}
