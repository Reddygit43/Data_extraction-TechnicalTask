@test
Feature: To validate the data from test file.

Scenario: Read data from a text file and extract data based on a pattern
Given I have a text file with path "src/test/testdatafiles/car_input_v2.txt"
  When I read the content of the file using the pattern
  And then value each registration at Cazoo website and write data to an external file
  Then I compare contents of the file "src/test/testdatafiles/car_output_v2.txt"