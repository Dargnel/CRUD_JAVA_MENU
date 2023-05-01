import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static Scanner scanner= new Scanner(System.in);
    public static void main(String[] args) throws SQLException {


        DBmanager.initConection();

        int opc;

        do{
            System.out.println("\n1.list Users");
            System.out.println("2.Add User");
            System.out.println("3.Update User");
            System.out.println("4.Delate User");
            System.out.println("5.exit");

            opc = scanner.nextInt();


            switch (opc){

                case 1:{
                    listUsers();
                    break;
                }

                case 2:{
                    addUser();
                    break;
                }

                case 3:{
                    updateUser();
                    break;
                }

                case 4:{
                    deleteUser();
                    break;
                }

                case 5:{
                    DBmanager.closeConection();
                    break;
                }

                default: {
                    System.out.println("Opcion incorrecta");
                }

            }


        }while (opc!=5);

    }

    public static void listUsers(){
        DBmanager.getUsers();
    }
    public static void addUser(){
        System.out.println("Nombre: ");
        scanner.nextLine();
        String nombre = scanner.nextLine();

        System.out.println("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.println("edad: ");
        int edad = scanner.nextInt();

        DBmanager.adduser(nombre,apellido,edad);


    }
    public static void updateUser(){

        listUsers();
        System.out.println("Seleccione ID: ");
        scanner.nextLine();
        Long id = scanner.nextLong();

        System.out.println("Nombre: ");
        scanner.nextLine();
        String nombre = scanner.nextLine();

        System.out.println("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.println("edad: ");
        int edad = scanner.nextInt();

        DBmanager.updateUser(id,nombre,apellido,edad);

    }
    public static void deleteUser(){

        listUsers();
        System.out.println("Seleccione ID: ");
        scanner.nextLine();
        Long id = scanner.nextLong();

        DBmanager.deleteUser(id);

    }

}