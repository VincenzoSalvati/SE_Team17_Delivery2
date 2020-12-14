// noinspection JSJQueryEfficiency
class BrowseController {

    constructor() {
    }

    initBrowseView(week) {
        let microServiceEndpoints = [
            // 0) JSON Static, we used it for defining the data interface of a generic record for updating
            "jsonprototypes/address-book-record-prototype.json",
            // 1) JSON Static, we used it for defining the data interface of a new record for adding
            "jsonprototypes/address-book-new-record-prototype.json",
            // 2) A PHP implementation of JSON service
            "services/address-book-record-get.php?activity=",
            // 3) A Java JSP implementation of JSON service
            "http://" + JAVA_TOMCAT_HOST + "/Esame/7_showTickets.jsp?week=" + week
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
        let controller = this;
        let staticHtml = $("#ewo-row-template").html();
        $.each(data, function (index, obj) {
            let row = staticHtml;
            row = row.replace(/{Id_EWO}/ig, obj.id);
            row = row.replace(/{Area}/ig, obj.area);
            row = row.replace(/{Type}/ig, obj.type);
            row = row.replace(/{EstimatedTime}/ig, obj.estim_time);
            row = row.replace(/{Department}/ig, obj.department);
            row = row.replace(/{Maintainer}/ig, obj.maintainer);
            row = row.replace(/{State}/ig, obj.state);
            row = row.replace()
            $('#ewo-rows').append(row);

        });
        if (data.length === 0) {
            $("tfoot :first-child").hide();
            $("tfoot").html('<tr><th colspan="6">No records</th></tr>');
        }
    }

    showMessageStatus(color, message) {
        $("#ewo-request-status").css("color", color);
        $("#ewo-request-status").html(message);
        $("#state-request-status").css("color", color);
        $("#state-request-status").html(message);
    }
}