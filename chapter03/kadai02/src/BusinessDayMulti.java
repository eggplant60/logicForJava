class BusinessDayMulti {
    static final int BASIS_YEAR = 2020; // �N�Z�N
    static final int LIMIT_YEAR = 2025; // ���E�N
    static final int YOUBI_OF_BASIS = 3; // �N�Z�N�̏����̗j��(���j��)

    public static void main(String[] args) {
        int year = Integer.parseInt(args[0]);   // �N�̓���
        int month = Integer.parseInt(args[1]);  // ���̓���
        int date = Integer.parseInt(args[2]);   // ���̓���
        int span = Integer.parseInt(args[3]);   // �Ԋu�̓���

        if (checkDate(year, month, date)) {
            // �N�����ŗ^�������t�̋N�Z������̓����𓾂�
            int days = elapsedDays(year, month, date);
            // �N�Z������̓�����^�����̓��t��YYYYMMDD �`���œ���
            int eymd = getYYYYMMDD(days + span);
            // days ����span ����̉c�Ɠ��t��YYYYMMDD �`���œ���
            int bymd = getBusinessDay(days, span);

            // ������Ă���̂͌o�ߓ����ł͂Ȃ��u�������v�Ȃ̂�+1
            System.out.println(year + "�N" + month + "��" + date + "���͋N�Z������" + (days + 1) + "���ڂł��B");

            String zengo = span < 0 ? "�O" : "��";
            System.out.println(Math.abs(span) + "��" + zengo + "��"
                + (eymd / 10000) + "�N" + (eymd % 10000) / 100 + "��"
                + (eymd % 100) + "���ł��B");

            System.out.println(Math.abs(span) + "�c�Ɠ�" + zengo + "��"
                + (bymd / 10000) + "�N" + (bymd % 10000) / 100 + "��"
                + (bymd % 100) + "���ł��B");
        } else {
            System.out.println(year + "�N" + month + "��"
                + date + "���͎w������𖞂����Ă��܂���B");
        }
    }

    // �w�茎�������݂����true ��Ԃ�
    static boolean checkDate(int year, int month, int date) {
        if (year < BASIS_YEAR || LIMIT_YEAR < year) return false;
        if (month < 1 || 12 < month) return false;
        if (date < 1) return false;
        if (daysOfMonth(year, month) < date) return false;
        return true;
    }

    // ���邤�N���l���������̓���
    static int daysOfMonth(int year, int month) {
        int[] DAYS_OF_MONTH = {31,28,31,30,31,30,31,31,30,31,30,31};
        int day = DAYS_OF_MONTH[month - 1];

        // 2���ȊO�͂��邤�N�Ɗ֌W�Ȃ�
        if (month != 2) {
            return day;
        }

        if (year % 400 == 0) {          // 400�Ŋ���؂��
            day++;
        } else if (year % 100 == 0) {   // 100�Ŋ���؂ꂩ��400�ł͊���؂�Ȃ�
            ;
        } else if (year % 4 == 0) {     // ����ȊO����4�Ŋ���؂��
            day++;
        }
        return day;
    }

    static int elapsedDays(int year, int month, int date) {
        int dYear = year - BASIS_YEAR; // �N�Z�N�Ƃ̍�
        //int days = dYear * 365 + (dYear + 3) / 4; // ����365 �{�����邤�N�␳
        int days = dYear * 365 + (dYear + 3) / 4 - (dYear + 99) / 100 + (dYear + 299) / 400;

        for (int m = 1; m < month; m++) { // m �͌��Ȃ̂�1 ����n�߂�
            days += daysOfMonth(year, m); // ���邤�N���l���������̓���
        }
        return days + date - 1; // �N�Z�����܂߂Ȃ��̂�1 ������
    }

    // static String getYoubiName(int days) {
    //     String[] NAME_OF_WEEK = {"��","��","��","��","��","��","�y"};
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

    // 1 ��1 ������̌o�ߓ���days ����Ԋu����span ����̉c�Ɠ�
    // TODO span�����̏ꍇ, span��0�̏ꍇ
    static int getBusinessDay(int days, int span) {
        if (span == 0) {
            return getYYYYMMDD(days);
        }
        
        int abs;    // span�̐�Βl
        int sigma;  // span�̐���
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

    // �T������
    static boolean isWeekend(int days) {
        if ((days + YOUBI_OF_BASIS) % 7 == 0) return true;
        if ((days + YOUBI_OF_BASIS) % 7 == 6) return true;
        return false;
    }

    // // �x������
    // static boolean isHoliday(int mmdd) {
    //     int[] HOLIDAYS = {101, 102, 103, 113, 211, 223, 320, 429, 503, 504, 505, 506, 720, 811, 921, 922, 1012, 1103, 1123, 1229, 1230, 1231,};
    //     for (int holiday: HOLIDAYS) {
    //         if (holiday == mmdd) return true;
    //     }
    //     return false;
    // }

    // �x������
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
        if (youbi != 1) return false; // ���j�łȂ���͔�Y��
        return isNationalHoliday(year, month, date - 1); // �O�����j���Ȃ�x��
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
