package cursojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class C_EntityManager_17 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em =  null;
        Query query;
        List<Object[]> partidos;
        String headers[];
        String res;
        StringBuilder consulta;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            em = emf.createEntityManager();
            
            headers = new String[]{"nombre1","nombre2"};
            
            consulta = new StringBuilder();
            
            consulta.append("select e1.nombre, e2.nombre ");
            consulta.append("from Partidos p ");
            consulta.append("    join p.idEquipo1 e1 ");
            consulta.append("    join p.idEquipo2 e2 ");
            consulta.append("    join p.idEstadio es ");
            consulta.append("where es.ciudad = :ciudad");
            
            query = em.createQuery(consulta.toString());
            
            query.setParameter("ciudad","Fortaleza");
            
            partidos = query.getResultList();
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", partidos.size(),partidos,headers);
            
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