<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/sockjs.js"></script>
<script src="/resources/js/stomp.min.js"></script>
<script>
	var roomId = '달찐이'
	var sock = new SockJS("/websockethandler");
// 	var sock = new SockJS("/chat");
	// var sock = new WebSocket("ws://localhost:8080/chat")
	// 1. SockJS를 내부에 들고 있는 client를 내어준다.
	var client = Stomp.over(sock);
	// 2. connection이 맺어지면 실행된다.
	client.connect({}, function() {
		// 3. send(path, header, message)로 메시지를 보낼 수 있다.
		client.send('/publish/chat/join', {}, JSON.stringify({
			chatRoomId : roomId,
			writer : '용민'
		}));
		// 4. subscribe(path, callback)로 메시지를 받을 수 있다. callback 첫번째 파라미터의 body로 메시지의 내용이 들어온다.
		client.subscribe('/subscribe/chat/room/' + roomId, function(chat) {
			var content = JSON.parse(chat.body);
			$('body').append(
					'<h1>' + content.message + '(' + content.writer + ')'
							+ '</h1>')
		});
	});
</script>
<title>Coming Soon - Start Bootstrap Theme</title>
</head>
<body>
	<div class="masthead">
		<div class="masthead-bg"></div>
		<div class="container h-100">
			<div class="row h-100">
				<div class="col-12 my-auto">
					<div class="masthead-content text-white py-5 py-md-0">
						<h1 class="mb-3">Welcome Rich!!</h1>
						<p class="mb-5">
							We will help you manage your assets more efficiently. Let's be <strong>rich</strong> in 2020 with lots of wealth.
						</p>
						<form action="/logout" method="post" id="frm" name="frm">
							<sec:authentication property="principal" />
							<input type="submit" value="LOGOUT!!" />
							<sec:csrfInput />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>