package cursojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class C_EntityManager_26 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em =  null;
        Query query;
        List<Object[]> grupos;
        String headers[];
        String res;
        StringBuilder consulta;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            em = emf.createEntityManager();
            
            headers = new String[]{"idEquipo","abreviatura","nombre","idGrupo"};
            
            consulta = new StringBuilder();
            
            consulta.append("SELECT t1.id_equipo, ");
            consulta.append("    t1.abreviatura, ");
            consulta.append("    t1.nombre, ");
            consulta.append("    t1.id_grupo ");
            consulta.append("FROM grupos t0, ");
            consulta.append("    equipos t1 ");
            consulta.append("WHERE ((t0.nombre = ?grupoNombre) ");
            consulta.append("    AND (t0.id_grupo = t1.id_grupo))");
            
            query = em.createNativeQuery(consulta.toString());
            query.setParameter("grupoNombre","B");
            
            grupos = query.getResultList();
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", grupos.size(),grupos,headers);
            
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