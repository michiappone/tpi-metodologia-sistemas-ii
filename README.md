
# TPI - Metodología de Sistemas II

## Sistema de Gestión de Pedidos

Trabajo Práctico Integrador de la Tecnicatura Universitaria en Programación - UTN.

## Descripción

El proyecto consiste en un sistema de gestión de pedidos desarrollado con Java y Spring Boot.

El objetivo del TPI es analizar la calidad interna y externa del sistema, identificar deuda técnica y aplicar refactorings que mejoren la mantenibilidad del código.

## Tecnologías utilizadas

- Java 17
- Spring Boot 4.1.0
- Spring Data JPA
- Hibernate
- Base de datos H2
- Gradle
- Lombok
- JUnit 5

## Funcionalidades

El sistema implementa operaciones para:

- Crear categorías.
- Registrar productos y asociarlos a categorías.
- Registrar usuarios.
- Crear pedidos.
- Agregar productos a pedidos.
- Calcular subtotales y totales.

La carga inicial de datos se realiza mediante DataLoader.

## Instalación y ejecución

Requisitos:
- JDK 17 o compatible con el proyecto.
- Conexión a Internet para descargar dependencias.

1. Clonar el repositorio.
2. Abrir el proyecto en IntelliJ IDEA.
3. Esperar la sincronización de Gradle.
4. Ejecutar GestionPedidosApplication.

También puede ejecutarse desde la terminal:

```powershell
.\gradlew.bat bootRun
```

La aplicación utiliza una base de datos H2 en memoria, configurada en application.properties.

## Pruebas

Para ejecutar las pruebas automatizadas:

```powershell
.\gradlew.bat clean test
```

Se implementaron seis pruebas que verifican:
- Cálculo de pedidos.
- Manejo de detalles nulos.
- Cálculo con varios productos.
- Inicialización automática de fechas.
- Inicio del contexto de Spring Boot.

Resultado obtenido: 6 pruebas aprobadas y 0 fallos.

## Mejoras implementadas

1. Extract Method en DataLoader.
2. Simplificación del cálculo total en Pedido.
3. Centralización de createdAt mediante @PrePersist.
4. Introduce Parameter Object mediante CargaDetallesPedido.
5. Corrección de equals() y hashCode() en DetallePedido.

## Documentación

- [Diagnóstico técnico](docs/diagnostico.md)
- [Code Review](docs/code-review.md)
- Evidencias de ejecución y pruebas en docs/evidencias.

## Versiones del proyecto

Los tags Git permiten identificar los diferentes hitos:

- estado-inicial
- etapa-diagnostico
- etapa-refactoring
- etapa-pruebas
- etapa-docs
- estadorefactorizado

Los dos últimos corresponden al cierre de la documentación y del proyecto.

## Limitaciones y mejoras futuras

- Incorporar validaciones de cantidades.
- Implementar hashing seguro para contraseñas.
- Mejorar el manejo de excepciones.
- Continuar separando las responsabilidades de DataLoader.
- Ampliar la cobertura de pruebas automatizadas.
- Revisar la igualdad de otras entidades JPA.

## Objetivo académico

Demostrar que la calidad del software no depende solamente de su funcionamiento, sino también de la claridad, mantenibilidad, seguridad y posibilidad de evolución del código.
