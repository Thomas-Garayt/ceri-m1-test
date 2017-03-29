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

public class IPokemonFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
	
    @Mock
    private static IPokemonFactory pokemonFactory;
    
	private static Pokemon aquali = new Pokemon(133,"Aquali",186,168,260,2729,202,5000,4,100);
	
    @Before
    public void setUp() throws PokedexException {
        MockitoAnnotations.initMocks(this);
        when(pokemonFactory.createPokemon(133, 2729, 202, 5000, 4)).thenReturn(aquali);
    }
	
    @Test
    public void testCreatePokemon() {
        Pokemon pokemon = pokemonFactory.createPokemon(133, 2729, 202, 5000, 4);
        
        /* Testing if the pokemon is correctly created */
        /*
        Assert.assertEquals("Aquali", pokemon.getName());
        Assert.assertEquals(133, pokemon.getIndex());
        Assert.assertEquals(186, pokemon.getAttack());
        Assert.assertEquals(168, pokemon.getDefense());
        Assert.assertEquals(260, pokemon.getStamina());
        */
        Assert.assertEquals(2729, pokemon.getCp());
        Assert.assertEquals(202, pokemon.getHp());
        Assert.assertEquals(5000, pokemon.getDust());
        Assert.assertEquals(4, pokemon.getCandy());

    }
}