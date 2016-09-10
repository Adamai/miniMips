package br.ufrpe.arquitetura.minimips;

public class DecoderBinAsse {
	
	public DecoderBinAsse(){
		
	}
	
	public String convert2(String entrada){
		String op = entrada.substring(26, 32);
		String inst= entrada.substring(0, 6);
		
		String tipo = verifInst(inst);
		String opverif = verifop(op,tipo);
		String regverif = verifReg(entrada, opverif,tipo);
		
		String resultado="";
		resultado = opverif + " " +regverif;

		return resultado;
	}
	
	public String verifop(String op, String tipo){ // VERIFICA A OPERAÇÃO 
		String resultOP = "";
		
		if(tipo.equals("R")){ //INTRUÇOES TIPO R
		switch(op){
		case"000000": resultOP = "sll";
			break;
		case"100000":resultOP = "add";
			break;
		}
		}
		else if(tipo.equals("I")){ //INSTRUÇOES TIPO I
			switch(op){
			
			}
		}
		else{       // INTRUÇOES TIPO J
			switch(op){
			
			}
		}
		return resultOP;
	}
	
	public String verifInst(String inst){ // VERIFICA O TIPO DE INSTRUÇÃO
		String instVerif = "";
		
		if(inst.equals("000000")){
			instVerif = "R";
			}
			else if (inst.equals("000010")||inst.equals("000011")){
				instVerif = "J";
			}
			else
				instVerif = "I";
		
			return instVerif;
	}
	
	public String verifReg(String entrada, String op, String tipo){ // CODIFICA OS REGISTRADORES
		String r1= "";//dest
		String r2= "";//op1
		String r3= "";//op2
		String r4= "";
		String result="";
		
		if(tipo.equals("R")){
			   r1= convertBin(entrada.substring(16, 21));
			   r2= convertBin(entrada.substring(6, 11));
			   r3= convertBin(entrada.substring(11, 16));
			   r4= convertBin(entrada.substring(21,26));
			   result = r1 + ", "+ r2 + ", "+r3;
			switch(op){
				case"add": result = "$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3; 
				   break;
				case"sll":result = "$"+r1 + ", "+ "$"+ r2 + ", "+ r4;
					break;
	
			}
		}
		
		return result;
	}
	
	
	public String convertBin(String bin){
		String result="";
		int BinarioDecimal = Integer.parseInt(bin, 2);
		
		result = Integer.toString(BinarioDecimal);
		
		return result;
	}
}