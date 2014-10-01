package cursojpa;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mx.com.lobos.controllers.EquiposJpaController;
import mx.com.lobos.entities.Equipos;

public class CursoJPA3 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EquiposJpaController ejc;
        List<Equipos> equipos;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            ejc = new EquiposJpaController(emf);
            equipos = ejc.findEquiposEntities();
            for(Equipos equipo:equipos){
                System.out.println(equipo.getAbreviatura()+'-'+equipo.getNombre());
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            if(emf != null){
                emf.close();
            }
        }
    }
}