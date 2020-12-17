const user = "root";
const pass = "admin";
const maint_user = ["maint1", "maint2", "maint3"]
const maint_pass = ["pass1", "pass2", "pass3"]
var dictCredentials = [];

var userValue = "";
var passValue = "";
var check = true;

function initDict(){
    dictCredentials["1"] = "pass1";
    dictCredentials["2"] = "pass2";
    dictCredentials["3"] = "pass3";
    dictCredentials["root"] = "admin";
}

function validatePass(passValue){
    return passValue.length>=5
}

var isCorrect = false;
function checkCredentials(user, pass){
    for (var key in dictCredentials){
        if(user==key){
            if(dictCredentials[key]===pass){
                isCorrect = true;
            }
        }
    }
    return isCorrect;
}

function getUserCredentials(){
    console.log('GET VALUE');
    userValue = $('#login').val().trim();
    passValue = $('#password').val().trim();
    
    initDict();

    console.log("USER: " + userValue  + "\nPASS: " + passValue);

    check = validatePass(passValue);

    if(check){
        if(checkCredentials(userValue, passValue)){
            if(userValue==="1" || userValue==="2"|| userValue==="3") {
                console.log('MAINTAINER LOGGED');
                location.href = "9_MaintainerHomePage.html?user=" + userValue + "&pass=" + passValue;
            }
            else{
                console.log('PLANNER LOGGED');
                location.href = "0_StartPage.html?user="+userValue+"&pass="+passValue;
            }
        }else{
            console.log("PASSWORD INCORRECT " + userValue + " " + passValue);
            userValue = "";
            passValue = "";
            alert('Utente o password errati.')
            //location.reload();
        }
    }else{
        userValue = "";
        passValue = "";
        alert('La password deve contenere almeno 5 caratteri.')
    }
}

