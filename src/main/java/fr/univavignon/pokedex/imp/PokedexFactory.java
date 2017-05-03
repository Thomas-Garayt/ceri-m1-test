package fr.univavignon.pokedex.imp;

import fr.univavignon.pokedex.api.*;

public class PokedexFactory implements IPokedexFactory {

	@Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {

        Pokedex pokedex = new Pokedex(metadataProvider, pokemonFactory);

        return pokedex;
    }

	
}