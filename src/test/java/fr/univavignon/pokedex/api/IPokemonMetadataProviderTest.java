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

public class IPokemonMetadataProviderTest {

   @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    
    @Mock
    private static IPokemonMetadataProvider pokemonMetadataProvider;
    
    @Before
    public void setUp() throws PokedexException {
        MockitoAnnotations.initMocks(this);
        when(pokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
    }
	
    @Test
    public void testGetPokemonMetadata() throws PokedexException {
    	PokemonMetadata pokemonMP = pokemonMetadataProvider.getPokemonMetadata(0);
        Assert.assertEquals("Bulbizarre", pokemonMP.getName());
    }
    
    @Test
    public void testGetPokemonMetadata() {
        Pokemon pokemon = pokemonFactory.createPokemon(133, 2729, 202, 5000, 4);
                
        /* Metadata - specific to the species */
        Assert.assertEquals("Aquali", pokemon.getName());
        Assert.assertEquals(133, pokemon.getIndex());
        Assert.assertEquals(186, pokemon.getAttack());
        Assert.assertEquals(168, pokemon.getDefense());
        Assert.assertEquals(260, pokemon.getStamina());
        
        /*
        Assert.assertEquals(2729, pokemon.getCp());
        Assert.assertEquals(202, pokemon.getHp());
        Assert.assertEquals(5000, pokemon.getDust());
        Assert.assertEquals(4, pokemon.getCandy());
         */
    }
	
}