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

  Scenario: many to one save 'one' and 'many'
#    When Exists data "商品":
#      | id  | name | price |
#      | 100 | mbp  | 100   |
    When Exists data "OrderLine":
      | quantity | product.name | order.code |
      | 10       | mbp          | S001       |
    Then All data "Order" should be:
    """
    : | quantity | product.name |
      | 11       | mbp          |
    """

  Scenario: one to many save 'one' and 'many'
