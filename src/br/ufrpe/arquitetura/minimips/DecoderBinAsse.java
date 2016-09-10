package br.ufrpe.arquitetura.minimips;

public class DecoderBinAsse {
	
	public DecoderBinAsse(){
		
	}
	
	public String convert2(String entrada){
		String op = entrada.substring(26, 32);
		String inst= entrada.substring(0, 5);
		
		String tipo = verifInst(inst);
		String opverif = verifop(op,tipo);
		String regverif = verifReg(entrada, opverif);
		
		String resultado="";
		resultado = opverif + " " +regverif;

		return resultado;
	}
	
	public String verifop(String op, String tipo){ // VERIFICA A OPERAÇÃO 
		String resultOP = "";
		
		if(tipo.equals("R")){ //INTRUÇOES TIPO R
		switch(op){
		
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
	
	public String verifReg(String entrada, String op){ // CODIFICA OS REGISTRADORES
		String r1= "";//dest
		String r2= "";//op1
		String r3= "";//op2
		String result="";
		
		switch(op){
		case"add": {
				   r1= convertBin(entrada.substring(16, 21));
				   r2= convertBin(entrada.substring(6, 11));
				   r3= convertBin(entrada.substring(11, 16));
				   result = r1 + ", "+ r2 + ", "+r3;
				   }
				   break;
		}
		
		
		return result;
	}
	
	
	public String convertBin(String bin){
		String result="";
		int BinarioDecimal = Integer.parseInt(bin, 2);
		result = "$"+BinarioDecimal;
		
		//switch(bin){
		//case"00000":result ="$0";
		//	break;
		//case"00001":result ="$1";
		//	break;
		//case"00010":result="$2";
		//	break:
		//case"00"
		//case"01000":result ="$8";
		//	break;
		//case"10000":result ="$16";
		//	break;
		//case"10001":result ="$17";
		//}
		
		return result;
	}
}