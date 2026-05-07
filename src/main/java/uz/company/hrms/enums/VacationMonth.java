package uz.company.hrms.enums;

public enum VacationMonth {
    YANVAR,
    FEVRAL,
    MART,
    APREL,
    MAY,
    IYUN,
    IYUL,
    AVGUST,
    SENTABR,
    OKTABR,
    NOYABR,
    DEKABR;

    public static VacationMonth from(java.time.Month month){
        return switch (month){
            case JANUARY -> YANVAR;
            case FEBRUARY -> FEVRAL;
            case MARCH -> MART;
            case APRIL -> APREL;
            case MAY -> MAY;
            case JUNE -> IYUN;
            case JULY -> IYUL;
            case AUGUST -> AVGUST;
            case SEPTEMBER -> SENTABR;
            case OCTOBER -> OKTABR;
            case NOVEMBER -> NOYABR;
            case DECEMBER -> DEKABR;
        };
    }
}
