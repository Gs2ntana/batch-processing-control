import com.process.service.ValidarArquivoImp;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.UUID;

public class ValidarArquivoTest {
    private ValidarArquivoImp validador;

    @BeforeEach
    public void setUp(){
        validador = new ValidarArquivoImp();
    }

    @Test
    void deveRetornarFalsoQuandoValorContemLetras() {
        String linhaInvalida = UUID.randomUUID().toString() + ";invalido;ab21b;Status.APROVADO";
        boolean resultado = validador.isValidLine(linhaInvalida);
        assertFalse(resultado);
    }

    @Test
    void deveRetornarTrueQuandoValido(){
        String linhaValida = UUID.randomUUID().toString() + ";valido;200;APROVADO";
        boolean resultado = validador.isValidLine(linhaValida);
        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseQuandoVazia(){
        String linhaInvalida = UUID.randomUUID().toString() + ";invalido;;Status.APROVADO";
        boolean resultado = validador.isValidLine(linhaInvalida);
        assertFalse(resultado);
    }
}
