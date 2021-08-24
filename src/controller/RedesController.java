package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	
	public RedesController() {
      super();
	}
	
	public String os() {
		
		String os = System.getProperty("os.name");//Traz o nome do sistema Operacional da m�quina
		return "Sistema operacional: "+os; //Retorna o nome do sistema operacional
	} 
	
	public void ip (String cmd) {
		RedesController soSistema = new RedesController(); //Instanciando a classe para verificar o sistema operacional
		String so = soSistema.os();
		
		//Verificando o nome do SO
		if (so.contains("Windows")) {
			cmd = "cmd /c start C:\\Windows\\System32\\cmd.exe";
		}else {
			cmd = "sudo su \\usr\\bin\\exo-open";
		}
		try {
			//Para abrir o prompt de comando
			Runtime.getRuntime().exec(cmd.toString());
			} catch (IOException e) {
				e.printStackTrace(); //Erro que ele vai mostrar caso o try n�o seja true
				}

			StringBuffer buffer = new StringBuffer();
			if(so.contains("Windows")) {
				buffer.append("ipconfig"); //Se cont�m o nome de windows, usa o comando ipconfig
			}else {
				buffer.append("ifconfig");// Sen�o, usa o comando de ifconfig do linux
			}
			
			//Para exibir os ipv4 apenas
			try {
				Process	processoCmd = Runtime.getRuntime().exec(buffer.toString());	//retorna processoCmd, enquanto ele estiver em execu��o, continua recebendo dados	
				InputStream fluxo = processoCmd.getInputStream(); //basicamente um fluxo de bits
				InputStreamReader leitor = new InputStreamReader(fluxo); //Vai ler e converter para String
				BufferedReader buffer2 = new BufferedReader(leitor); //Guarda a vari�vel no buffer
				String linha = buffer2.readLine(); //Lendo a primeira linha do buffer
				String bufferNovo = " ";
				
				System.out.println("Adaptadores IPv4:\n");
				
				while(linha != null) {
					linha = buffer2.readLine(); //Enquanto a linha for diferente de null, repete o comando para ler a pr�xima linha
				
				if(linha != null && linha.contains("Adaptador") || linha.contains("mtu")) {
					bufferNovo = linha; //Se a linha cont�m "Adaptador" ou "mtu", guarda no buffer
				}
					if(linha != null && linha.contains("IPv4") || linha.contains("inet ")) {
					System.out.println(bufferNovo); //Enquanto a linha cont�m IPv4 ou "inet", imprime na tela esses valores
					System.out.println(linha + "\n");
				}
					}
				buffer2.close(); //Fecha buffer
				leitor.close(); //Fecha leitor
				fluxo.close(); //Fecha fluxo
				
			} catch (IOException e) {
				e.printStackTrace(); //Erro que mostra caso entre na Exception
			}
			System.exit(0);
			
			
}
	public void ping(String cmd) {
		//Pegando o nome do So		
				RedesController opSystem = new RedesController();
				String so = opSystem.os();
					
          //Verificando o so
				if(so.contains("Windows")) {
					cmd = "cmd /c start C:\\Windows\\System32\\cmd.exe";
				}else {
					cmd = "sudo su \\usr\\bin\\exo-open";
				}
					
					try {
//						Execu��o do comando para abrir o prompt de comando
						Runtime.getRuntime().exec(cmd.toString()); //Tenta executar
						} catch (IOException e) {
							e.printStackTrace(); //Erro caso n�o consiga
							}
					
						StringBuffer buffer = new StringBuffer(); //Criando o buffer
						if(so.contains("Windows")) {
							buffer.append("ping -4 -n 10 www.google.com.br"); 
						}else {
							buffer.append("ping -4 -c 10 www.google.com.br");
						}
						
					try {
						Process processoCmd = Runtime.getRuntime().exec(buffer.toString()); //retorna um processo, enquanto estiver em execu��o, a vari�vel continua recebendo dados
						InputStream fluxo = processoCmd.getInputStream(); //Fluxo de bits
						InputStreamReader leitor = new InputStreamReader(fluxo); //L� e converte para String
						BufferedReader buffer2 = new BufferedReader(leitor); //Lendo a primeira linha do buffer
						String linha = buffer2.readLine();
						while(linha != null) {
							
							linha = buffer2.readLine();
							
							if(linha != null && linha.contains("ms,") || linha.contains("/mdev =")) {
								System.out.println(linha);
							}
						}
						buffer2.close();
						leitor.close();
						fluxo.close();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.exit(0);
						} 
}
