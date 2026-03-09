package classes;

import interfaces.Produit;

public class ProduitC implements Produit{
	private int prix;

	public ProduitC(int prix) {
		this.prix = prix;
	}

	@Override
	public int getPrix() {
		return prix;
	}

}
