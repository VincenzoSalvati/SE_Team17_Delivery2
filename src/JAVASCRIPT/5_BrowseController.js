class BrowseControllerEWO {

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
    initBrowseView(week) {

        let microServiceEndpoints = [
            // 0) JSON Static, we used it for defining the data interface of a generic record for updating
            "jsonprototypes/address-book-record-prototype.json",

            // 1) JSON Static, we used it for defining the data interface of a new record for adding
            "jsonprototypes/address-book-new-record-prototype.json",

            // 2) A PHP implementation of JSON service
            "services/address-book-record-get.php",

            // 3) A Java JSP implementation of JSON service
            "http://" + JAVA_TOMCAT_HOST + "/Esame/5_showActivitiesEWO.jsp?week=" + week,

            // 4) A Java JSP implementation of JSON service
            "http://" + JAVA_TOMCAT_HOST + "/Esame/5_showActivitiesEWOCount.jsp?week=" + week
        ];

        let selectedMicroServiceEndpoint = microServiceEndpoints[3];
        let selectedMicroServiceEndpointCount = microServiceEndpoints[4];
        let controller = this;

        /* Call the microservice and evaluate data and result status */
        $.getJSON(selectedMicroServiceEndpoint, function (data) {
            controller.renderGUI(data);
        }).done(function () {
            controller.showMessageStatus("green", "All done");
        }).fail(function () {
            controller.showMessageStatus("red", "Error while requesting service EWO: " + controller.serviceEndPoint);
        });

        this.showMessageStatus("black", "Requesting data from service: " + this.serviceEndPoint);

        //JSON For EWO Count
        $.getJSON(selectedMicroServiceEndpointCount, function (data) {
            controller.renderGUI_Count(data);
        }).done(function () {
            controller.showMessageStatus("green", "All done");
        }).fail(function () {
            controller.showMessageStatus("red", "Error while requesting service EWO Count: " + controller.serviceEndPoint);
        });

        this.showMessageStatus("black", "Requesting data from service: " + this.serviceEndPoint);
    }

    /**
     * Render the given JSON data into GUI static design
     *
     * @note Uses the static HTML of #address-book-row template as a row template.
     *       Then, for each data row in the given JOSN data array it dynamically
     *       render the single row HTML with data of the current iteration.
     *       After any iteration, the rendered row HTML is appended into
     *       #address-book-rows table body.  On ending iterations #address-book-rows HTML
     *       will be dynamically generated with the given JSON data array.
     *
     * @param  data a JSON representation of data
     */
    renderGUI(data) {
        /* Get the html template for table rows */
        let staticHtml = $("#activityEWO-row-template").html();
        /* Bind obj data to the template, then append to table body */
        $.each(data, function (index, obj) {
            let row = staticHtml;
            row = row.replace(/{IdEwo}/ig, obj.id);
            row = row.replace(/{AreaEwo}/ig, obj.area);
            row = row.replace(/{TypeEwo}/ig, obj.type);
            row = row.replace(/{EstimatedTimeEwo}/ig, obj.estim_time);
            $('#activityEWO-rows').append(row);
        });

        //$("#availMaint").append("AVAILABILITY " + nameMaintainer);

        /* When empty address-book */
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

    /**
     * Shows the given message with the given color into
     * GUI #request-status HTML element
     *
     * @param color string Message Color
     * @param message string Message text
     */
    showMessageStatus(color, message) {
        $("#request-status").css("color", color).html(message);
    }

}