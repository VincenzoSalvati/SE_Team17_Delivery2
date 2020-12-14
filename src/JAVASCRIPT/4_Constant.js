const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const activity = urlParams.get('activity')
const week = urlParams.get('week')
const specifications = urlParams.get('specifications')
const maintainer = urlParams.get('maintainer')
const day = urlParams.get('day')
const stringActivity = urlParams.get("strActivity")
const stringWorkNote = urlParams.get("strWorkNote")
const perc = urlParams.get("perc")
const estimatedTime = urlParams.get("time")

document.getElementById("week").innerHTML = "WEEK " + week;
document.getElementById("strWorkNote").innerHTML = stringWorkNote;
document.getElementById("strActivity").innerHTML = stringActivity;
document.getElementById("strPerc").innerHTML = perc + "%";
document.getElementById("giorno").innerHTML = day;
document.getElementById("totalMin").innerHTML = estimatedTime;