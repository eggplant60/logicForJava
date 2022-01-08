public class CenterValue {

    public int center(int a, int b, int c) {
        if (a <= b) {
            if (b <= c) {
                return b;
            } else {
                if (c <= a) {
                    return a;
                } else {
                    return c;
                }
            }
        } else {
            if (a <= c) {
                return a;
            } else {
                if (b <= c) {
                    return c;
                } else {
                    return b;
                }
            }
        }
    }

    public void printResult(int a, int b, int c) {
        System.out.println(String.format("center value(%d,%d,%d) = %d", a, b, c, center(a, b, c)));
    }

    public static void main(String argv[]) {
        CenterValue cv = new CenterValue();

        // 3”‚ª‚·‚×‚ÄˆÙ‚È‚é
        cv.printResult(3, 2, 1);
        cv.printResult(3, 1, 2);
        cv.printResult(2, 3, 1);
        cv.printResult(2, 1, 3);
        cv.printResult(1, 3, 2);
        cv.printResult(1, 2, 3);

        // ‘å2”‚ª“™‚µ‚¢
        cv.printResult(2, 2, 1);
        cv.printResult(2, 1, 2);
        cv.printResult(1, 2, 2);

        // ¬2”‚ª“™‚µ‚¢
        cv.printResult(2, 2, 3);
        cv.printResult(2, 3, 2);
        cv.printResult(3, 2, 2);

        // ‘S”‚ª“™‚µ‚¢
        cv.printResult(2, 2, 2);
    }
}
