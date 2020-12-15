class BrowseController {

    /**
     *  The service endpoint providing JSON data
     */

    constructor() {

    }

    /**
     * Fetch JSON data from the micro service, then call a function for rendering the View
     *
     * @use renderGUI(), showMessageStatus()
     */
    initBrowseView(activity, week, specifications, day) {
        console.log("http://" + JAVA_TOMCAT_HOST + "7_assignEWO.jsp?activity=" + activity + "&week=" + week + "&specifications=" + specifications + "&day=" + day)
        let microServiceEndpoints = [
            // 0) JSON Static, we used it for defining the data interface of a generic record for updating
            "../JSON/file.json",

            // 1) JSON Static, we used it for defining the data interface of a new record for adding
            "jsonprototypes/address-book-new-record-prototype.json",

            // 2) A PHP implementation of JSON service
            "services/address-book-record-get.php?activity=" + activity,

            // 3) A Java JSP implementation of JSON service
            "http://" + JAVA_TOMCAT_HOST + "/Esame/7_assignEWO.jsp?activity=" + activity + "&week=" + week + "&specifications=" + specifications + "&day=" + day
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
        let staticHtml = $("#maintainerEWO-row-template").html();
        $.each(data, function (index, obj) {
            let row = staticHtml;
            row = row.replace(/{Id}/ig, obj.id);
            row = row.replace(/{Maint}/ig, obj.maint);
            row = row.replace(/{Skills}/ig, obj.skills);
            row = row.replace(/{h8to9}/ig, obj.h8to9);
            row = row.replace(/{h9to10}/ig, obj.h9to10);
            row = row.replace(/{h10to11}/ig, obj.h10to11);
            row = row.replace(/{h11to12}/ig, obj.h11to12);
            row = row.replace(/{h14to15}/ig, obj.h14to15);
            row = row.replace(/{h15to16}/ig, obj.h15to16);
            row = row.replace(/{h16to17}/ig, obj.h16to17);
            $('#maintainerEWO-rows').append(row);
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