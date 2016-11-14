package br.ufrpe.arquitetura.minimips;

public class Instrucao {
	
	private String assembly;
	private String instrucao;
	private int[]entrada;
	private String imediato;
	
	public Instrucao(String assembly, String instrucao, int[] entrada, String imediato) {
		this.assembly = assembly;		//ex: "add $1, $2, $3"
		this.instrucao = instrucao;		//ex: "add"
		this.entrada = entrada;			//ex: 
		this.imediato = imediato;		//ex: "null"  (no caso desse add^) (caso normal: "0000000111111111" )
	}

	public String getAssembly() {
		return assembly;
	}

	public String getInstrucao() {
		return instrucao;
	}

	public int[] getEntrada() {
		//BUG
		//System.out.println(entrada[0]+" "+entrada[1]+" "+entrada[2]+" "+entrada[3]);
		return entrada;
	}

	public String getImediato() {
		return imediato;
	}
	
	
}
