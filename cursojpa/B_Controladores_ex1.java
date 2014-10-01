package cursojpa;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mx.com.lobos.controllers.PartidosJpaController;
import mx.com.lobos.entities.Partidos;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class B_Controladores_ex1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        PartidosJpaController pjc;
        List<Partidos> partidos;
        String res;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            pjc = new PartidosJpaController(emf);
            partidos = pjc.findPartidosEntities();
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", partidos.size(),partidos);
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