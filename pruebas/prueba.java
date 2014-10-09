/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mx.com.lobos.entities.Equipos;
import mx.com.lobos.entities.Estadios;
import mx.com.lobos.entities.Partidos;

/**
 *
 * @author joseo
 */
public class prueba {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Partidos partido;
        Long tiempoInicialNeto,tiempoFinalNeto;
        try{
            tiempoInicialNeto = new Date().getTime();
            for(int i = 0; i < 1000; i++){
                emf = Persistence.createEntityManagerFactory("cursoJPAPU");
                em = emf.createEntityManager();
                partido = new Partidos();
//                partido.setId(0);
//                partido.setFase(true);
//                partido.setFecha(new Date());
//                for(int e = 0; e < 100000; e++){
                partido.setIdEquipo1(em.getReference(Equipos.class,10));
                partido.setIdEquipo2(em.getReference(Equipos.class,12));
                partido.setIdEstadio(em.getReference(Estadios.class,4));
                em.close();
                emf.close();
            }
            em.persist(em);
            tiempoFinalNeto = new Date().getTime();
            System.out.println(tiempoFinalNeto-tiempoInicialNeto);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        } finally {
//            if (em != null){
//                em.close();
//            }
//            if (emf != null){
//                emf.close();
//            }
        }
    }
}