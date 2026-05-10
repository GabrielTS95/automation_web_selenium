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

Al terminar la ejecucion en GitHub, el workflow publica y sube el reporte Allure:

- Artifact `Reporte_Allure`: descarga el ZIP, descomprimelo y abre `index.html`.
- Artifact `automation-test-results`: contiene `surefire-reports` y `allure-results`.
- GitHub Pages: abre el enlace publicado por el job `run-tests` para ver el reporte en el navegador.

Si GitHub Pages no publica en el primer intento, revisa en el repositorio:

```text
Settings > Pages > Build and deployment > Source > GitHub Actions
```
