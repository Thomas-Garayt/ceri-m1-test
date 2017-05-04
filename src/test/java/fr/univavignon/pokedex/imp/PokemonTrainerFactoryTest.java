package fr.univavignon.pokedex.imp;

import fr.univavignon.pokedex.api.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;


public class PokemonTrainerFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private static PokemonTrainerFactory pokemonTrainerFactory;

    private IPokedexFactory pokedexFactory;


    @Before
    public void setUp() {

        pokemonTrainerFactory = new PokemonTrainerFactory();

        pokedexFactory = new PokedexFactory();

    }

    @Test
    public void testCreateTrainer() {

        PokemonTrainer pokemonTrainer = pokemonTrainerFactory.createTrainer("Thomas", Team.VALOR, pokedexFactory);
        PokemonTrainer pokemonTrainer2 = pokemonTrainerFactory.createTrainer("Thomas");
        
        assertNotNull(pokemonTrainer);
        assertNotNull(pokemonTrainer2);
        
        Pokemon pokemon = pokemonTrainer.getPokedex().createPokemon(12, 456, 321, 1200, 12);
        pokemonTrainer.getPokedex().addPokemon(pokemon);

        try {
            pokemonTrainerFactory.saveData(pokemonTrainer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PokedexException e) {
            e.printStackTrace();
        }

        TestCase.assertEquals("Thomas", pokemonTrainer.getName());
        TestCase.assertEquals(Team.VALOR, pokemonTrainer.getTeam());

        TestCase.assertEquals("Thomas", pokemonTrainer2.getName());
        TestCase.assertEquals(Team.VALOR, pokemonTrainer2.getTeam());
        
        assertNotNull(pokemonTrainer.getPokedex());
    }


}