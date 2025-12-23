package com.process.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarArquivoImp implements ValidarArquivo {
    private static final Pattern padraoTransacao = Pattern.compile("^\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12};.*?;\\d+(?:\\.\\d{1,2})?;[A-Z]+$");

    @Override
    public boolean isValidLine(String line) {
        Matcher matcher = padraoTransacao.matcher(line);
        return matcher.matches();
    }

    @Override
    public boolean isError(String line){
        return !isValidLine(line);
    }
}
