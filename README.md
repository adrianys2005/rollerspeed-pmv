# 🛼 Escuela de Patinaje "Roller Speed" - Santa Marta, Colombia
## Evidencia de Aprendizaje 3 (EA3) - Documentación de la API REST con Swagger / OpenAPI

**Institución Universitaria Digital de Antioquia (IU Digital)**  
**Facultad de Ingeniería – Tecnología en Desarrollo de Software**  
**Asignatura:** Frameworks  
**Unidad 3:** Documentación y Consumo de APIs RESTful en Aplicaciones Monolíticas  
**Tecnologías:** Spring Boot 3.3.3, Java 17/21, Springdoc OpenAPI 2.6.0 (Swagger UI), Thymeleaf, Bootstrap 5.  

---

## 👥 1. Conformación del Grupo de Trabajo y Roles Técnicos (EA3)

En cumplimiento de los criterios de evaluación y el trabajo colaborativo en equipos de 3 estudiantes:

| Integrante | Rol en el Proyecto | Responsabilidades Técnicas y Entregables |
| :--- | :--- | :--- |
| **Adrianys Saumeth** | **Líder Técnico & Desarrollador Backend REST** | • Arquitectura de la API REST y configuración de Springdoc OpenAPI 3.0 (`OpenApiConfig`).<br>• Implementación de `@RestController` para Aspirantes, Alumnos y Pagos.<br>• Mapeo de códigos de respuesta HTTP (200, 201, 400, 404) y administración de Git. |
| **Simón Cano Rojas** | **Ingeniero de Integración & Documentador OpenAPI** | • Enriquecimiento semántico de la documentación con `@Tag`, `@Operation`, `@Parameter` y `@Schema`.<br>• Implementación de `@RestController` para Clases, Instructores y Asistencia.<br>• Pruebas de contrato y validación de interoperabilidad desde Swagger UI. |
| **Yulian Germayony Mosquera Cetre** *(o asignado por grupo)* | **Especialista de Calidad (QA) & Documentación** | • Pruebas automatizadas de endpoints REST mediante herramientas HTTP / curl.<br>• Verificación de esquemas JSON en `/v3/api-docs` y validación de flujos CRUD.<br>• Redacción del informe técnico formal y estructuración del video demostrativo. |

---

## 📋 2. Contexto y Objetivos del Proyecto

La escuela **"Roller Speed"** (Santa Marta) opera bajo un modelo de arquitectura monolítica moderna en Spring Boot que combina:
1. **Frontend Web Monolítico (Thymeleaf):** Para la navegación web institucional y formularios de usuario.
2. **API RESTful Documentada (Swagger / OpenAPI):** Para permitir el consumo de datos desde aplicaciones móviles de padres de familia, sistemas externos de pasarelas de pago y tableros analíticos.

### 🎯 Objetivos de la Evidencia:
* Integrar la librería oficial `springdoc-openapi-starter-webmvc-ui` (v2.6.0) para Spring Boot 3.
* Documentar de forma exhaustiva los **7 módulos** del sistema: Aspirantes, Alumnos, Instructores, Clases, Pagos, Asistencia e Información Institucional.
* Proveer una interfaz interactiva Swagger UI accesible en tiempo real para pruebas de desarrollo e integración.

---

## 🌐 3. Catálogo General de Endpoints Documentados (19 Endpoints)

| Módulo | Método | URI / Endpoint | Descripción Funcional | Parámetros / Request Body | Códigos HTTP |
| :--- | :---: | :--- | :--- | :--- | :---: |
| **1. Aspirantes** | `GET` | `/api/v1/aspirantes` | Listar todos los aspirantes registrados | Ninguno | `200 OK` |
| **1. Aspirantes** | `GET` | `/api/v1/aspirantes/{id}` | Consultar aspirante por identificador | `id` (path, Long) | `200 OK`, `404 Not Found` |
| **1. Aspirantes** | `POST` | `/api/v1/aspirantes` | Registrar nuevo aspirante (alta y pago auto) | `Aspirante` (JSON Body) | `201 Created`, `400 Bad Request` |
| **2. Alumnos** | `GET` | `/api/v1/alumnos` | Directorio general de alumnos matriculados | Ninguno | `200 OK` |
| **2. Alumnos** | `GET` | `/api/v1/alumnos/{id}` | Consultar ficha técnica del alumno | `id` (path, Long) | `200 OK`, `404 Not Found` |
| **2. Alumnos** | `GET` | `/api/v1/alumnos/nivel/{nivel}` | Filtrar alumnos por categoría deportiva | `nivel` (Principiante, Intermedio, Avanzado) | `200 OK` |
| **2. Alumnos** | `PATCH` | `/api/v1/alumnos/{id}/estado` | Modificar estado de matrícula | `id` (path), `estado` (query) | `200 OK`, `404 Not Found` |
| **3. Instructores** | `GET` | `/api/v1/instructores` | Listar cuerpo técnico y entrenadores | Ninguno | `200 OK` |
| **3. Instructores** | `GET` | `/api/v1/instructores/{id}` | Consultar perfil y horarios del instructor | `id` (path, Long) | `200 OK`, `404 Not Found` |
| **4. Clases** | `GET` | `/api/v1/clases` | Grilla de clases con cálculo dinámico de cupos | Ninguno | `200 OK` |
| **4. Clases** | `GET` | `/api/v1/clases/{id}` | Consultar detalles de clase y pista asignada | `id` (path, Long) | `200 OK`, `404 Not Found` |
| **4. Clases** | `POST` | `/api/v1/clases` | Programar nueva sesión de entrenamiento | `Clase` (JSON Body) | `201 Created`, `400 Bad Request` |
| **5. Pagos** | `GET` | `/api/v1/pagos` | Historial de transacciones y mensualidades | Ninguno | `200 OK` |
| **5. Pagos** | `GET` | `/api/v1/pagos/{id}` | Consultar comprobante de pago | `id` (path, Long) | `200 OK`, `404 Not Found` |
| **5. Pagos** | `GET` | `/api/v1/pagos/metricas` | Balance financiero (recaudo, pendientes) | Ninguno | `200 OK` |
| **5. Pagos** | `POST` | `/api/v1/pagos` | Registrar nuevo comprobante de pago | `Pago` (JSON Body) | `201 Created`, `400 Bad Request` |
| **6. Asistencia** | `GET` | `/api/v1/asistencias` | Registro global de asistencias en pista | Ninguno | `200 OK` |
| **6. Asistencia** | `GET` | `/api/v1/asistencias/alumno/{alumnoId}` | Historial de asistencias por alumno | `alumnoId` (path, Long) | `200 OK` |
| **6. Asistencia** | `GET` | `/api/v1/asistencias/clase/{claseId}` | Lista de asistencia de una clase | `claseId` (path, Long) | `200 OK` |
| **6. Asistencia** | `POST` | `/api/v1/asistencias` | Marcar asistencia (Presente, Ausente, Justificado) | `Asistencia` (JSON Body) | `201 Created`, `400 Bad Request` |
| **7. Institucional** | `GET` | `/api/v1/institucional/servicios` | Catálogo de planes y mensualidades | Ninguno | `200 OK` |
| **7. Institucional** | `GET` | `/api/v1/institucional/eventos` | Cronograma de válidas y torneos | Ninguno | `200 OK` |
| **7. Institucional** | `GET` | `/api/v1/institucional/corporativo` | Misión, visión y valores corporativos | Ninguno | `200 OK` |

---

## 🚀 4. Acceso a Swagger UI y Especificación OpenAPI

Una vez iniciada la aplicación en `http://localhost:8080`:

* 🖥️ **Interfaz Gráfica Swagger UI:**  
  [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
* 📄 **Especificación OpenAPI 3.0 (JSON):**  
  [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## ⚙️ 5. Instrucciones de Ejecución Local

```bash
# 1. Clonar el repositorio oficial
git clone https://github.com/adrianys2005/rollerspeed-pmv.git
cd rollerspeed

# 2. Iniciar el servidor Spring Boot
./mvnw spring-boot:run

# 3. Abrir la documentación interactiva en el navegador
http://localhost:8080/swagger-ui/index.html
```
