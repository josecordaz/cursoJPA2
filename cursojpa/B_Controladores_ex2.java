package cursojpa;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mx.com.lobos.controllers.EstadiosJpaController;
import mx.com.lobos.entities.Estadios;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class B_Controladores_ex2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EstadiosJpaController ejc;
        List<Estadios> estadios;
        String res;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            ejc = new EstadiosJpaController(emf);
            estadios = ejc.findEstadiosEntities();
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", estadios.size(),estadios);
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