package org.example.entity;

import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum DepartementEnum {
    AIN("01", "Ain", ZoneEnum.METROPOLE),
    AISNE("02", "Aisne", ZoneEnum.METROPOLE),
    ALPES_DE_HAUTE_PROVENCE("04", "Alpes-de-Haute-Provence", ZoneEnum.METROPOLE),
    ALPES_MARITIMES("06", "Alpes-Maritimes", ZoneEnum.METROPOLE),
    ARDENNES("08", "Ardennes", ZoneEnum.METROPOLE),
    AUBE("10", "Aube", ZoneEnum.METROPOLE),
    Aude("11", "Aude", ZoneEnum.METROPOLE),
    AVEYRON("12", "Aveyron", ZoneEnum.METROPOLE),
    BOUCHES_DU_RHONE("13", "Bouches-du-Rhône", ZoneEnum.METROPOLE),
    CALVADOS("14", "Calvados", ZoneEnum.METROPOLE),
    CANTAL("15", "Cantal", ZoneEnum.METROPOLE),
    CHARENTE("16", "Charente", ZoneEnum.METROPOLE),
    CHARENTE_MARITIME("17", "Charente-Maritime", ZoneEnum.METROPOLE),
    CHER("18", "Cher", ZoneEnum.METROPOLE),
    CORREZE("19", "Corrèze", ZoneEnum.METROPOLE),
    COTE_D_OR("21", "Côte-d'Or", ZoneEnum.METROPOLE),
    COTES_D_ARMOR("22", "Côtes-d'Armor", ZoneEnum.METROPOLE),
    CREUSE("23", "Creuse", ZoneEnum.METROPOLE),
    DORDOGNE("24", "Dordogne", ZoneEnum.METROPOLE),
    DOUBS("25", "Doubs", ZoneEnum.METROPOLE),
    DROME("26", "Drôme", ZoneEnum.METROPOLE),
    EURE("27", "Eure", ZoneEnum.METROPOLE),
    EURE_ET_LOIR("28", "Eure-et-Loir", ZoneEnum.METROPOLE),
    FINISTERE("29", "Finistère", ZoneEnum.METROPOLE),
    GARD("30", "Gard", ZoneEnum.METROPOLE),
    GERS("32", "Gers", ZoneEnum.METROPOLE),
    GIRONDE("33", "Gironde", ZoneEnum.METROPOLE),
    GUADELOUPE("971", "Guadeloupe", ZoneEnum.GUADELOUPE),
    GUYANE("973", "Guyane", ZoneEnum.GUYANE),
    HAUTS_DE_SEINE("92", "Hauts-de-Seine", ZoneEnum.METROPOLE),
    HAUTE_CORSO("2B", "Haute-Corse", ZoneEnum.METROPOLE),
    HAUTE_GARONNE("31", "Haute-Garonne", ZoneEnum.METROPOLE),
    HAUTE_LOIRE("43", "Haute-Loire", ZoneEnum.METROPOLE),
    HAUTE_MARNE("52", "Haute-Marne", ZoneEnum.METROPOLE),
    HAUTE_SAVOIE("74", "Haute-Savoie", ZoneEnum.METROPOLE),
    HAUT_RHIN("68", "Haut-Rhin", ZoneEnum.ALSACE_MOSELLE),
    ISERE("38", "Isère", ZoneEnum.METROPOLE),
    JURA("39", "Jura", ZoneEnum.METROPOLE),
    LANDES("40", "Landes", ZoneEnum.METROPOLE),
    LOIRE("42", "Loire", ZoneEnum.METROPOLE),
    LOIRET("45", "Loiret", ZoneEnum.METROPOLE),
    LOT("46", "Lot", ZoneEnum.METROPOLE),
    LOT_ET_GARONNE("47", "Lot-et-Garonne", ZoneEnum.METROPOLE),
    MAINE_ET_LOIRE("49", "Maine-et-Loire", ZoneEnum.METROPOLE),
    MANCHE("50", "Manche", ZoneEnum.METROPOLE),
    MARNE("51", "Marne", ZoneEnum.METROPOLE),
    MARTINIQUE("972", "Martinique", ZoneEnum.MARTINIQUE),
    MAYOTTE("976", "Mayotte", ZoneEnum.MAYOTTE),
    MEURTHE_ET_MOSELLE("54", "Meurthe-et-Moselle", ZoneEnum.METROPOLE),
    MEUSE("55", "Meuse", ZoneEnum.METROPOLE),
    MORBIHAN("56", "Morbihan", ZoneEnum.METROPOLE),
    MOSELLE("57", "Moselle", ZoneEnum.ALSACE_MOSELLE),
    NORD("59", "Nord", ZoneEnum.METROPOLE),
    OISE("60", "Oise", ZoneEnum.METROPOLE),
    ORNE("61", "Orne", ZoneEnum.METROPOLE),
    PARIS("75", "Paris", ZoneEnum.METROPOLE),
    PAS_DE_CALAIS("62", "Pas-de-Calais", ZoneEnum.METROPOLE),
    PUY_DE_DOME("63", "Puy-de-Dôme", ZoneEnum.METROPOLE),
    PYRENEES_ATLANTIQUES("64", "Pyrénées-Atlantiques", ZoneEnum.METROPOLE),
    PYRENEES_ORIENTALES("66", "Pyrénées-Orientales", ZoneEnum.METROPOLE),
    REUNION("974", "La Réunion", ZoneEnum.LA_REUNION),
    RHONE("69", "Rhône", ZoneEnum.METROPOLE),
    SAONE_ET_LOIRE("71", "Saône-et-Loire", ZoneEnum.METROPOLE),
    SARTHE("72", "Sarthe", ZoneEnum.METROPOLE),
    SAVOIE("73", "Savoie", ZoneEnum.METROPOLE),
    SEMUR_EN_AUXOIS("21", "Côte-d'Or", ZoneEnum.METROPOLE),
    SEINE_ET_MARNE("77", "Seine-et-Marne", ZoneEnum.METROPOLE),
    SEINE_MARITIME("76", "Seine-Maritime", ZoneEnum.METROPOLE),
    SOMME("80", "Somme", ZoneEnum.METROPOLE),
    TARN("81", "Tarn", ZoneEnum.METROPOLE),
    TARN_ET_GARONNE("82", "Tarn-et-Garonne", ZoneEnum.METROPOLE),
    VAR("83", "Var", ZoneEnum.METROPOLE),
    VAUCLUSE("84", "Vaucluse", ZoneEnum.METROPOLE),
    VENDEE("85", "Vendée", ZoneEnum.METROPOLE),
    VIERGE("89", "Yonne", ZoneEnum.METROPOLE),
    VOSGES("88", "Vosges", ZoneEnum.METROPOLE),
    YVELINES("78", "Yvelines", ZoneEnum.METROPOLE),
    ;

    private final String code;
    private final String nom;
    private final ZoneEnum zone;

    DepartementEnum(String code, String nom, ZoneEnum zone) {
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

    public ZoneEnum getZone() {
        return zone;
    }
}