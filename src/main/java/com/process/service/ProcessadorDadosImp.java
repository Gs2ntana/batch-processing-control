package com.process.service;

import com.process.model.Transacao;
import com.process.util.Status;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public class ProcessadorDadosImp implements ProcessadorDados{
    @Override
    public Optional<Transacao> processar(String linhaValida) {
        try{
            String[] parte = linhaValida.trim().split(";");
            UUID id = UUID.fromString(parte[0]);
            Optional<String> nome = Optional.ofNullable(parte[1])
                    .filter(s -> !s.trim().isEmpty());
            BigDecimal valor = new BigDecimal(parte[2]);
            Status status = Status.valueOf(parte[3].toUpperCase());

            return Optional.of(new Transacao(id, nome, valor, status));
        }catch (Exception e){
            return Optional.empty();
        }
    }
}
