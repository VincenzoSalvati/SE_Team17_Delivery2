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
var idMaint;

let currentIdSelected = null;

function next(checkId, d) {
    var i = 0;
    // code to read selected table row cell data (values).
    $("#maintainerEWO-rows").on('click', '.cell', function () {
        // get the current row
        var row = $(this).closest("tr");
        var colSelected = parseInt(row.find("." + d).html().replaceAll(" min", ""));

        idMaint = row.find("#link_id").html();

        //currentIdSelected è l'id del maintainer scelto per primo
        //salvo in questa variabile l'id del primo maintainer dunque quando scelgo il prossimo orario
        //se questo è relativo ad un maintainer diverso, non mi fa selezionare
        if (idMaint == currentIdSelected || currentIdSelected == null)
            currentIdSelected = idMaint;

        while (i === 0) {
            console.log("VALORE SELEZIOANTO\t" + colSelected + "\nID MAINT\t" + idMaint)

            //mi prendo tutta la riga di valori che poi assegnerò alle variabili 'avail' che userò in 'initParams'
            avail_8to9 = parseInt(row.find(".link_8to9").html().replaceAll(" min", ""));
            avail_9to10 = parseInt(row.find(".link_9to10").html().replaceAll(" min", ""));
            avail_10to11 = parseInt(row.find(".link_10to11").html().replaceAll(" min", ""));
            avail_11to12 = parseInt(row.find(".link_11to12").html().replaceAll(" min", ""));
            avail_14to15 = parseInt(row.find(".link_14to15").html().replaceAll(" min", ""));
            avail_15to16 = parseInt(row.find(".link_15to16").html().replaceAll(" min", ""));
            avail_16to17 = parseInt(row.find(".link_16to17").html().replaceAll(" min", ""));

            console.log(avail_8to9);
            console.log(avail_9to10);
            console.log(avail_10to11);
            console.log(avail_11to12);
            console.log(avail_14to15);
            console.log(avail_15to16);
            console.log(avail_16to17);

            //una volta selezionato un button di una riga
            //disableOtherRows();
            if (idMaint == currentIdSelected) {
                if (document.getElementById("toBeAssMin").innerHTML != "Time expired")
                    selectHour(d, colSelected, idMaint);
                else
                    alert('Reached time limit');
            } else {
                alert('Assign work only to one maintainer at time');
            }
            i++;
        }

    });
}

function selectHour(d, val, idMaint) {

    //se il valore è 0 non posso prenderlo per assegnare del tempo, dunque non mostro
    if (val === 0) {
        $('#alertWarning').show('fade');
    } else {
        //aggiungo il valori selezionati al dizionario
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
            console.log(dict);
            //rendo disponibile il button Forward
            document.querySelector('.btn-primary').style.pointerEvents = 'auto';
            document.querySelector('.btn-primary').style.opacity = 1;
        }
    }
}

//serve a RESETTARE la scelta dei minuti
function clearHour() {
    current_time = estimatedTime;
    ass_time = 0;

    document.getElementById("totalMin").innerHTML = estimatedTime;
    document.getElementById("assMin").innerHTML = "";
    document.getElementById("toBeAssMin").innerHTML = "";
    currentIdSelected = null;

    //eliminare contenuto dizionario
    for (var key in dict) {
        delete dict[key];
    }

    //disabilito il button Forward
    document.querySelector('.btn-primary').style.pointerEvents = 'none';
    document.querySelector('.btn-primary').style.opacity = 0.5;
}

//serve a PREPARARE i parametri per il DB
// noinspection JSUnfilteredForInLoop
function initParams() {
    name_day = document.getElementById("giorno").innerHTML;
    console.log('GIORNO ', name_day)

    //ultimo elemento del dict
    lastElem = dict[id - 1];
    lastElemValue = parseInt(document.querySelector('.' + lastElem).textContent.replace(" min", ""));
    lastElemValue = Math.abs(current_time);
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
    console.log('UPDATE DB')
    console.log(idMaint);
    console.log(day);
    console.log(avail_8to9);
    console.log(avail_9to10);
    console.log(avail_10to11);
    console.log(avail_11to12);
    console.log(avail_14to15);
    console.log(avail_15to16);
    console.log(avail_16to17);

    $.post("http://" + JAVA_TOMCAT_HOST + "/Esame/crud_availabilityEWO.jsp",
        {
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
        //location.href = "1_showActivities.html?week=" + week;
    }).fail(function () {
        alert("Error while updating database");
    });
}
