package com.jdbk.medsync.model.Enum;

public enum Machine {

    BISTURI_ELECTRIQUE("bistouri électrique"),

    TABLE_D_OPERATION("table d'opération"),

    MONITEUR_DE_SURVEILLANCE("moniteur de surveillance"),

    AMPLIFICATEUR_DE_VOIX("amplificateur de voix"),

    SYSTEME_D_ASPIRATION("système d'aspiration"),

    SYSTEME_DE_RECHAUFFEMENT("système de réchauffement"),

    SYSTEME_D_ECLAIRAGE("système d'éclairage"),

    SYSTEME_D_IMAGERIE("système d'imagerie"),

    SYSTEME_DE_NAVIGATION("système de navigation"),
    SYSTEME_DE_ROBOT("système de robotique"),

    SYSTEME_DE_PERFUSION("système de perfusion"),

    SYSTEME_DE_VENTILATION("système de ventilation"),

    SYSTEME_D_ASSISTANCE_CARDIAQUE("système d'assistance cardiaque"),

    SYSTEME_D_DIALYSE("système de dialyse"),

    SYSTEME_D_ANALGESIE("système d'analgésie"),

    SYSTEME_D_ANESTHESIE("système d'anesthésie");

    private final String value;
    Machine(String s) {
        value = s;
    }

    public String getValue() {
        return value;
    }

    public static Machine fromString(String value){

        return switch (value) {
            case "bistouri électrique" -> BISTURI_ELECTRIQUE;
            case "table d'opération" -> TABLE_D_OPERATION;
            case "moniteur de surveillance" -> MONITEUR_DE_SURVEILLANCE;
            case "amplificateur de voix" -> AMPLIFICATEUR_DE_VOIX;
            case "système d'aspiration" -> SYSTEME_D_ASPIRATION;
            case "système de réchauffement" -> SYSTEME_DE_RECHAUFFEMENT;
            case "système d'éclairage" -> SYSTEME_D_ECLAIRAGE;
            case "système d'imagerie" -> SYSTEME_D_IMAGERIE;
            case "système de navigation" -> SYSTEME_DE_NAVIGATION;
            case "système de robotique" -> SYSTEME_DE_ROBOT;
            case "système de perfusion" -> SYSTEME_DE_PERFUSION;
            case "système de ventilation" -> SYSTEME_DE_VENTILATION;
            case "système d'assistance cardiaque" -> SYSTEME_D_ASSISTANCE_CARDIAQUE;
            case "système de dialyse" -> SYSTEME_D_DIALYSE;
            case "système d'analgésie" -> SYSTEME_D_ANALGESIE;
            case "système d'anesthésie" -> SYSTEME_D_ANESTHESIE;
            default -> null;
        };
    }
}