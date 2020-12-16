function selectHome() {
    $(".buttonHome").click(function () {
        location.href = "1_ShowActivities.html?week=" + week
    });
}