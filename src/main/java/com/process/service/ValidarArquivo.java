package com.process.service;

import java.math.BigDecimal;

public interface ValidarArquivo {
    boolean isValidLine(String line);
    boolean isError(String line);
}
