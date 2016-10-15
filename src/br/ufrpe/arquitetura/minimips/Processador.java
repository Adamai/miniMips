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
		}
		
		resultado.append(ula.resultado());
		return resultado;
	}
}
