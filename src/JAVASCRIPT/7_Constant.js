const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);

const activity = urlParams.get('activity')
const week = urlParams.get('week')
const estimatedTime = urlParams.get('estimatedTime')
const specifications = urlParams.get('specifications')
const stringActivity = urlParams.get('strActivity')
const stringWorkNote = urlParams.get('strWorkNote')
const stringSkills = urlParams.get('skills')

document.getElementById('strWeek').innerHTML = "WEEK " + week;
document.getElementById('strWorkNote').innerHTML = stringWorkNote;
document.getElementById('strActivity').innerHTML = stringActivity;
document.getElementById('strSkills').innerHTML = stringSkills;
document.getElementById('totalMin').innerHTML = estimatedTime;