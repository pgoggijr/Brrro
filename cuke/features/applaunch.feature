Feature: App Launch
	In order to welcome the user
	A menu should be displayed on first app launch

	Scenario: First Launch
		Given I see "Log Workout"
		Then I press "Log Workout"

	Scenario: Back Button
		Given the user is at a workout page or the history page
		When the back button is pressed
