package Application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Entities.Circle;
import Entities.Client;
import Entities.PrintService;
import Entities.Product;
import Entities.Rectangle;
import Entities.Shape;
import Services.CalculationService;


public class ProgramGenerics {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);

		
		System.out.println("Programa que leia uma quantidade N, e depois N números inteiros");
		System.out.println("Imprima os números de forma organizada. Informe primeiro valor digitado");
		System.out.println();
		
		PrintService<Integer> ps = new PrintService<>();
		
		System.out.print("Quantos Valores? ");
		int n = sc.nextInt();
		
		for (int i = 1; i <= n; i++) { 
			System.out.print("Entre com "+ i + "º Valor:  ");
			Integer value = sc.nextInt();
			ps.addValue(value);
		}

		ps.print();
		Integer y = ps.first();
		System.out.println("First: " + y);
		
		System.out.println();
		System.out.println("=========================================================================");
		System.out.println();
		
		System.out.println("Uma empresa de consultoria deseja avaliar a performance de produtos,funcionários,");
		System.out.println("dentre outras coisas. Um dos cálculos precisa é encontrar o maior dentre um conjunto,"); 
		System.out.println("de elementos. Fazer um programa que leia um	conjunto de produtos a partir de um arquivo,");
		System.out.println("(EX. DADOS: Computer,890.50) depois mostre o mais caro deles.");
		System.out.println();
		
		List<Product> list = new ArrayList<>();

		String path = "C:\\temp\\in.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Product(fields[0], Double.parseDouble(fields[1]))); 
				System.out.println(line);
				line = br.readLine();
			}
			
			Product x = CalculationService.max(list);
			System.out.println("Most expensive:");
			System.out.println(x);

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} 

		System.out.println();
		System.out.println("=========================================================================");
		System.out.println();
		
		System.out.println();
		System.out.println("Com tipos curinga podemos fazer métodos que recebem um genérico de 'qualquer tipo'");
		System.out.println();
		
		List<Integer> myInteiros = Arrays.asList(5, 2, 10);
		printList(myInteiros);		
		
		// Não é Possivel adiconar item a tipo coringa
		//List<?> list = new ArrayList<Integer>();
		//list.add(3); // erro de compilação
		
		
		System.out.println();
		System.out.println("=========================================================================");
		System.out.println();
		
		System.out.println();
		System.out.println("(Princípio GET/PUT)");
		System.out.println("Fazer um método para retornar a soma das áreas de uma lista de figuras."); 
		System.out.println();
		
		List<Shape> myShapes = new ArrayList<>();
		myShapes.add(new Rectangle(3.0, 2.0));
		myShapes.add(new Circle(2.0));
		
		List<Circle> myCircles = new ArrayList<>();
		myCircles.add(new Circle(2.0));
		myCircles.add(new Circle(3.0));
		
		System.out.println("Total area: " + totalArea(myCircles));
			
		System.out.println();
		System.out.println("=========================================================================");
		System.out.println();
		
		System.out.println();
		System.out.println("(Princípio GET/PUT)");
		System.out.println("Fazer um método que copia os elementos de uma lista para uma"); 
		System.out.println("outra lista que pode ser mais genérica que a primeira");
		System.out.println();
		
		List<Integer> myInts = Arrays.asList(1, 2, 3, 4);
		List<Double> myDoubles = Arrays.asList(3.14, 6.28);
		List<Object> myObjs = new ArrayList<Object>();
		System.out.println("Primeira Lista");
		copy(myInts, myObjs);
		printList(myObjs);
		System.out.println("LISTA FINAL");
		copy(myDoubles, myObjs);
		printList(myObjs);
		
		System.out.println();
		System.out.println("=========================================================================");
		System.out.println();
		
		System.out.println();
		System.out.println("(EQUALS)");
		System.out.println("Método que compara se o objeto é igual a outro, retornando true ou false");
		System.out.println();
		
		String a = "Maria";
		String b = "Alex";
		
		System.out.println(a + " EQUALS " + b + " --> " + a.equals(b));
		
		
		System.out.println();
		System.out.println("=========================================================================");
		System.out.println();
		
		System.out.println();
		System.out.println("(HASHCODE)");
		System.out.println("Método que compara se o objeto é igual a outro, retornando um número inteiro");
		System.out.println("representando um código gerado a partir das informações do objeto");
		System.out.println();
		
		System.out.println(a + " HashCode " + " --> " +a.hashCode());
		System.out.println(b + " HashCode " + " --> " +b.hashCode());		
		
		
		System.out.println();
		System.out.println("=========================================================================");
		System.out.println();
		
		System.out.println();
		System.out.println("(HASHCODE E EQUALS PERSONALIZADOS)");
		System.out.println("Método que compara se o objeto é igual a outro, retornando true ou false");
		System.out.println("Para ser igual tem que ter NOME e EMAIL IGUAL");
		System.out.println();
		
		Client c1 = new Client ("Maria", "maria@gmail.com");
		Client c2 = new Client ("Alex", "alex@gmail.com");
		
		System.out.println(c1 + " - HashCode " + " --> " + c1.hashCode());
		System.out.println(c2 + " - HashCode " + " --> " + c2.hashCode());
		System.out.println(c1 + " EQUALS " + c2 + " --> " + c1.equals(c2));
		
		
		
		sc.close();
	}

	public static void printList(List<?> list) {
		for (Object obj : list) {
		System.out.println(obj);
		}
	}
	
	public static double totalArea(List<? extends Shape> list) {
		double sum = 0.0;
		for (Shape s : list) {
			sum += s.area();
		}
		return sum;
	}  
	
	public static void copy(List<? extends Number> source, List<? super Number> destiny) {
		for(Number number : source) {
		destiny.add(number);
		}
	}
	
}
