@Employee
Feature: Employee

  Scenario Outline: Add Employee
    Given navego a la pagina de inicio
    When intento loguearme en la aplicacion con datos validos
    Then annado empleado con los datos "<name>", "<address>", "<city>", "<estado>", "<zipCode>", "<email>" y "<telefono>"
    And verifico que se adiciono el empleado
    Examples:
      | name     | address | city       | estado     | zipCode | email               | telefono |
      | empleado | calle 1 | montevideo | montevideo | 11200   | empleado1@gmail.com | 2222222  |