package fr.univavignon.pokedex.imp;

import fr.univavignon.pokedex.api.Pokemon;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;

public class PokemonFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private static PokemonFactory pokemonFactory;


    @Before
    public void setUp() {

        pokemonFactory = new PokemonFactory();

    }


    @Test
    public void testCreatePokemon() {

        Pokemon pokemon = pokemonFactory.createPokemon(42, 123, 97, 1000, 41);

        assertEquals(42, pokemon.getIndex());

    }


}