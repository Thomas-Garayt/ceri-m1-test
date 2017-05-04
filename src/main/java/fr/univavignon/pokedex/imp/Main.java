package fr.univavignon.pokedex.imp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

public class Main {

	private static Scanner sc;
	
	private static PokemonTrainer pokeTrainer = null;
	private static PokemonTrainerFactory pokeTrainerFac = null;
	
	public static void main(String[] args) throws IOException, PokedexException {
		pokeTrainerFac = new PokemonTrainerFactory();
		sc = new Scanner(System.in);

		while(true) {
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
				default:
			}
			
			if(str_Menu.equals("10")) {
				if(pokeTrainer != null) {
					System.out.println("Saving the trainer data...");
					pokeTrainerFac.saveData(pokeTrainer);	
					System.out.println("Data saved.");
				}
				System.out.println("Bye !");
				System.exit(0);
			}
		}		
	}
	
	private static void seePokedex() {
		Pokedex pokedex = pokeTrainer.getPokedex();
		if(pokedex.getPokemons().isEmpty()) {
			System.out.println("Pokedex empty");
		}
		else {
			System.out.println(pokedex.getPokemons());	
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
		
		PokedexFactory pk = new PokedexFactory();
		
		PokemonTrainerFactory pt = new PokemonTrainerFactory();
		
		pokeTrainer = pt.createTrainer(str_Name,team,pk);
	}
	
}
