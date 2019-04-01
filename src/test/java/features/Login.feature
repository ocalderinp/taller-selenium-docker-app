@Login
Feature: Login Application
  Como usuario quiero intentar acceder a la aplicacion

  Scenario: Successfull Login
    Given navego a la pagina de inicio
    When intento loguearme en la aplicacion con usuario "admin" password "admin123"
    Then verifico que estoy en la pagina de annadir empleado
    And verifico que estoy en la pagina de add employee logueado con el usuario "admin"
    Then hago logout en la aplicacion
    And verifico que estoy en la pagina de inicio

  Scenario Outline: Unsuccessful Login
    Given navego a la pagina de inicio
    When intento loguearme en la aplicacion con usuario "<user>" password "<password>"
    Then verifico que no puedo loguearme en la aplicacion
    Examples:
      | user  | password |
      | admin | admin    |
      | lolo  | lolo*    |