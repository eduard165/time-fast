
# 📌 Descripción del Proyecto

Este repositorio contiene la **API de la capa de servicios RESTful** para el proyecto integrador **Time-Fast**, desarrollado para la empresa ficticia **Time-Fast**, líder en el sector de paquetería y envíos. El objetivo del proyecto es ofrecer una solución robusta y eficiente para gestionar envíos desde diferentes plataformas (escritorio, web y móvil), facilitando la interacción entre **colaboradores, clientes y conductores**, garantizando la entrega y el monitoreo de paquetes en tiempo real.

La solución está compuesta por servicios RESTful implementados en **Java**, utilizando el framework **MyBatis** como ORM y conectividad con la base de datos **MySQL**. La autenticación y la seguridad se implementan siguiendo estándares REST y robustas validaciones de datos.

---

## 🚀 Tecnologías y Librerías utilizadas

- **Java**: Lenguaje principal del backend.
- **MyBatis**: Framework ORM para acceso y gestión de base de datos.
- **Gson (2.9.1)**: Para la serialización y deserialización de datos JSON.
- **MySQL Connector Java (8.0.20)**: Conexión y comunicación con la base de datos MySQL.
- **NetBeans 8**: Entorno de desarrollo utilizado para el proyecto.
- **RESTful Services**: Arquitectura implementada para la capa de servicios.
- **JDK 8**: Java Development Kit versión 8.




## 📝 Características Principales  

### 🔐 **Módulo de Colaboradores**  
- **Registro de Colaboradores:** Permite dar de alta a nuevos colaboradores con datos personales como **nombre, apellido paterno, apellido materno, CURP, correo electrónico, número de personal, contraseña, rol y fotografía**.  
- **Edición de Colaboradores:** Facilita la actualización de información, exceptuando el **número de personal y el rol** del colaborador.  
- **Eliminación de Colaboradores:** Proporciona la capacidad de eliminar información de colaboradores del sistema.  
- **Búsqueda Avanzada:** Realiza búsquedas por **nombre, número de personal y rol**.

---

### 🚗 **Módulo de Unidades**  
- **Registro de Unidades:** Permite dar de alta vehículos con información como **marca, modelo, año, VIN (Número de Identificación Vehicular), tipo de unidad y número de identificación interno**.  
- **Edición de Unidades:** Actualización de todos los campos excepto el **VIN**.  
- **Baja de Unidades:** Da de baja vehículos basándose en un **motivo**.  
- **Búsqueda Avanzada:** Facilita la búsqueda de vehículos por **VIN, marca y número de identificación interno**.

---

### 👥 **Módulo de Clientes**  
- **Registro de Clientes:** Permite dar de alta clientes con datos personales como **nombre, apellido paterno, apellido materno, dirección (calle, número, colonia, código postal), teléfono y correo electrónico**.  
- **Edición de Clientes:** Proporciona la capacidad de actualizar la información del cliente.  
- **Eliminación de Clientes:** El sistema permite eliminar la información de un cliente.  
- **Búsqueda Avanzada:** Realiza búsquedas por **nombre, dirección, correo electrónico y número de teléfono**.

---

### 📦 **Módulo de Envíos**  
- **Creación de Envíos:** Permite registrar un envío seleccionando el **cliente, origen, destino, número de guía y costo del envío**.  
- **Actualización de Envíos:** Facilita la modificación de cualquier dato del envío, excepto el **número de guía**.  
- **Consultas de Envíos:** Proporciona información detallada del envío, como **origen, destino, estado y conductor asignado**.  
- **Asignación de Conductores:** Permite asignar o cambiar el conductor responsable del envío.  
- **Actualización de Estatus:** Gestiona el estado del envío (**pendiente, en tránsito, entregado, cancelado**) y registra comentarios en caso de cambios significativos.

---

### 📬 **Módulo de Paquetes**  
- **Registro de Paquetes:** Permite añadir paquetes con información como **descripción, peso, dimensiones (alto, ancho, profundidad) y el envío al que pertenece**.  
- **Actualización de Paquetes:** Facilita la modificación de cualquier detalle del paquete, excepto el **envío al que está asignado**.  
- **Consultas de Paquetes:** Realiza búsquedas y operaciones CRUD para paquetes en relación con cada envío.  
- **Eliminación de Paquetes:** Proporciona la opción de eliminar paquetes de un envío específico.

