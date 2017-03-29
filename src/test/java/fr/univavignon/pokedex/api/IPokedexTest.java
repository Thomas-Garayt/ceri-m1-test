package fr.univavignon.pokedex.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class IPokedexTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    
    @Mock
    private static IPokedex pokedex;
    
    private static Pokemon bulbizarre = new Pokemon(0,"Bulbizarre",126,126,90,613,64,4000,4,56);
    
    private static Pokemon aquali = new Pokemon(133,"Aquali",186,168,260,2729,202,5000,4,100);
    
    private static Comparator<Pokemon> comparatorName = Comparator.comparing(PokemonMetadata::getName);
    private static Comparator<Pokemon> comparatorAttack = Comparator.comparing(PokemonMetadata::getAttack);    
    
    @Before
    public void setUp() throws PokedexException {
        MockitoAnnotations.initMocks(this);
        
        /* Define the properties the pokedex must return */
        when(pokedex.size()).thenReturn(151);
        when(pokedex.getPokemon(0)).thenReturn(bulbizarre);
        when(pokedex.getPokemon(200)).thenThrow(new PokedexException("Invalid index"));
        
        List<Pokemon> pokedex1 = new ArrayList<>();
        List<Pokemon> pokedex2 = new ArrayList<>();
        
        /* Creating a first list of pokemon */
        pokedex1.add(bulbizarre);
        pokedex2.add(aquali);
        
        /* Adding 149 other random pokemon in the list */
        for(int i = 0; i < 149; i++) {
        	pokedex1.add(new Pokemon(-1, "Random", 0, 0, 0, 0, 0, 0, 0, 0));
        }
        
        when(pokedex.getPokemons()).thenReturn(Collections.unmodifiableList(pokedex1));

        pokedex2.add(aquali);
        pokedex2.add(bulbizarre);
        
        when(pokedex.getPokemons(comparatorAttack)).thenReturn(Collections.unmodifiableList(pokedex1));
        when(pokedex.getPokemons(comparatorName)).thenReturn(Collections.unmodifiableList(pokedex2));
        
    }
    
    @Test
    public void testSize() {
    	Assert.assertEquals(151, pokedex.size());
    }
    
    @Test
    public void testAddPokemon() {
    	Assert.assertEquals(0, pokedex.addPokemon(bulbizarre));
    }
    
    @Test
    public void testGetPokemon() throws PokedexException {
        pokedex.addPokemon(bulbizarre);
        
        Assert.assertEquals("Bulbizarre", pokedex.getPokemon(0).getName());
        
        try {
            pokedex.getPokemon(200);
            Assert.fail("PokemonException");
        } catch(PokedexException e) {
        	Assert.assertEquals("Invalid index", e.getMessage());
        }
    }
    
    @Test
    public void testGetPokemonsWithOrder() throws PokedexException {
        List<Pokemon> pokedexWithoutOrder = pokedex.getPokemons();
        
        List<Pokemon> pokedexOrderedWithName = pokedex.getPokemons(comparatorAttack);
        List<Pokemon> pokedexOrderedWithAttack = pokedex.getPokemons(comparatorName);
        
        Assert.assertTrue(pokedexWithoutOrder.indexOf(aquali) >= pokedexOrderedWithName.indexOf(aquali));
        Assert.assertTrue(pokedexOrderedWithName.indexOf(aquali) <= pokedexOrderedWithAttack.indexOf(aquali));
    }
    
}
