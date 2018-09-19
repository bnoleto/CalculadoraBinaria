package codigos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

public class Calculadora {
	private ArrayList<Character> multiplicando_bin;
	private ArrayList<Character> multiplicador_bin;
	private ArrayList<Character> C_A = new ArrayList<Character>();
	private ArrayList<Character> resultado = new ArrayList<Character>();
	int precisao;
	boolean[] flag_mult = {true,true};
	
	private JLabel label_multis;
	private JLabel label_resultado;
	
	public Calculadora(int qtd_bits){
		this.precisao = qtd_bits-1;
	}
	
	public void set_labels(JLabel multis, JLabel resultado) {
		this.label_multis = multis;
		this.label_resultado = resultado;
	}
	
	public void alterar_limite_bits(int valor) {
		this.precisao = valor-1;
	}
	
	private void reset() {
		C_A.clear();
		resultado.clear();
		
		for(int i =0; i<precisao; i++) {
			C_A.add(0,'0');
			resultado.add('0');
		}
		C_A.add(0,'0');
		for(int i = 0; i<1000; i++)
			System.out.println("");
	}
	
	private ArrayList<Character> converter_para_binario(int numero, int flag){
		ArrayList<Character> numero_binario = new ArrayList<Character>();
		
		int quociente = Math.abs(numero);
		
		// impedirá a conversão caso o número inserido estoure o limite de 15 bits
		if(quociente >= Math.pow(2, precisao)) {
			flag_mult[flag] = false;
			String erro = "ERRO: O número " + numero + " excede o limite de "+ String.valueOf(precisao) + " bits!";
			System.out.println(erro);
			label_multis.setForeground(Color.RED);
			label_multis.setText(erro);
			for(int i = 0; i < precisao+1 ; i++) {
				
				numero_binario.add(0, '0');
			}
			return numero_binario;
		} else {
			flag_mult[flag] = true;
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
		System.out.print(C_A);
		System.out.print(Q);
		Character a_para_q = C_A.get(C_A.size()-1);
		C_A.add(0,'0');
		Q.add(0, a_para_q);
		C_A.remove(C_A.size()-1);
		Q.remove(Q.size()-1);
	}
	
	private Character encontrar_bit_sinal(int multiplicando, int multiplicador) {
		if(multiplicando < 0 && multiplicador > 0 || multiplicando > 0 && multiplicador < 0) {
			return '1';
		}
		else {
			return '0';
		}
	}
	
	private String converter_para_string(ArrayList<Character> vetor) {
		String string = "";
		
		for(int i = 0; i < vetor.size(); i++) {
			string += vetor.get(i);
		}
		
		return string;
	}
	
	public String multiplicar(int multiplicando, int multiplicador){
		
		reset();
		/*
		// caso 1 dos números seja zero
		if(multiplicador == 0 || multiplicando == 0) {
			resultado.add(0, '0');	// bit de sinal
			return converter_para_string(resultado);
		}*/
		
		multiplicando_bin = converter_para_binario(multiplicando, 0);
		multiplicador_bin = converter_para_binario(multiplicador, 1);
		
		if(!flag_mult[0] && !flag_mult[1]) {
			label_multis.setForeground(Color.RED);
			label_multis.setText("Os números inseridos excedem o limite de "+ String.valueOf(precisao) + " bits!");
		} else if (!flag_mult[0]) {
			label_multis.setForeground(Color.RED);
			String erro = "ERRO: O número " + multiplicando + " excede o limite de "+ String.valueOf(precisao) + " bits!";
			label_multis.setText(erro);
		} else if (!flag_mult[1]) {
			label_multis.setForeground(Color.RED);
			String erro = "ERRO: O número " + multiplicador + " excede o limite de "+ String.valueOf(precisao) + " bits!";
			label_multis.setText(erro);
		} else {
			label_multis.setForeground(Color.BLACK);
			label_multis.setText("Ok!");
		}
		
		// impedirá de realizar os cálculos caso algum dos valores extrapole o limite de bits
		if(multiplicador_bin == null || multiplicando_bin == null) {
			return "ERRO: O número inserido excede o limite de "+ String.valueOf(precisao) + " bits!";
		}
		
		ArrayList<Character> M = new ArrayList<Character>();
		for(int i = 1; i< multiplicando_bin.size(); i++)
			M.add(multiplicando_bin.get(i));
		
		ArrayList<Character> Q = new ArrayList<Character>();
		for(int i = 1; i< multiplicador_bin.size(); i++)
			Q.add(multiplicador_bin.get(i));
		
		System.out.println("[C,A][Q][M]");
		
		// iterações principais para a multiplicação
		for(int i = 0 ; i<precisao; i++) {
			if(Q.get(Q.size()-1) == '1') {
				C_A = adicao(C_A, M);
				deslocar(Q);
			}
			else {
				deslocar(Q);
			}
			System.out.println(M);
		}
		
		resultado = new ArrayList<Character>();
		
		for(int i=0;i<C_A.size(); i++) {
			resultado.add(C_A.get(i));
		}
		
		for(int i=0;i<Q.size(); i++) {
			resultado.add(Q.get(i));
		}
		
		// adicionará o bit de sinal no bit mais à esquerda;
		resultado.add(0, encontrar_bit_sinal(multiplicando, multiplicador));
		
		// irá desconsiderar os zeros mais à esquerda (com exceção do bit de sinal) para ficar em conformidade com a qtd de bits
		while(resultado.get(1) == '0' && resultado.size()>precisao+1) {
			resultado.remove(1);
		}
		
		if(resultado.size() > precisao+1) {
			label_resultado.setForeground(Color.RED);
			System.out.println("ATENÇÃO: O resultado excede o limite de "+ (precisao+1) + " bits!");
			label_resultado.setText("ATENÇÃO: O resultado excede o limite de "+ (precisao+1) + " bits!");
		} else {
			label_resultado.setForeground(Color.BLUE);
			label_resultado.setText(converter_para_decimal(resultado) +  " (base 10)");
		}
		
		return converter_para_string(resultado);
	}
	
	private String converter_para_decimal(ArrayList<Character> resultado2) {
		int resultado = 0;
		for(int i = 1; i < resultado2.size(); i++) {
			resultado += Integer.parseInt(resultado2.get(i).toString())*Math.pow(2, resultado2.size()-1-i);
		}
		if(resultado2.get(0) == '1') {
			resultado *= -1; 
		}
		return String.valueOf(resultado);
	}

	public String get_binario(int numero){
		String valor = "";
		
		ArrayList<Character> selecionado = null;
		
		if(numero == 0) {
			selecionado = multiplicando_bin;
		}
		if(numero == 1) {
			selecionado = multiplicador_bin;
		}
		
		for(int i = 0; i<selecionado.size(); i++) {
			valor += selecionado.get(i);
		}
		return valor;
	}
	
}
