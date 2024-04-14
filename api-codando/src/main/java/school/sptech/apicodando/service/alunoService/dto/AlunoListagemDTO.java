package school.sptech.apicodando.service.alunoService.dto;

public class AlunoListagemDTO {

    private Integer idAluno;
    private String nome;
    private String sobrenome;
    private String apelido;
    private String status;
    private Integer moedas;

    public Integer getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMoedas() {
        return moedas;
    }

    public void setMoedas(Integer moedas) {
        this.moedas = moedas;
    }
}
