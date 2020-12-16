function selectEwo() {
    $(".butEWO").click(function () {
        var $row = $(this).closest("tr");
        var $EWO = $row.find(".idEwo").text();
        var $estimatedTime = $row.find(".timeEwo").text();
        location.href = "6_ShowSpecificationsEWO.html?activity=" + $EWO.replace("ID ", "") + "&week=" + week + "&estimatedTime=" + $estimatedTime.replace("EstimatedTime ", "");
    });

}
