import java.sql.*;

public class DBmanager {

    private static String User ="postgres";
    private static String Pass="Dargnel";
    private  static Connection connection;

    public static void initConection () {

        try {
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Clients",User,Pass);
            System.out.println("Conectado a la base de datos por el puerto 5432");

        }catch (Exception e){
            System.out.println(e.getMessage());

        }

    }

    public  static  void   adduser(String nombre, String apellido, int edad)  {

        try {
            PreparedStatement pest = connection.prepareStatement("INSERT INTO users (nombre,apellido,edad) VALUES (?,?,?)");
            pest.setString(1,nombre);
            pest.setString(2,apellido);
            pest.setInt(3,edad);

            pest.execute();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void getUsers(){

        try {
            PreparedStatement pest = connection.prepareStatement("SELECT * FROM users order by id");
            ResultSet resultSet = pest.executeQuery();
            System.out.println("id||nombre||apellido||edad");
            while (resultSet.next()){
                long id =resultSet.getLong("id");
                String nombre =resultSet.getString("nombre");
                String apellido =resultSet.getString("apellido");
                int edad =resultSet.getInt("edad");

                System.out.println(id+"\t"+nombre+"\t"+apellido+"\t"+edad);

            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void updateUser(long id, String nombrenew, String apellidonew,int edadnew){

        try{
            PreparedStatement pest = connection.prepareStatement("UPDATE users SET nombre = ?, apellido = ?, edad = ? WHERE id = ? ");
            pest.setString(1,nombrenew);
            pest.setString(2,apellidonew);
            pest.setInt(3,edadnew);
            pest.setLong(4,id);

            int modificate = pest.executeUpdate();

            if (modificate==0){
                System.out.println("No se puedo modificar verifique los datos");
            }else {
                System.out.println("Modificacion exitosa");
            }

        }catch (Exception e){

        }

    }

    public static void deleteUser (Long id){

        try {
            PreparedStatement pest = connection.prepareStatement("Delete FROM users WHERE id = ?");
            pest.setLong(1,id);

            int modificate= pest.executeUpdate();

            if (modificate==0){
                System.out.println("No se encontro el usuario");
            }else {
                System.out.println("Usuario eliminado");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void closeConection() throws SQLException {
        connection.close();
    }

}
