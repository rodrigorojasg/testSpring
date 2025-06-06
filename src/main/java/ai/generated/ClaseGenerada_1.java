--- ANÁLISIS (Fase 1) ---
1. Clases que pueden reutilizarse sin modificar:
   - Employee.java
   - EmployeeCoreApiApplication.java
2. Clases que deben ser modificadas:
   - EmployeeRepository.java
   - EmployeeController.java
   - EmployeeService.java
   - EmployeeServiceImpl.java
3. Clases que deben ser completamente nuevas:
   - No hay ninguna.

--- PLAN (Fase 2) ---
- EmployeeRepository.java: Se agregará un nuevo método para buscar empleados que hayan nacido antes del año 2000.
- EmployeeController.java: Se agregará un nuevo endpoint para obtener los empleados que hayan nacido antes del año 2000.
- EmployeeService.java: Se agregará un nuevo método para obtener los empleados que hayan nacido antes del año 2000.
- EmployeeServiceImpl.java: Se implementará el nuevo método definido en EmployeeService.java.

--- GENERACIÓN (Fase 3) ---

```java
