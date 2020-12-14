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
    initBrowseView(activity, week) {

        let microServiceEndpoints = [
                // 0) JSON Static, we used it for defining the data interface of a generic record for updating
                "jsonprototypes/address-book-record-prototype.json",

                // 1) JSON Static, we used it for defining the data interface of a new record for adding
                "jsonprototypes/address-book-new-record-prototype.json",

                // 2) A PHP implementation of JSON service
                "services/address-book-record-get.php?activity=" + activity,

                // 3) A Java JSP implementation of JSON service
                "http://" + JAVA_TOMCAT_HOST + "/Esame/6_showSpecificationsEWO.jsp?activity=" + activity + "&week=" + week,

                // 4) Skills
                "http://" + JAVA_TOMCAT_HOST + "/Esame/6.2_showSpecificationsEWO.jsp?activity=" + activity
            ]
        ;

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

    /**
     * Render the given JSON data into GUI static design
     *
     * @note Uses the static HTML of #specifications-row template as a row template.
     *       Then, for each data row in the given JSON data array it dynamically
     *       render the single row HTML with data of the current iteration.
     *       After any iteration, the rendered row HTML is appended into
     *       #specifications-rows table body.  On ending iterations #specifications-rows HTML
     *       will be dynamically generated with the given JSON data array.
     *
     * @param  data a JSON representation of data
     */
    renderGUI(data) {
        /* Get the html template for table rows */
        let staticHtml = $("#specificationsEWO-row-template").html();
        let activityNav;
        let workNote;
        /* Bind obj data to the template, then append to table body */
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
            row = row.replace(/{Estimate_tr}/ig, obj.estimate_tr);
            $('#specificationsEWO-rows').append(row);
        });
        $("#strAct").append("Activity to assign:  " + activityNav);
        $("#strWorkNot").append(workNote);

        /* When empty specifications */
        if (data.length === 0) {
            $("tfoot :first-child").hide();
            $("tfoot").html('<tr><th colspan="5">No records</th></tr>');
        }
    }

    renderGUI2(data) {
        /* Get the html template for table rows */
        let staticHtml = $("#skill-row-template").html();
        var skill;
        var i = 0;
        $.each(data, function (index, obj) {
            let row = staticHtml;
            row = row.replace(/{IdSkill}/ig, obj.id);
            row = row.replace(/{Skill}/ig, obj.skill);
            $('#skill-rows').append(row)
                .append("<img class=\"check\" id=\"" + obj.id + "\" src = \"../img/check.png\">");

        });


        /* When empty specifications */
        if (data.length === 0) {
            $("tfoot :first-child").hide();
            $("tfoot").html('<tr><th colspan="5">No records</th></tr>');
        }
    }

    /**
     * Shows the given message with the given color into
     * GUI #request-status HTML element
     *
     * @param color string Message Color
     * @param message string Message text
     */
    showMessageStatus(color, message) {
        $("#request-status").css("color", color);
        $("#request-status").html(message);
    }

}