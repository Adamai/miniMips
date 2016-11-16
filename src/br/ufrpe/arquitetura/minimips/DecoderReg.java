package br.ufrpe.arquitetura.minimips;

public class DecoderReg {
	
	DecoderBinDec bindec = new DecoderBinDec();
	
	public StringBuffer verifReg(String entrada, String tipo, String resultOP, int[] registradores, String imediato){ // DECODIFICA OS REGISTRADORES
		
		String r1= "";//dest
		String r2= "";//op1
		String r3= "";//op2
		String r4= "";
		StringBuffer result= new StringBuffer();
		
		if(tipo.equals("R")){
			   r1= bindec.convertBin(entrada.substring(16, 21)); //resposta-> 4ªseq
			   r2= bindec.convertPositv(entrada.substring(6, 11)); //operador1-> 2ªseq // acho que todos devem ser convertPositiv
			   r3= bindec.convertBin(entrada.substring(11, 16)); //operador2-> 3ªseq
			   r4= bindec.convertBin(entrada.substring(21,26)); //operador especial-> 5ªseq
			  // result.append(r1 + ", "+ r2 + ", "+r3);
			switch(resultOP){
				case"add": result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3  /* +'\n' */   );
						registradores[0] = Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3); registradores[3] = Integer.parseInt(r3);  
						//System.out.println(registradores[0]+" "+registradores[1]+" "+registradores[2]+" "+registradores[3]);
					break;
				case"sub": result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3);
						registradores[0] = Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3); registradores[3] = Integer.parseInt(r3);
				   break;
				case"sll":result.append("$"+r1 + ", "+ "$"+ r3 + ", "+ r4);
						registradores[0]= Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[3] = Integer.parseInt(r4);
					break;
				case"srl":result.append("$"+r1 + ", "+ "$"+ r3 + ", "+ r4);
						registradores[0]= Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[3] = Integer.parseInt(r4);
					break;
				case"sra":result.append("$"+r1 + ", "+ "$"+ r3 + ", "+ r4);
						registradores[0]= Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[3] = Integer.parseInt(r4);
					break;
				case"sllv":result.append("$"+r1 + ", "+ "$"+ r3 + ", "+ "$"+r2); 
						registradores[0] = Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3);
					break;
				case"srlv":result.append("$"+r1 + ", "+ "$"+ r3 + ", "+ "$"+r2); 
						registradores[0] = Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3);
					break;
				case"syscall":result.append("");
					break;
				case"jr":result.append("$"+r2);
						registradores[1] = Integer.parseInt(r2);
					break;
				case"divu":result.append("$"+r2 +", "+ "$"+r3);
						registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3);
					break;
				case"div":result.append("$"+r2+", "+"$"+r3);
						registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3);
					break;
				case"multu":result.append("$"+r2+", $"+r3);
						registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3);
					break;
				case"slt":result.append("$"+r1+", $"+r2+", $"+r3);
						registradores[0] = Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3);
					break;
				case"mflo":result.append("$"+r1);
						registradores[0] = Integer.parseInt(r1);
					break;
				case"mfhi":result.append("$"+r1);
						registradores[0] = Integer.parseInt(r1);
					break;
				case"and":result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3);
						registradores[0] = Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3);
			     	break;
			    case"or":result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3);
			    		registradores[0] = Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3);
			     	break;
			    case"xor":result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3);
			    		registradores[0] = Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3);
			     	break;
			    case"nor":result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3);
			    		registradores[0] = Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3);
			     	break;
			    case"addu":result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3);
			    		registradores[0] = Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3);
			     	break;
			    case"subu":result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3);
			    		   registradores[0] = Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3); registradores[3] = Integer.parseInt(r3);
			     	break;
			    case"mult":result.append("$"+r2 + ", "+ "$"+ r3);
			               registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3);
			     	break;
			    case"srav":result.append("$"+r1 + ", "+ "$"+ r2 + ", "+ "$"+r3);
			    		registradores[0] = Integer.parseInt(r1); registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3);
			    	break;
				
	
			}
		}
		if(tipo.equals("I")){
				//6-11 -> operador 1
				//11-16 -> resposta
				//16-32 -> operador imediato
			 r2= bindec.convertBin(entrada.substring(6, 11)); //operador-> 2ªseq      
			 r3= bindec.convertBin(entrada.substring(11, 16)); //resposta-> 3ªseq
			 
			switch(resultOP){
			case"lui":result.append("$"+ r3 + ", " + bindec.convertBin(imediato));
					registradores[0] = Integer.parseInt(r3);
				break;
			case"addi":result.append("$"+ r3 + ", " + "$"+ r2 + ", " + bindec.convertBin(imediato));
					registradores[1] = Integer.parseInt(r2); registradores[0] = Integer.parseInt(r3); registradores[2] = 00000; registradores[3] = 00000;
				break;
			case"slti":result.append("$"+ r3 + ", $"+r2 + ", "+ bindec.convertBin(imediato));
					registradores[0] = Integer.parseInt(r3); registradores[1] = Integer.parseInt(r2);
				break;
			case"sw":result.append("$"+ r3 + ", "+bindec.convertBin(imediato)+"($"+r2+")");
					registradores[1] = Integer.parseInt(r3); registradores[2] = Integer.parseInt(r2);
				break;
			case"lw":result.append("$"+ r3 + ", "+bindec.convertBin(imediato)+"($"+r2+")");
			registradores[1] = Integer.parseInt(r3); registradores[2] = Integer.parseInt(r2);
				break;
			case"bne":result.append("$"+r2+", $"+r3+ ", "+ bindec.convertBin(imediato));
					registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3);
				break;
			case"bltz":result.append("$"+r2+ ", "+ bindec.convertBin(imediato));
					registradores[1] = Integer.parseInt(r2);
				break;
			case"beq":result.append("$"+r2+", $"+r3 +", " + bindec.convertBin(imediato));
					registradores[1] = Integer.parseInt(r2); registradores[2] = Integer.parseInt(r3);
				break;
			case"xori":result.append("$" + r3 + ", " + "$"+ r2+ ", "+ bindec.convertBin(imediato));
					registradores[0] = Integer.parseInt(r3); registradores[1] = Integer.parseInt(r2);
				break;
			case "ori":result.append("$" + r3 + ", " + "$"+ r2+ ", "+ bindec.convertBin(imediato));
					registradores[0] = Integer.parseInt(r3); registradores[1] = Integer.parseInt(r2);
				break;
			case "andi":result.append("$" + r3 + ", " + "$" + r2+ ", "+ bindec.convertBin(imediato));
					registradores[0] = Integer.parseInt(r3); registradores[1] = Integer.parseInt(r2);
				break;
			case "addiu":result.append("$" + r3 + ", " + "$" + r2+ ", "+ bindec.convertBin(imediato));
					registradores[1] = Integer.parseInt(r2); registradores[0] = Integer.parseInt(r3);
				break;
			case"lbu": result.append("$"+ r3 + ", "+bindec.convertBin(imediato)+"($"+r2+")");
			registradores[1] = Integer.parseInt(r3); registradores[2] = Integer.parseInt(r2);
				break;
			case"lb": result.append("$"+ r3 + ", "+bindec.convertBin(imediato)+"($"+r2+")");
			registradores[1] = Integer.parseInt(r3); registradores[2] = Integer.parseInt(r2);
				break;
			case"sb": result.append("$"+ r3 + ", "+bindec.convertBin(imediato)+"($"+r2+")");
			registradores[1] = Integer.parseInt(r3); registradores[2] = Integer.parseInt(r2);
			
		}
		
			
		}
		if(tipo.equals("J")){
			
		switch(resultOP){	
			case"j":result.append(", "+ bindec.convertPositv(imediato));
				break;
			case"jal":result.append(", "+ bindec.convertPositv(imediato));
				break;
		}
		}
		
		return result;
	}

}
