# Contrato de CU : Alta de Usuario.

| Nombre    | Alta de Proveedor                                         |
|-----------|----------------------------------------------------------------|
| Operación | `altaProveedor(nickname, nombre, apellido, correo, descripcion, url : string, FNacimiento: DTFecha) : bool` |
| Entrada | `nickname`: de tipo string, corresponde al nickname del proveedor que se desea registrar.<br/> `nombre` : IDEM anterior, pero corresponde al nombre. <br/>`apellido` : IDEM anterior, pero corresponde al apellido. <br/> `correo`: IDEM anterior, pero corresponde a la dirección de correo electrónico. <br/> `descripcion`: IDEM anterior, pero corresponde a una descripción general.<br/>`url`: IDEM anterior, pero corresponde al link a su sitio web. (Esta entrada es opcional). <br/> `FNacimiento`: Corresponde a la fecha de nacimiento del Proveedor que se desea registrar |
| Salida  | Retorna `falso` en caso de que exista un usuario registrado con el `nickname` ingresado o con el `correo` ingresado, en otro caso retorna `verdadero`. |
| Descripción | Se ingresan los datos correspondientes al proveedor que se desea dar de alta en el sistema, y se verifica que no exista otro usuario con el mismo nickame o correo, si se cumple esta verificación entonces se crea un nuevo Proveedor con esos datos. |
| Excepciones | No hay. |
| Precondiciones  | No hay. |
| Postcondiciones | Si no existe una instancia `U` de `Usuario` tal que `U.nickname == nickname` o `U.correo == correo` entonces se crea una nueva instancia de `Proveedor` con  los datos de entrada y se retorna `verdadero`. <br/> En caso contrario se retorna `falso`.|

| Nombre    | Alta de Turista                                         |
|-----------|----------------------------------------------------------------|
| Operación | `altaTurista(nickname, nombre, apellido, correo, nacionalidad : string, FNacimiento: DTFecha) : bool` |
| Entrada | `nickname`: de tipo string, corresponde al nickname del turista que se desea registrar.<br/> `nombre` : IDEM anterior, pero corresponde al nombre. <br/>`apellido` : IDEM anterior, pero corresponde al apellido. <br/> `correo`: IDEM anterior, pero corresponde a la dirección de correo electrónico. <br/> `Nacionalidad`: IDEM anterior, pero corresponde a la nacionalidad. <br/> `FNacimiento`: Corresponde a la fecha de nacimiento del Turista que se desea registrar |
| Salida  | Retorna `falso` en caso de que exista un usuario registrado con el `nickname` ingresado o con el `correo` ingresado, en otro caso retorna `verdadero`. |
| Descripción | Se ingresan los datos correspondientes al turista que se desea dar de alta en el sistema, y se verifica que no exista otro usuario con el mismo nickame o correo, si se cumple esta verificación entonces se crea un nuevo Turista con esos datos. |
| Excepciones | No hay. |
| Precondiciones  | No hay. |
| Postcondiciones | Si no existe una instancia `U` de `Usuario` tal que `U.nickname == nickname` o `U.correo == correo` entonces se crea una nueva instancia de `Turista` con  los datos de entrada y se retorna `verdadero`. <br/> En caso contrario se retorna `falso`.|