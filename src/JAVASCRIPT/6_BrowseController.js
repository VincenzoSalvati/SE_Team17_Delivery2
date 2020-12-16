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
            "services/address-book-record-get.php?",
            // 3) A Java JSP implementation of JSON service
            "http://" + JAVA_TOMCAT_HOST + "/Esame/6_ShowSpecificationsEWO.jsp?activity=" + activity + "&week=" + week,
            // 4) Skills
            "http://" + JAVA_TOMCAT_HOST + "/Esame/6.2_ShowSpecificationsEWO.jsp?activity=" + activity
        ];
        let selectedMicroServiceEndpoint = microServiceEndpoints[3];
        let selectedMicroServiceEndpoint2 = microServiceEndpoints[4];
        let controller = this;
        $.getJSON(selectedMicroServiceEndpoint, function (data) {
            controller.renderGUI(data);
        }).done(function () {
            $.getJSON(selectedMicroServiceEndpoint2, function (data) {
                controller.renderGUI2(data);
            }).done(function () {
            }).fail(function () {
            });
        }).fail(function () {
        });
    }

    renderGUI(data) {
        let staticHtml = $("#specificationsEWO-row-template").html();
        let activityNav;
        let workNote;
        let estimationTime;
        $.each(data, function (index, obj) {
            let row = staticHtml;
            row = row.replace(/{Id}/ig, obj.id);
            workNote = obj.work_note;
            row = row.replace(/{Work_note}/ig, workNote);
            row = row.replace(/{Int_des}/ig, obj.int_des);
            activityNav = obj.id_activity;
            row = row.replace(/{Id_activity}/ig, activityNav);
            row = row.replace(/{Week_activity}/ig, obj.week_activity);
            row = row.replace(/{Ewo_activity}/ig, obj.ewo_activity);
            estimationTime = obj.estimate_tr
            row = row.replace(/{Estimate_tr}/ig, estimationTime);
            $('#specificationsEWO-rows').append(row);
        });
        $("#strAct").append("Activity to assign:  " + activityNav);
        $("#strWorkNot").append(workNote);
        $("#strEstTim").append(estimationTime);
        if (data.length === 0) {
            $("tfoot :first-child").hide();
            $("tfoot").html('<tr><th colspan="5">No records</th></tr>');
        }
    }

    renderGUI2(data) {
        let staticHtml = $("#skill-row-template").html();
        $.each(data, function (index, obj) {
            let row = staticHtml;
            row = row.replace(/{IdSkill}/ig, obj.id);
            row = row.replace(/{Skill}/ig, obj.skill);
            $('#skill-rows').append(row)
                .append("<img class=\"check\" id=\"" + obj.id + "\" src = \"../img/check.png\" alt=\"\">");
        });
        if (data.length === 0) {
            $("tfoot :first-child").hide();
            $("tfoot").html('<tr><th colspan="5">No records</th></tr>');
        }
    }

    showMessageStatus(color, message) {
        $("#request-status").css("color", color)
            .html(message);
    }
}