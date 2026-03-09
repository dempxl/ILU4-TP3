package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import classes.Pair;
import classes.ProduitC;
import classes.VilleC;
import interfaces.Pays;
import interfaces.Produit;
import interfaces.Ville;
import records.*;

public class Main {
	
	// 1.a)
	public static int fact(int n) {
		// attention range prend de 1 à n EXCLU, d'où le n+1
		return IntStream
				.range(1, n+1)
				.reduce(1, (x,y) -> x*y);
	}
	
	// 1.d)
	public static int nbChiffre(int n) {
		int res = 0;
		for(int i = n; i > 0; i/= 10) {
			res++;
		}
		return res;
	}
	
	// 2.a)
	public static int sommeProduitsSum(Stream<Produit> sp) {
		// Il faut trouver une solution qui permet de passer d'un Stream à un IntStream...
		// mapToInt permet cela !
		return sp
				.mapToInt(Produit::getPrix)
				.sum();
	}
	
	// 2.b)
	public static int sommeProduitReduce(Stream<Produit> sp) {
		return sp
				.mapToInt(Produit::getPrix)
				.reduce(0, (x,y) -> x + y);
	}
	
	// 3.b)
	// même en enlevant orElse(null)
	public static Ville capitaleLaPlusPeuplee(Stream<Pays> p) {
		return p.map(Pays::getCapitale)
				.max(Comparator.comparing(Ville::getPopulation))
				.orElse(null);
	}
	
	// 3.c)
	public static List<Pays> triCPc(Stream<Pays> p){
		// tri p par continent puis par population croissante
		return p.sorted(Comparator
							.comparing(Pays::getContinent)
							.thenComparing(Pays::getPopulation)
				).toList();
	}
	
	// 3.d)
	public static List<Pays> triCPd(Stream<Pays> p){
		return p.sorted(Comparator
							.comparing(Pays::getContinent)
							.thenComparing(Pays::getPopulation, Comparator.reverseOrder())
						).toList();
	}	
	
	// 3.e)
	public static List<Ville> capitalesMoinsPeuplees(Stream<Pays> pays){
		// Ici on veut lister les Capitales qui sont moins peuplées que les autres villes de ce pays
		return pays.filter(p -> p.getVilles()
								.stream()
								.anyMatch(v -> v.getPopulation() > p.getCapitale().getPopulation()))
				.map(Pays::getCapitale)
				.toList();

							
	}
	



	public static void main(String[] args) {		
		// 1. Factorielle
		// b)
		// boxed transforme IntStream en Stream
		System.out.println("===Question b===");
		List<Integer> liste = IntStream
								.rangeClosed(1, 10)
								.map(Main::fact)
								.boxed()
								.toList();
		System.out.println(liste);
		
		// c)
		System.out.println("===Question c===");
		IntStream
			.iterate(1, n -> n+1)
			.limit(10)
			.map(Main::fact)
			.forEach(System.out::println);
		
		// d)
		System.out.println("===Question d===");
		IntStream
			.iterate(1, n -> n+1)
			.map(Main::fact)
			.filter(n -> nbChiffre(n) % 2 == 0)
			.limit(4)
			.forEach(System.out::println);
		// pour le filter on peut aussi faire n -> Integer.toString(n).length() % 2 == 0
		
		// e) 
		System.out.println("===Question e===");
		Stream
			.iterate(new Pair(1,1), p -> new Pair(p.e+1 ,(p.e+1) * p.fact))
			.limit(10)
			.map(p -> p.fact)
			.forEach(System.out::println);
		
		// 2. Produits et prix
		Stream<Produit> sp1 = Stream.of(
				new ProduitC(12),
				new ProduitC(32),
				new ProduitC(8),
				new ProduitC(9)
				);
		Stream<Produit> sp2 = Stream.of(
				new ProduitC(24), 
				new ProduitC(7), 
				new ProduitC(42)
				);
		// a.
		System.out.println("===Question 2.a)===");
		System.out.println(sommeProduitsSum(sp1)); //61
		
		// b.
		System.out.println("===Question 2.b)===");
		System.out.println(sommeProduitReduce(sp2)); // 73
		
		// /!\ On ne peut pas utiliser sp1/sp2 sur les deux syso !
		// Si on l'utilise par exemple sp1 pour 2.a) il faut faire un close() et redéfinir sp1
		
		// 3. Villes et Pays 
		// a.	
		// Obligé de rajouter un setter pour pour la ville, 
		// puisqu'il faut créer la ville PUIS le pays avant de pouvoir définir à la ville son pays 
		Ville toulouse = new VilleC(null, 511684, "Toulouse");
		Ville paris = new VilleC(null, 2103778, "Paris");
		Ville cahors = new VilleC(null, 20050, "Cahors");
		
		List<Ville> villesFrance = new ArrayList<>(Arrays.asList(toulouse,paris,cahors));
		
		Pays france = new PaysRecord("France",villesFrance,"Europe",paris,69000000);
		
		toulouse.setPays(france);
		paris.setPays(france);
		cahors.setPays(france);
		
		Ville tokyo = new VilleC(null, 14000000, "Tokyo");
		Ville kyoto = new VilleC(null, 1460000, "Kyoto");
		Ville osaka = new VilleC(null, 2000000, "Osaka");
		
		List<Ville> villesJapon = new ArrayList<>(Arrays.asList(tokyo,kyoto,osaka));
		
		Pays japon = new PaysRecord("Japon",villesJapon, "Asie", tokyo,128000000);
		
		tokyo.setPays(japon);
		kyoto.setPays(japon);
		osaka.setPays(japon);
		
		Ville stockholm = new VilleC(null, 962154, "Stockholm");
		Ville gothenburg = new VilleC(null, 562945, "Gothenburg");
		Ville malmo = new VilleC(null, 318242, "Malmo");
		
		List<Ville> villesSuede = new ArrayList<>(Arrays.asList(stockholm,gothenburg,malmo));
		
		Pays suede = new PaysRecord("Suede", villesSuede,"Europe",stockholm, 10564484);
		
		stockholm.setPays(suede);
		gothenburg.setPays(suede);
		malmo.setPays(suede);
		
		Stream<Pays> P1 = Stream.of(france,japon,suede);
		
		// b.
		System.out.println("\nCapitale la plus peuplee :");
		System.out.println(capitaleLaPlusPeuplee(P1));
		
		// c.
		Stream<Pays> P2 = Stream.of(france,japon,suede);

		System.out.println("\nListe des pays triées par continent puis par populations CROISSANTE :");
		for (Pays pays : triCPc(P2)) {
			System.out.println(pays.getContinent() 
					+ " - " + pays.getNom() 
					+ " (" + pays.getPopulation() + ") ");
		}
		
		// d.
		Stream<Pays> P3 = Stream.of(france,japon,suede);

		
		System.out.println("\nListe des pays triées par continent puis par populations DECROISSANTE :");
		for (Pays pays : triCPd(P3)) {
			System.out.println(pays.getContinent() 
					+ " - " + pays.getNom() 
					+ " (" + pays.getPopulation() + ") ");
		}
				
		// e.
		Ville capitaleX = new VilleC(null, 500000, "CapitaleX");
		Ville grandeVilleX = new VilleC(null, 2000000, "grandeVilleX");
		Ville petiteVilleX = new VilleC(null, 100000, "petiteVilleX");

		List<Ville> villesPaysX = new ArrayList<>(
		        Arrays.asList(capitaleX, grandeVilleX, petiteVilleX)
		);

		Pays paysX = new PaysRecord(
		        "PaysX",
		        villesPaysX,
		        "Amérique",
		        capitaleX,      // capitale moins peuplée que MegaCityX
		        8000000
		);

		capitaleX.setPays(paysX);
		grandeVilleX.setPays(paysX);
		petiteVilleX.setPays(paysX);
		
		Stream<Pays> P4 = Stream.of(france,japon,suede,paysX);

		
		System.out.println("\nListe des capitales moins peuplées que d'autre villes de pays :");
		for (Ville ville : capitalesMoinsPeuplees(P4)) {
			System.out.println(ville.getNom());
		}

		
	}

}
