package fr.univavignon.pokedex.imp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

public class Main {

	private static Scanner sc;
	
	private static PokemonTrainer pokeTrainer = null;
	private static PokemonTrainerFactory pokeTrainerFac = new PokemonTrainerFactory();
	private static PokemonFactory pokemonFac = new PokemonFactory();
	
	
	public static void main(String[] args) throws IOException, PokedexException {
		
		sc = new Scanner(System.in);

		while(true) {
			displayMainMenu();

			String str_Menu = sc.nextLine();
			
			switch(str_Menu) {
				case "1":
					try {
						loadTrainer();
					} catch (FileNotFoundException | PokedexException e) {
						e.printStackTrace();
					}
					break;
				case "2":
					newTrainer();
					break;
				case "3":
					seePokedex();
					break;
				case "4":
					createPokemon();
					break;
				default:
			}
			
			if("10".equals(str_Menu)) {
				if(pokeTrainer != null) {
					System.out.println("Saving the trainer data...");
					pokeTrainerFac.saveData(pokeTrainer);	
					System.out.println("Data saved.");
				}
				System.out.println("Bye " + pokeTrainer.getName() + " !");
				System.exit(0);
			}
		}		
	}
	
	private static void displayMainMenu() {
		System.out.println("--- ForkThemAll Menu ---");
		if(pokeTrainer != null) {
			System.out.println("Current trainer : " + pokeTrainer.getName());
		}
		System.out.println("# 1 - Load trainer");
		System.out.println("# 2 - Create trainer");
		
		/* Parts of menu only visible if a trainer is load */
		if(pokeTrainer != null) {
			System.out.println("# 3 - See your pokedex");
			System.out.println("# 4 - Add pokemon to your pokedex");
		}
		
		System.out.println("# 10 - Exit");
	}

	private static void createPokemon() throws IOException, PokedexException {
		/* 
		 * Correct data for test
		 * ID : 81 (Magnemite)
		 * CP : 129
		 * HP : 17
		 * Dust : 600
		 * Candy : 600
		 */
		System.out.println("Pokemon's index ? (ex : 81 - Magnemite) ");
		int str_Index = sc.nextInt();
		System.out.println("Pokemon's CP ? (ex : 129)");
		int str_Cp = sc.nextInt();
		System.out.println("Pokemon's HP ? (ex : 17)");
		int str_Hp = sc.nextInt();
		System.out.println("Pokemon's Dust ? (ex : 600)");
		int str_Dust = sc.nextInt();
		System.out.println("Pokemon's Candy ? (ex : 600)");
		int str_Candy = sc.nextInt();
		
		Pokemon poke = pokemonFac.createPokemon(str_Index, str_Cp, str_Hp, str_Dust, str_Candy);
		pokeTrainer.getPokedex().addPokemon(poke);
		pokeTrainerFac.saveData(pokeTrainer);	
	}

	private static void seePokedex() {
		Pokedex pokedex = pokeTrainer.getPokedex();
		if(pokedex.getPokemons().isEmpty()) {
			System.out.println("Pokedex empty");
		}
		else {
			for (Pokemon poke : pokedex.getPokemons()) {
			    System.out.println(poke.getName()
			    		+ " - Defense:" + poke.getDefense()
			    		+ " - Attack:" + poke.getAttack()
			    		+ " - Stamina:" + poke.getStamina() 
			    		+ " - CP:" + poke.getCp()
			    		+ " - HP:" + poke.getHp()
			    		+ " - Dust:" + poke.getDust()
			    		+ " - Candy:" + poke.getCandy()
			    		+ " - IV:" + poke.getIv()
			    );
			}
		}
	}

	private static void loadTrainer() throws FileNotFoundException, PokedexException {

		System.out.println("Name of your trainer ?");
		String str = sc.nextLine();
		
		PokemonTrainerFactory pt = new PokemonTrainerFactory();
		
		pokeTrainer = pt.createTrainer(str);
		
		if(pokeTrainer != null) {
			return;
		}
		else {
			System.out.println("Cannot find your trainer, do you want to create one ? (Yes, No)");
			String str_Create = sc.nextLine();
			if(str_Create.equalsIgnoreCase("Yes")) {
				newTrainer();
			}
			else {
				return;
			}
		}
	}

	private static void newTrainer() {
		System.out.println("Name of your trainer ?");
		String str_Name = sc.nextLine();
		
		System.out.println("Team of your trainer ? (Mystic,	Instinct, Valor)");
		String str_Team = sc.nextLine();
		
		Team team = null;

		switch(str_Team) {
			case "Mystic":
				team = Team.MYSTIC;
				break;
			case "Instinct":
				team = Team.INSTINCT;
				break;
			case "Valor":
				team = Team.VALOR;
				break;
			default:
				System.out.println("Incorrect team");
				break;
		}
		
		PokemonTrainerFactory pt = new PokemonTrainerFactory();
		
		pokeTrainer = pt.createTrainer(str_Name,team,new PokedexFactory());
	}
	
}
