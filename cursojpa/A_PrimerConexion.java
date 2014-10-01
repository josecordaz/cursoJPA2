package cursojpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class A_PrimerConexion {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            System.out.println("Prueba de conexión exitosa");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            if(emf != null){
                emf.close();
            }
        }
    }
}