package fr.univavignon.pokedex.imp;

import fr.univavignon.pokedex.api.*;

public class PokemonFactory implements IPokemonFactory {

	@Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {

        PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();

        Pokemon pokemon = null;

        try {

            PokemonMetadata metadata = metadataProvider.getPokemonMetadata(index);

            double iv = this.computeIV();

            pokemon = new Pokemon(
                    index,
                    metadata.getName(),
                    metadata.getAttack(),
                    metadata.getDefense(),
                    metadata.getStamina(),
                    cp,
                    hp,
                    dust,
                    candy,
                    iv
            );

        } catch (PokedexException e) {
            e.printStackTrace();
        }


        return pokemon;
    }


    /**
     * Compute the IV for a given pokemon
     *
     * @return double
     */
    private double computeIV() {

        double iv = 0;

        // Todo: calculate IV

        return iv;
}
	
}