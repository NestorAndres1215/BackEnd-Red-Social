# 🔧 Proyecto de Red Social - Backend

Este proyecto es el backend de una red social. Está hecho con Java y Spring Boot. Proporciona una API REST para manejar usuarios, publicaciones, comentarios, solicitudes de amistad y autenticación con JWT. Los usuarios tienen roles como USER, MODERADOR y ADMIN, y cada uno puede realizar acciones según su rol.

---

## 📌 Funcionalidades principales

- 📝 Registro y login de usuarios con validación de credenciales
- 🔑 Verificación de cuenta por código enviado al correo electrónico
- 👤 Gestión de perfiles de usuario: actualización de datos personales, imagen de perfil, etc.
- 🔐 Autenticación y autorización mediante JWT
- 🛡️ Control de acceso por roles (USER, MODERADOR, ADMIN)
- 📧 Envío de correos automáticos si la cuenta está suspendida, bloqueada o inactiva
- ✉️ Recuperación de contraseña mediante enlace enviado al correo
- 🔍 Búsqueda de usuarios por nombre o correo
- ⛔ Moderación: los moderadores pueden suspender o reactivar cuentas
- 📊 Panel de administración para gestión de usuarios, roles y estados (en desarrollo)
- 🕒 Auditoría básica: timestamps de creación/modificación en entidades
- ⚙️ Configuración dinámica de roles y permisos (según implementación)

---

## 🛠️ Tecnologías utilizadas

- ☕ Java 17+
- 🌱 Spring Boot
- 🔐 Spring Security con JWT para autenticación
- 📬 Spring Boot Starter Mail (para envío de correos de verificación o suspensión)
- 🛠️ Spring DevTools (para desarrollo en caliente)
- 🌐 Spring Web (para construir la API REST)
- 🗃️ Spring Data JPA con Hibernate
- 💾 MySQL como base de datos
- 📦 Maven como gestor de dependencias y compilación
- 📤 JAXB API para serialización/deserialización XML (opcional)

---

## 🚀 Características destacadas

- 🔐 Autenticación y autorización mediante JWT
- 👤 Gestión de usuarios con roles: USER, MODERADOR, ADMIN
- 📝 Publicaciones: crear, editar, eliminar y listar
- 💬 Comentarios en publicaciones
- 🤝 Solicitudes de amistad y gestión de contactos
- 📧 Verificación por correo electrónico (activación, suspensión, bloqueo)
- 🛡️ Control de acceso según estado de cuenta: activa, inactiva, suspendida, bloqueada
- 📊 Panel de administración (en desarrollo)
- 👤 **Gestión de usuarios**: registro, inicio de sesión, actualización de perfil y eliminación
- 🧩 **Arquitectura limpia**: uso de controladores, servicios, repositorios y DTOs

---

## 🧰 Requisitos previos

- ☕ Java 17 o superior
- 📦 Maven instalado y configurado en el `PATH`
- 🐬 MySQL en ejecución con:
  - Base de datos creada (por ejemplo: `red_social_db`)
  - Usuario y contraseña configurados en `application.properties` o `application.yml`

---

## 📁 Estructura del Proyecto

El proyecto está organizado en una arquitectura por capas:

### 📂 controller/
Encargados de manejar solicitudes HTTP (`@RestController`):
- Registro y login
- Gestión de publicaciones y comentarios
- Administración de usuarios y roles
- Acciones protegidas por rol

### 📂 service/
Contiene la lógica de negocio:
- Procesamiento de usuarios, roles y autenticación
- Generación y validación de JWT
- Envío de correos (verificación, recuperación, suspensión)

### 📂 repository/
Interfaces que extienden `JpaRepository`:
- CRUD de entidades
- Consultas personalizadas con `@Query`

### 📂 dto/
Objetos de transferencia de datos:
- `UsuarioDTO`, `LoginRequest`, `RegistroResponse`, etc.

### 📂 model/ o entity/
Clases que representan entidades de la base de datos:
- `Usuario`, `Rol`, `Publicacion`, `Comentario`, etc.
- Mapeadas con anotaciones JPA

### 📂 security/ o config/
Configuraciones de seguridad con Spring Security:
- Filtros JWT
- Rutas públicas/protegidas
- Codificación de contraseñas
- Configuración de CORS

### 📂 exception/ (opcional)
Manejo centralizado de errores personalizados:
- Usuario no encontrado
- Acceso no autorizado
- Datos inválidos

### 📂 util/ (opcional)
Clases utilitarias:
- Helpers, validadores, formatos de fecha, generadores de tokens, etc.

---

## 🛠️ Crear base de datos

Antes de ejecutar la aplicación, crea la base de datos en MySQL:

```sql
CREATE DATABASE red_social_db;
