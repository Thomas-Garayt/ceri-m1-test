package fr.univavignon.pokedex.imp;

import fr.univavignon.pokedex.api.*;

public class PokemonFactory implements IPokemonFactory {

	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		// TODO Auto-generated method stub

		return null;
		
		// Name, Attack, Defense and Stamina has to be get on webservice
		
		// IV has to be get using a web calculator

		// Pokemon pokemon = new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv/);	
	}
	
}