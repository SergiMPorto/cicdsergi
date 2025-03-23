#  Aplicación Número Primo

Este proyecto consiste en una pequeña aplicación Java que comprueba si un número es primo. Para hacerlo, se crean tres hilos, cada uno encargado de verificar un número distinto. Inicialmente, la idea era que estos números se recogieran desde consola mediante `Scanner`
, pero al desplegar la aplicación en ArgoCD, eso dio problemas porque el contenedor se quedaba esperando entrada.

Por eso, se cambió el enfoque y se decidió pasar los argumentos directamente como parámetros (`args`) en el `Job` de Kubernetes.
![appjava](https://github.com/SergiMPorto/cicdsergi/blob/master/images/appjava.png)

---

##  CI/CD con CircleCI

###  Estructura del pipeline

- Se define un `executor` usando la imagen `openjdk:21.0`.
- El primer `job` se encarga de:
  - Mostrar la versión de Java
  - Compilar las clases
  - Guardarlas para reutilizarlas en el `job` de test.
             ![executer](https://github.com/SergiMPorto/cicdsergi/blob/master/images/executor.png)
             ![version](https://github.com/SergiMPorto/cicdsergi/blob/master/images/versionjava.png)
  

> Al principio hubo problemas con las rutas de carpetas, así que se añadió un `job` adicional para listar los archivos y comprobar que todo estaba en su sitio.
 ![listfiles](https://github.com/SergiMPorto/cicdsergi/blob/master/images/showfiles.png)


### 🧪 Tests

- Se descarga JUnit 5 en una carpeta llamada `libs`.
- Se instalan las dependencias de Maven.
- Se ejecutan los tests unitarios con JUnit desde Maven.
  ![testJUnit](https://github.com/SergiMPorto/cicdsergi/blob/master/images/testjava.png)
  ![testapp](https://github.com/SergiMPorto/cicdsergi/blob/master/images/testapp.png)

###  Estilo de código y cobertura

- Se usa **Checkstyle** para validar el estilo del código Java. Si algo no cumple, el circleci nos da un punto rojo. 
- Se genera un informe de cobertura con **Jacoco**, y se construye el `.jar` como artefacto del proyecto.
- El `pom.xml` ya está preparado para generar este `.jar`.
![testline](https://github.com/SergiMPorto/cicdsergi/blob/master/images/testanlind.png)

###  Análisis de código con SonarCloud

- Se hace un análisis estático para medir la calidad del código.
- Se necesita una variable de entorno con el token de autenticación.
  ![sonarcloud](https://github.com/SergiMPorto/cicdsergi/blob/master/images/sunarcube.png)

### Publicación del artefacto en GitHub

- El `job release` sube el `.jar` a GitHub:
  1. Recupera el artefacto desde `testandlint`.
  2. Crea un release usando la API de GitHub (requiere contexto y token).
  3. Sube el `.jar` a ese release.

  ![artefact](https://github.com/SergiMPorto/cicdsergi/blob/master/images/uploadartefacs.png)
  ![artefactgithub](https://github.com/SergiMPorto/cicdsergi/blob/master/images/artefacto%20jar.png)

> Este paso solo se ejecuta en la rama `master`.

### Seguridad

- Se utiliza **GitGuardian (ggshield)** para escanear el repo.
- Busca claves API, tokens, contraseñas...

![security](https://github.com/SergiMPorto/cicdsergi/blob/master/images/security.png)
![giguardian](https://github.com/SergiMPorto/cicdsergi/blob/master/images/gitguardinweb.png)
![invariable](https://github.com/SergiMPorto/cicdsergi/blob/master/images/environment%20variables%20.png)
![workflows](https://github.com/SergiMPorto/cicdsergi/blob/master/images/workflows.png)


---

##  Resultado del análisis

Durante las pruebas con CircleCI, el análisis de seguridad detectó un problema con una key. Se intentaron varias soluciones como borrar los commits afectados o limpiar cachés, crear el gitignore pero nada funcionó del todo. La solución definitiva será rehacer el repo desde cero.

![resultadocircleci](https://github.com/SergiMPorto/cicdsergi/blob/master/images/circleciresult.png)
---

##  Despliegue en Kubernetes con ArgoCD


Para desplegar la aplicación en el clúster con ArgoCD se siguieron estos pasos:

1. Se creó la imagen Docker y se subió a DockerHub.
2. Inicialmente, se intentó con un `Deployment`, pero no funcionaba correctamente porque esperaba entrada desde consola.
3. Finalmente, se optó por un `Job`, que permite ejecutar el contenedor pasando directamente los argumentos (`5 7 11`) y se cierra cuando termina.
   ![dockerhubimage](https://github.com/SergiMPorto/cicdsergi/blob/master/images/subirimagendockerhub.png)
   ![job](https://github.com/SergiMPorto/cicdsergi/blob/master/images/job.png)
   ![argocd](https://github.com/SergiMPorto/cicdsergi/blob/master/images/creandoappenargocd.png)
   ![delivery](https://github.com/SergiMPorto/cicdsergi/blob/master/images/resultadodespliegue.png)
   ![argocddespliegue](https://github.com/SergiMPorto/cicdsergi/blob/master/images/argocddespliegue.png)

---

## Explicación vídeo:
- [video1 app y job build](https://www.loom.com/share/e5f90ce263f844eb9f426794aad62b60)
- [video2 jobs test](https://www.loom.com/share/e47d3cc433f542b3af32cf85e596bcdb)
- [video3 jobs security](https://www.loom.com/share/4a543a415c5c4a2c8c46d6a418e3b056)


