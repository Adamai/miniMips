package br.ufrpe.arquitetura.minimips;

public class ULA {

	private BancoRegistrador registradores;
	private String resultado;
	DecoderBinAsse binAsse = new DecoderBinAsse();
	DecoderBinDec bindec = new DecoderBinDec();
	
	public ULA(){
		this.registradores = new BancoRegistrador();
		resultado ="";
	}
	
	
	public String resultado(){
		for(int i = 0;i<32;i++){
			resultado+= "$"+i+ "="+ bindec.convertBin(registradores.getRegistrador(i))+";";
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
		case"addu" : registradores.setRegistrador(result,somador(registradores.getRegistrador(op1),registradores.getRegistrador(op2),op));
			break;
		case"sub" : registradores.setRegistrador(result, somador(registradores.getRegistrador(op1),registradores.getRegistrador(op2),op));
			break;
		case"addiu": registradores.setRegistrador(result, somador(registradores.getRegistrador(op1),imediato,op));
			break;
		case"multu": multiplicador("multu",registradores.getRegistrador(op1),registradores.getRegistrador(op2));
			break;
		case"div": multiplicador("div",registradores.getRegistrador(op1),registradores.getRegistrador(op2));
			break;
		case"divu": multiplicador("divu",registradores.getRegistrador(op1),registradores.getRegistrador(op2));
			break;
		case"mfhi": registradores.setRegistrador(result, registradores.getHi());
			break;
		case"mflo": registradores.setRegistrador(result, registradores.getLo());
			break;
		}
		
	}
			//TESTE DE MODIFICAÇÃO. AJEITAR ESSA BOSTA!
	public void logica(String op, int op1, int op2, int result, String imediato){
		switch(op){
		
		case"and": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), registradores.getRegistrador(op2+1), op));
				break;
		case"or": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), registradores.getRegistrador(op2+1), op));
				break;
		case"nor": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), registradores.getRegistrador(op2+1), op));
				break;
		case"xor": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), registradores.getRegistrador(op2+1), op));
				break;
		case"xori": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), imediato, op));
				break;
		case"ori": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), imediato, op));
				break;
		case"andi": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), imediato, op));
				break;
		case"slt": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), registradores.getRegistrador(op2+1), op));
			break;
		case"sltu": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), registradores.getRegistrador(op2+1), op));
			break;
		case"slti": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), imediato, op));
			break;
		
		}
	}
	
	public String binbit(String op1, String op2, String inst){
		StringBuffer bin = new StringBuffer();
		if (inst == "and" || inst == "andi"){
			int a = Integer.parseInt(bindec.convertBin(op1));
			int b = Integer.parseInt(bindec.convertBin(op2));
			int c = a & b;
			String result = Integer.toBinaryString(c);
			for(int i=0; i<op1.length() - result.length(); i++){
				bin.append(0);
			}
			bin.append(result);
		}
		if (inst == "or" || inst == "ori"){
			int a = Integer.parseInt(bindec.convertBin(op1));
			int b = Integer.parseInt(bindec.convertBin(op2));
			int c = a | b;
			String result = Integer.toBinaryString(c);
			for(int i=0; i<op1.length() - result.length(); i++){
				bin.append(0);
			}
			bin.append(result);
		}
		if (inst == "xor" || inst == "xori"){
			int a = Integer.parseInt(bindec.convertBin(op1));
			int b = Integer.parseInt(bindec.convertBin(op2));
			int c = a ^ b;
			String result = Integer.toBinaryString(c);
			for(int i=0; i<op1.length() - result.length(); i++){
				bin.append(0);
			}
			bin.append(result);
		}
		if (inst == "nor"){
			int a = Integer.parseInt(bindec.convertBin(op1));
			int b = Integer.parseInt(bindec.convertBin(op2));
			int c = ~(a | b);
			String result = Integer.toBinaryString(c);
			for(int i=0; i<op1.length() - result.length(); i++){
				bin.append(0);
			}
			bin.append(result);
		}
		if (inst == "slt" ||inst == "slti"){ //observar sltunsigned
			int a = Integer.parseInt(bindec.convertBin(op1));
			int b = Integer.parseInt(bindec.convertBin(op2));
			if( a < b )
				bin.append("0000000000000001");
			else
				bin.append("0000000000000000");
		}
		if (inst == "sltu"){
			int a = Integer.parseInt(bindec.convertPositv(op1));
			int b = Integer.parseInt(bindec.convertPositv(op2));
			if( a < b )
				bin.append("0000000000000001");
			else
				bin.append("0000000000000000");
		}
		return bin.toString();
	}
	
	public String somador(String op1, String op2, String inst){
		String result ="";
		int a = Integer.parseInt(bindec.convertBin(op1));
		int b = Integer.parseInt(bindec.convertBin(op2));
		int c = 0;
		StringBuffer bin = new StringBuffer();
		
		if(inst =="add" || inst == "addi" || inst == "addu" || inst == "addiu")
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
		int a = Integer.parseInt(bindec.convertBin(op1));
		int b = Integer.parseInt(bindec.convertBin(op2));
		int c = 5;
		String result ="";
		StringBuffer bin = new StringBuffer();
		
		if(inst == "mult" || inst == "multu"){
			c = a*b;
			result = Integer.toBinaryString(c);
		}
		if(inst == "div" || inst == "divu"){
			c = a/b;
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
	

