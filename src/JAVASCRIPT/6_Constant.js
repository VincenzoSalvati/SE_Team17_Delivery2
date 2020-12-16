const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);

const activity = urlParams.get('activity');
const week = urlParams.get('week');
const estimatedTime = urlParams.get('estimatedTime');

var skills = "";


