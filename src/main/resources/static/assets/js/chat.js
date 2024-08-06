// 1. 채팅 기능을 구현할 때 필요한 DOM을 미리 선택
const chatDiv = $('.chat'); // 채팅창
const message = $('#message'); // 입력창
const btn = $('#sendBtn'); // 메세지 보내는 버튼

// 2. 미리 메세지를 담을 javascript 객체 선언
//      우리의 DTO와 동일한 모양으로 설계
const chat = {
   "email" : $('#userEmail').html(),
   "message" : ""
};


// 3. 페이지 로딩이 끝나면, WebSocket과 연결
let socket; // WebSocket 객체가 담길 변수를 미리 선언
$(document).ready(function(){
   
   // Web Socket과 연결
   let url = "ws://localhost:8096/boot/chat";
   
   socket = new WebSocket(url); // 소켓 객체 생성
   
   // 웹소켓에 콜백함수 추가
   socket.onmessage=onMessage;

   // 채팅창 초기화(불러오기)
   chatDiv.html('');

   // 채팅 불러오기
   loadChat();
   
   // 보내기 버튼에 Click 이벤트 걸기
   btn.on("click", send);
   
});

// 4. 메세지 보내기 기능 만들기
function send(){
   // 1) 사용자가 적은 텍스트를 가져오기
   chat.message = message.val();
   
   // 2) javascript 객체 --> JSON 문자열
   var json = JSON.stringify(chat);
   
   // 3) 변환한 데이터를 소켓으로 전송
   socket.send(json);
   
   // 4) 메세지 창 내용 초기화
   message.val("");
   
   // 5. 보낸 내용이 채팅창에 보이게 만들기
   printChat(chat);
   
}

// 5. 채팅창에 채팅 출력하기
function printChat(data){
   
   var div = '';
   
   if(data.email == chat.email){
      
      div =`
         <div class="mychat">
            <div class="text-message">
               <p>${data.message}</p>
            </div>
         </div>
      `;
   }
   else{
      div =`
         <div class="other">
            <div class="username">
               <span>${data.email}</span>
            </div>
            <div class="text-message">
               <p>${data.message}</p>
            </div>
         </div>
      `;
   }
   
   chatDiv.append(div);
   
   // chatDiv(채팅창) 스크롤을 가장 밑으로 내려주기
   chatDiv.scrollTop(chatDiv[0].scrollHeight);
   
}

// 6. Client가 메세지를 받았을 때
function onMessage( msg ) {
   
   // 1) 받은 메세지 객체에서 실제 데이터만 빼기
   var json = msg.data;
   
   // 2) 채팅 출력
   printChat( JSON.parse(json) );
}
   

// 7. 이전 채팅 불러오기
function loadChat(){
   
   // 비동기 요청
   $.ajax({
      url: "load",
      type : "get",
      data : {},
      success : function(res){
         console.log(res);

		 for( let i = 0; i < res.length; i++ ) {
		
				temp = res[i];
				
				temp.email = temp.email.email;
		
				printChat(  );
		}
		
      },
      error : function(e){
         
      }
      
   });
   
   
}