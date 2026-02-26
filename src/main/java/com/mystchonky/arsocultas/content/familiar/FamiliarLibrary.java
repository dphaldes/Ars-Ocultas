package com.mystchonky.arsocultas.content.familiar;

public class FamiliarLibrary {
    public static final String FAMILIAR_DRAGON = appendFamiliar("dragon");

    public static String appendFamiliar(String fam) {
        return "familiar_" + fam;
    }
}
