# 🔧 Proyecto de Red Social - Backend

Este proyecto es el backend de una red social, desarrollado con **Java** y **Spring Boot**. Proporciona una **API RESTful** para gestionar usuarios, publicaciones, comentarios, autenticación con JWT y control de acceso según roles.

---

## ✨ Vista General del Proyecto

| Característica         | Descripción                                                                 |
|------------------------|-----------------------------------------------------------------------------|
| 🧱 Arquitectura         | Basada en capas: Controller, Service, Repository, DTO, Entity, Security     |
| 👤 Roles de usuario     | USER, MODERADOR, ADMIN                                                      |
| 🛡️ Seguridad            | Autenticación y autorización mediante JWT                                   |
| 📨 Notificaciones       | Correos para activación, suspensión o recuperación                          |
| 🔄 Estados de cuenta    | Activa, Inactiva, Suspendida, Bloqueada                                     |
| 📊 Panel administrativo | Para gestión avanzada (en desarrollo)                                       |

---

## 📌 Funcionalidades Principales

| Funcionalidad                                   | Estado      |
|--------------------------------------------------|-------------|
| 📝 Registro y login con validación               | ✅ Implementado |
| 🔑 Verificación por código (email)               | ✅ Implementado |
| 👤 Gestión de perfil e imagen                    | ✅ Implementado |
| 🔐 Seguridad con JWT y roles                     | ✅ Implementado |
| 📧 Correos automáticos según estado              | ✅ Implementado |
| ✉️ Recuperación de contraseña                    | ✅ Implementado |
| 🔍 Búsqueda de usuarios                          | ✅ Implementado |
| ⛔ Moderación de cuentas                         | ✅ Implementado |
| 📊 Panel de administración                       | 🧪 En desarrollo |
| 🕒 Auditoría de creación/modificación            | ✅ Implementado |

---

## 🛠️ Tecnologías Utilizadas

| Tecnología                | Uso en el Proyecto                                |
|---------------------------|----------------------------------------------------|
| ☕ Java 17+               | Lenguaje principal                                 |
| 🌱 Spring Boot           | Framework principal del backend                    |
| 🔐 Spring Security + JWT | Autenticación y autorización                       |
| 📬 Spring Mail           | Envío de correos electrónicos                      |
| 🗃️ Spring Data JPA       | Acceso a base de datos con Hibernate               |
| 🌐 Spring Web            | Construcción de la API REST                        |
| 💾 MySQL                 | Base de datos relacional                           |
| 📦 Maven                 | Gestión de dependencias y build                   |
| 🛠️ DevTools             | Recarga automática en desarrollo                   |
| 📤 JAXB (opcional)       | Soporte XML en caso necesario                      |

---

## 🧰 Requisitos Previos

| Requisito            | Versión mínima | Estado |
|----------------------|----------------|--------|
| Java                 | 17+            | ✅     |
| Maven                | Cualquier versión reciente | ✅     |
| MySQL                | Activo y con base de datos `red_social_db` creada | ✅     |

> Asegúrate de configurar tus credenciales en `application.properties` o `application.yml`.
