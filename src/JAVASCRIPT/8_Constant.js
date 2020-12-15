const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const week = urlParams.get('week')

document.getElementById("week").innerHTML = "WEEK " + week;

