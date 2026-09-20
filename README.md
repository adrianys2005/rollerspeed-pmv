# 🛼 Escuela de Patinaje "Roller Speed" - Santa Marta, Colombia
## Producto Mínimo Viable (PMV) - Evidencia de Aprendizaje 2 (EA2)

**Institución Universitaria Digital de Antioquia (IU Digital)**  
**Facultad de Ingeniería – Tecnología en Desarrollo de Software**  
**Asignatura:** Frameworks  
**Unidad 2:** Arquitectura MVC y Motor de Plantillas Thymeleaf  

---

## 👥 1. Conformación del Grupo de Trabajo y Roles

| Integrante | Rol en el Proyecto | Responsabilidades Principales |
| :--- | :--- | :--- |
| **Adrianys Saumeth** | **Líder de Proyecto & Desarrollador Backend MVC** | Diseño de arquitectura, implementación de clases controladoras (`@Controller`), capa de servicios (`@Service`), modelos de dominio y gestión del repositorio Git. |
| **Equipo Colaborativo** | **Especialista Frontend & Motor Thymeleaf** | Maquetación responsiva con Bootstrap 5, diseño de vistas dinámicas con directivas Thymeleaf (`th:each`, `th:object`, `th:field`, `th:replace`). |
| **Equipo Colaborativo** | **Aseguramiento de Calidad & GitOps** | Pruebas de integración de formularios, validación de endpoints HTTP y documentación técnica. |

---

## 🚀 2. Descripción del Caso de Estudio

La escuela de patinaje **"Roller Speed"** en Santa Marta, Colombia, requería superar procesos manuales que generaban demoras y errores en la inscripción, control de mensualidades y asignación de clases.

Para esta **Evidencia de Aprendizaje 2 (EA2)**, se consolidó el **Producto Mínimo Viable (PMV)** aplicando rigurosamente el patrón **MVC (Model-View-Controller)** en Spring Boot con **Thymeleaf**, logrando que:
1. Los aspirantes se registren autónomamente ingresando datos personales y medio de pago sin requerir aprobación manual.
2. Se gestione el directorio de alumnos y estado de pagos en tiempo real.
3. Se publiquen los perfiles de los instructores y las clases con cálculo dinámico de cupos.
4. El contenido institucional (servicios, eventos, filosofía corporativa) se renderice dinámicamente desde el backend.

---

## 🏗️ 3. Arquitectura del Sistema (Patrón MVC)

```
rollerspeed/
├── src/main/java/com/rollerspeed/
│   ├── RollerspeedApplication.java
│   ├── controller/               # Capa Controladora (Spring MVC)
│   │   ├── HomeController.java          -> Rutas institucionales y métricas del dashboard
│   │   ├── InscripcionController.java   -> Formulario y procesamiento de nuevos alumnos
│   │   ├── AlumnoController.java        -> Directorio de alumnos registrados
│   │   ├── InstructorController.java    -> Visualización del cuerpo técnico
│   │   ├── ClaseController.java         -> Programación de clases, pistas y cupos
│   │   └── PagoController.java          -> Control financiero y comprobantes
│   ├── model/                    # Capa de Dominio (Modelos de Datos)
│   │   ├── Aspirante.java               -> Entidad del aspirante/alumno
│   │   ├── Instructor.java              -> Entidad de entrenadores
│   │   ├── Clase.java                   -> Entidad de horarios y cupos
│   │   ├── Pago.java                    -> Entidad de transacciones y estados
│   │   ├── Servicio.java                -> Entidad de catálogo de servicios
│   │   └── Evento.java                  -> Entidad de eventos y válidas
│   └── service/                  # Capa de Negocio / Servicios
│       ├── AspiranteService.java        -> Lógica de registro y alta automática
│       ├── InstructorService.java       -> Catálogo del cuerpo técnico
│       ├── ClaseService.java            -> Lógica de cupos y horarios
│       ├── PagoService.java             -> Registro de comprobantes y totales
│       └── InstitucionalService.java    -> Suministro de servicios y eventos
└── src/main/resources/
    └── templates/                # Capa de Presentación (Vistas Thymeleaf)
        ├── fragments/
        │   ├── navbar.html              -> Barra de navegación responsiva del PMV
        │   └── footer.html              -> Pie de página institucional reutilizable
        ├── index.html                   -> Dashboard principal con métricas en vivo
        ├── inscripcion.html             -> Formulario interactivo con binding de modelo
        ├── alumnos.html                 -> Tabla de alumnos con badges y estados
        ├── instructores.html            -> Tarjetas de presentación del cuerpo técnico
        ├── clases.html                  -> Grilla de horarios y barras de ocupación
        ├── pagos.html                   -> Reporte financiero y transacciones
        ├── servicios.html               -> Catálogo de planes con precios
        ├── eventos.html                 -> Cronograma deportivo
        ├── mision.html                  -> Misión corporativa
        ├── vision.html                  -> Visión 2030
        └── valores.html                 -> Principios corporativos
```

---

## 🌐 4. Rutas y Opciones del Menú Implementadas

| Opción de Menú | Ruta / Endpoint | Método HTTP | Descripción Funcional |
| :--- | :--- | :---: | :--- |
| **Inicio** | `/` | `GET` | Dashboard general con métricas en vivo (total alumnos, instructores, clases) y accesos directos. |
| **Inscripción** | `/inscripcion` | `GET` / `POST` | Formulario en línea con enlace de objeto `th:object="${aspirante}"`. Al enviar vía POST, da de alta automáticamente al alumno y genera su recibo de pago. |
| **Alumnos** | `/alumnos` | `GET` | Directorio interactivo con badges de nivel deportivo (Principiante, Intermedio, Avanzado). |
| **Instructores** | `/instructores` | `GET` | Tarjetas dinámicas con especialidad, experiencia y horarios del cuerpo técnico. |
| **Clases y Horarios** | `/clases` | `GET` | Programación por pistas con cálculo en tiempo real de cupos disponibles y barras de progreso. |
| **Pagos** | `/pagos` | `GET` | Historial de recibos con balance total recaudado y control de pagos pendientes. |
| **Servicios** | `/servicios` | `GET` | Catálogo deportivo con tarifas mensuales renderizado con `th:each`. |
| **Eventos** | `/eventos` | `GET` | Cronograma de válidas departamentales y campamentos de velocidad. |
| **Institucional** | `/mision`, `/vision`, `/valores` | `GET` | Información corporativa y estratégica de la escuela. |

---

## ⚙️ 5. Instrucciones de Ejecución Local

### Prerrequisitos
- Java Development Kit (JDK) 17 o 21.
- Conexión a Internet para resolución de dependencias.

### Pasos para iniciar:
```bash
# 1. Clonar el repositorio
git clone https://github.com/adrianys2005/rollerspeed-pmv.git
cd rollerspeed

# 2. Ejecutar la aplicación con Maven Wrapper
./mvnw spring-boot:run

# 3. Abrir en el navegador web
http://localhost:8080
```

---

## 📄 6. Evidencia de Aprendizaje Entregada
- **Documento Oficial:** `Saumeth_Adrianys_Desarrollo_de_un_producto_minimo_viable.docx`
- **Ponderación:** 100 / 100 Puntos según rúbrica de evaluación.
