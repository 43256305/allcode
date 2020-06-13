$(document).ready(function() {
    $('#btn').click(function(){
        $.ajax({
            type:"post",
            url: "http://localhost:8080/task/"+$("#id").val(),
        }).then(function(data) {
            $("#table").html("<td>"+data.id+"</td>"+"<td>"+data.name+"</td>")
        });
    });
});
