@CheckHistory
  Feature:
    The user should be able to go back and see all the workouts he has previously logged, and edit them

    @CheckHistory1
    Scenario: Log a Squat rep and save to database
      Given I see "Check History"
      Then I press "Check History"
      Then I see the text "20150503"
      Then I press "20150503"
      Then I see the text "Log Workout"
      Then I press view with id "button_squats_5"
      Then I see the text "5"
      Then I go back
      Then I wait
      Then I go back
      Then I should see "WELCOME TO OUR APP"
      Then I press "Log Workout"
      Then I don't see the text "5"