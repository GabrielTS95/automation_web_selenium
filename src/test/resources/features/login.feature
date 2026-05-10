@Escenario001_PaginaLogin
Feature: Login de usuario

  @CP001 @LoginExitoso
  Scenario: Login exitoso
    Given el usuario esta en la pagina de login
    When el cliente ingresa el usuario "standard_user" y la contrasena "secret_sauce"
    And da clic en el boton de login
    Then deberia ver la pagina principal con el texto "Swag Labs"

  @CP002 @LoginInvalido
  Scenario: Mensaje de error cuando se ingresa usuario que no existe
    Given el usuario esta en la pagina de login
    When el cliente ingresa el usuario "standard" y la contrasena "secret_sauce"
    And da clic en el boton de login
    Then deberia ver el mensaje de error "Epic sadface: Username and password do not match any user in this service"

  @CP003 @LoginBloqueado
  Scenario: Mensaje de error cuando el usuario esta bloqueado
    Given el usuario esta en la pagina de login
    When el cliente ingresa el usuario "locked_out_user" y la contrasena "secret_sauce"
    And da clic en el boton de login
    Then deberia ver el mensaje de error "Epic sadface: Sorry, this user has been locked out."

  @CP004 @UsuarioRequerido
  Scenario: Mensaje de error cuando no se ingresa usuario
    Given el usuario esta en la pagina de login
    When el cliente ingresa el usuario "" y la contrasena "secret_sauce"
    And da clic en el boton de login
    Then deberia ver el mensaje de error "Epic sadface: Username is required"

  @CP005 @PasswordRequerido
  Scenario: Mensaje de error cuando no se ingresa contrasena
    Given el usuario esta en la pagina de login
    When el cliente ingresa el usuario "standard_user" y la contrasena ""
    And da clic en el boton de login
    Then deberia ver el mensaje de error "Epic sadface: Password is required"

  @CP006 @Logout
  Scenario: Logout exitoso
    Given el usuario esta en la pagina de login
    When el cliente ingresa el usuario "standard_user" y la contrasena "secret_sauce"
    And da clic en el boton de login
    Then deberia ver la pagina principal con el texto "Swag Labs"
    And abre el menu lateral
    And da clic en logout
    Then deberia volver a la pagina de login
