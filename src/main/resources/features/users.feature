@user
Feature: Validate users


  @usersGet
  Scenario: Validate that the response of the user request is 200
    When the following get request that brings us the users
    And the response is 200 user

  @PostUsers
  Scenario Outline: Validate post
    Given the following UserPost request that add users
    And the response is 200 for the UserPost
    Then the body response contains post update "<username>"

    Examples:
      |   username   |
      |   pperez    |

  @PutUsers
  Scenario Outline: Validate update create users
    Given  the following put request that update users
    And the response is 200 for the put user
    Then the body response contains put update "<updated_name>" of the user

    Examples:
      |updated_name  |
      | ccalvo       |

  @DeleteUsers
  Scenario: Delete an user
    Given The following get request that delete the user
    Then the response for delete users is 200