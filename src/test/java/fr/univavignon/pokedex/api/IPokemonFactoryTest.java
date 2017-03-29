package fr.univavignon.pokedex.api;

import org.junit.Assert;
import org.junit.Test;

public class IPokemonFactoryTest {
	
	@Test
	public void testCreatePokemon() {
		IPokemonFactory pokemonFactory = GlobalFactory.getIPokemonFactory();
		
		Pokemon pokeTest = pokemonFactory.createPokemon(0, 0, 0, 0, 0);
		
		Pokemon pokeTest2 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
		
		Assert.assertTrue(pokeTest.equals(pokeTest2));
	}
	
}