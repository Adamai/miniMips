package br.ufrpe.arquitetura.minimips;

import java.io.*;

public class TesteApp {
	public static void main(String[] args){
		try{
		
		FileReader entrada = new FileReader("miniMips/arquivos/entrada.txt");
		BufferedReader ler = new BufferedReader(entrada);
		FileWriter saida = new FileWriter("miniMips/arquivos/saida.txt");
		BufferedWriter escrever = new BufferedWriter(saida);
		DecoderHexBin dec1 = new DecoderHexBin();
		DecoderBinAsse dec2 = new DecoderBinAsse();
		Processador mips = new Processador();
		Mem memoria = new Mem();
		
		int counter = 0;
		
		String linhaEntrada = ler.readLine();
		while(linhaEntrada!=null){
			StringBuffer result = new StringBuffer();
			
			if(dec1.verify(linhaEntrada)){
			String binario = dec1.convert(linhaEntrada);
			//memoria.addInstmem(binario);				//adiciona a instrução em binario na memoria de instruções como decimais
			result = dec2.convert2(binario);
			Instrucao inst = new Instrucao(result.toString(), dec2.getInstrucao(), dec2.getEntrada() /*<- funcionando*/, dec2.getImediato());
			memoria.addInstrucao(inst);
			mips.addInst(inst);
			System.out.println(inst.getEntrada()[0]+" "+inst.getEntrada()[1]+" "+inst.getEntrada()[2]+" "+inst.getEntrada()[3]);
			linhaEntrada = ler.readLine();
			counter++;
			}
			if(dec1.verify(linhaEntrada)==false)
				result.append("Operacao nao identificada");
		}
		//memoria.testemerda();
		while(counter!= mips.getPC()){
			escrever.append(mips.getAssembly());
			escrever.newLine();
			StringBuffer registradores = mips.executar();
			escrever.append(registradores);
			escrever.newLine();
			
		}
		ler.close();
		
		escrever.close();
		
		//memoria.testemerda();
		//for(int y=0;y<56;y++){
		//System.out.print(memoria.getInstmemBin(y)+" ");
		//if(y%4==0 && y >0)
		//	System.out.print("\n");
		//}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}
	
	
}