# Decisiones técnicas del proyecto

## 1. Introducción

Este documento registra las principales decisiones tomadas durante el diagnóstico, refactoring y verificación del proyecto `gestion-pedidos`, desarrollado con Java y Spring Boot.

El objetivo fue mejorar la mantenibilidad, legibilidad y organización interna del código, procurando conservar el comportamiento funcional esperado.

## 2. Decisiones de refactoring

### DT-01: Aplicar Extract Method en DataLoader

**Problema:** El método `run()` contenía instrucciones repetidas para crear detalles de pedidos.

**Decisión:** Extraer la lógica repetida a un método denominado `agregarDosDetalles()`.

**Justificación:** Reducir la duplicación de código, simplificar el método principal y mejorar su legibilidad.

**Resultado:** Se reorganizó la carga inicial de datos y se verificó la ejecución de la aplicación.

### DT-02: Simplificar el método calcularTotal()

**Problema:** El método `Pedido.calcularTotal()` utilizaba una combinación de operaciones que dificultaba seguir el cálculo.

**Decisión:** Reemplazar esa lógica por un recorrido mediante `for`, acumulando los subtotales válidos.

**Justificación:** Facilitar la comprensión, depuración y mantenimiento del método.

**Resultado:** Se obtuvo una implementación más sencilla, verificada mediante pruebas con JUnit.

### DT-03: Centralizar la fecha de creación

**Problema:** Varios servicios repetían la asignación de `createdAt` mediante `LocalDateTime.now()`.

**Decisión:** Trasladar la inicialización de la fecha a la clase base de las entidades mediante `@PrePersist`.

**Justificación:** Evitar duplicaciones y concentrar una responsabilidad común en un solo componente.

**Resultado:** Se eliminó código repetido de los servicios y se incorporó una prueba de persistencia para verificar la inicialización automática.

### DT-04: Aplicar Introduce Parameter Object

**Problema:** El método encargado de agregar detalles recibía varios parámetros relacionados.

**Decisión:** Crear el `record` `CargaDetallesPedido` para agrupar el identificador del pedido y los dos detalles.

**Justificación:** Simplificar la firma del método y representar explícitamente los datos de la operación.

**Resultado:** El método pasó a recibir un único objeto de parámetros relacionados.

## 3. Corrección de un defecto detectado

Durante las pruebas se identificó que un pedido con un total esperado de $9.000 devolvía $5.000.

El análisis permitió detectar un problema relacionado con la igualdad de las entidades `DetallePedido` almacenadas en un `HashSet`.

Se revisaron y corrigieron los métodos `equals()` y `hashCode()` para evitar que dos entidades nuevas y diferentes fueran consideradas iguales únicamente por no tener un identificador persistido.

Esta modificación se clasificó como **corrección de error (bugfix)** y no como refactoring, porque solucionó un comportamiento incorrecto.

Se incorporó una prueba de regresión para verificar el resultado esperado.

## 4. Estrategia de pruebas

Se decidió utilizar JUnit y Gradle para comprobar el comportamiento de los componentes modificados.

Los casos evaluados incluyeron:

- Pedido sin detalles.
- Pedido con colección de detalles nula.
- Pedido con un producto.
- Pedido con varios productos.
- Inicialización automática de la fecha de creación.
- Inicio del contexto de Spring Boot.

**Resultado registrado:** 6 pruebas ejecutadas, 6 aprobadas y 0 fallidas.

Este resultado respalda los escenarios verificados, aunque no implica una cobertura completa del sistema.

## 5. Versionado y trazabilidad

Se utilizó Git para registrar los cambios de manera incremental.

Las principales decisiones fueron:

- Conservar el estado inicial del proyecto.
- Realizar commits descriptivos para los refactorings y las correcciones.
- Registrar capturas del código antes y después de las modificaciones.
- Utilizar tags para identificar los hitos del trabajo.
- Mantener la documentación técnica en la carpeta `docs`.

## 6. Mejoras pendientes

Durante el diagnóstico y el Code Review también se identificaron otros aspectos que podrían abordarse en futuras iteraciones:

- Sustituir excepciones genéricas por excepciones específicas.
- Incorporar validaciones de cantidades mayores que cero.
- Revisar el manejo seguro de las contraseñas.
- Evaluar la implementación de igualdad en otras entidades.
- Ampliar las pruebas automatizadas de servicios y reglas de negocio.

Estas mejoras quedaron documentadas como deuda técnica pendiente y no se presentan como funcionalidades ya implementadas.

## 7. Conclusión

Las decisiones técnicas se orientaron a mejorar la calidad interna del sistema mediante refactorings concretos, pruebas automatizadas y revisión del código.

El proceso permitió reducir duplicaciones, simplificar métodos, organizar responsabilidades y detectar un defecto funcional mediante las pruebas.

La comparación entre el estado inicial y el estado mejorado, junto con los commits y las evidencias, permite justificar las modificaciones realizadas y registrar las oportunidades de mejora futuras.