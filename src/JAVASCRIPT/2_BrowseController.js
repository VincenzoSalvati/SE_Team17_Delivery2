// noinspection JSJQueryEfficiency
class BrowseController {

    constructor() {

    }

    initBrowseView(activity, week) {
        let microServiceEndpoints = [
            // 0) JSON Static, we used it for defining the data interface of a generic record for updating
            "jsonprototypes/address-book-record-prototype.json",
            // 1) JSON Static, we used it for defining the data interface of a new record for adding
            "jsonprototypes/address-book-new-record-prototype.json",
            // 2) A PHP implementation of JSON service
            "services/address-book-record-get.php?activity=" + activity,
            // 3) A Java JSP implementation of JSON service
            "http://" + JAVA_TOMCAT_HOST + "/Esame/2_showSpecifications.jsp?activity=" + activity + "&week=" + week
        ];
        let selectedMicroServiceEndpoint = microServiceEndpoints[3];
        let controller = this;
        $.getJSON(selectedMicroServiceEndpoint, function (data) {
            controller.renderGUI(data);
        }).done(function () {
        }).fail(function () {
        });
    }

    renderGUI(data) {
        let staticHtml = $("#specifications-row-template").html();
        let activityNav;
        let skl;
        let workNote;
        $.each(data, function (index, obj) {
            let row = staticHtml;
            row = row.replace(/{Id}/ig, obj.id);
            workNote = obj.work_note;
            row = row.replace(/{Work_note}/ig, workNote);
            row = row.replace(/{Int_des}/ig, obj.int_des);
            activityNav = obj.id_activity;
            row = row.replace(/{Id_activity}/ig, activityNav);
            row = row.replace(/{Week_activity}/ig, obj.week_activity);
            skl = obj.skill;
            row = row.replace(/{Skill}/ig, skl);
            $('#specifications-rows').append(row);
        });
        $("#strAct").append("Activity to assign:  " + activityNav);
        $("#strSkl").append(skl);
        $("#strWorkNot").append(workNote);
        if (data.length === 0) {
            $("tfoot :first-child").hide();
            $("tfoot").html('<tr><th colspan="5">No records</th></tr>');
        }
    }

    showMessageStatus(color, message) {
        $("#request-status").css("color", color);
        $("#request-status").html(message);
    }
}