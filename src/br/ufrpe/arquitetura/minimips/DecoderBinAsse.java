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
		case"000010": resultOP="srl";
			break;
		case"000011": resultOP="sra";
			break;
		case"000100": resultOP="sllv";
			break;
		case"000110": resultOP="srlv";
			break;
		case"100000":resultOP = "add";
			break;
		case"100010":resultOP="sub";
			break;
		case"001100":resultOP="syscall";
			break;
		case"001000":resultOP = "jr";
			break;
		case"011011":resultOP= "divu";
			break;
		case"011010":resultOP="div";
			break;
		case"011001":resultOP="multu";
			break;
		case"101010":resultOP="slt";
			break;
		case"010010":resultOP="mflo";
			break;
		case"010000":resultOP="mfhi";
			break;
		case"100101":resultOP="or";
		   break;
		  case"100110":resultOP="xor";
		   break;
		  case"100111":resultOP="nor";
		   break;
		  case"100001":resultOP="addu";
		   break;
		  case"100011":resultOP="subu";
		   break;
		  case"011000":resultOP="mult";
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
			   r1= convertBin(entrada.substring(16, 21)); //resposta-> 4ªseq
			   r2= convertBin(entrada.substring(6, 11)); //operador-> 2ªseq
			   r3= convertBin(entrada.substring(11, 16)); //operador-> 3ªseq
			   r4= convertBin(entrada.substring(21,26)); //operador especial-> 5ªseq
			   result = r1 + ", "+ r2 + ", "+r3;
			switch(op){
				case"add": result = "$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3; 
				   break;
				case"sub": result = "$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3; 
				   break;
				case"sll":result = "$"+r1 + ", "+ "$"+ r3 + ", "+ r4;
					break;
				case"srl":result = "$"+r1 + ", "+ "$"+ r3 + ", "+ r4;
					break;
				case"sra":result = "$"+r1 + ", "+ "$"+ r3 + ", "+ r4;
					break;
				case"sllv":result = "$"+r1 + ", "+ "$"+ r3 + ", "+ "$"+r2; 
					break;
				case"srlv":result = "$"+r1 + ", "+ "$"+ r3 + ", "+ "$"+r2; 
					break;
				case"syscall":result = "";
					break;
				case"jr":result = "$"+r2;
					break;
				case"divu":result = "$"+r2 +", "+ "$"+r3;
					break;
				case"div":result = "$"+r2+", "+"$"+r3;
					break;
				case"multu":result = "$"+r2+", $"+r3;
					break;
				case"slt":result = "$"+r1+", $"+r2+", $"+r3;
					break;
				case"mflo":result= "$"+r1;
					break;
				case"mfhi":result= "$"+r1;
					break;
				case"and":result = "$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3;
			     break;
			    case"or":result = "$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3;
			     break;
			    case"xor":result = "$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3;
			     break;
			    case"nor":result = "$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3;
			     break;
			    case"addu":result = "$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3;
			     break;
			    case"subu":result = "$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3;
			     break;
			    case"mult":result = "$"+r2 + ", "+ "$"+ r3;
			     break;
				
	
			}
		}
		if(tipo.equals("I")){
			
		}
		if(tipo.equals("J")){
			
		}
		
		return result;
	}
	
	
	public String convertBin(String bin){
		String result="";
		if(bin.charAt(0)=='1'){
			String resultado = "-";
			int k = 1;
			String lakon = "";
			int i = bin.length()-1;
			for( ; i>=0 && k == 1 ; i--){
				if(bin.charAt(i)=='1')
					k=0;
			}
			for(int j = 0; j<=i && k==0; j++){
				if(bin.charAt(j)=='0')
					lakon += "1";
				else if(bin.charAt(j)=='1')
					lakon += "0";
			}
			int BinarioDecimal = Integer.parseInt(lakon, 2);
			result = Integer.toString(BinarioDecimal);
			resultado += lakon;
			return resultado;
		} else{
		int BinarioDecimal = Integer.parseInt(bin, 2);
		result = Integer.toString(BinarioDecimal);
		return result;
		}
	}
	
/*	public String negbincompl(String bin){
		int k = 1;
		String lakon = "";
		int i = bin.length()-1;
		for( ; i>=0 && k == 1 ; i--){
			if(bin.charAt(i)=='1')
				k=0;
		}
		for(int j = 0; j<=i && k==0; j++){
			if(bin.charAt(j)=='0')
				lakon += "1";
			else if(bin.charAt(j)=='1')
				lakon += "0";
		}
		return lakon;
	}*/
	
}