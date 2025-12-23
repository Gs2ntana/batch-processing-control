package com.process.service;

import com.process.model.Transacao;

import java.io.IOException;
import java.util.Optional;

public interface ProcessadorDados {
    Optional<Transacao> processar(String linhaValida);
}