class BusinessDayMulti {
    static final int BASIS_YEAR = 2020; // 起算年
    static final int LIMIT_YEAR = 2025; // 限界年
    static final int YOUBI_OF_BASIS = 3; // 起算年の初日の曜日(水曜日)

    public static void main(String[] args) {
        int year = Integer.parseInt(args[0]);   // 年の入力
        int month = Integer.parseInt(args[1]);  // 月の入力
        int date = Integer.parseInt(args[2]);   // 日の入力
        int span = Integer.parseInt(args[3]);   // 間隔の入力

        if (checkDate(year, month, date)) {
            // 年月日で与えた日付の起算日からの日数を得る
            int days = elapsedDays(year, month, date);
            // 起算日からの日数を与えその日付をYYYYMMDD 形式で得る
            int eymd = getYYYYMMDD(days + span);
            // days からspan 日後の営業日付をYYYYMMDD 形式で得る
            int bymd = getBusinessDay(days, span);

            // 聞かれているのは経過日数ではなく「数え日」なので+1
            System.out.println(year + "年" + month + "月" + date + "日は起算日から" + (days + 1) + "日目です。");

            String zengo = span < 0 ? "前" : "後";
            System.out.println(Math.abs(span) + "日" + zengo + "は"
                + (eymd / 10000) + "年" + (eymd % 10000) / 100 + "月"
                + (eymd % 100) + "日です。");

            System.out.println(Math.abs(span) + "営業日" + zengo + "は"
                + (bymd / 10000) + "年" + (bymd % 10000) / 100 + "月"
                + (bymd % 100) + "日です。");
        } else {
            System.out.println(year + "年" + month + "月"
                + date + "日は指定条件を満たしていません。");
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

    // static String getYoubiName(int days) {
    //     String[] NAME_OF_WEEK = {"日","月","火","水","木","金","土"};
    //     return NAME_OF_WEEK[(days + YOUBI_OF_BASIS) % 7];
    // }

    static int getYYYYMMDD(int days) {
        days++;
        int month = 1;
        int year  = BASIS_YEAR;
        while (daysOfMonth(year, month) <= days) {
            //System.out.println(String.format("(%d,%d,%d),%d", year, month, days, daysOfMonth(year, month)));
            days -= daysOfMonth(year, month);
            if (month >= 12) {
                year++;
                month = 1;
            } else {
                month++;
            }
        }
        return year * 10000 + month * 100 + (days + 1);
    }

    // 1 月1 日からの経過日数days から間隔日数span 日後の営業日
    // TODO spanが負の場合, spanが0の場合
    static int getBusinessDay(int days, int span) {
        if (span == 0) {
            return getYYYYMMDD(days);
        }
        
        int abs;    // spanの絶対値
        int sigma;  // spanの正負
        if (span > 0) {abs = span; sigma = 1;}
        else {abs = -span; sigma = -1;}

        for (int i = 0; i < abs; ) {
            days += sigma;
            if (isWeekend(days)) continue;
            int eymd  = getYYYYMMDD(days);
            int year  = (eymd / 10000);
            int month = (eymd % 10000) / 100;
            int date  = (eymd % 100);
            if (isTotalHoliday(year, month, date)) continue;
            i++;
        }
        return getYYYYMMDD(days);
    }

    // 週末判定
    static boolean isWeekend(int days) {
        if ((days + YOUBI_OF_BASIS) % 7 == 0) return true;
        if ((days + YOUBI_OF_BASIS) % 7 == 6) return true;
        return false;
    }

    // // 休日判定
    // static boolean isHoliday(int mmdd) {
    //     int[] HOLIDAYS = {101, 102, 103, 113, 211, 223, 320, 429, 503, 504, 505, 506, 720, 811, 921, 922, 1012, 1103, 1123, 1229, 1230, 1231,};
    //     for (int holiday: HOLIDAYS) {
    //         if (holiday == mmdd) return true;
    //     }
    //     return false;
    // }

    // 休日判定
    static boolean isTotalHoliday(int year, int month, int date) {
        if (isNationalHoliday(year, month, date)) return true;
        if (isFurikaeHoliday(year, month, date)) return true;
        if (isOtherHoliday(month, date)) return true;
        return false;
    }

    // OK
    static boolean isNationalHoliday(int year, int month, int date) {
        int[] FIX_HOLIDAYS = {101, 211, 223, 429, 503, 504, 505, 811, 1103, 1123,};
        int mmdd = month * 100 + date;
        for (int holiday: FIX_HOLIDAYS) {
            if (holiday == mmdd) return true;
        }
        if (isHappyMonday(year, month, date)) return true;
        if (isEquinox(year, month, date)) return true;
        return false;
    }

    // OK
    static boolean isFurikaeHoliday(int year, int month, int date) {
        int[] MULTI_SHIFT = {2020, 2025, 2026};
        for (int shift_year: MULTI_SHIFT) {
            if (year == shift_year && month == 5 && date == 6) return true;
        }
        int youbi = (elapsedDays(year, month, date) + YOUBI_OF_BASIS) % 7;
        if (youbi != 1) return false; // 月曜でなけれは非該当
        return isNationalHoliday(year, month, date - 1); // 前日が祝日なら休日
    }

    // OK
    static boolean isOtherHoliday(int month, int date) {
        int[] OTHER_HOLIDAYS = {101,102,103,1229,1230,1231,};
        int mmdd = month * 100 + date;
        for (int holiday: OTHER_HOLIDAYS) {
            if (holiday == mmdd) return true;
        }
        return false;
    }

    // OK
    static boolean isHappyMonday(int year, int month, int date) {
        int[] HAPPY_MONDAYS = {102, 703, 903, 1002,};
        int youbiOfFirstDay = (elapsedDays(year, month, 1) + YOUBI_OF_BASIS) % 7;
        for (int mmnn: HAPPY_MONDAYS) {
            if (mmnn / 100 != month) continue;
            int n = mmnn % 100;
            int happyMonday = (8 - youbiOfFirstDay) % 7 + n * 7 - 6;
            if (date == happyMonday) return true;
        }
        return false;
    }

    // OK
    static boolean isEquinox(int year, int month, int date) {
        int[][] EQUINOX = {{320, 922},{320, 923},{321, 923},{321, 923}};
        int mmdd = month * 100 + date;
        for (int equinox: EQUINOX[year % 4]) {
            if (equinox == mmdd) return true;
        }
        return false;
    }
}
