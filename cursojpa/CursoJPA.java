package cursojpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CursoJPA {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            emf.close();
        }
    }
}