package br.ufrpe.arquitetura.minimips;

public class Processador {

	private ULA ula;
	
	public Processador(){
		this.ula = new ULA();
	}
	
	public StringBuffer resultInst(String inst, int[] entrada, String imediato){
		StringBuffer resultado = new StringBuffer();
		/*entrada[0] = resultado
		 *entrada[1] = op1
		 *entrada[2] = op2
		 *entrada[3] = shift aritmetico/logico
		 */
		
		switch(inst){
		case"add" : ula.aritmetica("add",entrada[1],entrada[2],entrada[0],"");
					break;
		case"subu":ula.aritmetica("subu",entrada[1],entrada[2],entrada[0],"");
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
		
		}
		
		resultado.append(ula.resultado());
		return resultado;
	}
}
