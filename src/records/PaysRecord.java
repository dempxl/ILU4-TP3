package records;

import java.util.List;
import interfaces.Pays;
import interfaces.Ville;

// L'interface utilise getVilles()
// Or un record génère automatiquement villes()
// On est donc obligé de redéfinir les méthodes
public record PaysRecord(
		String nom,
        List<Ville> villes,
        String continent,
        Ville capitale,
        int population
) implements Pays {
	@Override
	public String getNom() { return nom; }

    @Override
    public List<Ville> getVilles() { return villes; }

    @Override
    public String getContinent() { return continent; }

    @Override
    public Ville getCapitale() { return capitale; }

    @Override
    public int getPopulation() { return population; }
    
    
}