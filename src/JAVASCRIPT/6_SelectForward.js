function selectForward() {
    $(".buttonForward").click(function () {
        var $row = $(this).closest("tr");
        var $id = $row.find(".id").text();
        var $strActivity = $row.find(".strActivity").text();
        var $strWorkNote = $row.find(".strWorkNote").text();
        location.href = "7_assignEWO.html?activity=" + activity + "&week=" + week + "&estimatedTime=" + estimatedTime + "&specifications=" + $id + "&strActivity=" + $strActivity + "&strWorkNote=" + $strWorkNote + "&skills=" + skills;
    });
}