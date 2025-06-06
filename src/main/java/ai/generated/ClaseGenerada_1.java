--- ANÁLISIS (Fase 1) ---
1. Clases que pueden reutilizarse directamente sin cambios:
   - Employee.java
   - EmployeeCoreApiApplication.java
2. Clases que deben modificarse (añadir métodos, anotaciones, endpoints, etc.):
   - EmployeeRepository.java
   - EmployeeController.java
   - EmployeeService.java
   - EmployeeServiceImpl.java
3. No se necesitan clases completamente nuevas.

--- PLAN (Fase 2) ---
- EmployeeRepository.java: Agregar un nuevo método para buscar empleados nacidos antes del año 2000.
- EmployeeController.java: Agregar un nuevo endpoint para obtener empleados nacidos antes del año 2000.
- EmployeeService.java: Agregar un nuevo método en la interfaz para obtener empleados nacidos antes del año 2000.
- EmployeeServiceImpl.java: Implementar el nuevo método de la interfaz EmployeeService.

--- GENERACIÓN (Fase 3) ---

```java
