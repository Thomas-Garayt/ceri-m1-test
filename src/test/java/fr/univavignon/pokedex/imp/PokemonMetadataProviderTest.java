package fr.univavignon.pokedex.imp;

import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

public class PokemonMetadataProviderTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private static PokemonMetadataProvider pokemonMetadataProvider;


    @Before
    public void setUp() {

        pokemonMetadataProvider = new PokemonMetadataProvider();

    }

    @Test
    public void testGetPokemonMetadata() {

        PokemonMetadata metadata = null;
        try {
            metadata = pokemonMetadataProvider.getPokemonMetadata(42);
        } catch (PokedexException e) {
            e.printStackTrace();
        }

        assertEquals(42, metadata.getIndex());
    }


    @Test(expected = PokedexException.class)
    public void testGetPokemonMetadataErrorMin() throws PokedexException {
    	pokemonMetadataProvider.getPokemonMetadata(0);
    }


    
    @Test(expected = PokedexException.class)
    public void testGetPokemonMetadataErrorMax() throws PokedexException {	
		pokemonMetadataProvider.getPokemonMetadata(151);
    }

}