package com.process.service;

import com.process.model.Transacao;

import java.math.BigDecimal;
import java.util.List;

public interface GerarRelatorio {
    BigDecimal valorTransacoes(List<Transacao> transacoes);
    BigDecimal valorAprovadas(List<Transacao> transacoes);
    int totalTransacoes(List<Transacao> transacoes);
    int totalErros(List<Transacao> transacoes);
    void gerarArquivoResumo(List<Transacao> transacoes, String diretorioSaida);
}
