const user = "root";
const pass = "admin";
var userValue = "";
var passValue = "";
var check = true;

function validatePass(passValue){
    return passValue.length>=5
}

function getUserCredentials(){
    console.log('GET VALUE');
    userValue = $('#login').val().trim();
    passValue = $('#password').val().trim();

    //userValue = document.getElementById('login').innerHTML;
    //passValue = document.getElementById('password').innerHTML;

    console.log("USER: " + userValue  + "\nPASS: " + passValue);

    check = validatePass(passValue);

    if(check){
        if(userValue===user && passValue===pass){
            location.href = "0_startPage.html?user="+userValue+"&pass="+passValue;
        }else{
            console.log("PASSWORD INCORRECT " + userValue + " " + passValue);
            alert('Utente o password errati.')
            //location.reload();
        }
    }else{
        alert('La password deve contenere almeno 6 caratteri.')
    }
}