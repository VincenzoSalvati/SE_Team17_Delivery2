const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const week = urlParams.get('week')
window.onload = localStorage.getItem("week");
document.getElementById("strWeek").innerHTML = "WEEK " + week

