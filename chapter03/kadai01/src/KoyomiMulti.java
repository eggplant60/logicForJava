class KoyomiMulti {
    static final int BASIS_YEAR = 1900; // 起算年
    static final int LIMIT_YEAR = 2100; // 限界年
    static final int YOUBI_OF_BASIS = 1; // 起算年の初日の曜日(月曜)

    public static void main(String[] args) {
        int year = Integer.parseInt(args[0]); // 年の入力
        int month = Integer.parseInt(args[1]); // 月の入力
        int date = Integer.parseInt(args[2]); // 日の入力
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
}