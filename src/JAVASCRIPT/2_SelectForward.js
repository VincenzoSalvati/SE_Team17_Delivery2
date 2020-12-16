function selectForward() {
    $(".buttonForward").click(function () {
        var $row = $(this).closest("tr");
        var $text = $row.find(".id").text();
        var $strActivity = $row.find(".strActivity").text();
        var $strSkills = $row.find(".strSkills").text();
        var $strWorkNote = $row.find(".strWorkNote").text();
        location.href = "3_AssignMaintainer.html?activity=" + activity + "&week=" + week + "&specifications=" + $text + "&strActivity=" + $strActivity + "&strSkills=" + $strSkills + "&time=" + estimatedTime
            + "&strWorkNote=" + $strWorkNote;
    });
}