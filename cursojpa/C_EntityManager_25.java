package cursojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class C_EntityManager_25 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em =  null;
        Query query;
        List<Object[]> equipos;
        String headers[];
        String res;
        StringBuilder consulta;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            em = emf.createEntityManager();
            
            headers = new String[]{"idEquipo","abreviatura","nombre","idGrupo"};
            
            consulta = new StringBuilder();
            
            consulta.append("SELECT id_equipo, ");
            consulta.append("   abreviatura, ");
            consulta.append("   nombre, ");
            consulta.append("   id_grupo ");
            consulta.append("FROM equipos ");
            consulta.append("WHERE nombre LIKE ?nombre");
            
            query = em.createNativeQuery(consulta.toString());
            query.setParameter("nombre","U%");
            
            equipos = query.getResultList();
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", equipos.size(),equipos,headers);
            
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