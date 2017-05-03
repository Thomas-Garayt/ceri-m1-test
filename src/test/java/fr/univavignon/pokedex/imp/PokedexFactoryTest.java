package fr.univavignon.pokedex.imp;

import fr.univavignon.pokedex.api.IPokedex;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class PokedexFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private static PokedexFactory pokedexFactory;

    private PokemonMetadataProvider pokemonMetadataProvider;

    private PokemonFactory pokemonFactory;


    @Before
    public void setUp() {

        pokedexFactory = new PokedexFactory();
        pokemonMetadataProvider = new PokemonMetadataProvider();
        pokemonFactory = new PokemonFactory();

    }

    @Test
    public void testCreatePokedex() {

        IPokedex pokedex = pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory);

        assertEquals(0, pokedex.size());
        assertNotNull(pokedex);

    }
}