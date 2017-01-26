package br.ufrpe.arquitetura.minimips;

public class Mem {
	
	private int[] memory;
	//private int[] instmem;
	//private int memadd = 0;
	DecoderBinDec debindec = new DecoderBinDec();
	//private Instrucao[] memorias;
	
	
	public Mem(){
		//this.memorias = new Instrucao[56];
		this.memory = new int[10000];
		//this.instmem = new int[240];
		//this.memadd = 0;
		for(int i=0;i<this.memory.length;i++){	//cada slot de memória vai ter um decimal entre 0 e 255 (0000000 - 11111111)
			this.memory[i] = 0;					//esses slots podem ser transformados em strings de binarios usando: Integer.toBinaryString
		//	this.instmem[i] = 0;				//Olhar gets
		}
	}
	

	
	
	/*public void addInstmem(String instbin){
		String a = instbin;
		if(instbin != null && instbin.length() == 32){
		this.instmem[memadd] = Integer.parseInt(debindec.convertPositv(a.substring(0, 8)));
		memadd++;
		//System.out.println(this.instmem[0] + " " +this.instmem[1] + " " +this.instmem[2] + " " + Integer.toBinaryString(this.instmem[2]));
		this.instmem[memadd] = Integer.parseInt(debindec.convertPositv(a.substring(8, 16)));
		memadd++;
		//System.out.println(this.instmem[0] + " " +this.instmem[1] + " " +this.instmem[2] + " " + Integer.toBinaryString(this.instmem[2]));
		this.instmem[memadd] = Integer.parseInt(debindec.convertPositv(a.substring(16, 24)));
		memadd++;
		//System.out.println(this.instmem[0] + " " +this.instmem[1] + " " +this.instmem[2] + " " + Integer.toBinaryString(this.instmem[2]));
		this.instmem[memadd] = Integer.parseInt(debindec.convertPositv(a.substring(24, 32)));
		memadd++;
		//System.out.println(this.instmem[0] + " " +this.instmem[1] + " " +this.instmem[2] + " " + Integer.toBinaryString(this.instmem[2]));
	}}*/
	
	public void storebyte(String binbyte, int address){		//salva a string de um byte em binario no endereço address
		this.memory[address] = Integer.parseInt(debindec.convertPositv(binbyte));
		//System.out.println(binbyte);
		//System.out.println(address+" "+this.memory[address]);
		
	}
	
	public String loadbyte(int address){		//retorna string do byte no endereço address
		StringBuffer bin = new StringBuffer();
		for(int x=0; x< 8 - Integer.toBinaryString(this.memory[address]).length(); x++){
			bin.append("0");
		}
		bin.append(Integer.toBinaryString(this.memory[address]));
		if(Integer.toBinaryString(this.memory[address]).length() > 8){
			//System.out.println(Integer.toBinaryString(this.memory[address]));
			//System.out.println(address);
		}
		//System.out.println(bin.toString());
		return bin.toString();
	}
	
	public String loadbytezero(int address){		//retorna string do byte em word no endereço address
		//System.out.println(address);
		StringBuffer bin = new StringBuffer();
		StringBuffer rebin = new StringBuffer();
		//System.out.println(this.memory[address]);
		if(Integer.toBinaryString(this.memory[address]).length() == 8 && Integer.toBinaryString(this.memory[address]).charAt(0) == '1'){
			//é um numero negativo e sua word precisa ser completada com 1's
			bin.append("1");
			for(int i=0;i<24;i++)
				rebin.append("1");
		}
		else{
			bin.append("0");
			for(int i=0;i<24;i++)
				rebin.append("0");
		}
		for(int x=1; x< 8 - Integer.toBinaryString(this.memory[address]).length(); x++){
			bin.append("0");
		}
		bin.append(Integer.toBinaryString(this.memory[address]));
		rebin.append(bin);
		//System.out.println(rebin.toString());
		return rebin.toString();
	}
	
	public String loadbytezeroU(int address){		//retorna string do byte positivo/sem sinal em word no endereço address
		StringBuffer bin = new StringBuffer();
		StringBuffer rebin = new StringBuffer();
		for(int i=0;i<24;i++)
			rebin.append("0");
		if(Integer.toBinaryString(this.memory[address]).length() == 8 && Integer.toBinaryString(this.memory[address]).charAt(0) == '1'){
			bin.append("1");
			bin.append(Integer.toBinaryString(this.memory[address]).substring(1));
		}
		else{
			for(int x=0; x< 8 - Integer.toBinaryString(this.memory[address]).length(); x++){
				bin.append("0");
			}
			bin.append(Integer.toBinaryString(this.memory[address]));
		}
		rebin.append(bin);
		return rebin.toString();
	}
	
	
	/*public String getInstmemBin(int i){
		//System.out.println(this.instmem[0] + " " +this.instmem[1] + " " +this.instmem[2] + " " + Integer.toBinaryString(this.instmem[2]));
		StringBuffer bin = new StringBuffer();
		for(int x=0; x< 8 - Integer.toBinaryString(this.instmem[i]).length(); x++){
			bin.append(0);
		}
		bin.append(Integer.toBinaryString(this.instmem[i]));
		return bin.toString();
	}*/
	
	


}
