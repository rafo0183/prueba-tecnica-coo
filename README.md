# prueba-tecnica-coo
<h2>Prueba técnica Backend</h2>
<p>Proyecto construido con java, spring boot, maven, hibernate y JUnit</p>
<p>Para acceder al swagger <b>[Dirección localhost]/doc/swagger-ui/index.html</b></p>
<p>Para conexión directa, los servicios HTTP son: </p>
<table>
    <thead>
        <tr>
            <th>
                Método
            </th>
            <th>
                Ruta
            </th>
            <th>
                Descripción
            </th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>
                GET
            </td>
            <td>
                /v1/task/get/{taskId}
            </td>
            <td>
                Obtiene la tarea del id {taskId}
            </td>
        </tr>
        <tr>
            <td>
                GET
            </td>
            <td>
                /v1/task/getAll
            </td>
            <td>
                Obtiene todas las tareas de la BD
            </td>
        </tr>
        <tr>
            <td>
                POST
            </td>
            <td>
                /v1/task/add
            </td>
            <td>
                Agrega una nueva tarea
            </td>
        </tr>
        <tr>
            <td>
                PUT
            </td>
            <td>
                /v1/task/replace
            </td>
            <td>
                Reemplaza una tarea existente
            </td>
        </tr>
        <tr>
            <td>
                PATCH
            </td>
            <td>
                /v1/task/update/{taskId}
            </td>
            <td>
                Modifica información de una tarea de id taskId
            </td>
        </tr>
    </tbody>
</table>
<p>Existe una carpeta llamada "DB" con comandos básicos por si se quiere montar un docker con MySQL (latest)</p>
<p>Este proyecto se desarrolló en un período de 24 horas por  <a href="https://www.linkedin.com/in/rfuenteso/">Ricardo Fuentes</a></p>
