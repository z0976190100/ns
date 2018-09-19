# resting-nashorn

Backend javascript code evaluation, executing by Nashorn engine.

Application provides two scenarios of client interaction with backend: syncronized and async.

In syncronized scenario request returns json data with console output, process id, results of evaluation of client script.

In async case, request returns "201 resource created" response and location of resourse, so client can use this locator to retrieve state or result. During evaluation process client can request state of process, in response it should get json object containing console output of script at the moment of request proceeding.

Retrieving of result returns full script state json and causes elimination of result in memory of app. 

Note: errors of script during evaluation are assumed as result of evaluation and stand as console output parameter of script state json.
