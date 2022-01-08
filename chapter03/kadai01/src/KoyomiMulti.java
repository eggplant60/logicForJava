class KoyomiMulti {
    static final int BASIS_YEAR = 1900; // �N�Z�N
    static final int LIMIT_YEAR = 2100; // ���E�N
    static final int YOUBI_OF_BASIS = 1; // �N�Z�N�̏����̗j��(���j)

    public static void main(String[] args) {
        int year = Integer.parseInt(args[0]); // �N�̓���
        int month = Integer.parseInt(args[1]); // ���̓���
        int date = Integer.parseInt(args[2]); // ���̓���
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
}