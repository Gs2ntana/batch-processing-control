package com.process.service;

import com.process.model.Transacao;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.*;
import java.util.List;



public class GerarRelatoriaImp implements GerarRelatorio{

    @Override
    public BigDecimal valorTransacoes(List<Transacao> transacoes) {
        return transacoes.stream().map(Transacao::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal valorAprovadas(List<Transacao> transacoes) {
        return transacoes.stream()
                .filter(t -> "APROVADO".equalsIgnoreCase(t.getStatus().toString()))
                .map(Transacao::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public int totalTransacoes(List<Transacao> transacoes) {
        return (int) transacoes.size();
    }

    @Override
    public int totalErros(List<Transacao> transacoes) {
        return (int) transacoes.stream()
                .filter(t -> t.getValue().compareTo(BigDecimal.ZERO) < 0)
                .count();
    }

    @Override
    public void gerarArquivoResumo(List<Transacao> transacoes, String diretorioSaida) {
        int total = totalTransacoes(transacoes);
        BigDecimal valorAprovado = valorAprovadas(transacoes);
        int erros = totalErros(transacoes);

        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n");
        jsonBuilder.append("  \"totalTransacoes\": ").append(total).append(",\n");
        jsonBuilder.append("  \"valorAprovado\": ").append(valorAprovado).append(",\n");
        jsonBuilder.append("  \"quantidadeErros\": ").append(erros).append("\n");
        jsonBuilder.append("}");

        try {
            Path pathDiretorio = Paths.get(diretorioSaida);
            if (!Files.exists(pathDiretorio)) {
                Files.createDirectories(pathDiretorio);
            }

            Path pathArquivo = pathDiretorio.resolve("summary.json");
            Files.writeString(pathArquivo, jsonBuilder.toString());
            System.out.println("Relatório JSON gerado com sucesso em: " + pathArquivo.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Erro ao gravar o arquivo de resumo: " + e.getMessage());
        }
    }
}
