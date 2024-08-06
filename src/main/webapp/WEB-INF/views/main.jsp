<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <title>Forty by HTML5 UP</title>
      <meta charset="UTF-8" />
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <link rel="stylesheet" href="assets/css/main.css">
   </head>
   <body>
    <div class="header">
        <div class="menu-icon" onclick="openMenu()">
            <img src="img/menu.png">
        </div>
        <div class="logo">
            <img src="img/Re_Plogging_로고.png" alt="" />
        </div>
        <ul class="menu">
            <li>플로코스</li>
            <li>분리배출</li>
            <li>커뮤니티</li>
            <li>그린마켓</li>
            <li onclick="openModal()">로그인</li>
            <li class="menu-close" onclick="closeMenu()">X</li>
        </ul>

        <div class="user-icon" onclick="openModal()">
            <img src="img/비로그인.png">
        </div>
    </div>

    <div class="container">
        <div class="text">
            <img src="img/왕관.png" alt="왕관">
            <P class="normal">이달의 </P>
            <P class="plo-point">플로킹!</P>
        </div>

        <div class="banner">
            <p><span>오영희</span>님!</p>
        </div>
    </div>

    <div class="plogging_01">
        <img src="img/메인_01.png" alt="Plogging Image">
        <div class="plogging_01-TC">
            <div id="plogging_01-title">
                <p class="title">Plogging<span class="dot">.</span></p>
            </div>
            <div class="plogging_01-content">
                <span>
                    2016년에 스웨덴에서 시작된 플로깅은<br>
                    스웨덴어 ‘줍다’라는 의미의 ‘플로카 우프’와<br>
                    영어 ‘조깅’의 합성어로<br>
                    <span class="point">조깅하면서 쓰레기를 줍는 행동</span>을 말합니다.
                </span>
            </div>
        </div>
    </div>

        <div class="plogging_02 fade-in-right">
            <img src="img/메인_02.png" alt="Plogging Image">
            <div class="plogging_02-TC">
                <div id="plogging_02-title">
                    <p class="title">좋은점<span class="dot">.</span></p>
                </div>
                <div class="plogging_02-content">
                    <span>
                        달리기에 스쿼트 동작과 유사한 쓰레기 줍는 동작이 더해지면<br>
                        운동 효과가 강화되고 칼로리 소모도 증가합니다.<br>
                        이를 통해<span class="point">체력 단련과 건강 증진을 도모</span>할 수 있으며,<br>
                        <span class="point">환경 보호 운동</span>으로 지역사회에 공헌도 할 수 있습니다.
                    </span>
                </div>
            </div>
        </div>


        <div class="plogging_03 fade-in-left">
            <img src="img/메인_03.png" alt="Plogging Image">
            <div class="plogging_03-TC">
                <div id="plogging_03-title">
                    <p class="title">참여방법<span class="dot">.</span></p>
                </div>
                <div class="plogging_03-content">
                    <span>
                        플로깅에 참여하려면,<br>
                        쓰레기를 담을 튼튼한 <span class="point">봉투</span>와<br>손을 보호하기 위한 <span class="point">장갑</span>을 준비하세요.<br>
                        또는, 쓰레기를 줍기 위한 <span class="point">집게</span>를 사용할 수도 있습니다.<br><br>
                        <span class="point">친구</span>나 <span class="point">가족</span>과 함께 참여하면<br>
                        더 즐겁고 효과적으로 플로깅을 즐길 수 있습니다.
                    </span>
                </div>
            </div>
        </div>

        <div class="plogging_04 fade-in-right">
            <img src="img/메인_04.png" alt="Plogging Image">
            <div class="plogging_04-TC">
                <div id="plogging_04-title">
                    <p class="title">분리배출<span class="dot">.</span></p>
                </div>
                <div class="plogging_04-content">
                    <span>
                        분리배출을 어려워하는 여러분들을 위해 플로깅을 통해<br>
                        수집한 쓰레기 이미지를 첨부하거나,<br>
                        <span class="point">분리배출 탭</span>에서 배출방법을 안내해드립니다.<br>
                        <p><a href="#" class="custom-button">분리배출 방법 보러가기</a></p>
                    </span>
                </div>
            </div>
        </div>


    <!-- 모달 오버레이 -->
    <div class="modal-overlay" id="modal">
        <div class="modal-container">
            <img src="img/Re_Plogging_로고.png" alt="Re: Plogging Logo">
            
            <form id="loginForm" action="" method="post">
                <input type="text" id="useremail" name="user_id" placeholder="ID@example.com">
                <input type="password" id="password" name="user_pw" placeholder="비밀번호를 입력하세요.">
                <div id="errorMessage" class="error-message">이메일 또는 비밀번호를 잘못 입력했습니다.<br>입력하신 내용을 다시 확인해주세요.</div>
                
                <button type="submit" class="login-button">로그인</button>
                <div class="or">
                    <div class="line"></div>
                    <div>또는</div>
                    <div class="line"></div>
                </div>
                <button class="kakao-login">Kakao 로그인</button>
            </form>
                
                <div class="login-options">
                    <a href="#">회원가입</a>
                    <a href="#">비밀번호 찾기</a>
                </div>
            
            <!-- 모달 닫기 버튼 -->
            <div class="modal-close" onclick="closeModal()">닫기</div>
        </div>
    </div>

    <footer>
        © 2024 지구수호대 Korea Corporation All Rights Reserved.
    </footer>
    
    <!-- Scripts -->
    <script src="assets/js/main.js"></script>
   </body>
</html>

