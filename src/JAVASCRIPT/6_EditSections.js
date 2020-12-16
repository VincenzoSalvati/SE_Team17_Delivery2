function popupEditEstimTime(idSpecifications) {
    var new_text = prompt("Edit estimated time required:");
    if (new_text != null && isNumeric(new_text) && !(!isNaN(new_text) && new_text.toString().indexOf('.') !== -1)) {
        if (parseInt(new_text) > 0)
            saveNewEstimTime(idSpecifications, new_text);
        else
            alert("Please, insert only integer number values strictly positive")
    } else if (new_text != null)
        alert("Please, insert only integer number values");
}

function isNumeric(str) {
    if (typeof str != "string") return false // we only process strings!
    return !isNaN(str) && // use type coercion to parse the _entirety_ of the string (`parseFloat` alone does not do this)...
        !isNaN(parseFloat(str)) // ...and ensure strings of whitespace fail
}

//save new Intervention Type on db
function saveNewIntType(idSpecifications, text_mod) {
    $.post("http://" + JAVA_TOMCAT_HOST + "/Esame/Crud_specificationsEWO.jsp",
        {
            id: idSpecifications,
            int_des: text_mod
        }, function (data) {
        }).done(function () {
        location.reload();
        alert("Database updated");
    }).fail(function () {
        alert("Error while updating database");
    });
}

function popupEditIntType(idSpecifications) {
    var new_text = prompt("Edit intervention type:");
    if (new_text != null)
        saveNewIntType(idSpecifications, new_text);
}

//save new Estimated Time on db
function saveNewEstimTime(idSpecifications, text_mod) {
    $.post("http://" + JAVA_TOMCAT_HOST + "/Esame/Crud_specificationsEWO.jsp",
        {
            id: idSpecifications,
            estimate_tr: text_mod,
            week: 1,
            ewo: 1   /*diamo per scontato che sia EWO*/
        }, function (data) {
        }).done(function () {
        location.reload();
        alert("Database updated");
    }).fail(function () {
        alert("Error while updating database");
    });
}

//manage skill
var list1 = [];
var list2 = [];

//fetch skill
function manageSkill(thisObj, idSkill, skill) {
    var check = document.getElementById(idSkill);
    var c = 0;
    for (var i = 0; i < list1.length; i++) {
        if (list1[i] === idSkill) {
            list1.pop(idSkill);
            list2.pop(skill);
            check.style.visibility = 'hidden';
            c = c + 1;
        }
    }
    if (c === 0) {
        list1.push(idSkill);
        list2.push(skill);
        check.style.visibility = 'visible';
    }
}

//organize string skill
function escamotageToManageStringSkill() {
    var s;
    if (list1.length > 0) {
        for (var i = 0; i < list1.length; i++) {
            if (i === 0)
                s = list1[i].toString();
            else
                s = s + "-" + list1[i].toString();
        }
    }
    return s;
}

//remove skill from list HTML (Current skills setted)
function removeItem() {
    var ul = document.getElementById("dynamic-list-skill");
    ul.innerHTML = "";
}

//add skill from list HTML (Current skills setted)
function addItem(ski) {
    var ul = document.getElementById("dynamic-list-skill");
    var li = document.createElement("li");
    li.setAttribute('id', ski);
    li.appendChild(document.createTextNode(ski));
    ul.appendChild(li);
}

function selectSubmit(idSpec) {
    if (list1.length > 0)
        updateDb(idSpec);
}

//update specifications EWO on db
function updateDb(idSpec) {
    /* Call the microservice and evaluate data and result status */
    $.post("http://" + JAVA_TOMCAT_HOST + "/Esame/6.3_ShowSpecificationsEWO.jsp",
        {
            specifications: idSpec,
            stringSkills: escamotageToManageStringSkill()
        }, function (data) {
        }).done(function () {
        alert("Database updated");
    }).fail(function () {
        alert("Error while updating database");
    });

    skills = "";
    removeItem()
    for (var i = 0; i < list2.length; i++) {
        var check = document.getElementById(list1[i]);
        check.style.visibility = 'hidden';
        addItem(list2[i]);
        skills = skills + "<br>" + list2[i];
    }
    list1 = [];
    list2 = [];
}