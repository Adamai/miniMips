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
		
		
		
		String linhaEntrada = ler.readLine();
		while(linhaEntrada!=null){
			String result = "";
			if(dec1.verify(linhaEntrada)){
			String binario = dec1.convert(linhaEntrada);
			result = dec2.convert2(binario);
			}
			if(dec1.verify(linhaEntrada)==false)
				result = "Operacao nao identificada";
			escrever.append(result);
			escrever.newLine();
			linhaEntrada = ler.readLine();
		}
		ler.close();
		
		escrever.close();
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		
	}
	
	
}