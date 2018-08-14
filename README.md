# resting-nashorn

Backend javascript code evaluation, executing by Nashorn JVM engine.

Application provides two scenarios of client interaction with backend: syncronized and async.

In syncronized scenario request returns json data with console output, process id, results of evaluation of client script.

In async case, request returns "202 resource created" response and location of resourse, so client can use this locator to retrieve state or result. During evaluation process client can request state of process, in response it should get json object containing console output of script at the moment of request proceeding.

All results of evaluation are saved in memory and avaliable by id of script while app is running.

Note: errors of script during evaluation are assumed as result of evaluation and stand as console output parameter of script state json.
