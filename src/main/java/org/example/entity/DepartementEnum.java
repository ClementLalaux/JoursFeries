package org.example.entity;

public enum DepartementEnum {
    AIN("01", "Ain", "Métropole"),
    AISNE("02", "Aisne", "Métropole"),
    ALPES_DE_HAUTE_PROVENCE("04", "Alpes-de-Haute-Provence", "Métropole"),
    ALPES_MARITIMES("06", "Alpes-Maritimes", "Iles"),
    ARDENNES("08", "Ardennes", "Métropole"),
    AUBE("10", "Aube", "Métropole"),
    Aude("11", "Aude", "Métropole"),
    AVEYRON("12", "Aveyron", "Métropole"),
    BOUCHES_DU_RHONE("13", "Bouches-du-Rhône", "Métropole"),
    CALVADOS("14", "Calvados", "Métropole"),
    CANTAL("15", "Cantal", "Métropole"),
    CHARENTE("16", "Charente", "Métropole"),
    CHARENTE_MARITIME("17", "Charente-Maritime", "Métropole"),
    CHER("18", "Cher", "Métropole"),
    CORREZE("19", "Corrèze", "Métropole"),
    COTE_D_OR("21", "Côte-d'Or", "Métropole"),
    COTES_D_ARMOR("22", "Côtes-d'Armor", "Métropole"),
    CREUSE("23", "Creuse", "Métropole"),
    DORDOGNE("24", "Dordogne", "Métropole"),
    DOUBS("25", "Doubs", "Métropole"),
    DROME("26", "Drôme", "Métropole"),
    EURE("27", "Eure", "Métropole"),
    EURE_ET_LOIR("28", "Eure-et-Loir", "Métropole"),
    FINISTERE("29", "Finistère", "Métropole"),
    GARD("30", "Gard", "Métropole"),
    GERS("32", "Gers", "Métropole"),
    GIRONDE("33", "Gironde", "Métropole"),
    GUADELOUPE("971", "Guadeloupe", "Iles"),
    GUYANE("973", "Guyane", "Iles"),
    HAUTS_DE_SEINE("92", "Hauts-de-Seine", "Métropole"),
    HAUTE_CORSO("2B", "Haute-Corse", "Métropole"),
    HAUTE_GARONNE("31", "Haute-Garonne", "Métropole"),
    HAUTE_LOIRE("43", "Haute-Loire", "Métropole"),
    HAUTE_MARNE("52", "Haute-Marne", "Métropole"),
    HAUTE_SAVOIE("74", "Haute-Savoie", "Métropole"),
    HAUT_RHIN("68", "Haut-Rhin", "Alsace-Moselle"),
    ISERE("38", "Isère", "Métropole"),
    JURA("39", "Jura", "Métropole"),
    LANDES("40", "Landes", "Métropole"),
    LOIRE("42", "Loire", "Métropole"),
    LOIRET("45", "Loiret", "Métropole"),
    LOT("46", "Lot", "Métropole"),
    LOT_ET_GARONNE("47", "Lot-et-Garonne", "Métropole"),
    MAINE_ET_LOIRE("49", "Maine-et-Loire", "Métropole"),
    MANche("50", "Manche", "Métropole"),
    MARNE("51", "Marne", "Métropole"),
    MARTINIQUE("972", "Martinique", "Iles"),
    MAYOTTE("976", "Mayotte", "Iles"),
    MEURTHE_ET_MOSELLE("54", "Meurthe-et-Moselle", "Alsace-Moselle"),
    MEUSE("55", "Meuse", "Métropole"),
    MORBIHAN("56", "Morbihan", "Métropole"),
    MOSELLE("57", "Moselle", "Alsace-Moselle"),
    NORD("59", "Nord", "Métropole"),
    OISE("60", "Oise", "Métropole"),
    ORNE("61", "Orne", "Métropole"),
    PARIS("75", "Paris", "Métropole"),
    PAS_DE_CALAIS("62", "Pas-de-Calais", "Métropole"),
    PUY_DE_DOME("63", "Puy-de-Dôme", "Métropole"),
    PYRENEES_ATLANTIQUES("64", "Pyrénées-Atlantiques", "Métropole"),
    PYRENEES_ORIENTALES("66", "Pyrénées-Orientales", "Métropole"),
    REUNION("974", "La Réunion", "Iles"),
    RHONE("69", "Rhône", "Métropole"),
    SAONE_ET_LOIRE("71", "Saône-et-Loire", "Métropole"),
    SARTHE("72", "Sarthe", "Métropole"),
    SAVOIE("73", "Savoie", "Métropole"),
    SEMUR_EN_AUXOIS("21", "Côte-d'Or", "Métropole"),
    SEINE_ET_MARNE("77", "Seine-et-Marne", "Métropole"),
    SEINE_MARITIME("76", "Seine-Maritime", "Métropole"),
    SOMME("80", "Somme", "Métropole"),
    TARN("81", "Tarn", "Métropole"),
    TARN_ET_GARONNE("82", "Tarn-et-Garonne", "Métropole"),
    VAR("83", "Var", "Métropole"),
    VAUCLUSE("84", "Vaucluse", "Métropole"),
    VENDEE("85", "Vendée", "Métropole"),
    VIERGE("89", "Yonne", "Métropole"),
    VOSGES("88", "Vosges", "Métropole"),
    YVELINES("78", "Yvelines", "Métropole"),
    ;

    private final String code;
    private final String nom;
    private final String zone;

    DepartementEnum(String code, String nom, String zone) {
        this.code = code;
        this.nom = nom;
        this.zone = zone;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public String getZone() {
        return zone;
    }
}
