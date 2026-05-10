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
- Automaticamente cada lunes a las 07:00 hora Peru.

GitHub Actions usa UTC. Para cambiar el horario, edita esta linea:

```yaml
- cron: "0 12 * * 1"
```

Ejemplos:

```yaml
# Cada hora
- cron: "0 * * * *"

# Lunes a viernes a las 08:00 hora Peru
- cron: "0 13 * * 1-5"
```

## Notificacion por correo

El workflow envia un correo a `ergatosa.95@gmail.com` al finalizar cada ejecucion, tanto si pasa como si falla.

Configura estos secretos en GitHub:

```text
Settings > Secrets and variables > Actions > New repository secret
```

Secretos requeridos:

- `SMTP_HOST`: servidor SMTP. Para Gmail: `smtp.gmail.com`.
- `SMTP_PORT`: puerto SMTP. Para Gmail: `587`.
- `SMTP_USERNAME`: correo remitente.
- `SMTP_PASSWORD`: clave SMTP o app password.

Secreto opcional:

- `SMTP_FROM`: remitente visible. Si no existe, se usa `SMTP_USERNAME`.

Para Gmail, usa una app password, no la clave normal de tu cuenta.

## Resultados

Al terminar la ejecucion en GitHub, el workflow publica y sube el reporte Allure:

- Artifact `Reporte_Allure_Local`: descarga el ZIP, descomprimelo y abre `index.html` directamente.
- Artifact `Reporte_Allure_Web`: version completa para servidor web o GitHub Pages.
- Artifact `automation-test-results`: contiene `surefire-reports` y `allure-results`.
- GitHub Pages: abre el enlace publicado por el job `run-tests` para ver el reporte en el navegador.

Si GitHub Pages no publica en el primer intento, revisa en el repositorio:

```text
Settings > Pages > Build and deployment > Source > GitHub Actions
```
