package classes;

import interfaces.Pays;
import interfaces.Ville;

public class VilleC implements Ville{
	
	private Pays pays;
	private int population;
	private String nom;

	public VilleC(Pays pays, int population, String nom) {
		this.setPays(pays);
		this.population = population;
		this.nom = nom;
	}

	@Override
	public Pays getPays() {
		return pays;
	}

	@Override
	public int getPopulation() {
		return population;
	}

	@Override
	public String getNom() {
		return nom;
	}
	
	@Override
	public String toString() {
        return "Ville(" + nom + ") Population(" + population + ")" ;	
    }

	public void setPays(Pays pays) {
		this.pays = pays;
	}
}
