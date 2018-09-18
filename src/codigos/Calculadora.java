package codigos;

import java.util.ArrayList;
import java.util.List;

public class Calculadora {
	private ArrayList<Character> multiplicando_bin;
	private ArrayList<Character> multiplicador_bin;
	private ArrayList<Character> C_A = new ArrayList<Character>();
	private ArrayList<Character> resultado;
	int precisao;
	
	
	
	
	public Calculadora(int precisao){
		this.precisao = precisao;
		for(int i =0; i<precisao; i++)
			C_A.add(0,'0');
	}
	
	public ArrayList<Character> converter_para_binario(int numero){
		ArrayList<Character> numero_binario = new ArrayList<Character>();
		
		int quociente = Math.abs(numero);
		
		// impedirá a conversão caso o número inserido estoure o limite de 15 bits
		if(quociente >= Math.pow(2, precisao)) {
			System.out.println("O número " + numero + " excede o limite de 15 bits!");
			return null;
		}
		
		// dividirá o número sucessivamente por 2 e armazenará o resto na pilha
		for(int i = 0; i < precisao ; i++) {
			char c = Character.forDigit(quociente % 2,2);
			numero_binario.add(0, c);
			quociente /= 2;
		}
		
		// acrescentará o dígito de sinal à esquerda
		if(numero < 0) {
			numero_binario.add(0, '1');
		}
		else {
			numero_binario.add(0, '0');
		}
		return numero_binario;
	}
	
	private int soma(Character c0, Character c1) {
		/*
		if(c0 == '0' && c1 == '0') {
			return '0';
		}
		if(c0 == '0' && c1 == '1' || c0 == '1' && c1 == '0') {
			return '1';
		}
		if(c0 == '1' && c1 == '1') {
			return '2';
		}*/
		
		return Character.getNumericValue(c0)+Character.getNumericValue(c1);
	}
	
	private ArrayList<Character> adicao(ArrayList<Character> num1, ArrayList<Character> m){
		
		ArrayList<Character> menor = (num1.size() < m.size()) ? num1 : ((num1.size() > m.size()) ? m : null);
		ArrayList<Character> maior = (num1.size() > m.size()) ? num1 : ((num1.size() < m.size()) ? m : null);
		
		while(menor != null && menor.size() < maior.size()) {
			menor.add(0,'0');
		}
		
		ArrayList<Character> resultado_global = new ArrayList<Character>();
		int overflow = 0;
		for(int i = m.size()-1 ; i>=0; i--) {
			int resultado_local = soma(num1.get(i), m.get(i));
			if(overflow > 0) {
				resultado_local += 1;
				overflow--;
			}
			if(resultado_local > 1) {
				overflow++;
				if(resultado_local % 2 == 0) {
					resultado_global.add(0, '0');
				}
				else {
					resultado_global.add(0, '1');
				}
			}
			else {
				resultado_global.add(0, Character.forDigit(resultado_local, 2));
			}
		}
		return resultado_global;
	}
	
	private void deslocar(List<Character> Q) {
		Character a_para_q = C_A.get(C_A.size()-1);
		C_A.add(0,'0');
		Q.add(0, a_para_q);
		C_A.remove(C_A.size()-1);
		Q.remove(Q.size()-1);
	}
	
	public void multiplicar(int multiplicando, int multiplicador){
		if(multiplicador == 0 || multiplicando == 0) {
			return;
		}
		
		multiplicando_bin = converter_para_binario(multiplicando);
		multiplicador_bin = converter_para_binario(multiplicador);
		
		System.out.println(multiplicador_bin);
		System.out.println(multiplicando_bin);
		
		ArrayList<Character> M = new ArrayList<Character>();
		for(int i = 1; i< multiplicando_bin.size(); i++)
			M.add(multiplicando_bin.get(i));
		
		ArrayList<Character> Q = new ArrayList<Character>();
		for(int i = 1; i< multiplicador_bin.size(); i++)
			Q.add(multiplicador_bin.get(i));
		
		for(int i = 0 ; i<precisao; i++) {
			if(Q.get(Q.size()-1) == '1') {
				C_A = adicao(C_A, M);
			}
			else {
				deslocar(Q);
			}
		}
		
		
		for(int i=0;i<C_A.size(); i++)
			System.out.print(C_A.get(i));
		
		for(int i=0;i<Q.size(); i++)
			System.out.print(Q.get(i));
		
		
	}
	
}
