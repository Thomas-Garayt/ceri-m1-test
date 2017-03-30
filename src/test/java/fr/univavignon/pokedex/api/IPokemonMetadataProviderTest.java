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
        Assert.assertEquals(0, pokemonMP.getIndex());
        Assert.assertEquals("Bulbizarre", pokemonMP.getName());
        Assert.assertEquals(126, pokemonMP.getAttack());
        Assert.assertEquals(126, pokemonMP.getDefense());
        Assert.assertEquals(90, pokemonMP.getStamina());
    }
  	
}