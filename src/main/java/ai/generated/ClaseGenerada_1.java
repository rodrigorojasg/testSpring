--- ANÁLISIS (Fase 1) ---
1. Las clases que pueden reutilizarse sin modificar son: Employee.java, EmployeeCoreApiApplication.java
2. Las clases que deben ser modificadas son: EmployeeRepository.java, EmployeeService.java, EmployeeServiceImpl.java, EmployeeController.java
3. No se necesitan clases completamente nuevas.

--- PLAN (Fase 2) ---
- EmployeeRepository.java: Se agregará un nuevo método para buscar empleados nacidos antes del año 2000.
- EmployeeService.java y EmployeeServiceImpl.java: Se agregará un nuevo método para llamar al método del repositorio que se acaba de crear.
- EmployeeController.java: Se agregará un nuevo endpoint que llamará al nuevo método en el servicio.

--- GENERACIÓN (Fase 3) ---

```java
