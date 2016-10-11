package br.ufrpe.arquitetura.minimips;

public class BancoRegistrador {

	String[]registradores;
	
	public BancoRegistrador(){
		this.registradores = new String[32];
		for(int i = 0;i<32;i++){
			registradores[i] = "00000000000000000000000000000000";
			if(i == 2)
				registradores[i] = "00000000000000000000000000000111";
			if(i == 3)
				registradores[i] = "00000000000000000000000000000001";
		}
	}
	
	public String getRegistrador(int i){
		return registradores[i];
	}
	
	public void setRegistrador(int i, String valor){
		registradores[i] = valor;
	}
}
