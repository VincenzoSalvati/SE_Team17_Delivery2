function selectForward() {
    $(".buttonForward").click(function () {
        var $row = $(this).closest("tr");
        var $id = $row.find(".id").text();
        var $strActivity = $row.find(".strActivity").text();
        var $strWorkNote = $row.find(".strWorkNote").text();
        var $estmTime = $row.find(".strEstmTime").text();
        location.href = "7_AssignEWO.html?activity=" + activity + "&week=" + week + "&estimatedTime=" + $estmTime + "&specifications=" + $id + "&strActivity=" + $strActivity + "&strWorkNote=" + $strWorkNote + "&skills=" + skills;
    });
}