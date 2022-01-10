class KoyomiMulti {
    static final int BASIS_YEAR = 1900; // 起算年
    static final int LIMIT_YEAR = 2100; // 限界年
    static final int YOUBI_OF_BASIS = 1; // 起算年の初日の曜日(月曜)

    public static void main(String[] args) {
        int year  = Integer.parseInt(args[0]); // 年の入力
        int month = Integer.parseInt(args[1]); // 月の入力
        int date  = Integer.parseInt(args[2]); // 日の入力

        if (checkDate(year, month, date)) {
            int days = elapsedDays(year, month, date);
            System.out.println(year + "年" + month + "月" + date + "日 " + getYoubiName(days) + "曜日は正しい日付です。");
            System.out.println(BASIS_YEAR + "年 1 月 1 日からの経過日数は" + days + "日です。 ");
            int remain = elapsedDays(2030, 1, 1) - days;
            System.out.println("2030 年まであと"+ remain +"日です。");
        } else {
            System.out.println(year + "年" + month + "月" + date + "日は指定条件を満たしていません。");
        }
    }

    // 指定月日が存在すればtrue を返す
    static boolean checkDate(int year, int month, int date) {
        if (year < BASIS_YEAR || LIMIT_YEAR < year) return false;
        if (month < 1 || 12 < month) return false;
        if (date < 1) return false;
        if (daysOfMonth(year, month) < date) return false;
        return true;
    }

    // うるう年を考慮した月の日数
    static int daysOfMonth(int year, int month) {
        int[] DAYS_OF_MONTH = {31,28,31,30,31,30,31,31,30,31,30,31};
        int day = DAYS_OF_MONTH[month - 1];

        // 2月以外はうるう年と関係ない
        if (month != 2) {
            return day;
        }

        if (year % 400 == 0) {          // 400で割り切れる
            day++;
        } else if (year % 100 == 0) {   // 100で割り切れかつ400では割り切れない
            ;
        } else if (year % 4 == 0) {     // それ以外かつ4で割り切れる
            day++;
        }
        return day;
    }

    static int elapsedDays(int year, int month, int date) {
        int dYear = year - BASIS_YEAR; // 起算年との差
        //int days = dYear * 365 + (dYear + 3) / 4; // 差を365 倍しうるう年補正
        int days = dYear * 365 + (dYear + 3) / 4 - (dYear + 99) / 100 + (dYear + 299) / 400;

        for (int m = 1; m < month; m++) { // m は月なので1 から始める
            days += daysOfMonth(year, m); // うるう年を考慮した月の日数
        }
        return days + date - 1; // 起算日を含めないので1 を引く
    }

    static String getYoubiName(int days) {
        String[] NAME_OF_WEEK = {"日","月","火","水","木","金","土"};
        return NAME_OF_WEEK[(days + YOUBI_OF_BASIS) % 7];
    }

}