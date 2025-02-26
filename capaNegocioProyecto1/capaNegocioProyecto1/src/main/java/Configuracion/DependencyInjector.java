
package Configuracion;

import BO.CitaBO;
import BO.CitaSinCitaBO;
import BO.ConsultaBO;
import BO.MedicoBO;
import BO.PacienteBO;
import BO.UsuarioBO;
import conexion.ConexionBD;
import conexion.IConexionBD;


/**
 * Esta clase se encarga de configurar que actúan como inyector de dependencias para la capa negocio.
 * Proporciona métodos estáticos para crear instancias de las clases de negocio.
 * Asegurando que todas tengan la misma implementación de conexión a la base de datos.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class DependencyInjector {
    
    /**
     * Este método crea una instancia de PacienteBO con una conexión a la base de datos.
     * 
     * @return Una nueva instancia de la clase PacienteBO.
     */
    public static PacienteBO crearPacienteBO() {
        IConexionBD conexion = new ConexionBD();
        PacienteBO pacienteBO = new PacienteBO(conexion);

        return pacienteBO;
    }
    
    /**
     * Este método crea una instancia de MedicoBO con una conexión a la base de datos.
     * 
     * @return Una nueva instancia de la clase MedicoBO.
     */
    public static MedicoBO crearMedicoBO() {
        IConexionBD conexion = new ConexionBD();
        MedicoBO medicoBO = new MedicoBO(conexion);

        return medicoBO;
    }
    
    /**
     * Este método crea una instancia de UsuarioBO con una conexión a la base de datos.
     * 
     * @return Una nueva instancia de la clase UsuarioBO.
     */
    public static UsuarioBO crearUsuarioBO() {
        IConexionBD conexion = new ConexionBD();
        UsuarioBO usuarioBO = new UsuarioBO(conexion);

        return usuarioBO;
    }
    

    /**
     * Este método crea una instancia de CitaBO con una conexión a la base de datos.
     * 
     * @return Una nueva instancia de la clase CitaBO.
     */
    public static CitaBO crearCitaBO() {
        IConexionBD conexion = new ConexionBD();
        CitaBO citaBO = new CitaBO(conexion);

        return citaBO;
    }

    /**
     * Este método crea una instancia de CitaSinCitaBO con una conexión a la base de datos.
     * 
     * @return Una nueva instancia de la clase CitaSinCitaBO.
     */
    public static CitaSinCitaBO crearCitaSinCitaBO() {
        IConexionBD conexion = new ConexionBD();
        CitaSinCitaBO citaSinCitaBO = new CitaSinCitaBO(conexion);

        return citaSinCitaBO;
    }
    
    /**
     * Este método crea una instancia de ConsultaBO con una conexión a la base de datos.
     * 
     * @return Una nueva instancia de la clase ConsultaBO.
     */
    public static ConsultaBO crearConsultaBO() {
        IConexionBD conexion = new ConexionBD();
        ConsultaBO consultaBO = new ConsultaBO(conexion);

        return consultaBO;
    }
    
//    /**
//     * 
//     * @return 
//     */
//    public static DireccionBO crearDireccionBO() {
//        IConexionBD conexion = new ConexionBD();
//        DireccionBO direccionBO = new DireccionBO(conexion);
//
//        return direccionBO;
//    }
//    
//    /**
//     * 
//     * @return 
//     */
//    public static HorarioBO crearHorarioBO() {
//        IConexionBD conexion = new ConexionBD();
//        HorarioBO horarioBO = new HorarioBO(conexion);
//
//        return horarioBO;
//    }
}

