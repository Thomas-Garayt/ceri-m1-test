package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import fr.univavignon.pokedex.imp.Pokedex;

public class IPokemonTrainerFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
	
    @Mock
    private static IPokedexFactory IpokedexFactory;
    
    @Mock
    private static IPokemonTrainerFactory pokemonTrainerFactory;

    @Mock
	private static Pokedex pokedex;
    
	private static PokemonTrainer trainerTest = new PokemonTrainer("Toto",Team.MYSTIC, pokedex);
    
    @Before
    public void setUp() throws PokedexException {
        MockitoAnnotations.initMocks(this);
        when(pokemonTrainerFactory.createTrainer("Toto", Team.MYSTIC, IpokedexFactory)).thenReturn(trainerTest);
    } 
    
	@Test
	public void testCreateTrainer() {
        PokemonTrainer pokemonTrainer = pokemonTrainerFactory.createTrainer("Toto", Team.MYSTIC, IpokedexFactory);
        Assert.assertEquals("Toto", pokemonTrainer.getName());
        Assert.assertEquals(Team.MYSTIC, pokemonTrainer.getTeam());
	}
	
}