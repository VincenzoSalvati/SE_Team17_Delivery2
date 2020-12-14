var current_time = estimatedTime;
var ass_time = 0;

//dizionario che memorizza i minuti selezionati
var dict = [];
//id autoincrementale che tiene traccia dei vari minuti selezionati
var id = 0;

var avail_8to9 = 0;
var avail_9to10 = 0;
var avail_10to11 = 0;
var avail_11to12 = 0;
var avail_14to15 = 0;
var avail_15to16 = 0;
var avail_16to17 = 0;
var id_availability = 0;

//serve per il CHECK
function selectHour(checkId, d) {
    var check = document.getElementById(checkId);
    var val = parseInt(document.querySelector('.' + d).textContent.replace(" min", ""));
    //se il valore è 0 non posso prenderlo per assegnare del tempo, dunque non mostro
    if (val === 0) {
        check.style.visibility = 'hidden';
        $('#alertWarning').show('fade');
    } else {
        if (check.style.visibility === 'visible') {
            check.style.visibility = 'hidden';
            //rimuovo il valore deselezionato dal dizionario
            delete dict[d];
            current_time += parseInt(val);
            if (current_time > 0) {
                document.getElementById("totalMin").innerHTML = current_time;
            } else if (current_time <= 0) {
                document.getElementById("totalMin").innerHTML = "Time expired";
                disableAllButton();
                //rendo disponibile il button Forward
                document.querySelector('.btn-primary').style.pointerEvents = 'auto';
                document.querySelector('.btn-primary').style.opacity = 1;
            }
        } else {
            check.style.visibility = 'visible';
            //aggiungo il valori selezionati al dizionario
            dict[id] = d;
            id++;
            current_time -= parseInt(val);
            ass_time += parseInt(val);
            if (current_time > 0) {
                document.getElementById("toBeAssMin").innerHTML = current_time;
                document.getElementById("assMin").innerHTML = ass_time;
            } else if (current_time <= 0) {
                document.getElementById("toBeAssMin").innerHTML = "Time expired";
                disableAllButton();
                //rendo disponibile il button Forward
                document.querySelector('.btn-primary').style.pointerEvents = 'auto';
                document.querySelector('.btn-primary').style.opacity = 1;
            }
        }
    }
}

//serve a DISABILITARE la scelta dei minuti
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

//serve a RIABILITARE la scelta dei minuti
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

//serve a RESETTARE la scelta dei minuti
function clearHour() {
    current_time = estimatedTime;
    for (var i = 0; i < 7; i++) {
        document.getElementById("checkId" + i).style.visibility = 'hidden';
        document.getElementById("totalMin").innerHTML = estimatedTime;
    }
    enableAllButton();
    //disabilito il button Forward
    document.querySelector('.btn-primary').style.pointerEvents = 'none';
    document.querySelector('.btn-primary').style.opacity = 0.5;
}

//serve a PREPARARE i parametri per il DB
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
    //ultimo elemento del dict
    lastElem = dict[id - 1];
    lastElemValue = parseInt(document.querySelector('.' + lastElem).textContent.replace(" min", ""));
    lastElemValue = Math.abs(lastElemValue + current_time);
    for (var key in dict) {
        switch (dict[key]) {
            case "link_8to9":
                if (key < id - 1) {
                    avail_8to9 = 0;
                } else if (parseInt(key) === id - 1) {
                    avail_8to9 = lastElemValue;
                }
                break;
            case "link_9to10":
                if (key < id - 1) {
                    avail_9to10 = 0;
                } else if (parseInt(key) === id - 1) {
                    avail_9to10 = lastElemValue;
                }
                break;
            case "link_10to11":
                if (key < id - 1) {
                    avail_10to11 = 0;
                } else if (parseInt(key) === id - 1) {
                    avail_10to11 = lastElemValue;
                }
                break;
            case "link_11to12":
                if (key < id - 1) {
                    avail_11to12 = 0;
                } else if (parseInt(key) === id - 1) {
                    avail_11to12 = lastElemValue;
                }
                break;
            case "link_14to15":
                if (key < id - 1) {
                    avail_14to15 = 0;
                } else if (parseInt(key) === id - 1) {
                    avail_14to15 = lastElemValue;
                }
                break;
            case "link_15to16":
                if (key < id - 1) {
                    avail_15to16 = 0;
                } else if (parseInt(key) === id - 1) {
                    avail_15to16 = lastElemValue;
                }
                break;
            case "link_16to17":
                if (key < id - 1) {
                    avail_16to17 = 0;
                } else if (parseInt(key) === id - 1) {
                    avail_16to17 = lastElemValue;
                }
                break;
            default:
                break;
        }
    }

}

//serve a AGGIORNARE il DB
function updateDB() {
    $.post("http://" + JAVA_TOMCAT_HOST + "/Esame/crud_availability.jsp",
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
        console.log(activity)
        console.log(week)
        console.log(specifications)
        console.log(maintainer)
        console.log(day)
        location.href = "6_assignEWO.html?activity=" + activity + "&week=" + week + "&time=" + estimatedTime + "&specifications=" + specifications + "&day=" + day + "&strActivity=" + stringActivity + "&strWorkNote=" + stringWorkNote + "&strSkills=" + stringSkills;
    }).fail(function () {
        alert("Error while updating database");
    });
}
