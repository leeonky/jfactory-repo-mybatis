Feature: save

  Scenario: save single entity
    When Exists data "Product":
      | name | price |
      | mbp  | 100   |
      | ipod | 50    |
    Then All data "Product" should be:
    """
    : | name | price |
      | mbp  | 100   |
      | ipod | 50    |
    """
    When Exists data "Order":
      | id  | code |
      | 100 | S001 |
    Then All data "Order" should be:
    """
    : | id  | code |
      | 100 | S001 |
    """

  Scenario: one to many save 'one'

  Scenario: one to many save 'many'
