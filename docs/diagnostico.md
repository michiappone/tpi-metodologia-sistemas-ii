# Diagnóstico de calidad y deuda técnica

## Trabajo Práctico Integrador - Metodología de Sistemas II

**Sistema:** Gestión de Pedidos
**Tecnologías:** Java, Spring Boot, Spring Data JPA y H2.

## 1. Objetivo

Analizar la calidad interna y externa del sistema Gestión de Pedidos, identificar problemas de diseño e implementación y proponer mejoras basadas en SOLID, Clean Code y técnicas de refactoring.

## 2. Calidad externa e interna

### Calidad externa

El sistema permite registrar entidades y gestionar pedidos mediante operaciones implementadas en sus servicios.

Durante la ejecución inicial se observó que Hibernate realiza operaciones de consulta, inserción y actualización sobre la base de datos.

La consola mostró el mensaje "DATOS CARGADOS CORRECTAMENTE".

### Calidad interna

Aunque el sistema ejecuta la carga inicial de datos, se identificaron problemas relacionados con la legibilidad, duplicación de código, validación de datos, manejo de errores, seguridad y cobertura de pruebas.

Estos problemas representan deuda técnica que podría dificultar el mantenimiento y la evolución del software.

## 3. Problemas técnicos identificados

### Problema 1 - Long Method

- **Archivo:** DataLoader.java
- **Ubicación:** Método run()
- **Evidencia:** 01_antes_DataLoader.png
- **Descripción:** El método concentra la creación de categorías, productos, usuarios y pedidos.
- **Impacto:** Dificulta la lectura y el mantenimiento.
- **Riesgo:** Mayor posibilidad de errores al modificar la carga inicial.
- **Prioridad:** Media.
- **Mejora propuesta:** Aplicar Extract Method para separar responsabilidades.

### Problema 2 - Excepciones genéricas

- **Archivo:** DetallePedidoService.java
- **Ubicación:** Método agregarDetalle()
- **Evidencia:** 02_antes_DetallePedidoService.png
- **Descripción:** Se utilizan excepciones RuntimeException para pedidos y productos inexistentes.
- **Impacto:** Dificulta identificar y manejar errores específicos.
- **Riesgo:** Tratamiento inadecuado de errores.
- **Prioridad:** Media.
- **Mejora propuesta:** Utilizar excepciones más específicas.

### Problema 3 - Falta de validaciones

- **Archivo:** DetallePedidoCreate.java y DetallePedidoService.java
- **Evidencia:** 03_antes_DetallePedidoCreate.png
- **Descripción:** No se valida que la cantidad solicitada sea mayor que cero.
- **Impacto:** Posibilidad de registrar cantidades inválidas.
- **Riesgo:** Inconsistencias en los importes de pedidos.
- **Prioridad:** Alta.
- **Mejora propuesta:** Incorporar validaciones de cantidad.

### Problema 4 - Complejidad innecesaria

- **Archivo:** Pedido.java
- **Ubicación:** Método calcularTotal()
- **Evidencia:** 04_antes_Pedido_calcularTotal.png
- **Descripción:** Se utilizan operaciones Optional y Stream anidadas para calcular el total.
- **Impacto:** Reduce la legibilidad.
- **Riesgo:** Mayor dificultad para modificar o depurar el cálculo.
- **Prioridad:** Media.
- **Mejora propuesta:** Simplificar el método conservando su comportamiento.

### Problema 5 - Identidad basada en atributos modificables

- **Archivo:** Producto.java
- **Evidencia:** 05_antes_Producto_identidad.png
- **Descripción:** equals() y hashCode() utilizan el atributo nombre mediante Lombok.
- **Impacto:** Dos productos diferentes con el mismo nombre pueden considerarse iguales.
- **Riesgo:** Comportamiento incorrecto en colecciones y comparaciones.
- **Prioridad:** Alta.
- **Mejora propuesta:** Revisar la implementación de igualdad considerando la identidad de la entidad JPA.

### Problema 6 - Código duplicado

- **Archivos:** CategoriaService.java, ProductoService.java, UsuarioService.java, PedidoService.java y DetallePedidoService.java
- **Evidencia:** 06_antes_CategoriaService.png
- **Descripción:** Se repiten instrucciones para inicializar los atributos eliminado y createdAt.
- **Impacto:** Aumenta la duplicación y el costo de mantenimiento.
- **Riesgo:** Inconsistencias al modificar la inicialización.
- **Prioridad:** Media.
- **Mejora propuesta:** Centralizar responsabilidades comunes en Base.java.

### Problema 7 - Contraseñas sin protección

- **Archivo:** UsuarioService.java
- **Evidencia:** 07_antes_UsuarioService.png
- **Descripción:** La contraseña se asigna directamente desde el DTO sin aplicar hashing.
- **Impacto:** Exposición potencial de información sensible.
- **Riesgo:** Compromiso de credenciales almacenadas.
- **Prioridad:** Alta.
- **Mejora propuesta:** Incorporar hashing seguro con BCrypt.

### Problema 8 - Cobertura de pruebas insuficiente

- **Archivo:** GestionPedidosApplicationTests.java
- **Evidencia:** 08_antes_Pruebas.png
- **Descripción:** Solamente existe una prueba contextLoads(), que no verifica las operaciones funcionales.
- **Impacto:** No se detectan automáticamente regresiones funcionales.
- **Riesgo:** Introducción de errores durante los refactorings.
- **Prioridad:** Alta.
- **Mejora propuesta:** Incorporar pruebas automatizadas con JUnit.

## 4. Conclusión del diagnóstico

El sistema presenta funcionalidades implementadas y evidencia de ejecución de la carga inicial, pero contiene problemas de calidad interna que requieren mejoras.

Se identificaron ocho debilidades técnicas, destacándose problemas de validación, seguridad, identidad de entidades y cobertura de pruebas.

El siguiente paso será establecer pruebas del comportamiento original y aplicar refactorings controlados, documentando las modificaciones y verificando que no se introduzcan regresiones.
