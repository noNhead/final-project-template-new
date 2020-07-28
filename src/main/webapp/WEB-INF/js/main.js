$(document).ready(function(){
    $("#buttonSearch").click(function () {
        $.post("search", $("searchQuery").val());
        $.get("search", function (responseText) {
            let parser = jQuery.parseJSON(responseText);
            console.log(responseText);
            parser.forEach(function (item, i, array) {
                $("#searchTable").prepend("<tr>" +
                    "<td><img src=''/></td>" +
                    "<td><p>item.title</p></td>" +
                    "<td><p>item.author</p></td>" +
                    "<td><p>item.year</p></td>" +
                    "<td><p>item.title</p></td>" +
                    "<td><p>item.title</p></td>" +
                    "<td><p>item.title</p></td>" +
                    "</tr>");
            });
        });
    });
});