package com.process.model;

import java.math.BigDecimal;

public class Relatorio {
    public int totalArquivos;
    public int linhasComErro;
    public BigDecimal valorTotal;
    public BigDecimal valorAprovadas;

    public Relatorio(int totalArquivos, int linhasComErro, BigDecimal valorTotal, BigDecimal valorAprovadas) {
        this.totalArquivos = totalArquivos;
        this.linhasComErro = linhasComErro;
        this.valorTotal = valorTotal;
        this.valorAprovadas = valorAprovadas;
    }
}
