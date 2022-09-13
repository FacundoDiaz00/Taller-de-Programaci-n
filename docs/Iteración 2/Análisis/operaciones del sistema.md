## Nuevas
  List<String> obtenerIdCategorias() : set(String)

  List<String> obtenerIdActividadesTuristicasConfirmadasPorCategoria(String idCategoria)  

  List<String> obtenerIdCategoriasDisponibleParaInscripcion(String idActividad, String idTurista)
  
  comprarPaquete(idPaquete : string, cantTuristas : int)

  List<DTListadoUsuario> obtenerDTListadoUsuarios();

  obtenerDTUsuarioDetallePrivado(nick: String): DTUsuario (pero devuelve en realidad DTTuristaDetallePrivado y DTProveedorDetallePrivado)

  void altaInscrpicionSalidaTuristica(String nomSalTuri, String nicknameTuris, int cantTuris, Stirng nombrePaquete?) //nueva operacion, se elimina la fecha y se agrega el paquete (este opcional)

## Modificadas 
obtenerIdActividadesTuristicas => obtenerIdActividadesConfirmadasPorDepartamento

agregar parámetro img:Imagen (opcional) al los métodos altaTurista y altaProveedor

agregar parámetro img:Imagen (opcional) y categorías:Set(string) a altaActividadTurística

obtenerDTProveedorDetalle devuelve las actividades QUE HAYAN SIDO APROBADAS

