package fr.univavignon.pokedex.api;

import org.junit.Assert;
import org.junit.Test;

public class IPokedexFactoryTest  {
	
	/**
	 * Creates a new pokedex instance using the given 
	 * <tt>metadataProvider</tt> and <tt>pokemonFactory</tt>. 
	 * 
	 * @param metadataProvider Metadata provider the created pokedex will use.
	 * @param pokemonFactory Pokemon factory the created pokedex will use.
	 * @return Created pokedex instance.
	 */
	
	@Test
	public void testCreatePokedex() {
		try {
			IPokemonMetadataProvider metadataProvider = GlobalFactory.getIPokemonMetadataProvider();
			IPokemonFactory pokemonFactory = GlobalFactory.getIPokemonFactory();
			IPokedexFactory pokedexFactory = GlobalFactory.getIPokedexFactory();
			
			IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
			
			int indexPika = 25;
			Pokemon pikachu = new Pokemon(indexPika, "Pikachu", 3, 2, 2, 2, 2, 0, 0, 0);
			int idPikachu = pokedex.addPokemon(pikachu);
			Assert.assertTrue(pokedex.getPokemon(idPikachu).equals(pikachu));
		}
		catch (PokedexException pex) {
			pex.printStackTrace();
		}
	}
	
}