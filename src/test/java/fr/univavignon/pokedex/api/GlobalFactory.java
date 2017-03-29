package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class GlobalFactory {
	
	@Mock 
	private static IPokemonMetadataProvider metadataProvider;
	
	@Mock 
	private static IPokemonFactory pokemonFactory;
	
	@Mock 
	private static IPokedexFactory pokedexFactory;
	
	@Mock 
	private static IPokemonTrainerFactory trainerFactory;
	
	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule(); 
	
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);	
	}
	
	public static IPokemonMetadataProvider getIPokemonMetadataProvider() {
		return metadataProvider;
	}

	public static IPokemonFactory getIPokemonFactory() {
		return Mockito.mock(IPokemonFactory.class);
	}

	public static IPokedexFactory getIPokedexFactory() {
		return Mockito.mock(IPokedexFactory.class);
	}

	public static IPokemonTrainerFactory getIPokemonTrainerFactory() {
		return trainerFactory;
	}
}