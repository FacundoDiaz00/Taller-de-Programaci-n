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
```
### IControladorActividadTuristica
```
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

```

### IControladorPaquete
```
+ altaPaquete(String, String, int, float, LocalDate): void
+ obtenerNombrePaquetes(): List<String>
+ agregarActividadAPaquete(String, String): void
+ obtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete(String, String): List<String>

+ obtenerDTPaqueteDetalles(): List<DTPaqueteDetalles>
+ obtenerPaquetes(): List<DTPaquete> // listado web
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
+ obtenerDTUsuario(): List<DTUsuario> // para listar en la página en la que aparecen todos los usuarios
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