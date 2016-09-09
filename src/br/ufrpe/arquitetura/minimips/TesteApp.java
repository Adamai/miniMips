package br.ufrpe.arquitetura.minimips;

import java.io.*;

public class TesteApp {
	public static void main(String[] args){
		String a = "02114020";
		DecoderHexBin dec = new DecoderHexBin();
		
		String binario = dec.convert(a);
		System.out.println(binario);
		
		DecoderBinAsse dec2 = new DecoderBinAsse();
		
		String assembley = dec2.convert2(binario);
		System.out.println(assembley);
		
		
		String teste = "batata";
		
		System.out.println(teste.substring(1, 3));
	}
	
	
}
