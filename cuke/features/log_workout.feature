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
    Then I see the text "5"
    Then I press view with id "button_log"
    Then I go back
    Then I should see "WELCOME TO OUR APP"
    Then I press "Log Workout"
    Then I see the text "5"

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
    Then I press view with id "button_squats_1"
    Then the view with id of "button_squats_1" should have the text "0"
