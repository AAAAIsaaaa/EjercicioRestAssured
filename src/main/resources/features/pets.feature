@pet
Feature: (e2e) Validate pets

  @PetPost
  Scenario Outline: Validate the response of the post is 200
    Given the following petsPost request to add pets
    And the response is 200 for post
    Then the body response contains the "<name>" of the new pet
    Examples:
      | name         |
      | cacatua      |

  @GetPets
  Scenario: Validate that the response of pets creation is 200
    Given the following get request that brings us the pets
    Then the response is 200

  @PutPets
  Scenario Outline: Validate update create pets
    Given  the following put request that update pets
    And the response is 200 for the put pets
    Then the body response contains update "<updated_name>" pets

    Examples:
      |updated_name  |
      | yoshi         |

  @DeletePets
  Scenario: Delete a pet
    Given The following get request that delete the pet
    Then the response for delete pets is 404