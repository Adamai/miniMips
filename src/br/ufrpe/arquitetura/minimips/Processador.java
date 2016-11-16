package br.ufrpe.arquitetura.minimips;
//import java.util.ArrayList;

public class Processador {

	private ULA ula;
	private Instrucao[] instrucoes = new Instrucao[8000];
	private int PC = 0;
	private int instadd = 0;
	private Mem memory;
	
	public Processador(){
		this.memory = new Mem();
		this.ula = new ULA();
		//this.instrucoes = new ArrayList<Instrucao>();
	}
	
	public void pular(int pulo){
		this.PC = pulo;
	}
	public void setPC(int i){
		this.PC = i;
	}
	public int getPC(){
		return this.PC;
	}
	
	public String getAssembly(){
		//System.out.println(PC +" "+ instrucoes[PC].getAssembly());
		return instrucoes[PC].getAssembly();
	}
	
	public StringBuffer executar(){
		//BUG
		//int[] a =instrucoes.get(0).getEntrada();
		//System.out.println(a[0]);
		//System.out.println(a[0]+" "+a[1]+" "+a[2]);
		return resultInst(instrucoes[PC].getInstrucao(),instrucoes[PC].getEntrada(),instrucoes[PC].getImediato());
	}
	
	public void addInst(Instrucao i){
		//int[] a = i.getEntrada();
		//System.out.println(a[0]+" "+a[1]+" "+a[2]);
		//funcionando
		instrucoes[instadd] = i;
		instadd++;
		//int[]teste = instrucoes[0].getEntrada();
		//System.out.println(teste[0]);
		//int[] a =instrucoes.get(0).getEntrada();
		//System.out.println(a[0]);
	}
	
	public StringBuffer resultInst(String inst, int[] entrada, String imediato){
		boolean jump = false;
		StringBuffer resultado = new StringBuffer();
		/*entrada[0] = resultado
		 *entrada[1] = op1
		 *entrada[2] = op2
		 *entrada[3] = shift aritmetico/logico
		 */
		
		switch(inst){
		case"add" : ula.aritmetica("add",entrada[1],entrada[2],entrada[0],"");
					break;
		case"subu": ula.aritmetica("subu",entrada[1],entrada[2],entrada[0],"");
					break;
		case"addi" : ula.aritmetica("addi",entrada[1],0,entrada[0], imediato);
					break;
		case"mult" : ula.aritmetica("mult",entrada[1],entrada[2],0,"");
					break;
		case"and" : ula.logica("and", entrada[1], entrada[2], entrada[0], "");
					break;
		case"or" : ula.logica("or", entrada[1], entrada[2], entrada[0], "");
					break;
		case"nor" : ula.logica("nor", entrada[1], entrada[2], entrada[0], "");
					break;
		case"xor" : ula.logica("xor", entrada[1], entrada[2], entrada[0], "");
					break;
		case"xori" : ula.logica("xori",entrada[0],0,entrada[1], imediato);
					break;
		case"ori" : ula.logica("ori",entrada[0],0,entrada[1], imediato);
					break;
		case"andi" : ula.logica("andi",entrada[0],0,entrada[1], imediato);
					break;
		case"slt" : ula.logica("slt", entrada[1], entrada[2], entrada[0], "");
					break;
		case"sltu" : ula.logica("sltu", entrada[1], entrada[2], entrada[0], "");
					break;
		case"slti" : ula.logica("slti",entrada[0],0,entrada[1], imediato);
					break;
		case"addu" : ula.aritmetica("addu",entrada[1],entrada[2],entrada[0],"");
					break;
		case"sub": ula.aritmetica("sub",entrada[1],entrada[2],entrada[0],"");
					break;
		case"addiu" : ula.aritmetica("addiu",entrada[0],0,entrada[1], imediato);
					break;
		case"multu" : ula.aritmetica("multu",entrada[1],entrada[2],0,"");
					break;
		case"div" : ula.aritmetica("div",entrada[1],entrada[2],0,"");
			break;
		case"divu" : ula.aritmetica("divu",entrada[1],entrada[2],0,"");
			break;
		case"sll": ula.shift("sll", entrada[1], entrada[3], entrada[0]);
			break;
		case"srl": ula.shift("srl", entrada[1], entrada[3], entrada[0]);
			break;
		case"sra": ula.shift("sra", entrada[1], entrada[3], entrada[0]);
			break;
		case"sw":ula.data("sw", entrada[1], entrada[2], imediato, memory);
			break;
		case"sb":ula.data("sb", entrada[1], entrada[2], imediato, memory);
			break;
		case"lw":ula.data("lw", entrada[1], entrada[2], imediato, memory);
			break;
		case"lb":ula.data("lb", entrada[1], entrada[2], imediato, memory);
			break;
		case"lbu":ula.data("lbu", entrada[1], entrada[2], imediato, memory);
			break;
	  case "j" :  PC = ula.desvio("j",0,0,0,imediato, PC);   //Algo tipo isso para operaçoes que envolvem desvio incodicional , tá feito na ula, n sei se funciona
	   			   jump = true;						    //Para caso de desvio condicional o valor é calculado em relaçao a PC. Ex: PC + 5, PC - 10
	   			   break;
	   case "jr" :  PC = ula.desvio("jr",entrada[1],0,0,imediato, PC);   
		   jump = true;						    
		   break;
	   case "jal" :  PC = ula.desvio("jal",0,0,0,imediato, PC);   //usa imediato
		   jump = true;						 
		   break;
	   case "beq" :  PC = ula.desvio("beq",entrada[1],entrada[2],0,imediato, PC);   //usa imediato
		   jump = true;						   
		   break;
	   case "bne" :  PC = ula.desvio("bne",entrada[1],entrada[2],0,imediato, PC);  //usa imediato
		   jump = true;						   
		   break;
	   case "bltz" :  PC = ula.desvio("bltz",entrada[1],0,0,imediato, PC);   //usa imediato
		   jump = true;						    
		   break;
		}
		
		if(!jump) // só adiciona caso a instrução não seja de desvio
			PC++;
		
		resultado.append(ula.resultado());
		return resultado;
	}
}
