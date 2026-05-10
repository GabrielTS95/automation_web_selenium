@Escenario001_PaginaLogin
Feature: Login de usuario


  @CP001
  Scenario:  Login exitoso.
    Given el usuario esta en la pagina de login
    When el cliente ingresa el usuario "standard_user" y la contrasena "secret_sauce"
    And da clic en el boton de login
    Then deberia ver la pagina principal con el texto "Swag Labs"


  @CP002
  Scenario:  Mensaje de error cuando se ingresa usuario que no existe
    Given el usuario esta en la pagina de login
    When el cliente ingresa el usuario "standard" y la contrasena "secret_sauce"
    And da clic en el boton de login
    Then deberia ver el mensaje de error "Epic sadface: Username and password do not match any user in this service"


