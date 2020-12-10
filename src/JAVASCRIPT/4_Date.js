var dat = new Date();
var d = dat.getDay();
document.getElementById("numero").innerHTML = dat.getDate();
if (d === 1) {
    sett = "Monday";
} else if (d === 2) {
    sett = "Tuesday";
} else if (d === 3) {
    sett = "Wednesday";
} else if (d === 4) {
    sett = "Thursday";
} else if (d === 5) {
    sett = "Friday";
} else if (d === 6) {
    sett = "Saturday";
} else if (d === 0) {
    sett = "Sunday";
}
document.getElementById("giorno").innerHTML = sett;