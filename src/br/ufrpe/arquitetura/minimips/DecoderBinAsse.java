package br.ufrpe.arquitetura.minimips;

public class DecoderBinAsse {
	
private String tipo;
private String resultOP;
private String imediato;
private int[] registradores = new int[4]; 


	public StringBuffer convert2(String entrada){

		 verifop(entrada);
		StringBuffer registradores = verifReg(entrada);

		StringBuffer resultado= new StringBuffer();
				resultado.append(this.resultOP + " " + registradores);

		return resultado;
	}
	
	public void verifop(String entrada){ // VERIFICA A OPERAÇÃO 
		
		String inst = entrada.substring(0, 6);
		
		
		if(inst.equals("000000")){
			this.tipo = "R"; // this.tipo = "R"
		}
		else if(inst.equals("000010")||inst.equals("000011")){
			this.tipo = "J"; // this.tipo = "J"
		}
		else{
			this.tipo= "I";  //this.tipo = "I"
			}
		
		if(this.tipo.equals("R")){    //INTRUÇOES TIPO R
			String aux = entrada.substring(26, 32);
		switch(aux){
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
		case"100100":resultOP="and";
			break;
		case"000111": resultOP ="srav";
		break;
		}
		}
		else if(tipo.equals("I")){ //INSTRUÇOES TIPO I
			imediato = entrada.substring(16,32);
			switch(inst){
			case"001111": resultOP = "lui";
				break;
			case"001000": resultOP="addi";
				break;
			case"001010": resultOP="slti";
				break;
			case"101011": resultOP= "sw";
				break;
			case"100011": resultOP = "lw";
				break;
			case"000101": resultOP = "bne";
				break;
			case"000001": resultOP = "bltz";
				break;
			case"000100":resultOP = "beq";
				break;
			case"001110": resultOP="xori";
				break;
			case"001101": resultOP="ori";
				break;
			case"001100": resultOP="andi";
				break;
			case"001001": resultOP="addiu";
				break;
			case"100000": resultOP="lb";
				break;
			case"101000": resultOP="sb";
				break;
			case"100100": resultOP ="lbu";
				break;
			
			}
		}
		else{       // INTRUÇOES TIPO J
			imediato = entrada.substring(6,32);
			switch(inst){
			case"000010": resultOP="j";
				break;
			case"000011": resultOP="jal";
				break;
			}
		}
	}
	
	public StringBuffer verifReg(String entrada){ // DECODIFICA OS REGISTRADORES
		
		String r1= "";//dest
		String r2= "";//op1
		String r3= "";//op2
		String r4= "";
		StringBuffer result= new StringBuffer();
		
		if(this.tipo.equals("R")){
			   r1= convertBin(entrada.substring(16, 21)); //resposta-> 4ªseq
			   r2= convertBin(entrada.substring(6, 11)); //operador-> 2ªseq
			   r3= convertBin(entrada.substring(11, 16)); //operador-> 3ªseq
			   r4= convertBin(entrada.substring(21,26)); //operador especial-> 5ªseq
			  // result.append(r1 + ", "+ r2 + ", "+r3);
			switch(resultOP){
				case"add": result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3+'\n');
						registradores[0] = Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3); registradores[3] = Integer.parseInt(r3);  
				   break;
				case"sub": result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3);
						registradores[0] = Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3); registradores[3] = Integer.parseInt(r3);
				   break;
				case"sll":result.append("$"+r1 + ", "+ "$"+ r3 + ", "+ r4);
					break;
				case"srl":result.append("$"+r1 + ", "+ "$"+ r3 + ", "+ r4);
					break;
				case"sra":result.append("$"+r1 + ", "+ "$"+ r3 + ", "+ r4);
					break;
				case"sllv":result.append("$"+r1 + ", "+ "$"+ r3 + ", "+ "$"+r2); 
					break;
				case"srlv":result.append("$"+r1 + ", "+ "$"+ r3 + ", "+ "$"+r2); 
					break;
				case"syscall":result.append("");
					break;
				case"jr":result.append("$"+r2);
					break;
				case"divu":result.append("$"+r2 +", "+ "$"+r3);
					break;
				case"div":result.append("$"+r2+", "+"$"+r3);
					break;
				case"multu":result.append("$"+r2+", $"+r3);
					break;
				case"slt":result.append("$"+r1+", $"+r2+", $"+r3);
					break;
				case"mflo":result.append("$"+r1);
					break;
				case"mfhi":result.append("$"+r1);
					break;
				case"and":result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3);
			     	break;
			    case"or":result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3);
			     	break;
			    case"xor":result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3);
			     	break;
			    case"nor":result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3);
			     	break;
			    case"addu":result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3);
			     	break;
			    case"subu":result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3);
			    		   registradores[0] = Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3); registradores[3] = Integer.parseInt(r3);
			     	break;
			    case"mult":result.append("$"+r2 + ", "+ "$"+ r3);
			               registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3);
			     	break;
			    case"srav":result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3);
			    	break;
				
	
			}
		}
		if(this.tipo.equals("I")){
		
			 r2= convertBin(entrada.substring(6, 11)); //operador-> 2ªseq
			 r3= convertBin(entrada.substring(11, 16)); //operador-> 3ªseq
			  
			 
			switch(resultOP){
			case"lui":result.append("$"+ r3 + ", " + convertBin(imediato));
				break;
			case"addi":result.append("$"+ r3 + ", " + "$"+ r2 + ", " + convertBin(imediato));
					   registradores[1] = Integer.parseInt(r3); registradores[0] = Integer.parseInt(r2); 
				break;
			case"slti":result.append("$"+ r3 + ", $"+r2 + ", "+ convertBin(imediato));
				break;
			case"sw":result.append("$"+ r3 + ", "+convertBin(imediato)+"($"+r2+")");
				break;
			case"lw":result.append("$"+ r3 + ", "+convertBin(imediato)+"($"+r2+")");
				break;
			case"bne":result.append("$"+r2+", $"+r3+ ", "+ convertBin(imediato));
				break;
			case"bltz":result.append("$"+r2+ ", "+ convertBin(imediato));
				break;
			case"beq":result.append("$"+r2+", $"+r3 +", " + convertBin(imediato));
				break;
			case"xori":result.append("$" + r3 + ", " + "$"+ r2+ ", "+ convertBin(imediato));
				break;
			case "ori":result.append("$" + r3 + ", " + "$"+ r2+ ", "+ convertBin(imediato));
				break;
			case "andi":result.append("$" + r3 + ", " + "$" + r2+ ", "+ convertBin(imediato));
				break;
			case "addiu":result.append("$" + r3 + ", " + "$" + r2+ ", "+ convertBin(imediato));
				break;
			case"lbu": result.append("$"+ r3 + ", "+convertBin(imediato)+"($"+r2+")");
				break;
			case"lb": result.append("$"+ r3 + ", "+convertBin(imediato)+"($"+r2+")");
				break;
			case"sb": result.append("$"+ r3 + ", "+convertBin(imediato)+"($"+r2+")");
			
		}
		
			
		}
		if(this.tipo.equals("J")){
			
		switch(resultOP){	
			case"j":result.append(", "+ convertPositv(imediato));
				break;
			case"jal":result.append(", "+ convertPositv(imediato));
				break;
		}
		}
		
		return result;
	}
	
	
	public static String convertBin(String bin){
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
          for(int j = i+1; j<bin.length(); j++){
            lakon += bin.charAt(j);
          }
			int BinarioDecimal = Integer.parseInt(lakon, 2);
			result = Integer.toString(BinarioDecimal);
			resultado += result;
			return resultado;
		}  else{
		int BinarioDecimal = Integer.parseInt(bin, 2);
		result = Integer.toString(BinarioDecimal);
		return result;
		}
	}
	
	public String convertPositv(String bin){
		String result = "";
		int BinarioDecimal = Integer.parseInt(bin, 2);
		result = Integer.toString(BinarioDecimal);
		return result;
	}
	
	public String getInstrucao(){
		return this.resultOP;
	}
	
	public int[] getEntrada(){
		return registradores;
	}
	
	public String getImediato(){
		return imediato;
	}
	
	
}