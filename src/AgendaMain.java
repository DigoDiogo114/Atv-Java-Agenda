import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class AgendaMain {

	public static void main(String[] args) {
		
		HashMap<String, Contato> hmpContato = new HashMap<String, Contato>();
		int op = 11;
		
		do {
			
		op = Integer.parseInt(JOptionPane.showInputDialog("1- Importar Contatos\n"
					+ "2- Exportar Contatos\n"
					+ "3- Inserir Contato\n"
					+ "4- Remover Contato usando o Número de Telefone\n"
					+ "5- Remover Contato usando o Nome pessoal\n"
					+ "6- Localizar Contato usando o Nome pessoal\n"
					+ "7- Localizar Contato usando o Número de telefone\n"
					+ "8- Listar todos os contatos\n"
					+ "9- Realizar chamada\n"
					+ "10- Limpar Agenda\n"
					+ "11- Sair da Agenda"));
			
		switch (op) {
		case 1:
			try {
				InputStream is = new FileInputStream("/home/diogo/eclipse-workspace/Agendinha/contatos/listaSalva.txt");
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader buffer = new BufferedReader(isr);
				
				String linha = null;
				while((linha = buffer.readLine())!=null) {
					String[] key = linha.split(" ");
					hmpContato.put(key[3], new Contato(linha));
				}
				buffer.close();
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		case 2:
			try {
				OutputStream os = new FileOutputStream("/home/diogo/eclipse-workspace/Agendinha/contatos/listaSalva.txt");
				OutputStreamWriter osw = new OutputStreamWriter(os);
				BufferedWriter writer = new BufferedWriter(osw);
				
				Iterator<java.util.Map.Entry<String, Contato>> export = hmpContato.entrySet().iterator();
				while(export.hasNext()) {
					java.util.Map.Entry<String, Contato> en = export.next();
					writer.append(en.getValue().toString());
					writer.append("\r\n");
				}
				writer.close();
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			String telNum = JOptionPane.showInputDialog("Digite o número de telefone: ");
			String nome = JOptionPane.showInputDialog("Digite o nome: ");
			String cpf = JOptionPane.showInputDialog("Digite o cpf: ");
			
			hmpContato.put(telNum, new Contato(nome, cpf, telNum)); break;
		
		case 4:
			String telNumRmv = null;
			telNumRmv = JOptionPane.showInputDialog("Digite o número a remover");
			hmpContato.remove(telNumRmv); break;
			
		case 5:
			String nomeRmv = JOptionPane.showInputDialog("Digite o nome a remover");
			String remover = null;
			Iterator<java.util.Map.Entry<String, Contato>> it = hmpContato.entrySet().iterator();
			while(it.hasNext()) {
				java.util.Map.Entry<String, Contato> e = it.next();
				if(e.getValue().getNome().equals(nomeRmv)) {
					remover = e.getKey();
					hmpContato.remove(remover);
					break;
				}
			}break;
			 
		case 6:
			String nomeSrc = JOptionPane.showInputDialog("Digite o nome: ");
			Iterator<java.util.Map.Entry<String, Contato>> srcByName = hmpContato.entrySet().iterator();
			while(srcByName.hasNext()) {
				java.util.Map.Entry<String, Contato> e = srcByName.next();
				if(e.getValue().getNome().equals(nomeSrc)) {
					System.out.println(e.getValue());
				}
			}
			break;
		case 7:
			String telSrc = JOptionPane.showInputDialog("Digite o telefone: ");
			if(hmpContato.containsKey(telSrc)) {
				System.out.println(hmpContato.get(telSrc));
				break;
			}
			break;
		case 8:
			Iterator<java.util.Map.Entry<String, Contato>> show = hmpContato.entrySet().iterator();
			while(show.hasNext()) {
				java.util.Map.Entry<String, Contato> e = show.next();
				System.out.println(e.getValue());
			}break;
		case 9:
			String nomeLig = JOptionPane.showInputDialog("Digite o nome para a ligação: ");
			Iterator<java.util.Map.Entry<String, Contato>> lig = hmpContato.entrySet().iterator();
			while(lig.hasNext()) {
				java.util.Map.Entry<String, Contato> e = lig.next();
				if(e.getValue().getNome().equals(nomeLig)) {
					System.out.println("Ligando para "+nomeLig);
					break;
				}
			}break;
		case 10:
			hmpContato.clear();
		}
		}while(op!=11);
		}
		
	}
