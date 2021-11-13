package com.rroca.encryptap.utils;

/**
 * EncryptData class used in API
 */
public class EncryptData {
    private String seed;
    private String texto;

    /**
     * @return seed
     */
    public String getSeed() {
        return seed;
    }

    /**
     * @param seed the seed
     */
    public void setSeed(String seed) {
        this.seed = seed;
    }

    /**
     * @return texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the text
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }
}
