package br.ufrpe.arquitetura.minimips;

public class DecoderBinDec {
	public String convertPositv(String bin){
		String result = "";
		int BinarioDecimal = Integer.parseInt(bin, 2);
		result = Integer.toString(BinarioDecimal);
		return result;
	}
	
	public String convertBin(String bin){
		String result="";
		if(bin.charAt(0)=='1'){
			String resultado = "-";
			int k = 1;
			String lakon = "";
			int i = bin.length()-1;
			for( ; i>=0 && k == 1 ; i--){
				if(bin.charAt(i)=='1')
					k=0;
			}
			for(int j = 0; j<=i && k==0; j++){
				if(bin.charAt(j)=='0')
					lakon += "1";
				else if(bin.charAt(j)=='1')
					lakon += "0";
			}
          for(int j = i+1; j<bin.length(); j++){
            lakon += bin.charAt(j);
          }
			int BinarioDecimal = Integer.parseInt(lakon, 2);
			result = Integer.toString(BinarioDecimal);
			resultado += result;
			return resultado;
		}  else{
		int BinarioDecimal = Integer.parseInt(bin, 2);
		result = Integer.toString(BinarioDecimal);
		return result;
		}
	}

}
