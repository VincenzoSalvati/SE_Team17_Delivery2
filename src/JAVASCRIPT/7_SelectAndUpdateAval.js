var current_time = estimatedTime;
var ass_time = 0;
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
var idMaint;
let currentIdSelected = null;
//dizionario delle celle selezionate
var CellSelected = [];
var cellCount = 0;

function clickCell(checkId, d) {
    var i = 0;
    //variabile che serve a capire se si può scegliere o meno un minuto selezionato
    //quando è settata a false vuol dire che ho già selezionato del valore
    var check = true;

    $("#maintainerEWO-rows").on('click', '.cell', function () {
        var row = $(this).closest("tr");
        var colSelected = parseInt(row.find("." + d).html().replaceAll(" min", ""));
        idMaint = row.find("#link_id").html();
        if (idMaint === currentIdSelected || currentIdSelected == null)
            currentIdSelected = idMaint;

        while (i === 0) {
            avail_8to9 = parseInt(row.find(".link_8to9").html().replaceAll(" min", ""));
            avail_9to10 = parseInt(row.find(".link_9to10").html().replaceAll(" min", ""));
            avail_10to11 = parseInt(row.find(".link_10to11").html().replaceAll(" min", ""));
            avail_11to12 = parseInt(row.find(".link_11to12").html().replaceAll(" min", ""));
            avail_14to15 = parseInt(row.find(".link_14to15").html().replaceAll(" min", ""));
            avail_15to16 = parseInt(row.find(".link_15to16").html().replaceAll(" min", ""));
            avail_16to17 = parseInt(row.find(".link_16to17").html().replaceAll(" min", ""));
            //disableOtherRows();

            console.log('STEP ', cellCount);
            //inserisco nel dizionario l'id della cella selezionata, se già presente setto check a false
            //altrimenti lo setto a true

            for (var key in CellSelected) {
                // noinspection JSUnfilteredForInLoop
                if(d===CellSelected[key]){
                    console.log('ELEM ALREADY EXIST IN DICT', d);
                    check=false;
                }
            }

            //quindi se check == false non inserisco di nuovo l'elemento
            if(check){
                console.log('ELEM ADD TO DICT', d);
                CellSelected[cellCount] = d;
                cellCount++;
                console.log('CURRENT COUNT', cellCount)
            }

            if (idMaint === currentIdSelected) {
                if (document.getElementById("toBeAssMin").innerHTML !== "Time expired")
                    //se l'elemento selezionato non è uno di quelli già scelti va bene altrimenti do errore
                    if(check)
                        selectHour(d, colSelected, idMaint);
                    else
                        alert('Already selected. Please choose another hour availab.');
                else
                    alert('Reached time limit');
            } else {
                alert('Assign work only to one maintainer at time');
            }
            i++;
        }
    });
}

//select maintainer's available minutes
function selectHour(d, val, idMaint) {
    if (val === 0) {
        $('#alertWarning').show('fade');
    } else {
        dict[id] = d;
        id++;
        current_time -= parseInt(val);
        ass_time += parseInt(val);
        if (current_time > 0) {
            document.getElementById("toBeAssMin").innerHTML = current_time;
            document.getElementById("assMin").innerHTML = ass_time;
        } else if (current_time <= 0) {
            document.getElementById("assMin").innerHTML = ass_time;
            document.getElementById("toBeAssMin").innerHTML = "Time expired";
            //enable button Send
            document.querySelector('.btn-primary').style.pointerEvents = 'auto';
            document.querySelector('.btn-primary').style.opacity = 1;
        }
    }
}

//reset current_time
function clearHour() {
    current_time = estimatedTime;
    ass_time = 0;
    document.getElementById("totalMin").innerHTML = estimatedTime;
    document.getElementById("assMin").innerHTML = "";
    document.getElementById("toBeAssMin").innerHTML = "";
    currentIdSelected = null;
    for (var key in dict) {
        // noinspection JSUnfilteredForInLoop
        delete dict[key];
    }

    for (var key in CellSelected) {
        // noinspection JSUnfilteredForInLoop
        delete CellSelected[key];
    }
    //disable button Send
    document.querySelector('.btn-primary').style.pointerEvents = 'none';
    document.querySelector('.btn-primary').style.opacity = 0.5;
}

//initialize data for db
// noinspection JSUnfilteredForInLoop
function initParams() {
    name_day = document.getElementById("giorno").innerHTML;
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
    $.post("http://" + JAVA_TOMCAT_HOST + "/Esame/Crud_availabilityEWO.jsp",
        {
            week: week,
            id_maint: idMaint,
            day: day,
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
