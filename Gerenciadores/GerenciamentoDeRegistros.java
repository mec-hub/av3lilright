package Gerenciadores;
import java.util.ArrayList;

public interface GerenciamentoDeRegistros {
    void cadastrarRegistro();
    void alterarRegistro();
    void removerRegistro();
    void listarRegistros();
    void buscarRegistro();
    ArrayList<FluxoDeCaixa> ordenarRegistros();
}
