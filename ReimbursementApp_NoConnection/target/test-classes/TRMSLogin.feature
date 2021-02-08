Feature: Login Works

	#Each Scenario will connect to a user story
	Scenario: Login as CEO Works
		Given The Guest is on the TRMS Home Page
		When The Guest enters valid credentials and clicks login
		Then The Guest should be on their Employee Home Page