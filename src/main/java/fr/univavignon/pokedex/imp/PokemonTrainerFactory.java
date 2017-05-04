package fr.univavignon.pokedex.imp;

import java.io.*;

import com.google.gson.Gson;

import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonTrainerFactory;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

public class PokemonTrainerFactory implements IPokemonTrainerFactory, SerializerTool {

	private String path;

    public PokemonTrainerFactory() {
        this.path = ".pokedex42/data/trainers/";
        this.initPath(path);
    }


    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {

        PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();

        PokemonFactory pokemonFactory = new PokemonFactory();

        Pokedex pokedex = (Pokedex) pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        PokemonTrainer pokemonTrainer = null;

        try {

            if (checkFile(path, name)) {
                System.out.println("Loading " + name + "...");
                pokemonTrainer = (PokemonTrainer) this.loadData(name);
                System.out.println("Load successfully");
            } else {
                System.out.println("Saving " + name + "...");
                pokemonTrainer = new PokemonTrainer(name, team, pokedex);
                this.saveData(pokemonTrainer);
                System.out.println("Saving successfully");
            }

        } catch (Exception e) {
            e.getMessage();
        }

        return pokemonTrainer;
    }

    public PokemonTrainer createTrainer(String name) {

        PokemonTrainer pokemonTrainer = null;

        try {
            if (checkFile(path, name)) {
                System.out.println("Loading " + name + "...");
                pokemonTrainer = (PokemonTrainer) this.loadData(name);
                System.out.println("Load successfully");
            } 
        } catch (Exception e) {
            e.getMessage();
        }

        return pokemonTrainer;
    }
    
    @Override
    public void saveData(Object object) throws IOException, PokedexException {

        PokemonTrainer pokemonTrainer = (PokemonTrainer) object;

        if (pokemonTrainer == null) {
            throw new PokedexException("Couldn't save empty trainer !");
        }

        if (path == null) {
            throw new PokedexException("Trainer path is not defined !");
        }

        String filename = this.getFileName(path, pokemonTrainer.getName());

        this.persistData(filename, pokemonTrainer);
    }

	@Override
    public Object loadData(String name) throws FileNotFoundException, PokedexException {

        if (path == null) {
            throw new PokedexException("Trainer path is not defined !");
        }
        PokemonTrainer pokemonTrainer = null;

        String filename = this.getFileName(path, name);

        try (Reader reader = new FileReader(filename)) {

            Gson gson = new Gson();
             
            pokemonTrainer = gson.fromJson(reader, PokemonTrainer.class);

            if (pokemonTrainer == null) {
                throw new PokedexException("Error loading trainer !");
            }

            reader.close();
        } catch (IOException e) {
            e.getMessage();
        }


        return pokemonTrainer;
	}
}
