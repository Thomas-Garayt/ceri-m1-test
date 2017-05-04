package fr.univavignon.pokedex.api;

/**
 * Factory interface for class that aims to create Pokemon instance.
 *
 * @author Pierre Perez
 */
public class PokemonFactory implements IPokemonFactory {
    /**
     * Creates a pokemon instance computing it IVs.
     *
     * @param index Pokemon index.
     * @param cp    Pokemon CP.
     * @param hp    Pokemon HP.
     * @param dust  Required dust for upgrading pokemon.
     * @param candy Required candy for upgrading pokemon.
     * @return Created pokemon instance.
     */
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return null;
    }
}
