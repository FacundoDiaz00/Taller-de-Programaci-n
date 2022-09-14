### IControladorUsuario

+ modificarUsuario(DTUsuario): void
+ obtenerIdUsuarios(): List<String>
+ obtenerIdTuristas() : List<String>
+ obtenerIdProveedores() : List<String>
+ altaTurista(String, String, String, String, LocalDate, String): void
+ altaProveedor(String, String, String, String, String, String, LocalDate): void

+ obtenerDTUsuario(String): DTUsuario
+ obtenerDTUsuarioDetalle(String): DTUsuario

### IControladorActividadTuristica

+ obtenerIdDepartamentos(): List<String>
+ obtenerIdActividadesTuristicas(String): List<String>
+ altaActividadTuristica(String, String, String, String, int, float, String, LocalDate): void
+ existeActividadTuristica(String): boolean
+ altaInscripcionSalidaTuristica(String, String, int, LocalDate): void
+ altaDepartamento(String, String, String): void
+ obtenerDTSalidasTuristicas(String): List<DTSalidaTuristica>
+ altaSalidaTuristica(String, String, LocalDateTime, LocalDate, String, int): void
+ obtenerIdSalidasTuristicas(String): List <String>

+ obtenerDetallesActividadTuristica(String): DTActividadTuristicaDetalle
+ obtenerDTSalidaTuristica(String): DTSalidaTuristica
+ obtenerDTSalidaTuristicaDetalle(String): DTSalidaTuristicaDetalle
+ obtenerDTInscripcion(String, String): DTInscripcion

### IControladorPaquete

+ altaPaquete(String, String, int, float, LocalDate): void
+ obtenerNombrePaquetes(): List<String>
+ agregarActividadAPaquete(String, String): void
+ obtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete(String, String): List<String>

+ obtenerDetallesPaquetes(): List<DTPaqueteDetalles>

### IControladorMaestro

+ cargarDatosDePrueba(): void