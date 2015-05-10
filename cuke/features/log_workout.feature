@logWorkout
Feature: Log Workout
  In order to provide a useful platform for workout tracking
  the workout tracker interface should be intuitive

  @logWorkout1
  Scenario: Log a Squat rep and save to database
    Given I see "Log Workout"
    Then I press "Log Workout"
    Then I see the text "Log a Workout"
    Then I press view with id "button_squats_1"
    Then I press view with id "button_squats_2"
    Then I press view with id "button_squats_3"
    Then I press view with id "button_squats_4"
    Then I press view with id "button_squats_5"
    Then I press view with id "button_deadlift_1"
    Then the view with id of "button_squats_1" should have the text "5"
    And the view with id of "button_squats_2" should have the text "5"
    And the view with id of "button_squats_3" should have the text "5"
    And the view with id of "button_squats_4" should have the text "5"
    And the view with id of "button_squats_5" should have the text "5"
    And the view with id of "button_deadlift_1" should have the text "5"
    Then I press view with id "button_log"
    Then I go back
    Then I should see "WELCOME TO OUR APP"
    Then I press "Log Workout"
    Then the view with id of "button_squats_1" should have the text "5"
    And the view with id of "button_squats_2" should have the text "5"
    And the view with id of "button_squats_3" should have the text "5"
    And the view with id of "button_squats_4" should have the text "5"
    And the view with id of "button_squats_5" should have the text "5"
    And the view with id of "button_deadlift_1" should have the text "5"

  @logWorkout2
  Scenario: Log a Squat rep so many times that it goes back to zero
    Given I see "Log Workout"
    Then I press "Log Workout"
    Then I see the text "Log a Workout"
    Then I press view with id "button_squats_1"
    Then I press view with id "button_squats_1"
    Then I press view with id "button_squats_1"
    Then I press view with id "button_squats_1"
    Then I press view with id "button_squats_1"
    Then the view with id of "button_squats_1" should have the text "0"
