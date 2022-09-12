## Nuevas
  List<String> obtenerIdCategorias() : set(String)

  List<String> obtenerIdActividadesTuristicasConfirmadasPorCategoria(String idCategoria)  

  List<String> obtenerIdCategoriasDisponibleParaInscripcion(String idActividad, String idTurista)
  
  comprarPaquete(idPaquete : string, cantTuristas : int)

  obtenerDTUsuarioDetallePrivado(nick: String): DTUsuario (pero devuelve en realidad DTTuristaDetallePrivado y DTProveedorDetallePrivado)

## Modificadas 
obtenerIdActividadesTuristicas => obtenerIdActividadesConfirmadasPorDepartamento

agregar parametro Paquete al metodo altaInscrpicionSalidaTuristica

agregar parámetro img:Imagen (opcional) al los métodos altaTurista y altaProveedor

agregar parámetro img:Imagen (opcional) y categorías:Set(string) a altaActividadTurística

obtenerDTProveedorDetalle devuelve las actividades QUE HAYAN SIDO APROBADAS

