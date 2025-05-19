package app;

import modelo.Actor;
import modelo.ActorManager;

import java.sql.SQLException;

public class AppActor {
    public static void main(String[] args) {
        ActorManager actorManager = new ActorManager();

        Actor actor1 = new Actor(1, "Leonardo", "DiCaprio");
        Actor actor2 = new Actor(2, "Scarlett", "Johansson");
        Actor actor3 = new Actor(3, "Robert", "Downey Jr.");

        actorManager.post(actor1);
        actorManager.post(actor2);
        actorManager.post(actor3);

        System.out.println("Lista de actores:");
        for (Actor actor : actorManager.getAll()) {
            System.out.println(actor.getId() + " - " + actor.getFirstName() + " " + actor.getLastName());
        }

        Actor actorModificado = new Actor(2, "Scarlett", "Hernandez");
        actorManager.put(actorModificado);

        System.out.println("\nLista de actores despues de modificar:");
        for (Actor actor : actorManager.getAll()) {
            System.out.println(actor.getId() + " - " + actor.getFirstName() + " " + actor.getLastName());
        }

        try {
            actorManager.delete(1); 
        } catch (SQLException e) {
            System.out.println("Error al eliminar actor: " + e.getMessage());
        }

        System.out.println("\nLista de actores despues de borrar:");
        for (Actor actor : actorManager.getAll()) {
            System.out.println(actor.getId() + " - " + actor.getFirstName() + " " + actor.getLastName());
        }
    }
}