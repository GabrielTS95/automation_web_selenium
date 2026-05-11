#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")/.."
export HEADLESS="${HEADLESS:-true}"

set +e
mvn clean test
test_exit_code=$?
set -e

if command -v allure >/dev/null 2>&1; then
  allure generate --single-file target/allure-results -o target/Reporte_Allure_Local --clean
  echo "Reporte Allure local generado:"
  echo "$(pwd)/target/Reporte_Allure_Local/index.html"
else
  echo "Allure CLI no esta instalado o no esta en el PATH."
  echo "Se generara el reporte con Maven en target/allure-report."
  mvn allure:report -DskipTests
  echo "Reporte Allure Maven generado:"
  echo "$(pwd)/target/allure-report/index.html"
fi

exit "$test_exit_code"
