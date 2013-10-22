function updateChat() {
    var count = $(":selected").val();
    $.ajax({
        url : '/ajax/' + count,
        success : function(records) {
            $('#chat').html(records);
        }
    });
}

function saveRecord() {
    var message = $("#message").val();
    $.ajax({
        url : '/save-record/',
        type: "POST",
        dataType: "text",
        encoding:"UTF-8",
        data: "message=" + message,
        success : function() {
            $("#message").val('');
            updateChat();
        }
    });
}

updateChat();
setInterval(updateChat, 10000);