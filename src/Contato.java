
public class Contato {

	private String nome;
	private String cpf;
	private String telefone;
	
	public Contato(String nome, String cpf, String telefone) {
		this.cpf=cpf;
		this.nome=nome;
		this.telefone=telefone;
	}
	
	public Contato(String linha) {
		
		String[] infos = linha.split(" ");
		nome=infos[1];
		telefone=infos[3];
		cpf=infos[5];
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return "Contato: "+nome+" Telefone: "+telefone+" CPF: "+cpf;
	}
	
}
