package br.ufrpe.arquitetura.minimips;

public class ULA {

	private BancoRegistrador registradores;
	private String resultado;
	
	public ULA(){
		this.registradores = new BancoRegistrador();
		resultado ="";
	}
	
	
	public String resultado(){
		for(int i = 0;i<32;i++){
			resultado+= "$"+i+ "="+ DecoderBinAsse.convertBin(registradores.getRegistrador(i))+";";
			//System.out.println(registradores.getRegistrador(i));
		}
		String s = resultado;
		resultado="";
		return s;
	}
	
	public void aritmetica(String op, int op1,int op2, int result, String imediato){
		
		switch(op){
			
		case"add" : registradores.setRegistrador(result,somador(registradores.getRegistrador(op1),registradores.getRegistrador(op2),op));
			break;
		case"subu" : registradores.setRegistrador(result, somador(registradores.getRegistrador(op1),registradores.getRegistrador(op2),op));
			break;
		case"addi": registradores.setRegistrador(result, somador(registradores.getRegistrador(op1),imediato,op));
			break;
		case"mult": multiplicador("mult",registradores.getRegistrador(op1),registradores.getRegistrador(op2));
			break;
			
		
		}
		
	}
	
	public String somador(String op1, String op2, String inst){
		String result ="";
		int a = Integer.parseInt(DecoderBinAsse.convertBin(op1));
		int b = Integer.parseInt(DecoderBinAsse.convertBin(op2));
		int c = 0;
		StringBuffer bin = new StringBuffer();
		
		if(inst =="add" || inst == "addi")
			 c = a+b;
		else
			 c = a-b;
		
		result = Integer.toBinaryString(c);
		for(int i = 1;i<=32-result.length();i++){
			if(c>0)
				bin.append("0");
			else
				bin.append("1");
		}
			bin.append(result);
		

		return bin.toString();
		}
	
	
	public void multiplicador(String inst, String op1, String op2){
		int a = Integer.parseInt(DecoderBinAsse.convertBin(op1));
		int b = Integer.parseInt(DecoderBinAsse.convertBin(op2));
		int c = 5;
		String result ="";
		StringBuffer bin = new StringBuffer();
		
		if(inst == "mult"){
			c = a*b;
			result = Integer.toBinaryString(c);
		}
			for(int i = 1;i<=64-result.length();i++){
				if(c>0)
					bin.append("0");
				else
					bin.append("1");
		}
			bin.append(result);
			result = bin.toString();
			registradores.setHi(result.substring(0, 32));
			registradores.setLo(result.substring(32,64));
	}
			
	}
	

