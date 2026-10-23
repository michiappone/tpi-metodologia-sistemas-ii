
# Code Review - Sistema Gestión de Pedidos

## 1. Objetivo

Evaluar la calidad del código luego de los refactorings realizados, verificar las mejoras y registrar los problemas técnicos pendientes.

## 2. Checklist técnico

| Criterio | Estado | Observación |
|---|---|---|
| Métodos con responsabilidades claras | Parcial | Se extrajo la carga de detalles, pero DataLoader sigue siendo extenso. |
| Código legible y comprensible | Cumple | Se simplificó calcularTotal(). |
| Reducción de código duplicado | Cumple | Se centralizó createdAt mediante @PrePersist. |
| Parámetros organizados | Cumple | Se incorporó CargaDetallesPedido. |
| Validación de cantidades | Pendiente | Falta impedir cantidades menores o iguales a cero. |
| Manejo específico de excepciones | Pendiente | Se siguen utilizando RuntimeException genéricas. |
| Seguridad de contraseñas | Pendiente | Falta implementar hashing seguro. |
| Igualdad de entidades | Parcial | Se corrigió DetallePedido; falta revisar Producto. |
| Pruebas automatizadas | Cumple | Se ejecutaron 6 pruebas sin fallos. |
| Evidencias de funcionamiento | Cumple | Se registraron capturas y resultados de ejecución. |

## 3. Observaciones de la revisión

### Observación 1 - Responsabilidades

DataLoader continúa concentrando gran parte de la carga inicial.

Recomendación: continuar separando la creación de entidades en métodos específicos.

### Observación 2 - Validaciones

DetallePedidoService no valida explícitamente cantidades mayores que cero.

Recomendación: incorporar validaciones para evitar datos inconsistentes.

### Observación 3 - Seguridad

UsuarioService asigna directamente la contraseña recibida.

Recomendación: aplicar un mecanismo de hashing seguro.

### Observación 4 - Pruebas

Las pruebas permitieron detectar un error en la igualdad de DetallePedido.

Corrección realizada: implementación de equals() y hashCode() y agregado de una prueba de regresión.

## 4. Criterios de aceptación técnica

- El proyecto debe compilar correctamente.
- Los refactorings deben estar documentados.
- Las pruebas automatizadas deben finalizar sin fallos.
- Los cambios deben estar registrados en Git.
- Los problemas pendientes deben quedar identificados y justificados.

## 5. Resultado de la revisión

Los cuatro refactorings están implementados y las seis pruebas automatizadas finalizaron correctamente.

La revisión detectó mejoras en legibilidad, responsabilidades y organización del código.

También quedaron identificadas oportunidades de mejora relacionadas con validaciones, seguridad y manejo de excepciones.

El sistema cumple los criterios de verificación definidos para esta etapa, aunque la revisión no implica que todos los problemas técnicos originales estén resueltos.
