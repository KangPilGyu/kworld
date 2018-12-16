<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script src="/resources/js/moment.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
	// --------------------------- Modal 설정
	$("#myModal").show();
	
	$("#okBtn").click(function(){
		if($("#nickname").val()==""){
			alert("닉네임을 입력해주십시오~~");
			return ;
		}
		$("#sender").val($("#nickname").val());
		$("#myModal").hide();
		//open
		openSocket();
	});
	
	// enter 키 처리
	$("#messageInput").keypress(function(e) { 

	    if (e.keyCode == 13){
	    	send();
	    }    
	});
	
});
</script>
 

    
<div class="row" style="height: 60px"></div>
<div class="container" style="width:100%">
    <div>
        <input type="text" id="sender" value="유저명" style="display: none;">
        <input type="text" id="socketId" value="" style="display: none;">
    </div>
<div class="modal" id="myModal" style="width:100%">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Input Your NickName</h4>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
       <sec:authorize access="isAuthenticated()">
       	닉네임 : <input id="nickname" type="text" value="<sec:authentication property="principal.m_name"/>">
       </sec:authorize>
       <sec:authorize access="isAnonymous()">
      	닉네임 : <input id="nickname" type="text" value="익명${cnt}">
      	</sec:authorize>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button id="okBtn" type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
      </div>
    </div>
  </div>
</div>


<div class="messaging">
      <div class="inbox_msg">
        <div class="inbox_people">
          <div class="headind_srch">
            <div class="recent_heading">
              <h4>Chatting</h4>
            </div>
            <!-- <div class="srch_bar">
              <div class="input-group">
                <input type="text"  placeholder="Search" >
                <span class="input-group-btn" >
                <button class="btn" type="button"> <i class="fa fa-search" aria-hidden="true"></i></button>
                </span> </div>
            </div> -->
          </div>
          
          <div class="inbox_chat">
         </div>
         </div>
        
        <div class="mesgs" style="overflow:auto">
          <div class="msg_history">
          
    
            
            
   
          </div>
          
          <div class="type_msg">
            <div class="input_msg_write">
              <input id="messageInput" type="text" class="write_msg" placeholder="Type a message" />
              <button class="msg_send_btn" type="button" onclick="send();"><i class="fa fa-paper-plane"></i></button>
            </div>
          </div>
          
        </div>
      </div>
            
    </div>
</div>




<!--     <div>
        <button type="button" onclick="openSocket();">Open</button>
        <button type="button" onclick="send();">Send</button>
        <button type="button" onclick="closeSocket();">Close</button>
    </div> -->
    <!-- Server responses get written here -->
    
       <!-- websocket javascript -->
    <script>
        var ws;
        
        function openSocket(){
            if(ws!==undefined && ws.readyState!==WebSocket.CLOSED){
                writeResponse("WebSocket is already opened.");
                return;
            }
            
            //웹소켓 객체 만드는 코드
            
            ws=new WebSocket("ws://freekworld.com/echo");
                         
            ws.onopen=function(){
              	//닉네임 전송...
            	var jmsg={ 
              			type:"username",
              			context: $("#sender").val(),
              			id:"username",
              			date: ""
              	};
                ws.send(JSON.stringify(jmsg));               
            };
            ws.onmessage=function(event){
            	var msg = JSON.parse(event.data);
            	//alert(event.data);
            	switch(msg.type){
            		case "id":
            			$("#socketId").val(msg.context);
            			break;
            		case "list":
            			writelist(msg.context);
            			break;
            		case "receive":
            			writeChat(msg);
            			break;
            		case "alert":
            			closeChat(msg);
            			break;
            	}
            	
            };
            ws.onclose=function(event){
                closeSocket();
            }
            
        }
        
        function send(){
        	var mss=$("#messageInput").val();
        	var jmsg={
          			type:"send",
          			context: $("#messageInput").val(),
          			id:$("#socketId").val(),
          			date: ""
          	};
        	ws.send(JSON.stringify(jmsg));  
        	$("#messageInput").val("");
        	$("#messageInput").focus();
        	
        	var addstr = "";
        	addstr+='<div class="outgoing_msg"><div class="sent_msg">';
        	addstr+='<p>'+mss+'</p>';
        	addstr+='<span class="time_date">'+$("#sender").val()+' | '+moment().format().slice(0,16).replace("T"," ")+'</span> </div></div>';
            
        	$(".msg_history").append(addstr);
        	$(".msg_history").animate({ scrollTop: $('.msg_history').prop("scrollHeight")}, 0);
        }
        
        function closeSocket(){
            ws.close();
        }
        function writeChat(msg){
        	var addstr = "";
        	addstr+='<div class="incoming_msg"><div class="incoming_msg_img">';
        	addstr+='<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>';
        	addstr+='<div class="received_msg"><div class="received_withd_msg">';
        	addstr+='<p>'+msg.context+'</p>';
        	addstr+='<span class="time_date">'+msg.id+' | ' +msg.date+'</span></div></div></div>';         
            
        	$(".msg_history").append(addstr);
        	$(".msg_history").animate({ scrollTop: $('.msg_history').prop("scrollHeight")}, 0);
        
        } 
        function closeChat(msg){
        	var str='<span class="time_date">'+msg.context+' | '+ msg.date +'</span>' ;
        	$(".msg_history").append(str);
        	$(".msg_history").animate({ scrollTop: $('.msg_history').prop("scrollHeight")}, 0);
        }
        function writelist(json){

        	var ary = JSON.parse(json);
        	var str="";
        	
        	for(var i = 0; i < ary.length ; i++){
        		str += '<div class="chat_list';
        		if($("#socketId").val()==ary[i].num) str +=" active_chat";
        		str += '"><div class="chat_people"><div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>';
        		str += '<div class="chat_ib"><h5>'+ary[i].name+'</h5>';
        		str +='  </div></div></div>';
        	}
          	$(".inbox_chat").html(str);

        }
  </script>

