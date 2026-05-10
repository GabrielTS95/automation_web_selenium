# Automation Framework Web

Automatizacion web con Java 17, Maven, Selenium, Cucumber y Allure.

## Ejecutar localmente

Desde la raiz del proyecto:

```bash
mvn clean test
```

En Windows tambien puedes ejecutar:

```bat
scripts\run-automation.bat
```

Para ejecutar sin abrir navegador visible:

```bat
scripts\run-automation-headless.bat
```

## Ejecutar en GitHub Actions

El workflow esta en:

```text
.github/workflows/automation-tests.yml
```

Se puede ejecutar de dos formas:

- Manualmente desde GitHub: `Actions > Web Automation Tests > Run workflow`.
- Automaticamente todos los dias a las 08:00 hora Peru.

GitHub Actions usa UTC. Para cambiar el horario, edita esta linea:

```yaml
- cron: "0 13 * * *"
```

Ejemplos:

```yaml
# Cada hora
- cron: "0 * * * *"

# Lunes a viernes a las 08:00 hora Peru
- cron: "0 13 * * 1-5"
```

## Resultados

Al terminar la ejecucion en GitHub, el workflow sube artefactos con:

- `target/surefire-reports`
- `target/allure-results`
- `target/Reporte_Allure`, si Allure CLI esta disponible
- `target/site/allure-maven-plugin`, si el reporte Allure de Maven se genera correctamente
