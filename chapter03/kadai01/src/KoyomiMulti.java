class KoyomiMulti {
    static final int BASIS_YEAR = 1900; // �N�Z�N
    static final int LIMIT_YEAR = 2100; // ���E�N
    static final int YOUBI_OF_BASIS = 1; // �N�Z�N�̏����̗j��(���j)

    public static void main(String[] args) {
        int year  = Integer.parseInt(args[0]); // �N�̓���
        int month = Integer.parseInt(args[1]); // ���̓���
        int date  = Integer.parseInt(args[2]); // ���̓���

        if (checkDate(year, month, date)) {
            int days = elapsedDays(year, month, date);
            System.out.println(year + "�N" + month + "��" + date + "�� " + getYoubiName(days) + "�j���͐��������t�ł��B");
            System.out.println(BASIS_YEAR + "�N 1 �� 1 ������̌o�ߓ�����" + days + "���ł��B ");
            int remain = elapsedDays(2030, 1, 1) - days;
            System.out.println("2030 �N�܂ł���"+ remain +"���ł��B");
        } else {
            System.out.println(year + "�N" + month + "��" + date + "���͎w������𖞂����Ă��܂���B");
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

    static String getYoubiName(int days) {
        String[] NAME_OF_WEEK = {"��","��","��","��","��","��","�y"};
        return NAME_OF_WEEK[(days + YOUBI_OF_BASIS) % 7];
    }

}