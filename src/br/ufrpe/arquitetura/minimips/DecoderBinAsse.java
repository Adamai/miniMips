package br.ufrpe.arquitetura.minimips;

public class DecoderBinAsse {
	
private String tipo;
private String resultOP;
private String imediato;
private int[] registradores = new int[4]; 
DecoderReg dereg = new DecoderReg();


	public StringBuffer convert2(String entrada){

		 verifop(entrada);
		StringBuffer registradores = dereg.verifReg(entrada, this.tipo, this.resultOP, this.registradores, this.imediato);

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