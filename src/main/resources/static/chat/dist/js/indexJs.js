var groupChatList;
var user2;
jQuery(document).ready(
    function () {
        var token = sessionStorage.getItem("token");
        var user = sessionStorage.getItem("user");
        user2 = JSON.parse(user);
        resetFriendsList();
        console.info(user2);
        var socket = new SockJS('/endpoint-websocket'); //连接上端点(基站)
        stompClient = Stomp.over(socket);			//用stom进行包装，规范协议
        stompClient.debug = null;

        getUserGroupChatList(user2.id);//获取用户群聊列表
        connectPrivateChatWebSocket(user2.id);//连接WebSocket


        $("#sendMsgBtn").click(function () {
            stompClient.send(
                "/app/singleChat/private",
                {},
                JSON.stringify({
                    'content': $("#context").val(),
                    'receiveUserId': 2,
                    'sendUserId': user2.id
                }));
        });

        $("#sendMsgBtnForGroup").click(function () {
            stompClient.send(
                "/app/groupChat/send",
                {},
                JSON.stringify({
                    'content': $("#context").val(),
                    'receiveGroupId': 2,
                    'sendUserId': user2.id
                }));
        })
    }
);

/**
 * 订阅私聊服务器
 */
function connectPrivateChatWebSocket(userId) {
    stompClient.connect(
        {},
        function (frame) {
            console.log("WebSocket服务器连接成功");
            //console.log('Connected: ' + frame);
            //订阅私人频道
            stompClient.subscribe('/chat/single/' + userId, function (result) {
                console.info(JSON.parse(result.body));
                //saveRecordingMsg(result);
            });
            //订阅群聊频道
            groupChatList.forEach(function (val,index) {
                console.log(val);
                stompClient.subscribe('/groupChat/send/' + val.id, function (result) {
                    console.info(JSON.parse(result.body));
                });
            });
        },
        function (error) {
            console.log("websocket服务器连接失败:" + error);
        })
}


/**
 * 获取用户群聊列表
 * @param userId
 */
function getUserGroupChatList(userId) {
    var isSuccess;
    $.ajax({
        url: "/groupChat/getUserGroupChatListByUserId?userId=" + userId,
        type: "GET",
        async: false,
        success: function (result) {
            if (result.code == 200) {
                //console.log(result.data);
                groupChatList = result.data;
                isSuccess = true;
            } else {
                console.log("fail");
                isSuccess = false;
            }
        }
    });
}

//保存接受到的消息
function saveRecordingMsg(message) {
    $.ajax({
        url: "/chat/saveChatRecording",
        type: "POST",
        contentType: "application/json;charset=utf-8",
        data: message,
        success: function (result) {
            if (result.code == 200) {
                console.log("success");
            } else {
                console.log("fail");
            }
        }
    })
}

//初始化好友列表
function resetFriendsList() {
    var friendsList = JSON.parse(sessionStorage.getItem("friendsList"));
    if (friendsList == null) {
        return;
    }
    var str = '';
    friendsList.forEach(function (val, index) {
        console.info(val);
        if (index == 0) {
            str += '<li class="active">';
        } else {
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