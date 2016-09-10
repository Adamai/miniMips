package br.ufrpe.arquitetura.minimips;

public class DecoderHexBin {

	public DecoderHexBin() {

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
			case 'A':
				saida += "1010";

				break;
			case 'B':
				saida += "1011";

				break;
			case 'C':
				saida += "1100";

				break;
			case 'D':
				saida += "1101";

				break;
			case 'E':
				saida += "1110";

				break;
			case 'F':
				saida += "1111";

				break;
			}
		}

		return saida;
	}

}
