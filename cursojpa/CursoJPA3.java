package cursojpa;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mx.com.lobos.controllers.EquiposJpaController;
import mx.com.lobos.entities.Equipos;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class CursoJPA3 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EquiposJpaController ejc;
        List<Equipos> equipos;
        String res;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            ejc = new EquiposJpaController(emf);
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