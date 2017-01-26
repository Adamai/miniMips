package br.ufrpe.arquitetura.minimips;

public class ULA {

	private BancoRegistrador registradores;
	private String resultado;
	DecoderBinAsse binAsse = new DecoderBinAsse();
	DecoderBinDec bindec = new DecoderBinDec();
	
	public ULA(){
		this.registradores = new BancoRegistrador();
		resultado ="";
	}
	
	
	public String resultado(){
		for(int i = 0;i<32;i++){
			if(i==31){int ka = 4*Integer.parseInt(bindec.convertBin(registradores.getRegistrador(i)));
				resultado+= "$"+i+ "="+ Integer.toString(ka)+";";
			} else
			resultado+= "$"+i+ "="+ bindec.convertBin(registradores.getRegistrador(i))+";";
			//System.out.println(registradores.getRegistrador(i));
		}
		String s = resultado;
		resultado="";
		return s;
	}
	
	public void aritmetica(String op, int op1,int op2, int result, String imediato){
		
		switch(op){
			
		case"add" : registradores.setRegistrador(result,somador(registradores.getRegistrador(op1),registradores.getRegistrador(op2),op));
			break;
		case"subu" : registradores.setRegistrador(result, somador(registradores.getRegistrador(op1),registradores.getRegistrador(op2),op));
			break;
		case"addi": registradores.setRegistrador(result, somador(registradores.getRegistrador(op1),imediato,op));
			break;
		case"mult": multiplicador("mult",registradores.getRegistrador(op1),registradores.getRegistrador(op2));
			break;
		case"addu" : registradores.setRegistrador(result,somador(registradores.getRegistrador(op1),registradores.getRegistrador(op2),op));
			break;
		case"sub" : registradores.setRegistrador(result, somador(registradores.getRegistrador(op1),registradores.getRegistrador(op2),op));
			break;
		case"addiu": registradores.setRegistrador(result, somador(registradores.getRegistrador(op1),imediato,op));
			break;
		case"multu": multiplicador("multu",registradores.getRegistrador(op1),registradores.getRegistrador(op2));
			break;
		case"div": multiplicador("div",registradores.getRegistrador(op1),registradores.getRegistrador(op2));
			break;
		case"divu": multiplicador("divu",registradores.getRegistrador(op1),registradores.getRegistrador(op2));
			break;
		case"mfhi": registradores.setRegistrador(result, registradores.getHi());
			break;
		case"mflo": registradores.setRegistrador(result, registradores.getLo());
			break;
		}
		
	}
	
	public int desvio(String op, int op1,int op2, int result, String imediato, int PC){
		int desvio = 0;
		//int kek = Integer.parseInt(bindec.convertBin(imediato))/4;
		switch(op){
		case"j": desvio = Integer.parseInt(bindec.convertBin(imediato));
			break;
		case"jr": desvio = Integer.parseInt(bindec.convertPositv(registradores.getRegistrador(op1)));
			break;
		case"jal": desvio = Integer.parseInt(bindec.convertBin(imediato));
					StringBuffer bin = new StringBuffer();
					String a = Integer.toBinaryString(PC+1);
					for(int i=0; i<32-a.length();i++)
						bin.append("0");
					bin.append(a);
					registradores.setRegistrador(31, bin.toString());
			break;
		case"beq": desvio = Integer.parseInt(bindec.convertBin(imediato));
					if(registradores.getRegistrador(op1)==registradores.getRegistrador(op2))
						desvio = PC +1+ Integer.parseInt(bindec.convertBin(imediato));
					else
						desvio = PC+1;
			break;
		case"bne": desvio = Integer.parseInt(bindec.convertBin(imediato));
					if(registradores.getRegistrador(op1)!=registradores.getRegistrador(op2))
						desvio = PC+ 1 + Integer.parseInt(bindec.convertBin(imediato));
					else
						desvio = PC+1;
			break;
		case"bltz": desvio = Integer.parseInt(bindec.convertBin(imediato));
				if(Integer.parseInt(bindec.convertBin(registradores.getRegistrador(op1))) < 0)
					desvio = PC +1+ Integer.parseInt(bindec.convertBin(imediato));
				else
					desvio = PC+1;
			break;
			
		}
		
		return desvio;
	}
			
	public void logica(String op, int op1, int op2, int result, String imediato){
		switch(op){
		
		case"and": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), registradores.getRegistrador(op2+1), op));
				break;
		case"or": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), registradores.getRegistrador(op2+1), op));
				break;
		case"nor": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), registradores.getRegistrador(op2+1), op));
				break;
		case"xor": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), registradores.getRegistrador(op2+1), op));
				break;
		case"xori": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), imediato, op));
				break;
		case"ori": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), imediato, op));
				break;
		case"andi": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), imediato, op));
				break;
		case"slt": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), registradores.getRegistrador(op2+1), op));
			break;
		case"sltu": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), registradores.getRegistrador(op2+1), op));
			break;
		case"slti": registradores.setRegistrador(result, binbit(registradores.getRegistrador(op1+1), imediato, op));
			break;
		
		}
	}
	
	public void shift(String op, int op1, int opsp, int result){
		switch(op){
		case"sll": registradores.setRegistrador(result, shiftbit(registradores.getRegistrador(op1), registradores.getRegistrador(opsp), op));
			break;
		case"srl": registradores.setRegistrador(result, shiftbit(registradores.getRegistrador(op1), registradores.getRegistrador(opsp), op));
			break;
		case"sra": registradores.setRegistrador(result, shiftbit(registradores.getRegistrador(op1), registradores.getRegistrador(opsp), op));
		}
	}
	
	public void data(String op, int op1, int op2, String imediato, Mem memory){
		int address = Integer.parseInt(bindec.convertBin(registradores.getRegistrador(op2))) + Integer.parseInt(bindec.convertBin(imediato));
				//System.out.println(address);
		switch(op){
		case"sw":	//sw $t1,-100($t2)   Store word : Armazena a palavra (4 bytes) do registrador $t1 no endereço de memória
					//representado pela soma do endereço base ($t2) + deslocamento (nesse exemplo, -100)
					String wordsave = registradores.getRegistrador(op1);
					System.out.println(wordsave);
					memory.storebyte(wordsave.substring(0,8), address);
					memory.storebyte(wordsave.substring(8, 16), address+1);
					memory.storebyte(wordsave.substring(16, 24), address+2);
					memory.storebyte(wordsave.substring(24, 32), address+3);
			
			
			
			/*int end; StringBuffer adder = new StringBuffer();
					for(int i=0;i<registradores.getRegistrador(op1).length();i++){
						if(registradores.getRegistrador(op1).charAt(i)=='1'){
							for( ;i<registradores.getRegistrador(op1).length();i=i+8){
							end = i + 8;
							if(end >= registradores.getRegistrador(op1).length())
								end = 32;
							if(registradores.getRegistrador(op1).substring(i, end).length()<8){
									adder.append(registradores.getRegistrador(op1).substring(i, end));
									for(int x=0;x<8 - registradores.getRegistrador(op1).substring(i, end).length(); x++){
										adder.append("0");
									}}else if (registradores.getRegistrador(op1).substring(i, end).length()==8)
							adder.append(registradores.getRegistrador(op1).substring(i, end));
							//System.out.println(registradores.getRegistrador(op1).substring(i, end));
							memory.storebyte(adder.toString(), address);
							System.out.println("pipoca");
							address++;
							}
						}
					}*/
					
					/*memory.storebyte(registradores.getRegistrador(op1).substring(0, 8), address);
					memory.storebyte(registradores.getRegistrador(op1).substring(8, 16), address+1);
					memory.storebyte(registradores.getRegistrador(op1).substring(16, 24), address+2);
					memory.storebyte(registradores.getRegistrador(op1).substring(24, 32), address+3);*/
			break;
		case"sb":	 memory.storebyte(registradores.getRegistrador(op1).substring(24, 32), address);
					//System.out.println(registradores.getRegistrador(op1).substring(24, 32));
					//System.out.println(address);
			break;
		case"lw":	 registradores.setRegistrador(op1, memory.loadbyte(address)+memory.loadbyte(address+1)+memory.loadbyte(address+2)+memory.loadbyte(address+3));
			//System.out.print(address);
			break;
		case"lb":	 registradores.setRegistrador(op1, memory.loadbytezero(address));
		//System.out.println(memory.loadbytezero(address));
			break;
		case"lbu":	registradores.setRegistrador(op1, memory.loadbytezeroU(address));
			break;
		}
	}
	
	//public void datatrans(String op, int op1, int op2, )
	
	public String shiftbit(String op1, String opsp, String inst){
		StringBuffer bin = new StringBuffer();
		if(inst == "sll"){
			int a = Integer.parseInt(bindec.convertBin(op1));
			int b = Integer.parseInt(bindec.convertBin(opsp));
			int c = a << b;
			String result = Integer.toBinaryString(c);
			for(int i=0; i<op1.length() - result.length(); i++){
				bin.append("0");
			}
			bin.append(result);
		}
		if(inst == "sra"){
			int a = Integer.parseInt(bindec.convertBin(op1));
			int b = Integer.parseInt(bindec.convertBin(opsp));
			int c = a >> b;
			String result = Integer.toBinaryString(c);
			for(int i=0; i<op1.length() - result.length(); i++){
				bin.append("0");
			}
			bin.append(result);
		}
		if(inst == "srl"){
			int a = Integer.parseInt(bindec.convertBin(op1));
			int b = Integer.parseInt(bindec.convertBin(opsp));
			int c = a >>> b;
			String result = Integer.toBinaryString(c);
			for(int i=0; i<op1.length() - result.length(); i++){
				bin.append("0");
			}
			bin.append(result);
		}
		return bin.toString();
	}
	
	public String binbit(String op1, String op2, String inst){
		StringBuffer bin = new StringBuffer();
		if (inst == "and" || inst == "andi"){
			int a = Integer.parseInt(bindec.convertBin(op1));
			int b = Integer.parseInt(bindec.convertBin(op2));
			int c = a & b;
			String result = Integer.toBinaryString(c);
			for(int i=0; i<op1.length() - result.length(); i++){
				bin.append("0");
			}
			bin.append(result);
		}
		if (inst == "or" || inst == "ori"){
			int a = Integer.parseInt(bindec.convertBin(op1));
			int b = Integer.parseInt(bindec.convertBin(op2));
			int c = a | b;
			String result = Integer.toBinaryString(c);
			for(int i=0; i<op1.length() - result.length(); i++){
				bin.append("0");
			}
			bin.append(result);
		}
		if (inst == "xor" || inst == "xori"){
			int a = Integer.parseInt(bindec.convertBin(op1));
			int b = Integer.parseInt(bindec.convertBin(op2));
			int c = a ^ b;
			String result = Integer.toBinaryString(c);
			for(int i=0; i<op1.length() - result.length(); i++){
				bin.append("0");
			}
			bin.append(result);
		}
		if (inst == "nor"){
			int a = Integer.parseInt(bindec.convertBin(op1));
			int b = Integer.parseInt(bindec.convertBin(op2));
			int c = ~(a | b);
			String result = Integer.toBinaryString(c);
			for(int i=0; i<op1.length() - result.length(); i++){
				bin.append("0");
			}
			bin.append(result);
		}
		if (inst == "slt" ||inst == "slti"){ //observar sltunsigned
			int a = Integer.parseInt(bindec.convertBin(op1));
			int b = Integer.parseInt(bindec.convertBin(op2));
			if( a < b )
				bin.append("0000000000000001");
			else
				bin.append("0000000000000000");
		}
		if (inst == "sltu"){
			int a = Integer.parseInt(bindec.convertPositv(op1));
			int b = Integer.parseInt(bindec.convertPositv(op2));
			if( a < b )
				bin.append("0000000000000001");
			else
				bin.append("0000000000000000");
		}
		return bin.toString();
	}
	
	public String somador(String op1, String op2, String inst){
		String result ="";
		int a = Integer.parseInt(bindec.convertBin(op1));
		int b = Integer.parseInt(bindec.convertBin(op2));
		int c = 0;
		StringBuffer bin = new StringBuffer();
		
		if(inst =="add" || inst == "addi" || inst == "addu" || inst == "addiu")
			 c = a+b;
		else
			 c = a-b;
		
		result = Integer.toBinaryString(c);
		for(int i = 1;i<=32-result.length();i++){
			if(c>=0)
				bin.append("0");
			else
				bin.append("1");
		}
			bin.append(result);
		

		return bin.toString();
		}
	
	
	public void multiplicador(String inst, String op1, String op2){
		int a = Integer.parseInt(bindec.convertBin(op1));
		int b = Integer.parseInt(bindec.convertBin(op2));
		int c = 5;
		String result ="";
		StringBuffer bin = new StringBuffer();
		
		if(inst == "mult" || inst == "multu"){
			c = a*b;
			result = Integer.toBinaryString(c);
		}
		if(inst == "div" || inst == "divu"){
			c = a/b;
			result = Integer.toBinaryString(c);
		}
			for(int i = 1;i<=64-result.length();i++){
				if(c>0)
					bin.append("0");
				else
					bin.append("1");
		}
			bin.append(result);
			result = bin.toString();
			registradores.setHi(result.substring(0, 32));
			registradores.setLo(result.substring(32,64));
	}
			
	}
	

