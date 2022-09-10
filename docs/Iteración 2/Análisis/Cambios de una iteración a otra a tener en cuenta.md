# Modificaciones de la logica del sistema de una tarea a la otra para tener en cuenta:

## Alta de usuario:
- Los usuarios nuevos pueden incluir una imagen si su alta es desde la web

## Consulta de usuario:
- Se debe mostrar su imágen (si tiene)
- Del proveedor se muestran solo las actividades confirmadas, siendo la única excepción el caso en el que consulte su propio perfil.
- Si un turista consulta su propio perfil, se debe mostrar información extra de las salidas y las compras de paquetes hechas por él
- La selección de un **paquete** (además de salida y actividad) deberá mostrar info detallada del mismo (¿redirección a esa web?)

## Modificar datos de usuario
- También se podría modificar la imágen

## Alta de actividad turística:
- Pueden incluir una imagen si es desde la web
- Hay que seleccionar al menos una categoría.
- Siempre se crea la instancia con estado 'Agregada'

## Consulta de actividad turística
- Muestra solo confirmadas
- (solo web) Se debe mostrar la imágen
- Se deben listar las categorías asociadas

## Alta de salida
- Pueden incluir una imagen si es desde la web
- Solo relacionadas a act. confirmadas


## Consulta de salida
- se listan solo las act. confirmadas
- se muestra la imagen (solo web)

## Inscripcion a salida
- se listan solo las act. confirmadas
- se debe indicar la forma de pago (normal o paquete)
- si hay varios paquetes comprados que incluyan esta actividad **y** tengan esta cantidad de inscripciones disponibles **y** estén vigentes, se debe seleccionar una de las compras

## Consulta de paquete
- Se muestran las imágenes de las act. asociadas
- Se listan todas las categorías asociadas

## Compra de paquete
**completamente nuevo**

