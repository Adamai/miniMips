package br.ufrpe.arquitetura.minimips;

public class Instrucao {
	
	private String assembly;
	private String instrucao;
	private int[]entrada;
	private String imediato;
	
	public Instrucao(String assembly, String instrucao, int[] entrada, String imediato) {
		super();
		this.assembly = assembly;
		this.instrucao = instrucao;
		this.entrada = entrada;
		this.imediato = imediato;
	}

	public String getAssembly() {
		return assembly;
	}

	public String getInstrucao() {
		return instrucao;
	}

	public int[] getEntrada() {
		return entrada;
	}

	public String getImediato() {
		return imediato;
	}
	
	
}
