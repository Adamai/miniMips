package br.ufrpe.arquitetura.minimips;

public class ULA {

	private BancoRegistrador registradores;
	private String resultado;
	
	public ULA(){
		this.registradores = new BancoRegistrador();
		resultado ="";
	}
	
	
	public String resultado(){
		for(int i = 0;i<32;i++){
			resultado+= "$"+i+ "="+ DecoderBinAsse.convertBin(registradores.getRegistrador(i))+";";
		}
		String s = resultado;
		resultado="";
		return s;
	}
	
	public String aritmetica(String op, int op1,int op2, int result, int imediato){
		
		switch(op){
			
		case"add" : registradores.setRegistrador(result,somador(registradores.getRegistrador(op1),registradores.getRegistrador(op2),op));
			break;
			
		
		}
		
		return resultado();
	}
	
	public String somador(String op1, String op2, String inst){
		String result ="";
		String carry = "0";
		
		if(inst == "add"){
			for(int i = 31;i>=0;i--){
				if(op1.charAt(i)=='0' && op2.charAt(i)=='0'&&carry=="0"){ // 0+0+0
					result= "0"+result;
				}
				else if(op1.charAt(i)=='1' && op2.charAt(i)=='0'&&carry=="0") //1+0+0
					result = "1" + result;
				else if(op1.charAt(i)=='0' && op2.charAt(i)=='1'&&carry=="0") // 0+1+0
					result = "1" + result;
				else if(op1.charAt(i)=='1' && op2.charAt(i)=='1'&&carry=="0"){ // 1+1+0
					result = "0"+result;
					carry = "1";
				}
				else if(op1.charAt(i)=='1' && op2.charAt(i)=='1'&&carry=="1"){ // 1+1+1
					result = "1"+result;
					carry = "1";
				}
				else if(op1.charAt(i)=='0' && op2.charAt(i)=='0'&&carry=="1"){ //0+0+1
					result="1"+result;
					carry="0";
				}
				else{
					result = "0"+result;
					carry = "1";
				}
					
			}
			
		}
		else{
			//subtração
		}
		
		return result;
		}
			
	}
	

