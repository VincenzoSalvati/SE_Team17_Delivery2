const JAVA_TOMCAT_HOST = "192.168.1.5:8080";

class BrowseController {

    constructor() {

    }

    initBrowseView(activity, week, specifications) {
        let microServiceEndpoints = [
            // 0) JSON Static, we used it for defining the data interface of a generic record for updating
            "jsonprototypes/address-book-record-prototype.json",
            // 1) JSON Static, we used it for defining the data interface of a new record for adding
            "jsonprototypes/address-book-new-record-prototype.json",
            // 2) A PHP implementation of JSON service
            "services/address-book-record-get.php?activity=" + activity,
            // 3) A Java JSP implementation of JSON service
            "http://" + JAVA_TOMCAT_HOST + "/Esame/3_assignMaintainer.jsp?activity=" + activity + "&week=" + week + "&specifications=" + specifications
        ];
        let selectedMicroServiceEndpoint = microServiceEndpoints[3];
        let controller = this;
        $.getJSON(selectedMicroServiceEndpoint, function (data) {
            controller.renderGUI(data);
        }).done(function () {
            controller.showMessageStatus("green", "All done");
        }).fail(function () {
            controller.showMessageStatus("red", "Error while requesting service: " + controller.serviceEndPoint);
        });
        this.showMessageStatus("black", "Requesting data from service: " + this.serviceEndPoint);
    }

    renderGUI(data) {
        let staticHtml = $("#maintainer-row-template").html();
        $.each(data, function (index, obj) {
            let row = staticHtml;
            row = row.replace(/{Id}/ig, obj.id);
            row = row.replace(/{Maint}/ig, obj.maint);
            row = row.replace(/{Skills}/ig, obj.skills);
            row = row.replace(/{Mon}/ig, obj.mon);
            row = row.replace(/{Tue}/ig, obj.tue);
            row = row.replace(/{Wed}/ig, obj.wed);
            row = row.replace(/{Thu}/ig, obj.thu);
            row = row.replace(/{Fri}/ig, obj.fri);
            row = row.replace(/{Sat}/ig, obj.sat);
            row = row.replace(/{Sun}/ig, obj.sun);
            $('#maintainer-rows').append(row);
        });
        if (data.length === 0) {
            $("tfoot :first-child").hide();
            $("tfoot").html('<tr><th colspan="6">No records</th></tr>');
        }
    }

    showMessageStatus(color, message) {
        $("#request-status").css("color", color).html(message);
    }
}