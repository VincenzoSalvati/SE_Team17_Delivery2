function popupEditWorkNote(idSpecifications) {
    var new_text = prompt("Edit notes:");
    if (new_text != null)
        saveNewWorkNote(idSpecifications, new_text);
}

//save new Workspace Note on db
function saveNewWorkNote(idSpecifications, text_mod) {
    $.post("http://" + JAVA_TOMCAT_HOST + "/Esame/Crud_specifications.jsp",
        {
            id: idSpecifications,
            work_note: text_mod
        }, function (data) {
        }).done(function () {
        location.reload();
        alert("Database updated");
    }).fail(function () {
        alert("Error while updating database");
    });
}