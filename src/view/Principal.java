package view;

import controller.RedesController; //Importando a classe RedesController

public class Principal {

	public static void main(String[] args) {
		RedesController redesController = new RedesController(); //Método construtor
		
		//Mostrando o sistema operacional
		String os = redesController.os(); //instancia
		System.out.println(os); //Mostra o sistema operacional
		
		//Mostrando adaptadores de rede ipv4
		String ipValidation = "";
		redesController.ip(ipValidation);
		
		//Mostrando o ping
		String pingValidation = "";
		redesController.ping(pingValidation);

	}

}
