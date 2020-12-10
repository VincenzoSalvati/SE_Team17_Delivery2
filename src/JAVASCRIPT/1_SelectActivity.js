function next() {
    $(".btn_cell").click(function () {
        var $row = $(this).closest("tr");
        var $text = $row.find(".id").text();
        var $time = $row.find(".time").text();
        location.href = "2_showSpecifications.html?activity=" + $text + "&week=" + week + "&time=" + $time;
    });
}