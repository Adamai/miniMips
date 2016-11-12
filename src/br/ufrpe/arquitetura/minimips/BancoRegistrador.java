package br.ufrpe.arquitetura.minimips;

public class BancoRegistrador {

	String[]registradores;
	String Hi = "00000000000000000000000000000000";
	String Lo = "00000000000000000000000000000000";
	
	public BancoRegistrador(){
		this.registradores = new String[32];
		for(int i = 0;i<32;i++){
			registradores[i] = "00000000000000000000000000000000";
			//if(i == 2)
			//	registradores[i] = "00000000000000000000000000000111";
			//if(i == 3)
			//	registradores[i] = "00000000000000000000000000000001";
		}
	}
	
	public String getRegistrador(int i){
		return registradores[i];
	}
	
	public void setRegistrador(int i, String valor){
		registradores[i] = valor;
	}

	public String getHi() {
		return Hi;
	}

	public void setHi(String hi) {
		Hi = hi;
	}

	public String getLo() {
		return Lo;
	}

	public void setLo(String lo) {
		Lo = lo;
	}
	
	
}
