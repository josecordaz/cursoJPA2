package cursojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.lobos.entities.Estadios;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class C_EntityManager_18 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em =  null;
        Query query;
        List<Estadios> estadios;
        String res;
        StringBuilder consulta;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            em = emf.createEntityManager();
            
            consulta = new StringBuilder();
            
            consulta.append("select distinct es ");
            consulta.append("from Partidos p ");
            consulta.append("    join p.idEstadio es ");
            consulta.append("    join p.idEquipo1 e1 ");
            consulta.append("    join p.idEquipo2 e2 ");
            consulta.append("    join e1.idGrupo g1 ");
            consulta.append("    join e2.idGrupo g2 ");
            consulta.append("where g1.nombre = :nombreGrupo ");
            consulta.append("    or g2.nombre = :nombreGrupo ");
            
            query = em.createQuery(consulta.toString());
            
            query.setParameter("nombreGrupo","D");
            
            estadios = query.getResultList();
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", estadios.size(),estadios);
            
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