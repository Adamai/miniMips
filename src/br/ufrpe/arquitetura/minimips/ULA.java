package br.ufrpe.arquitetura.minimips;

public class ULA {

	private BancoRegistrador registradores;
	private String resultado;
	DecoderBinAsse binAsse = new DecoderBinAsse();
	
	public ULA(){
		this.registradores = new BancoRegistrador();
		resultado ="";
	}
	
	
	public String resultado(){
		for(int i = 0;i<32;i++){
			resultado+= "$"+i+ "="+ binAsse.convertBin(registradores.getRegistrador(i))+";";
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
			for(int i=0; i<op1.length(); i++){
				if(op1.charAt(i) == '1' && op2.charAt(i) == '1')
					bin.append("1");
				else
					bin.append("0");
			}
		}
		if (inst == "or" || inst == "ori"){
			for(int i=0; i<op1.length(); i++){
				if(op1.charAt(i) == '1' || op2.charAt(i) == '1')
					bin.append("1");
				else
					bin.append("0");
			}
		}
		if (inst == "xor" || inst == "xori"){
			for(int i=0; i<op1.length(); i++){
				if((op1.charAt(i) == '1' && op2.charAt(i) == '0') || (op1.charAt(i) == '0' && op2.charAt(i) == '1'))
					bin.append("1");
				else
					bin.append("0");
			}
		}
		if (inst == "nor"){
			for(int i=0; i<op1.length(); i++){
				if(op1.charAt(i) == '1' || op2.charAt(i) == '1')
					bin.append("0");
				else
					bin.append("1");
			}
		}
		if (inst == "slt" || inst == "sltu" || inst == "slti"){ //observar sltunsigned
			int a = Integer.parseInt(binAsse.convertBin(op1));
			int b = Integer.parseInt(binAsse.convertBin(op2));
			if( a < b )
				bin.append("0000000000000001");
			else
				bin.append("0000000000000000");
		}
		return bin.toString();
	}
	
	public String somador(String op1, String op2, String inst){
		String result ="";
		int a = Integer.parseInt(binAsse.convertBin(op1));
		int b = Integer.parseInt(binAsse.convertBin(op2));
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
		int a = Integer.parseInt(binAsse.convertBin(op1));
		int b = Integer.parseInt(binAsse.convertBin(op2));
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
	

