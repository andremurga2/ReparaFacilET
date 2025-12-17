# ReparaFácil

## Nombre de la aplicación

**ReparaFácil**

---

## 👥 Integrantes del proyecto

* **Benjamin Verdejo**
* **Andre Murga**

---

## Descripción general

**ReparaFácil** es una aplicación móvil Android desarrollada en **Kotlin**, orientada a la **gestión de servicios técnicos**. El sistema permite a distintos tipos de usuarios interactuar según su rol (**cliente, técnico y administrador**), facilitando la solicitud, asignación y seguimiento de servicios.

La aplicación utiliza **Jetpack Compose** para la interfaz gráfica y sigue una arquitectura por capas, consumiendo servicios a través de **APIs REST**.

---

## Objetivo del proyecto

Desarrollar una aplicación Android funcional que demuestre:

* Uso de Kotlin y Jetpack Compose
* Arquitectura limpia y separación de responsabilidades
* Consumo de APIs REST
* Manejo de estados y navegación
* Control de acceso mediante roles

---

## ⚙️ Funcionalidades principales

### - Autenticación

* Inicio de sesión de usuarios
* Registro de nuevos usuarios
* Persistencia de sesión mediante token
* Redirección automática según rol

### - Cliente

* Creación de solicitudes de servicio
* Visualización de servicios asociados
* Detalle y seguimiento del estado del servicio
* Visualización de notificaciones

### - Técnico

* Visualización de servicios asignados
* Consulta de detalle y seguimiento del servicio

### - Administrador

* Supervisión general del sistema
* Gestión y control de información

---

## Endpoints utilizados

### - Endpoints propios (Backend)

Consumidos desde la app móvil mediante **Retrofit**:

* `POST /auth/login` → Inicio de sesión
* `POST /auth/register` → Registro de usuario
* `GET /servicios` → Obtención de servicios
* `POST /servicios` → Creación de servicio
* `GET /servicios/{id}` → Detalle de servicio
* `GET /seguimiento/{servicioId}` → Seguimiento del servicio
* `GET /notificaciones` → Notificaciones del usuario

---

## Arquitectura del proyecto

El proyecto está organizado en capas:

### - data

* `remote`: definición de APIs y DTOs
* `repository`: lógica de acceso a datos
* `local`: manejo de sesión (`SessionManager`)

### - ui

* `screens`: pantallas organizadas por rol
* `viewmodel`: manejo de estado
* `navigation`: control de navegación
* `theme`: estilos y colores

---

## Instrucciones para ejecutar el proyecto

1. Clonar el repositorio:

```bash
git clone https://github.com/andremurga2/ReparaFacilET.git
```

2. Abrir el proyecto en **Android Studio**
3. Sincronizar Gradle
4. Ejecutar en un emulador o dispositivo físico

---

## APK firmado

* El APK firmado se genera en modo **Release**
* Ruta de salida:

```
app/build/outputs/apk/release/app-release.apk
```

### Keystore

* El archivo `.jks` se mantiene fuera del repositorio por seguridad
* Ejemplo de ubicación local:

```
C:/Users/benja/Documents/keystore/reparafacil-release.jks
```

---

## Código fuente

* El código fuente de la **aplicación móvil Android** está incluido en este repositorio
* El backend se comunica mediante **API REST**

---

## Estado del proyecto

* ✔ Autenticación funcional
* ✔ Gestión de servicios
* ✔ Navegación por roles
* ✔ Creación y seguimiento de servicios
* ✔ APK Release generado

---

## Contexto académico

Proyecto desarrollado como parte de una **evaluación**, cumpliendo los requisitos técnicos y funcionales solicitados.

---

> ReparaFácil — gestión de servicios técnicos
