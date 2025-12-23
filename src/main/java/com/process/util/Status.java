package com.process.util;

public enum Status {
    APROVADO(1),
    RECUSADO(0),
    PROCESSANDO(3),
    ERROR(4);

    private final int codigo;

    Status(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}
