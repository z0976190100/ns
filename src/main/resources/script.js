

var a = "string to log into console";
var count = 0;
var res = 0;
for(var i = 0; i < 100; i++ ){
    print(a + " " + i);
}

var pause = function (duration) {
    while (count < duration){
        count++;
    }
    res = duration;
    count  = 0;
};

pause(2000000);

var result = function(){
    var str = "result of evaluation is: ";
    return str + res;
};

result();


/*

var a = "string to log into console"; var count = 0; var res = 0; for(var i = 0; i < 100; i++ ){ print(a + " " + i); };
var pause = function (duration) { while (count < duration){ count++; }; res = duration; count  = 0; }; pause(2000000);
var result = function(){ var str = "result of evaluation is: "; return str + res; }; result();

*/