## Nuevas
  List<String> obtenerIdCategorias() : set(String)

  List<String> obtenerIdActividadesTuristicasConfirmadasPorCategoria(String idCategoria)  

  List<String> obtenerIdCategoriasDisponibleParaInscripcion(String idActividad, String idTurista)

## Modificadas 
obtenerIdActividadesTuristicas => obtenerIdActividadesConfirmadasPorDepartamento

agregar parametro Paquete al metodo altaInscrpicionSalidaTuristica

agregar parámetro Imagen (opcional) al los métodos altaTurista y altaProveedor

obtenerDTProveedorDetalle devuelve las actividades QUE HAYAN SIDO APROBADAS

