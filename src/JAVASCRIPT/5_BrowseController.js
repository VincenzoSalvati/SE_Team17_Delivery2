class BrowseControllerEWO {

    constructor() {
    }

    initBrowseView(week) {
        let microServiceEndpoints = [
            // 0) JSON Static, we used it for defining the data interface of a generic record for updating
            "jsonprototypes/address-book-record-prototype.json",

            // 1) JSON Static, we used it for defining the data interface of a new record for adding
            "jsonprototypes/address-book-new-record-prototype.json",

            // 2) A PHP implementation of JSON service
            "services/address-book-record-get.php",

            // 3) A Java JSP implementation of JSON service
            "http://" + JAVA_TOMCAT_HOST + "/Esame/5_ShowActivitiesEWO.jsp?week=" + week,

            // 4) A Java JSP implementation of JSON service
            "http://" + JAVA_TOMCAT_HOST + "/Esame/5_ShowActivitiesEWOCount.jsp?week=" + week
        ];
        let selectedMicroServiceEndpoint = microServiceEndpoints[3];
        let selectedMicroServiceEndpointCount = microServiceEndpoints[4];
        let controller = this;
        $.getJSON(selectedMicroServiceEndpoint, function (data) {
            controller.renderGUI(data);
        }).done(function () {
            controller.showMessageStatus("green", "All done");
        }).fail(function () {
            controller.showMessageStatus("red", "Error while requesting service: " + selectedMicroServiceEndpoint);
        });
        this.showMessageStatus("black", "Requesting data from service: " + selectedMicroServiceEndpoint);
        $.getJSON(selectedMicroServiceEndpointCount, function (data) {
            controller.renderGUI_Count(data);
        }).done(function () {
            controller.showMessageStatus("green", "All done");
        }).fail(function () {
            controller.showMessageStatus("red", "Error while requesting service: " + selectedMicroServiceEndpoint);
        });
        this.showMessageStatus("black", "Requesting data from service: " + selectedMicroServiceEndpoint);
    }

    renderGUI(data) {
        let staticHtml = $("#activityEWO-row-template").html();
        $.each(data, function (index, obj) {
            let row = staticHtml;
            row = row.replace(/{IdEwo}/ig, obj.id);
            row = row.replace(/{AreaEwo}/ig, obj.area);
            row = row.replace(/{TypeEwo}/ig, obj.type);
            row = row.replace(/{EstimatedTimeEwo}/ig, obj.estim_time);
            $('#activityEWO-rows').append(row);
        });
        if (data.length === 0) {
            $("tfoot :first-child").hide();
            $("tfoot").html('<tr><th colspan="6">No records</th></tr>');
        }
    }

    renderGUI_Count(data) {
        $.each(data, function (index, obj) {
            $("#ewo-count").append(obj.ewo_count);
        });
    }

    showMessageStatus(color, message) {
        $("#request-status").css("color", color)
            .html(message);
    }
}