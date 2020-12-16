function selectActivity() {
    $(".btn_cell").click(function () {
        var $row = $(this).closest("tr");
        var $text = $row.find(".id").text();
        var $time = $row.find(".time").text();
        location.href = "2_ShowSpecifications.html?activity=" + $text + "&week=" + week + "&time=" + $time;
    });
}

function logOut(){
    location.href = "loginPage.html";
}