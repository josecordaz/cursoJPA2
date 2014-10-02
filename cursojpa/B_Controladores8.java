package cursojpa;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mx.com.lobos.controllers.EquiposJpaController;
import mx.com.lobos.controllers.GruposJpaController;
import mx.com.lobos.entities.Equipos;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class B_Controladores8 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EquiposJpaController ejc;
        GruposJpaController gjc;
        List<Equipos> equipos;
        Equipos equipo;
        String res;
        try {
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            
            ejc = new EquiposJpaController(emf);
            gjc = new GruposJpaController(emf);
            
            equipo = new Equipos();
            equipo.setIdEquipo(0);
            equipo.setIdGrupo(gjc.findGrupos(9));
            equipo.setNombre("Manchester United");
            equipo.setAbreviatura("MNU");
            
            ejc.create(equipo);
            
            equipo = new Equipos();
            equipo.setIdEquipo(0);
            equipo.setIdGrupo(gjc.findGrupos(9));
            equipo.setNombre("Bayer Munich");
            equipo.setAbreviatura("BYM");
            
            ejc.create(equipo);
            
            equipos = ejc.findEquiposEntities();
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", equipos.size(),equipos);
            
        /**/System.out.println(JSONUtil.formatJSONPretty(res));
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            if(emf != null){
                emf.close();
            }
        }
    }
}