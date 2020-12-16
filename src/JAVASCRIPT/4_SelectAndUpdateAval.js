var current_time = estimatedTime;
//dict which stores the minutes
var dict = [];
//id self-incremented which tracks selected minutes
var id = 0;
var avail_8to9 = 0;
var avail_9to10 = 0;
var avail_10to11 = 0;
var avail_11to12 = 0;
var avail_14to15 = 0;
var avail_15to16 = 0;
var avail_16to17 = 0;
var id_availability = 0;

//used in order to show/hide the image of check and in order to calculate current_time
function selectHour(checkId, d) {
    var check = document.getElementById(checkId);
    var val = parseInt(document.querySelector('.' + d).textContent.replace(" min", ""));
    if (val === 0) {
        check.style.visibility = 'hidden';
        $('#alertWarning').show('fade');
    } else {
        if (check.style.visibility === 'visible') {
            check.style.visibility = 'hidden';
            delete dict[d];
            current_time += parseInt(val);
            if (current_time > 0) {
                document.getElementById("totalMin").innerHTML = current_time;
            } else if (current_time <= 0) {
                document.getElementById("totalMin").innerHTML = "Time expired";
                disableAllButton();
                //enable button Send
                document.querySelector('.btn-primary').style.pointerEvents = 'auto';
                document.querySelector('.btn-primary').style.opacity = 1;
            }
        } else {
            check.style.visibility = 'visible';
            dict[id] = d;
            id++;
            current_time -= parseInt(val);
            if (current_time > 0) {
                document.getElementById("totalMin").innerHTML = current_time;
            } else if (current_time <= 0) {
                document.getElementById("totalMin").innerHTML = "Time expired";
                disableAllButton();
                //enable button Send
                document.querySelector('.btn-primary').style.pointerEvents = 'auto';
                document.querySelector('.btn-primary').style.opacity = 1;
            }
        }
    }
}

//disable minutes
function disableAllButton(flag) {
    document.getElementById("link_8to9").style.cursor = 'not-allowed';
    document.getElementById("link_8to9").style.opacity = 0.5;
    document.getElementById("link_8to9").style.pointerEvents = 'none';

    document.getElementById("link_9to10").style.cursor = 'not-allowed';
    document.getElementById("link_9to10").style.opacity = 0.5;
    document.getElementById("link_9to10").style.pointerEvents = 'none';

    document.getElementById("link_10to11").style.cursor = 'not-allowed';
    document.getElementById("link_10to11").style.opacity = 0.5;
    document.getElementById("link_10to11").style.pointerEvents = 'none';

    document.getElementById("link_11to12").style.cursor = 'not-allowed';
    document.getElementById("link_11to12").style.opacity = 0.5;
    document.getElementById("link_11to12").style.pointerEvents = 'none';

    document.getElementById("link_14to15").style.cursor = 'not-allowed';
    document.getElementById("link_14to15").style.opacity = 0.5;
    document.getElementById("link_14to15").style.pointerEvents = 'none';

    document.getElementById("link_15to16").style.cursor = 'not-allowed';
    document.getElementById("link_15to16").style.opacity = 0.5;
    document.getElementById("link_15to16").style.pointerEvents = 'none';

    document.getElementById("link_16to17").style.cursor = 'not-allowed';
    document.getElementById("link_16to17").style.opacity = 0.5;
    document.getElementById("link_16to17").style.pointerEvents = 'none';
}

//enable minutes
function enableAllButton() {
    document.getElementById("link_8to9").style.cursor = 'default';
    document.getElementById("link_8to9").style.opacity = 1;
    document.getElementById("link_8to9").style.pointerEvents = 'auto';

    document.getElementById("link_9to10").style.cursor = 'default';
    document.getElementById("link_9to10").style.opacity = 1;
    document.getElementById("link_9to10").style.pointerEvents = 'auto';

    document.getElementById("link_10to11").style.cursor = 'default';
    document.getElementById("link_10to11").style.opacity = 1;
    document.getElementById("link_10to11").style.pointerEvents = 'auto';

    document.getElementById("link_11to12").style.cursor = 'default';
    document.getElementById("link_11to12").style.opacity = 1;
    document.getElementById("link_11to12").style.pointerEvents = 'auto';

    document.getElementById("link_14to15").style.cursor = 'default';
    document.getElementById("link_14to15").style.opacity = 1;
    document.getElementById("link_14to15").style.pointerEvents = 'auto';

    document.getElementById("link_15to16").style.cursor = 'default';
    document.getElementById("link_15to16").style.opacity = 1;
    document.getElementById("link_15to16").style.pointerEvents = 'auto';

    document.getElementById("link_16to17").style.cursor = 'default';
    document.getElementById("link_16to17").style.opacity = 1;
    document.getElementById("link_16to17").style.pointerEvents = 'auto';
}

//reset current_time
function clearHour() {
    current_time = estimatedTime;
    for (var i = 0; i < 7; i++) {
        document.getElementById("checkId" + i).style.visibility = 'hidden';
        document.getElementById("totalMin").innerHTML = estimatedTime;
    }
    enableAllButton();
    //disable button Send
    document.querySelector('.btn-primary').style.pointerEvents = 'none';
    document.querySelector('.btn-primary').style.opacity = 0.5;
}

//initialize data for database
// noinspection JSUnfilteredForInLoop
function initParams() {
    avail_8to9 = parseInt(document.querySelector('.link_8to9').textContent.replace(" min", ""));
    avail_9to10 = parseInt(document.querySelector('.link_9to10').textContent.replace(" min", ""));
    avail_10to11 = parseInt(document.querySelector('.link_10to11').textContent.replace(" min", ""));
    avail_11to12 = parseInt(document.querySelector('.link_11to12').textContent.replace(" min", ""));
    avail_14to15 = parseInt(document.querySelector('.link_14to15').textContent.replace(" min", ""));
    avail_15to16 = parseInt(document.querySelector('.link_15to16').textContent.replace(" min", ""));
    avail_16to17 = parseInt(document.querySelector('.link_16to17').textContent.replace(" min", ""));
    id_availability = document.getElementById('link_id').innerHTML;
    lastElem = dict[id - 1];
    lastElemValue = parseInt(document.querySelector('.' + lastElem).textContent.replace(" min", ""));
    lastElemValue = Math.abs(current_time);
    for (var key in dict) {
        // noinspection JSUnfilteredForInLoop
        switch (dict[key]) {
            case "link_8to9":
                if (key < id - 1) {
                    avail_8to9 = 0;
                } else {
                    // noinspection JSUnfilteredForInLoop
                    if (parseInt(key) === id - 1) {
                        avail_8to9 = lastElemValue;
                    }
                }
                break;
            case "link_9to10":
                if (key < id - 1) {
                    avail_9to10 = 0;
                } else {
                    // noinspection JSUnfilteredForInLoop
                    if (parseInt(key) === id - 1) {
                        avail_9to10 = lastElemValue;
                    }
                }
                break;
            case "link_10to11":
                if (key < id - 1) {
                    avail_10to11 = 0;
                } else {
                    // noinspection JSUnfilteredForInLoop
                    if (parseInt(key) === id - 1) {
                        avail_10to11 = lastElemValue;
                    }
                }
                break;
            case "link_11to12":
                if (key < id - 1) {
                    avail_11to12 = 0;
                } else {
                    // noinspection JSUnfilteredForInLoop
                    if (parseInt(key) === id - 1) {
                        avail_11to12 = lastElemValue;
                    }
                }
                break;
            case "link_14to15":
                if (key < id - 1) {
                    avail_14to15 = 0;
                } else {
                    // noinspection JSUnfilteredForInLoop
                    if (parseInt(key) === id - 1) {
                        avail_14to15 = lastElemValue;
                    }
                }
                break;
            case "link_15to16":
                if (key < id - 1) {
                    avail_15to16 = 0;
                } else {
                    // noinspection JSUnfilteredForInLoop
                    if (parseInt(key) === id - 1) {
                        avail_15to16 = lastElemValue;
                    }
                }
                break;
            case "link_16to17":
                if (key < id - 1) {
                    avail_16to17 = 0;
                } else {
                    // noinspection JSUnfilteredForInLoop
                    if (parseInt(key) === id - 1) {
                        avail_16to17 = lastElemValue;
                    }
                }
                break;
            default:
                break;
        }
    }

}

//update availability on db
function updateDB() {
    $.post("http://" + JAVA_TOMCAT_HOST + "/Esame/Crud_availability.jsp",
        {
            id: id_availability,
            avail_8to9: avail_8to9,
            avail_9to10: avail_9to10,
            avail_10to11: avail_10to11,
            avail_11to12: avail_11to12,
            avail_14to15: avail_14to15,
            avail_15to16: avail_15to16,
            avail_16to17: avail_16to17
        }, function (data) {
        }).done(function () {
        location.href = "1_ShowActivities.html?week=" + week;
    }).fail(function () {
        alert("Error while updating database");
    });
}