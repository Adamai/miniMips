package br.ufrpe.arquitetura.minimips;

public class Mem {
	
	private int[] memory;
	private int[] instmem;
	private int memadd = 0;
	DecoderBinDec debindec = new DecoderBinDec();
	private Instrucao[] memorias;
	
	
	public Mem(){
		this.memorias = new Instrucao[56];
		this.memory = new int[56];
		this.instmem = new int[56];
		for(int i=0;i<this.memory.length;i++){	//cada slot de memória vai ter um decimal entre 0 e 255 (0000000 - 11111111)
			this.memory[i] = 0;					//esses slots podem ser transformados em strings de binarios usando: Integer.toBinaryString
			this.instmem[i] = 0;				//Olhar gets
		}
	}
	
	public void addInstrucao(Instrucao MERDA){
		this.memorias[memadd] = MERDA;
		//System.out.println(this.memorias[memadd].getEntrada()[0]+" "+this.memorias[memadd].getEntrada()[1]+" "+this.memorias[memadd].getEntrada()[2]);
		memadd++;
	}
	
	
	public void addInstmem(String instbin){
		this.instmem[memadd] = Integer.parseInt(debindec.convertPositv(instbin.substring(0, 8)));
		memadd++;
		//System.out.println(this.instmem[0] + " " +this.instmem[1] + " " +this.instmem[2] + " " + Integer.toBinaryString(this.instmem[2]));
		this.instmem[memadd] = Integer.parseInt(debindec.convertPositv(instbin.substring(8, 16)));
		memadd++;
		//System.out.println(this.instmem[0] + " " +this.instmem[1] + " " +this.instmem[2] + " " + Integer.toBinaryString(this.instmem[2]));
		this.instmem[memadd] = Integer.parseInt(debindec.convertPositv(instbin.substring(16, 24)));
		memadd++;
		//System.out.println(this.instmem[0] + " " +this.instmem[1] + " " +this.instmem[2] + " " + Integer.toBinaryString(this.instmem[2]));
		this.instmem[memadd] = Integer.parseInt(debindec.convertPositv(instbin.substring(24, 32)));
		memadd++;
		//System.out.println(this.instmem[0] + " " +this.instmem[1] + " " +this.instmem[2] + " " + Integer.toBinaryString(this.instmem[2]));
	}
	
	public String getInstmemBin(int i){
		//System.out.println(this.instmem[0] + " " +this.instmem[1] + " " +this.instmem[2] + " " + Integer.toBinaryString(this.instmem[2]));
		StringBuffer bin = new StringBuffer();
		for(int x=0; x< 8 - Integer.toBinaryString(this.instmem[i]).length(); x++){
			bin.append(0);
		}
		bin.append(Integer.toBinaryString(this.instmem[i]));
		return bin.toString();
	}
	
	public void testemerda(){
		System.out.println(this.memorias[0].getEntrada()[1]);
	}

}
