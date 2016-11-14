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
		
		int counter = 0;
		
		String linhaEntrada = ler.readLine();
		while(linhaEntrada!=null){
			StringBuffer result = new StringBuffer();
			
			if(dec1.verify(linhaEntrada)){
			String binario = dec1.convert(linhaEntrada);
			result = dec2.convert2(binario);
			Instrucao inst = new Instrucao(result.toString(), dec2.getInstrucao(), dec2.getEntrada() /*<- funcionando*/, dec2.getImediato());
			mips.addInst(inst);
			linhaEntrada = ler.readLine();
			counter++;
			}
			if(dec1.verify(linhaEntrada)==false)
				result.append("Operacao nao identificada");
		}
		while(counter!= mips.getPC()){
			escrever.append(mips.getAssembly());
			escrever.newLine();
			StringBuffer registradores = mips.executar();
			escrever.append(registradores);
			escrever.newLine();
			
		}
		ler.close();
		
		escrever.close();
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