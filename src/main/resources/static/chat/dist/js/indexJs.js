jQuery(document).ready(
    function () {
        var token = sessionStorage.getItem("token");
        var user = sessionStorage.getItem("user");
        var user2 = JSON.parse(user);
        resetFriendsList();
        console.info(user);
        var socket = new SockJS('/endpoint-websocket'); //连接上端点(基站)
        stompClient = Stomp.over(socket);			//用stom进行包装，规范协议
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/chat/single/' + user2.id, function (result) {
                //console.info(JSON.parse(result));
                console.info(JSON.parse(result.body));
                saveRecordingMsg(result);
            });
        });

        $("#sendMsgBtn").click(function () {
            stompClient.send(
                "/app/singleChat/private",
                {},
                JSON.stringify({
                    'content': $("#context").val(),
                    'receiveUserId': 2,
                    'sendUserId': user2.id
                }));
        })
    }
);
//保存接受到的消息
function saveRecordingMsg(message) {
   $.ajax({
       url:"/chat/saveChatRecording",
       type:"POST",
       contentType: "application/json;charset=utf-8",
       data:message,
       success:function (result) {
            if(result.code == 200){
                console.log("success");
            }else {
                console.log("fail");
            }
       }
   })
}

//初始化好友列表
function resetFriendsList() {
    var friendsList = JSON.parse(sessionStorage.getItem("friendsList"));
    if(friendsList == null){
        return;
    }
    var str = '';
    friendsList.forEach(function (val, index) {
        console.info(val);
        if(index == 0){
            str += '<li class="active">';
        }else {
            str += '<li>';
        }
        str += '<div class="conversation ">';
        str += '';
        str += '<div class="user-avatar user-avatar-rounded online">';
        str += '<div class="chatriq-user chatriq-user-11"></div>';
        str += '</div>';
        str += '<div class="conversation__details">';
        str += '<div class="conversation__name">';
        str += '<div class="conversation__name--title">' + '用户Id:' + val.id + '用户名：' + val.userName + '</div>';
        str += '<div class="conversation__time">07:15 PM</div>';
        str += '</div>';
        str += '<div class="conversation__message">';
        str += '<div class="conversation__message-preview">';
        str += 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Soluta consequuntur accusantium tempora. Ad officiis voluptate neque, deleniti porro necessitatibus aut!';
        str += '</div>';
        str += '<span><i class="mdi mdi-pin"></i></span>';
        str += '</div>';
        str += '</div>';
        str += '</div>';
        str += '</li>';
    });

    $("#friendsList").html(str);

}