<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/manage/common/taglibs.jsp"%>
<%@ include file="/manage/common/meta.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">
    <title>锁屏页面</title>
</head>

<body class="lock-screen" onload="startTime()">
    <div class="lock-wrapper">
        <div id="time"></div>
        <div class="lock-box text-center">
            <img src="${ctx }/img/head-lg.png" alt="锁屏"/>
            <h1>张三丰</h1>
            <span class="locked">锁定</span>
            <form role="form" class="form-inline" action="index.html">
                <div class="form-group col-lg-24">
                    <input type="password" placeholder="请输入密码" id="exampleInputPassword2" class="form-control lock-input">
                    <button class="btn btn-lock" type="submit">
                        <i class="fa fa-arrow-right"></i>
                    </button>
                </div>
            </form>
        </div>
    </div>
    <script>
        function startTime(){
            var today=new Date();
            var h=today.getHours();
            var m=today.getMinutes();
            var s=today.getSeconds();
            // add a zero in front of numbers<10
            m=checkTime(m);
            s=checkTime(s);
            document.getElementById('time').innerHTML=h+":"+m+":"+s;
            t=setTimeout(function(){startTime()},500);
        }

        function checkTime(i){
            if (i<10){
                i="0" + i;
            }
            return i;
        }
    </script>
</body>
</html>
