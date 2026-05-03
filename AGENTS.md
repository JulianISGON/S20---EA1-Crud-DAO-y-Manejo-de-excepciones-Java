# 📋 AGENTS.MD - Guía de Agentes Especializados

**Última actualización:** 2026-05-02  
**Propósito:** Documentar agentes disponibles para optimizar desarrollo de proyectos

---

## 🤖 Agentes Disponibles

### 1. **Plan** ✅
**Descripción:** Investiga y crea planes multifase detallados y educativos

**Cuándo usar:**
- Necesitas estructura completa de un proyecto
- Requieres plan detallado paso a paso
- Quieres aprender nueva tecnología/patrón
- Tienes proyecto complejo con múltiples fases

**Ejemplo de uso:**
```
run_subagent(
  agentName: "Plan",
  task: "Crea plan para implementar patrón DAO en Java..."
)
```

**Qué entrega:**
- ✅ Plan estructurado en fases
- ✅ Explicaciones educativas
- ✅ Ejemplos prácticos
- ✅ Archivos a crear/modificar
- ✅ Conceptos fundamentales explicados

**Proyecto actual:** Usado para plan CRUD DAO (S20 - EA1)

---

### 2. **CVE Remediator** 🔒
**Descripción:** Detecta y corrige vulnerabilidades de seguridad (CVEs) en dependencias

**Cuándo usar:**
- Necesitas actualizar dependencias inseguras
- Quieres verificar vulnerabilidades en `pom.xml`, `package.json`, `build.gradle`
- Tienes errores de seguridad en el proyecto
- Necesitas mantener build funcional después de corregir CVEs

**Ejemplo de uso:**
```
run_subagent(
  agentName: "CVE Remediator",
  task: "Detecta y corrige vulnerabilidades en build.gradle manteniendo el proyecto funcional"
)
```

**Qué entrega:**
- ✅ Análisis de CVEs encontrados
- ✅ Versiones seguras recomendadas
- ✅ Cambios aplicados automáticamente
- ✅ Build verificado y funcional

**Ecosistemas soportados:**
- Maven
- npm
- Gradle
- pip
- Go
- etc.

---

## 📊 Matriz de Decisión

| Necesidad | Agente Recomendado | Prioridad |
|-----------|-------------------|-----------|
| Crear plan de proyecto | **Plan** | ⭐⭐⭐⭐⭐ |
| Estructurar arquitectura | **Plan** | ⭐⭐⭐⭐ |
| Aprender nueva tecnología | **Plan** | ⭐⭐⭐⭐⭐ |
| Corregir vulnerabilidades | **CVE Remediator** | ⭐⭐⭐⭐⭐ |
| Actualizar dependencias | **CVE Remediator** | ⭐⭐⭐⭐ |
| Verificar seguridad | **CVE Remediator** | ⭐⭐⭐ |

---

## 🎯 Casos de Uso Prácticos

### Caso 1: Iniciar un nuevo proyecto
**Acciones recomendadas:**
1. ✅ Usar **Plan** para estructura general
2. ✅ Usar **CVE Remediator** después de añadir dependencias

### Caso 2: Proyecto existente con vulnerabilidades
**Acciones recomendadas:**
1. ✅ Usar **CVE Remediator** para identificar y corregir
2. ✅ Usar **Plan** si necesitas refactorizar arquitectura

### Caso 3: Aprender patrón o tecnología
**Acciones recomendadas:**
1. ✅ Usar **Plan** con contexto educativo
2. ✅ Pedir explicaciones detalladas

---

## 📝 Registro de Usos

### ✅ Proyecto 1: S20 - EA1 (CRUD DAO Funcionarios)
- **Agente usado:** Plan
- **Fecha:** 2026-05-01
- **Resultado:** Plan completo de 8 fases con código ejemplo
- **Fase 1 completada:** ✅ Entorno configurado
  - Java 17 verificado
  - Gradle 9.0.0 funcionando
  - Dependencias agregadas (MySQL Connector, SLF4J, JUnit)
  - Estructura de carpetas creada
  - Script SQL preparado
  - Build compilado exitosamente
- **Próximas fases:** Fase 2 (Conectividad con BD)

### ✅ Limpieza de documentación
- **Fecha:** 2026-05-01
- **Resultado:** Se definió la política de mantener solo 3 archivos Markdown en la raíz del proyecto
- **Archivos permitidos:** `README.md`, `AGENTS.md`, `EJECUTAR_PROYECTO.md`
- **Objetivo:** Evitar archivos duplicados o innecesarios

---

## 🚀 Próximas Mejoras

- [ ] Agregar agente para refactorización automática
- [ ] Agregar agente para generación de tests
- [ ] Agregar agente para optimización de rendimiento
- [ ] Agregar agente para documentación automática (JavaDoc, etc.)
- [ ] Agregar agente para integración continua (CI/CD)

---

## 📌 Notas Importantes

⚠️ **Recuerda:**
- Siempre verificar que el agente sea el correcto antes de usarlo
- Los agentes pueden procesar tareas complejas, pero tú mantén el control
- Documenta resultados en este archivo para referencia futura
- Cada agente tiene limitaciones específicas (revisar descripción)

### 🎓 Preferencia del usuario para acompañamiento

- El usuario prefiere explicaciones **muy detalladas, paso a paso**, especialmente para tareas de Git/GitHub.
- Al ejecutar comandos críticos (por ejemplo `commit`, `push`, `pull --rebase`), explicar brevemente:
  - qué hace el comando,
  - por qué se ejecuta en ese momento,
  - cómo validar que salió bien.
- Mantener este estilo en futuros proyectos para facilitar aprendizaje y reutilización.

### 🚢 Playbook reutilizable: subir proyecto a GitHub (detallado)

> Usar este flujo cuando se quiera publicar un proyecto local en un repositorio remoto ya creado.

1. **Entrar al proyecto correcto**
   - Verifica que estás en la carpeta raíz del proyecto.
   - Comando:
     ```powershell
     Set-Location "RUTA_DEL_PROYECTO"
     ```

2. **Confirmar si ya existe repositorio Git local**
   - Revisa estado:
     ```powershell
     git --no-pager status --short --branch
     ```
   - Si aparece `not a git repository`, inicializar:
     ```powershell
     git init
     ```

3. **Verificar si el remoto tiene contenido**
   - Esto evita conflictos por historiales distintos:
     ```powershell
     git ls-remote --heads "URL_DEL_REPOSITORIO"
     ```

4. **Configurar rama principal y remoto**
   - Renombra/define rama principal:
     ```powershell
     git branch -M main
     ```
   - Agrega remoto (si no existe):
     ```powershell
     git remote add origin "URL_DEL_REPOSITORIO"
     ```
   - Verifica:
     ```powershell
     git remote -v
     ```

5. **Preparar cambios para commit**
   - Ver archivos modificados:
     ```powershell
     git --no-pager status
     ```
   - Agrega cambios:
     ```powershell
     git add .
     ```
   - Valida que quedaron en staging:
     ```powershell
     git --no-pager status --short
     ```

6. **Crear commit claro**
   - Comando:
     ```powershell
     git commit -m "feat: descripcion corta y clara de los cambios"
     ```

7. **Sincronizar si el remoto ya tenía commits**
   - Si falla el push por `non-fast-forward`, ejecutar:
     ```powershell
     git pull --rebase origin main
     ```
   - Resolver conflictos, luego:
     ```powershell
     git add .
     git rebase --continue
     ```

8. **Subir al remoto**
   - Primera vez (configura upstream):
     ```powershell
     git push -u origin main
     ```
   - Siguientes veces:
     ```powershell
     git push
     ```

9. **Verificación final**
   - Confirmar historial local:
     ```powershell
     git --no-pager log --oneline -n 5
     ```
   - Confirmar estado limpio:
     ```powershell
     git --no-pager status
     ```
   - Verificar en GitHub que el commit aparece en la rama esperada.

10. **Errores comunes y solución rápida**
   - `Authentication failed`:
     - Usar token personal (PAT) en HTTPS o configurar SSH.
   - `src refspec main does not match any`:
     - Crear al menos un commit antes de `push`.
   - `remote origin already exists`:
     - Actualizar URL:
       ```powershell
       git remote set-url origin "URL_DEL_REPOSITORIO"
       ```
   - `non-fast-forward`:
     - Ejecutar `git pull --rebase origin main` y volver a `push`.

## 🧹 Política de documentación del proyecto

Para evitar archivos innecesarios, en este proyecto solo se mantendrán **3 archivos Markdown** en la raíz:

1. `README.md`
2. `AGENTS.md`
3. `EJECUTAR_PROYECTO.md`

**No se deben crear más archivos `.md` de resumen, índice o fases.**
Si se necesita documentar algo nuevo, se debe actualizar uno de esos tres archivos.

> Nota: cualquier documento temporal, duplicado o de relleno debe eliminarse para no generar basura en el repositorio.

---

## 🔗 Enlaces Útiles

- **Documentación de Agentes:** Ver en el workspace
- **Proyectos Anteriores:** Revisar histórico de commits
- **Mejores Prácticas:** Documentadas en cada proyecto

---

**Última revisión:** 2026-05-01  
**Autor:** GitHub Copilot  
**Estado:** 🟢 Activo
