const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);

const id_maint = urlParams.get('user');
console.log(id_maint)

function logOut(){
    location.href = "0_LoginPage.html";
}

