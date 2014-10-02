package cursojpa;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mx.com.lobos.controllers.EquiposJpaController;
import mx.com.lobos.controllers.GruposJpaController;
import mx.com.lobos.entities.Equipos;
import mx.com.lobos.entities.Grupos;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class B_Controladores2_ex1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        GruposJpaController gjc;
        List<Grupos> grupos;
        Grupos grupo;
        String res;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            gjc = new GruposJpaController(emf);
            
            /* Nuevo estadio */
            grupo = new Grupos();
            grupo.setIdGrupo(0);
            grupo.setNombre("Z");
            
            gjc.create(grupo);
            grupos = gjc.findGruposEntities();
            
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", grupos.size(),grupos);
            
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