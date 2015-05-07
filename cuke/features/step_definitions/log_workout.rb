Then(/^the view with id of "([^"]*)" should have the text "([^"]*)"$/) do |arg1, arg2|
  element_exists("* id:'arg1' text:'arg2'")
end