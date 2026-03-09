package interfaces;

import java.util.List;

public interface Pays {
	String getNom();
	List<Ville> getVilles();
	String getContinent();
	Ville getCapitale();
	int getPopulation();
}
