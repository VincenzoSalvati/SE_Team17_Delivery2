var day = "";
var dat = new Date();
var d = dat.getDay();
if (d === 1) {
    day = "Monday";
} else if (d === 2) {
    day = "Tuesday";
} else if (d === 3) {
    day = "Wednesday";
} else if (d === 4) {
    day = "Thursday";
} else if (d === 5) {
    day = "Friday";
} else if (d === 6) {
    day = "Saturday";
} else if (d === 0) {
    day = "Sunday";
}

document.getElementById("numero").innerHTML = dat.getDate();
document.getElementById("giorno").innerHTML = day;