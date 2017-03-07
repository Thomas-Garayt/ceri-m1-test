package fr.univavignon.pokedex.api;

import org.junit.Assert;
import org.junit.Test;

public class IPokedexFactoryTest  {
	
	@Test
	public void testCreatePokedex() {
		try {
			IPokemonMetadataProvider metadataProvider = GlobalFactory.getIPokemonMetadataProvider();
			IPokemonFactory pokemonFactory = GlobalFactory.getIPokemonFactory();
			IPokedexFactory pokedexFactory = GlobalFactory.getIPokedexFactory();
			
			IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
			
			Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
			int idBulbi = pokedex.addPokemon(bulbizarre);
			Assert.assertTrue(pokedex.getPokemon(idBulbi).equals(bulbizarre));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}