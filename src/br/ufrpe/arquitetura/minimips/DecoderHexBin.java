package br.ufrpe.arquitetura.minimips;

public class DecoderHexBin {

	public DecoderHexBin() {

	}
	
	public Boolean verify(String entrada){
		if(entrada != null && entrada.length() == 10){
			if(entrada.charAt(0)=='0' && entrada.charAt(1) == 'x'){
			String hex = entrada.substring(2);
			int k = 0;
			for(int i=0;i<8;i++){
				if(47 < hex.charAt(i) && hex.charAt(i)< 58 || 96 < hex.charAt(i) && hex.charAt(i) < 103)
					k++;	
			} 
			if(k==8) return true;
			else return false;}
			else return false;
		} else return false;
	}
	

	public String convert(String entrada) {
		String saida = "";
		for (int i = 2; i < 10; i++) {
			char x = entrada.charAt(i);
			switch (x) {
			case '0':
				saida = saida + "0000";

				break;
			case '1':
				saida = saida + "0001";

				break;
			case '2':
				saida += "0010";

				break;
			case '3':
				saida += "0011";

				break;
			case '4':
				saida += "0100";

				break;
			case '5':
				saida += "0101";

				break;
			case '6':
				saida += "0110";

				break;
			case '7':
				saida += "0111";
				break;
			case '8':
				saida += "1000";

				break;
			case '9':
				saida += "1001";

				break;
			case 'a':
				saida += "1010";

				break;
			case 'b':
				saida += "1011";

				break;
			case 'c':
				saida += "1100";

				break;
			case 'd':
				saida += "1101";

				break;
			case 'e':
				saida += "1110";

				break;
			case 'f':
				saida += "1111";

				break;
			}
		}
		return saida;
	}

}
