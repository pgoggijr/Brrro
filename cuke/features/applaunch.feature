@appLaunch
Feature: App Launch
	In order to welcome the user
	A menu should be displayed on first app launch

	Scenario: First Launch
		Given I see "Log Workout"
		Then I press "Log Workout"
        Then I should see "Log Workout"

	Scenario: Back Button
	  Given I see "Check History"
	  Given I see "Log Workout"
	  Then I press "Log Workout"
	  Then I go back
	  Then I should see "Log Workout"
