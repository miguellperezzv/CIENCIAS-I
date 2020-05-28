import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JTextField;


public class Principal extends JFrame implements ActionListener {

	JLabel info1 = new JLabel("Ingrese la expresion en infijo ");
	JTextField txtFinfijo = new JTextField();
	JButton btnEnviar = new JButton("Enviar");
	static int matriz[][]= {{1,1,0,0,0,0,1},{1,1,0,0,0,0,1},{1,1,1,1,0,0,1},{1,1,1,1,0,0,1},{1,1,1,1,1,0,1},{0,0,0,0,0,0,0}};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
		Principal m = new Principal();
		m.setSize(700, 500);
		m.setLayout(null);
		m.setVisible(true);

	}

	Principal() {
		//System.out.print("hola");
		Container c = getContentPane();
		c.add(info1);
		info1.setBounds(50, 10, 400, 100);
		c.add(txtFinfijo);
		txtFinfijo.setBounds(200, 50, 100, 30);
		c.add(btnEnviar);
		btnEnviar.setBounds(600, 100, 50, 40);
		btnEnviar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String infijo = txtFinfijo.getText();
		ArrayList<String> arrayinfijo = LlenarArray(infijo);
		
	
		if(arrayinfijo.get(0).equalsIgnoreCase("f")) {
			System.out.println("expresion invalida");
		}
		else {
			System.out.print("EN INFIJO: ");
			for(int i=0;i<arrayinfijo.size();i++) {
				System.out.print(arrayinfijo.get(i)+ " ");
			}
		}
		
		
		
		ArrayList<String> arrayposfijo = conversionPosfijo(arrayinfijo);
		
		/**
		 * COMPROBAR SI QUED� EN POSFIJO
		 * **/
		System.out.print("EN POSFIJO: ");
		for(int i=0;i<arrayposfijo.size();i++) { 
			System.out.print(arrayposfijo.get(i)+ " ");
		}
		
		/**
		 * Calculando desde posfijo
		 * **/
		int resPosfijo=calcular(arrayposfijo);
		System.out.println(resPosfijo);
	}

	
	private ArrayList<String> LlenarArray(String infijo) {
		ArrayList<String> array = new ArrayList<String>();
		int c = 0;
		boolean validez=true;
		while(c<infijo.length()) {
			if(infijo.charAt(c)<'0'|| infijo.charAt(c)>'9') {
				if(infijo.charAt(c)== '+'||infijo.charAt(c)=='-'||infijo.charAt(c)=='*'||infijo.charAt(c)=='/'||infijo.charAt(c)=='^'||infijo.charAt(c)=='(' ||infijo.charAt(c)==')') {
					//expresion valida
					validez=true;
					array.add( Character.toString(infijo.charAt(c)));
				}
				else {
					validez=false;
				}
			}
			else {
				array.add(Character.toString(infijo.charAt(c)));
			}
			
			c++;
		}
		if(validez==false) {
			array.set(0, "f");
		}
		return array;
	}
	private boolean confirmarCaracter (String string) {
		boolean validez=true;
		if(string.charAt(0)>'0'&& string.charAt(0)<'9') {
			validez=true;
		}
		else {
			validez=false;
		}
		return validez;
	}
	private ArrayList<String> conversionPosfijo(ArrayList<String> arrayinfijo){
		int c=0;
		Stack<String> pila= new Stack<String>();
		ArrayList<String> arrayposfijo = new ArrayList<String>();
		while(c<arrayinfijo.size()) {
			String temp=arrayinfijo.get(c);
			if(confirmarCaracter(temp)==false) {
				if(pila.isEmpty()) {
					pila.push(temp);
				}
				else {
					if(jerarquia(pila.peek(),temp)==1) {
						arrayposfijo.add(pila.pop());
						pila.push(temp);
						
					}
					else { //si no es 1
						pila.push(temp);
					}
				} 
			} 
			
			else {
				arrayposfijo.add(temp);
			}
			
			
			//cierre if confirmar caracter
			
			c++;//cierre else confirmar caracter
		}//cierre del while
		return arrayposfijo;
	}
	private int calcular(ArrayList<String> posfijo){
		int c=0;
		int resultado=0;
		Stack<Integer> pila = new Stack<Integer>();
		while(c<posfijo.size()) {
			String temp=posfijo.get(c);
			if(confirmarCaracter(temp)==true) {
				pila.push(Integer.parseInt(temp));
			}
			
			else {
				int a=pila.pop();
				int b=pila.pop();
				resultado= operar(a,b,temp);
				pila.push(c);
			}
		c++;	
		}
		return resultado;
		
	}
	private int operar(int a, int b, String temp) {
		int resultado=0;
		switch(temp) {
		case "+":
			resultado=b+a;
			break;
		
		case "-":
			resultado=b-a;
			break;
			
		case "*":
			resultado=b*a;
			break;
			
		case "/":
			resultado=b/a;
			break;
			
		case "^":
			resultado=(int) Math.pow(b, a);
			break;
			
		
		}
		return resultado;
	}

	private int jerarquia (String pString, String aString) { 
		int i=0;
		int j=0;
		switch(pString) {
		case "+":
			i=0;
			break;
		
		case "-":
			i=1;
			break;
			
		case "*":
			i=2;
			break;
			
		case "/":
			i=3;
			break;
			
		case "^":
			i=4;
			break;
			
		case "(":
			i=5;
			break;

		}
		
		switch(aString) {
		case "+":
			j=0;
			break;
		
		case "-":
			j=1;
			break;
			
		case "*":
			j=2;
			break;
			
		case "/":
			j=3;
			break;
			
		case "^":
			j=4;
			break;
			
		case "(":
			j=5;
			break;
			
		case ")":
			j=6;
			break;

		}
		
		return matriz[i][j];
		
	}
	private ArrayList<String> crearArray(String infijo) {
		ArrayList<String> array = new ArrayList<String>();
		int c = 0;
		while(c<infijo.length()) {
			if(infijo.charAt(c)>='0'&& infijo.charAt(c)<='9') {
				System.out.println(" es numero");
				String temp= Character.toString(infijo.charAt(c));
				
				boolean salida=false;
				while(salida==false) {
					if((c++)>infijo.length()) {
						salida=true;
						System.out.println("saliendo del while interno porque "+ c++ +"es mayor que "+infijo.length());
					}
					else {
						c++;
						if(infijo.charAt(c)>='0'&& infijo.charAt(c)<='9') {
							temp+=infijo.charAt(c);
						}
						else {
							array.add(temp);
							c--;
						}
					}
				}
				
				
			}
			else {
				array.add( Character.toString(infijo.charAt(c)));
				c++;
			}
			
		}
		return array;

	}

}
