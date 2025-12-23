package com.process.model;

import com.process.util.Status;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public class Transacao {
    private UUID id;
    private Optional<String> nome;
    private BigDecimal valor;
    private Status status;

    public Transacao(UUID id, Optional<String> nome, BigDecimal valor, Status status){
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.status = status;
    }

    public UUID getId() { return id; }
    public Optional<String> getClientName() { return nome; }
    public BigDecimal getValue() { return valor; }
    public Status getStatus() { return status; }
}
