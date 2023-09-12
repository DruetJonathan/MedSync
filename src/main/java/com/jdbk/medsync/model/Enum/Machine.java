package com.jdbk.medsync.model.Enum;

public enum Machine {

    SYSTEME_DE_ROBOT("système de robotique"),

    SYSTEME_DE_PERFUSION("système de perfusion"),

    SYSTEME_D_ASSISTANCE_CARDIAQUE("système d'assistance cardiaque"),

    SYSTEME_D_DIALYSE("système de dialyse"),

    SYSTEME_D_ANALGESIE("système d'analgésie");

    private final String value;
    Machine(String s) {
        value = s;
    }

    public String getValue() {
        return value;
    }

    public static Machine fromString(String value){

        return switch (value) {

            case "système de robotique" -> SYSTEME_DE_ROBOT;
            case "système de perfusion" -> SYSTEME_DE_PERFUSION;
            case "système d'assistance cardiaque" -> SYSTEME_D_ASSISTANCE_CARDIAQUE;
            case "système de dialyse" -> SYSTEME_D_DIALYSE;
            case "système d'analgésie" -> SYSTEME_D_ANALGESIE;
            default -> null;
        };
    }
}