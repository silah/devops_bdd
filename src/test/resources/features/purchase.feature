Feature: Make a purchase
    # New scenario to cover a purchase
    Scenario: Danny makes a purchase with his Revolut card
    Given that Danny has 100 euro in his account
    When Danny makes a purchase of 20 euro
    Then The balance should be 80