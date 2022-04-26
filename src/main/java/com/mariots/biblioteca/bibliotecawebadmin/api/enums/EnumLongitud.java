package com.mariots.biblioteca.bibliotecawebadmin.api.enums;

public enum EnumLongitud {
    BREVE("breve"),LARGO("largo");

    String valor;

    private EnumLongitud(String valor){
        this.valor= valor;
    }

    public String getValor() {
        return valor;
    }

}
