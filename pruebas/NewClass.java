package pruebas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mx.com.lobos.entities.Grupos;

public class NewClass {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Grupos grupo;
        Grupos nuevoGrupo;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            em = emf.createEntityManager();
            em.getTransaction().begin();
            //grupo = em.getReference(Grupos.class,3);
            grupo = new Grupos();
            grupo.setIdGrupo(3);
            grupo.setNombre("K");
            nuevoGrupo = (Grupos) em.merge(grupo);
            System.out.println(nuevoGrupo.getNombre());
            em.getTransaction().commit();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}
