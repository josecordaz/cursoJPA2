package cursojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class C_EntityManager_19 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em =  null;
        Query query;
        List<Object[]> estadios;
        String headers[];
        String res;
        StringBuilder consulta;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            em = emf.createEntityManager();
            
            headers = new String[]{"idEquipo","nombre","idGrupo"};
            
            consulta = new StringBuilder();
            
            consulta.append("SELECT id_equipo, ");
            consulta.append(" nombre, ");
            consulta.append(" id_grupo ");
            consulta.append("FROM equipos");
            
            query = em.createNativeQuery(consulta.toString());
            
            estadios = query.getResultList();
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", estadios.size(),estadios,headers);
            
        /**/System.out.println(JSONUtil.formatJSONPretty(res));
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            if(em != null){
                em.close();
            }
            if(emf != null){
                emf.close();
            }
        }
    }
}