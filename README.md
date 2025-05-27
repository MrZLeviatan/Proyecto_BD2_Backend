# Proyecto BD2 - Backend

Backend desarrollado en Java con Spring Boot para la gestión de exámenes en línea, parte del proyecto de la materia Bases de Datos II. Este backend se conecta a una base de datos Oracle donde reside la lógica principal implementada en PL/SQL.

---

## Descripción del Proyecto

Esta aplicación permite a los docentes crear, administrar y calificar exámenes en línea, apoyándose en funcionalidades implementadas en la base de datos mediante PL/SQL como:

- Gestión de bancos de preguntas.
- Generación automática de exámenes basados en criterios configurables.
- Calificación automática de respuestas.
- Estadísticas y reportes de desempeño académico.
- Administración de cursos, grupos y horarios para una mejor organización.

El backend ofrece una API REST que interactúa con los procedimientos almacenados, funciones y triggers de Oracle, proporcionando una capa intermedia segura y escalable para la lógica del sistema.

---

## Tecnologías Utilizadas

- Java 11+
- Spring Boot
- Spring Data JPA
