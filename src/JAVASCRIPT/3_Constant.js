const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);

const activity = urlParams.get('activity')
const week = urlParams.get('week')
const specifications = urlParams.get('specifications')
const stringActivity = urlParams.get("strActivity");
const stringSkills = urlParams.get("strSkills");
const stringWorkNote = urlParams.get("strWorkNote")
const estimatedTime = urlParams.get("time")

const mon = "Monday"
const tue = "Tuesday"
const wed = "Wednesday"
const thu = "Thursday"
const fri = "Friday"
const sat = "Saturday"
const sun = "Sunday"

document.getElementById("week").innerHTML = "WEEK " + week;
document.getElementById("stringActivity").innerHTML = stringActivity;
document.getElementById("stringSkills").innerHTML = stringSkills.replaceAll("-", "<br>- ");