 package modelo;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n*** Menu Principal ***");
            System.out.println("1. Gestionar categorias");
            System.out.println("2. Gestionar actores");
            System.out.println("3. Gestionar peliculas");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            try {
                switch (opcion) {
                    case 1:
                        gestionarCategorias();
                        break;
                    case 2:
                        gestionarActores();
                        break;
                    case 3:
                        gestionarPeliculas();
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                }
            } catch (SQLException e) {
                System.out.println("Error en la base de datos: " + e.getMessage());
            }

        } while (opcion != 0);

        sc.close();
    }

    public static void gestionarCategorias() throws SQLException {
        Scanner sc = new Scanner(System.in);
        CategoryManager gestor = new CategoryManager();
        int opcion;

        do {
            System.out.println("\n*** Menu Categorias ***");
            System.out.println("1. Ver todas las categorias");
            System.out.println("2. Agregar categoria");
            System.out.println("3. Modificar categoria");
            System.out.println("4. Eliminar categoria");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    List<Category> categorias = gestor.getAll();
                    for (Category c : categorias) {
                        System.out.println("ID: " + c.getId() + " | Nombre: " + c.getName());
                    }
                    break;
                case 2:
                    System.out.print("Nombre de la nueva categoria: ");
                    String nuevaCategoria = sc.nextLine();
                    Category nueva = new Category();
                    nueva.setName(nuevaCategoria);
                    gestor.post(nueva);
                    break;
                case 3:
                    System.out.print("ID de la categoria a modificar: ");
                    int idModificar = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    Category modificar = new Category();
                    modificar.setId(idModificar);
                    modificar.setName(nuevoNombre);
                    gestor.put(modificar);
                    break;
                case 4:
                    System.out.print("ID de la categoria a eliminar: ");
                    int idEliminar = sc.nextInt();
                    gestor.delete(idEliminar);
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);
    }

    public static void gestionarActores() throws SQLException {
        Scanner sc = new Scanner(System.in);
        ActorManager gestor = new ActorManager();
        int opcion;

        do {
            System.out.println("\n*** Menu Actores ***");
            System.out.println("1. Ver todos los actores");
            System.out.println("2. Agregar actor");
            System.out.println("3. Modificar actor");
            System.out.println("4. Eliminar actor");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    List<Actor> actores = gestor.getAll();
                    for (Actor a : actores) {
                        System.out.println("ID: " + a.getId() + " | Nombre: " + a.getFirstName() + " " + a.getLastName());
                    }
                    break;
                case 2:
                    System.out.print("Nombre del actor: ");
                    String firstName = sc.nextLine();
                    System.out.print("Apellido del actor: ");
                    String lastName = sc.nextLine();
                    Actor nuevoActor = new Actor();
                    nuevoActor.setFirstName(firstName);
                    nuevoActor.setLastName(lastName);
                    gestor.post(nuevoActor);
                    break;
                case 3:
                    System.out.print("ID del actor a modificar: ");
                    int idModificar = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Nuevo apellido: ");
                    String nuevoApellido = sc.nextLine();
                    Actor modificarActor = new Actor();
                    modificarActor.setId(idModificar);
                    modificarActor.setFirstName(nuevoNombre);
                    modificarActor.setLastName(nuevoApellido);
                    gestor.put(modificarActor);
                    break;
                case 4:
                    System.out.print("ID del actor a eliminar: ");
                    int idEliminar = sc.nextInt();
                    gestor.delete(idEliminar);
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);
    }

    public static void gestionarPeliculas() throws SQLException {
        Scanner sc = new Scanner(System.in);
        FilmManager gestor = new FilmManager();
        int opcion;

        do {
            System.out.println("\n*** Menu Peliculas ***");
            System.out.println("1. Ver todas las peliculas");
            System.out.println("2. Agregar pelicula");
            System.out.println("3. Modificar pelicula");
            System.out.println("4. Eliminar pelicula");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    List<Film> peliculas = gestor.getAll();
                    for (Film f : peliculas) {
                        System.out.println("ID: " + f.getId() + " | Titulo: " + f.getTitle() + " | Año: " + f.getReleaseYear());
                    }
                    break;
                case 2:
                    Film nueva = new Film();
                    System.out.print("Titulo: ");
                    nueva.setTitle(sc.nextLine());
                    System.out.print("Descripcion: ");
                    nueva.setDescription(sc.nextLine());
                    System.out.print("Año de estreno: ");
                    nueva.setReleaseYear(sc.nextInt());
                    System.out.print("ID del idioma: ");
                    nueva.setLanguageId(sc.nextInt());
                    System.out.print("Duracion (minutos): ");
                    nueva.setLength(sc.nextInt());
                    gestor.post(nueva);
                    break;
                case 3:
                    Film editar = new Film();
                    System.out.print("ID de la pelicula a modificar: ");
                    editar.setId(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Nuevo titulo: ");
                    editar.setTitle(sc.nextLine());
                    System.out.print("Nueva descripcion: ");
                    editar.setDescription(sc.nextLine());
                    System.out.print("Nuevo año: ");
                    editar.setReleaseYear(sc.nextInt());
                    System.out.print("Nuevo ID de idioma: ");
                    editar.setLanguageId(sc.nextInt());
                    System.out.print("Nueva duracion: ");
                    editar.setLength(sc.nextInt());
                    gestor.put(editar);
                    break;
                case 4:
                    System.out.print("ID de la pelicula a eliminar: ");
                    gestor.delete(sc.nextInt());
                    break;
                case 0:
                    System.out.println("Volver al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);
    }
}