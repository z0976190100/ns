# resting-nashorn
Backend javascript code evaluation, executing by Nashorn JVM engine.

Application provides two scenarios of client interaction with backend: syncronized and async.

In syncronized scenario request returns json data with console output, process id, results of evaluation of client script.

In async case, request returns "202 resource created" response and location of resourse, so client can use this locator to retrieve
state or result. During evaluation process client can request state of process, in response it should get json object 
containing console output of script at the moment of request proceeding.

All results of evaluation are saved in memory and avaliable by id of script while app is running.

Note: errors of script during evaluation are assumed as result of evaluation and stand as console output parameter of script state
json.
 


**API reference**
----
***Upload Client Script*** 
-
Uploads client script. 
* **URL**

  /script

* **Method:**

  `POST`
  
*  **URL Params**

   **Required:**
 
   `script=[String]`

* **Data Params**

  `async=[boolean]` optional, false by default

* **Success Response:**

sync case:
  * **Code:** 200 <br />
    **Content:** `{ processorId : 1, consoleLog : [], result : [], evalDone : []; }`
    
async case:
  * **Code:** 201 <br />
  * **Headers:** Location: URL/script/1 <br />
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
 
  ***Get State of Script Evaluation*** 
-

* **URL**
  /script/:id
or
  /script/state/:id

* **Method:**

  `GET`
  
*  **URL Params**

   none

* **Data Params**

  none

* **Success Response:**

  * **Code:** 200 <br />
  * **Content:** `{ processorId : 1, consoleLog : [], result : [], evalDone : []; }`
     
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
  

 ***Get Results of Script Evaluation*** 
-

* **URL**
  
  /script/result/:id

* **Method:**

  `GET`
  
*  **URL Params**

   none

* **Data Params**

  none

* **Success Response:**

  * **Code:** 200 <br />
  * **Content:** `{ processorId : 1, consoleLog : [], result : [], evalDone : []; }`
     
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
  




