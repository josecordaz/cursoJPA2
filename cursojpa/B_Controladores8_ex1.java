package cursojpa;

import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mx.com.lobos.controllers.EquiposJpaController;
import mx.com.lobos.controllers.EstadiosJpaController;
import mx.com.lobos.controllers.PartidosJpaController;
import mx.com.lobos.entities.Partidos;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class B_Controladores8_ex1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        PartidosJpaController pjc;
        EquiposJpaController ejc;
        EstadiosJpaController esjc;
        List<Partidos> partidos;
        Partidos partido;
        String res;
        Calendar calendar;
        try {
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            
            pjc = new PartidosJpaController(emf);
            ejc = new EquiposJpaController(emf);
            esjc = new EstadiosJpaController(emf);
            
            calendar = Calendar.getInstance();
            calendar.set(2014,11,04);
            
            partido = new Partidos();
            
            partido.setId(0);
            partido.setIdEquipo1(ejc.findEquipos(33));
            partido.setIdEquipo2(ejc.findEquipos(34));
            partido.setIdEstadio(esjc.findEstadios(12));
            partido.setFase(true);
            partido.setFecha(calendar.getTime());
            
            pjc.create(partido);
            
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