### IControladorUsuario
```
+ modificarUsuario(DTUsuario): void
+ obtenerIdUsuarios(): List<String>
+ obtenerIdTuristas() : List<String>
+ obtenerIdProveedores() : List<String>
+ altaTurista(String, String, String, String, LocalDate, String): void
+ altaProveedor(String, String, String, String, String, String, LocalDate): void

+ obtenerDTUsuario(String): DTTurista | DTProveedor
+ obtenerDTUsuarioDetalle(String): DTTuristaDetalle | DTProveedorDetalle
+ obtenerDTUsuarioDetallePrivado(String): DTTuristaDetallePrivado | DTProveedorDetallePrivado

+ validarUsuarioPorCorreo(correo: string, passwd : string)
+ validarUsuarioPorNickname(nickname: string, passwd : string)
+ modificarUsuario(DtUsuario)
```
### IControladorActividadTuristica
```
+ obtenerIdDepartamentos(): List<String>
+ altaActividadTuristica(String, String, String, String, int, float, String, LocalDate, List<String>): void
+ existeActividadTuristica(String): boolean
+ altaInscripcionSalidaTuristica(String, String, int, LocalDate, string): void
+ altaDepartamento(String, String, String): void
+ obtenerDTSalidasTuristicas(String): List<DTSalidaTuristica>
+ altaSalidaTuristica(String, String, LocalDateTime, LocalDate, String, int): void
+ obtenerIdSalidasTuristicas(String): List <String>
+ obtenerIdActividadesTuristricasConfirmadasPorCategoria(String) : List<String>
+ obtenerIdActividadesTuristricasConfirmadasPorDepartamento(string) : List<String>
+ obtenerDetallesActividadTuristica(nombreAct: String): DTActividadTuristicaDetalles 

+ obtenerDetallesActividadTuristica(String): DTActividadTuristicaDetalle
+ obtenerDTSalidaTuristica(String): DTSalidaTuristica
+ obtenerDTSalidaTuristicaDetalle(String): DTSalidaTuristicaDetalle
+ obtenerDTInscripcion(String, String): DTInscripcion

+ altaDeCategoria(String)
+ obtenerIdActividadesTuristicasAgregadas() : List<String>
+ aceptarORechasarActividadesTuristica(string, bool)


```

### IControladorPaquete
```
+ altaPaquete(String, String, int, float, LocalDate): void
+ obtenerNombrePaquetes(): List<String>
+ agregarActividadAPaquete(String, String): void
+ obtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete(String, String): List<String>

+ obtenerDTPaqueteDetalles(): List<DTPaqueteDetalles>
+ obtenerPaquetes(): List< DTPaquete> // listado web
+ obtenerIdComprasDisponiblesParaInscripcion(string, string) : List<String>
+ comprarPaquete(string, string, int)
```
### IControladorMaestro
```
+ cargarDatosDePrueba(): void
```

### Operaciones que consume la web:
#### Todas:
##### Menú:
```
+ obtenerIdDepartamentos(): List<String>
+ obtenerIdCategorias(): List<String>

```
##### Header:
```
+ obtenerDTUsuario(string) // Para traer el nombre del usuario logueado y su imagen
```

#### consulta_actividad_turistica.html
```
+ obtenerDTActividadTuristicaDetalle(String): DTActividadTuristicaDetalle
```
#### consulta_de_paquete.html
```
+ obtenerDTPaqueteDetalle(String): DTPaqueteDetalle
```
#### consulta_de_salida_turistica.html
```
+ obtenerDTSalidaTuristica(String): DTSalidaTuristica 
```
#### consulta_de_usuario.html
```
+ obtenerDTUsuarios(): List<DTUsuario> // para listar en la página en la que aparecen todos los usuarios
```
#### index.html
```
+ obtenerDTActividadesTuristicasAceptadas(): List<DTActividadTuristica>
+ obtenerDTPaquetes(): List<DTPaquete>
```
#### inscribirse_a_salida.html
```
+ obtenerDTSalidaTuristica(String): DTSalidaTuristica 
+ obtenerIdPaquetesParaInscripcion(nombre_actividad, cant_turistas): List<String>
```
#### perfil_de_usuario_turista_gral.html
```
+ obtenerDTUsuarioDetalle(String): DTTuristaDetalle | DTProveedorDetalle
```
#### perfil_de_usuario_proveedor_gral.html
```
+ obtenerDTUsuarioDetalle(String): DTTuristaDetalle | DTProveedorDetalle
```
#### perfil_de_usuario_turista_privado.html
```
+ obtenerDTUsuarioDetallePrivado(String): DTTuristaDetallePrivado | DTProveedorDetallePrivado
```
#### perfil_de_usuario_proveedor_privado.html
```
+ obtenerDTUsuarioDetallePrivado(String): DTTuristaDetallePrivado | DTProveedorDetallePrivado
```