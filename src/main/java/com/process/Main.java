package com.process;

import com.process.service.GerarRelatoriaImp;
import com.process.service.ValidarArquivoImp;
import com.process.service.ProcessadorDadosImp;
import com.process.model.Transacao;
import com.process.util.Status;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        ProcessadorDadosImp processador = new ProcessadorDadosImp();
        ValidarArquivoImp validador = new ValidarArquivoImp();
        GerarRelatoriaImp geradorRelatorio = new GerarRelatoriaImp();

        try{
            Path diretorioEntrada = Paths.get("data\\input");
            List<Transacao> todasTransacoes = new ArrayList<>();
            Files.list(diretorioEntrada)
                    .filter(path -> path.toString().endsWith(".dat"))
                    .forEach(arquivo -> {
                        try (Stream<String> linhas = Files.lines(arquivo)) {
                            linhas.forEach(linha -> {
                                if (validador.isValidLine(linha)) {
                                    Optional<Transacao> transacaoOpt = processador.processar(linha);
                                    transacaoOpt.ifPresent(t -> todasTransacoes.add(t));
                                } else {
                                    todasTransacoes.add(new Transacao(UUID.randomUUID(),Optional.of("Linha inválida"),new BigDecimal("-1"), Status.ERROR));
                                }
                            });
                        } catch (IOException e) {
                            System.err.println("Erro ao ler o arquivo: " + arquivo + " - " + e.getMessage());
                        }
                    });
            String diretorioSaida = "data\\output";
            geradorRelatorio.gerarArquivoResumo(todasTransacoes, diretorioSaida);

        } catch (IOException e) {
            System.err.println("Erro fatal ao listar diretório: " + e.getMessage());
        }
    }
}