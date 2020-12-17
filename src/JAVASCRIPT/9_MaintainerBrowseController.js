class BrowseControllerMaintainer {

    constructor() {
    }

    initBrowseView(id_maint) {
        let microServiceEndpoints = [
            // 0) JSON Static, we used it for defining the data interface of a generic record for updating
            "jsonprototypes/address-book-record-prototype.json",

            // 1) JSON Static, we used it for defining the data interface of a new record for adding
            "jsonprototypes/address-book-new-record-prototype.json",

            // 2) A PHP implementation of JSON service
            "services/address-book-record-get.php",

            // 3) A Java JSP implementation of JSON service
            "http://" + JAVA_TOMCAT_HOST + "/Esame/ShowMaintainerAssignment.jsp?id_maint=" + id_maint,

            // 4) A Java JSP implementation of JSON service
            "http://" + JAVA_TOMCAT_HOST + "/Esame/ShowMaintainerAssignment.jsp?id_maint=" + id_maint
        ];
        let selectedMicroServiceEndpoint = microServiceEndpoints[4];

        let controller = this;
        $.getJSON(selectedMicroServiceEndpoint, function (data) {
            controller.renderGUI(data);
        }).done(function () {
            controller.showMessageStatus("green", "All done");
        }).fail(function () {
            controller.showMessageStatus("red", "Error while requesting service: " + selectedMicroServiceEndpoint);
        });
        this.showMessageStatus("black", "Requesting data from service: " + selectedMicroServiceEndpoint);

    }

    renderGUI(data) {
        let staticHtml = $("#activityMaintainer-row-template").html();
        $.each(data, function (index, obj) {
            let row = staticHtml;
            row = row.replace(/{ID_MAINT}/ig, obj.id_maintainer);
            row = row.replace(/{WEEK_ACTIVITY}/ig, obj.week_activity);
            row = row.replace(/{SITE}/ig, obj.site);
            row = row.replace(/{TYPE}/ig, obj.type);
            row = row.replace(/{ESTIM_TIME}/ig, obj.estim_time);
            row = row.replace(/{INT_DES}/ig, obj.int_des);
            row = row.replace(/{WORK_NOTE}/ig, obj.work_note);
            row = row.replace(/{EWO_ACTIVITY}/ig, obj.ewo_activity);
            $('#activityMaintainer-rows').append(row);
        });
        if (data.length === 0) {
            $("tfoot :first-child").hide();
            $("tfoot").html('<tr><th colspan="6">No records</th></tr>');
        }
    }

    showMessageStatus(color, message) {
        $("#request-status").css("color", color)
            .html(message);
    }
}