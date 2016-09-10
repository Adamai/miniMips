package br.ufrpe.arquitetura.minimips;

public class DecoderBinAsse {
	
	public DecoderBinAsse(){
		
	}
	
	public String convert2(String entrada){
		String op = entrada.substring(26, 32);
		String inst= entrada.substring(0, 5);
		String resultado="";
		
		String opverif = verifop(op);
		String regverif = verifReg(entrada, opverif);
		
		
		resultado = opverif + " " +regverif;

		return resultado;
	}
	
	public String verifop(String op){
		String resultOP = "";
		
		switch(op){
		
		case"100000":resultOP = "ADD";
			break;
		}
		
		return resultOP;
	}
	
	
	public String verifReg(String entrada, String op){
		String r1= "";//dest
		String r2= "";//op1
		String r3= "";//op2
		String result="";
		
		switch(op){
		case"ADD": {
				   r1= convertBin(entrada.substring(16, 21));
				   r2= convertBin(entrada.substring(6, 11));
				   r3= convertBin(entrada.substring(11, 16));
				   result = r1 + ", "+ r2 + ", "+r3;
				   }
				   break;
		}
		
		
		return result;
	}
	
	
	public String convertBin(String bin){
		String result="";
		int BinarioDecimal = Integer.parseInt(bin, 2);
		result = "$"+BinarioDecimal;
		
		//switch(bin){
		//case"00000":result ="$0";
		//	break;
		//case"00001":result ="$1";
		//	break;
		//case"00010":result="$2";
		//	break:
		//case"00"
		//case"01000":result ="$8";
		//	break;
		//case"10000":result ="$16";
		//	break;
		//case"10001":result ="$17";
		//}
		
		return result;
	}
}