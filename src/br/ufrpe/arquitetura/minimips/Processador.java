package br.ufrpe.arquitetura.minimips;
import java.util.ArrayList;

public class Processador {

	private ULA ula;
	private ArrayList<Instrucao>instrucoes = new ArrayList<Instrucao>();
	private int PC = 0;
	
	public Processador(){
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
		return instrucoes.get(PC).getAssembly();
	}
	
	public StringBuffer executar(){
		//BUG
		//int[] a =instrucoes.get(0).getEntrada();
		//System.out.println(a[0]);
		//System.out.println(a[0]+" "+a[1]+" "+a[2]);
		return resultInst(instrucoes.get(PC).getInstrucao(),instrucoes.get(PC).getEntrada(),instrucoes.get(PC).getImediato());
	}
	
	public void addInst(Instrucao i){
		//int[] a = i.getEntrada();
		//System.out.println(a[0]+" "+a[1]+" "+a[2]);
		//funcionando
		instrucoes.add(i);
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
		case"addi" : ula.aritmetica("addi",entrada[0],0,entrada[1], imediato);
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
	 //case "j" : ula.desvio("j"
		}
		
		if(!jump) // s� adiciona caso a instru��o n�o seja de desvio
			PC++;
		
		resultado.append(ula.resultado());
		return resultado;
	}
}
