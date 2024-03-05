package school.sptech.crudusuario;

import java.time.LocalDate;

public class Aluno extends Usuario{

    private int codigoSala;

    public Aluno() {
        super();
    }


//    public Aluno(String nome, LocalDate dataNascimento, int idade, String username, String senha, int codigoSala) {
//        super(nome, dataNascimento, idade, username, senha);
//        this.codigoSala = codigoSala;
//    }

    public int getCodigoSala() {
        return codigoSala;
    }

    public void setCodigoSala(int codigoSala) {
        this.codigoSala = codigoSala;
    }
}
