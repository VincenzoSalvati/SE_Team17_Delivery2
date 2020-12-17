const user = "root";
const pass = "admin";
const maint_user = ["maint1", "maint2", "maint3"]
const maint_pass = ["pass1", "pass2", "pass3"]
var dictCredentials = [];

var userValue = "";
var passValue = "";
var check = true;

function initDict(){
    dictCredentials["maint1"] = "pass1";
    dictCredentials["maint2"] = "pass2";
    dictCredentials["maint3"] = "pass3";
    dictCredentials["root"] = "admin";
}

function validatePass(passValue){
    return passValue.length>=5
}

var isCorrect = false;
function checkCredentials(user, pass){
    for (var key in dictCredentials){
        console.log('KEY ', key);
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
            location.href = "0_startPage.html?user="+userValue+"&pass="+passValue;
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

