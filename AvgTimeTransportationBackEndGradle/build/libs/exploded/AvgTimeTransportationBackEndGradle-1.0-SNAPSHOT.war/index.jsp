<%--
  Created by IntelliJ IDEA.
  User: PYF
  Date: 2018/5/15
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>

<head>
    <style type="text/css">
        html{height: 100%;}
        body {
            height: auto;
            background-size:cover;
            /*color: rgba(255,255,255,.95);*/
            background: url(${pageContext.request.contextPath}/img/loginBackground.jpg) no-repeat fixed center center;
        }

        .signinpanel {
            width: 320px;
            margin: 10% auto 0 auto;
        }

        .signinpanel .logopanel {
            float: none;
            width: auto;
            padding: 0;
            background: none;
        }

        .signinpanel .signin-info ul {
            list-style: none;
            padding: 0;
            margin: 20px 0;
        }

        .signinpanel .form-control {
            display: block;
            margin-top: 15px;
        }

        .signinpanel .btn {
            margin-top: 15px;
        }

        .m-t-md {
            font-weight: bold;
            font-family: "微软雅黑";
            color: #000;
        }

        .signinpanel form {
            background: rgba(255, 255, 255, 0.2);
            border: 1px solid rgba(255,255,255,.3);
            -moz-box-shadow: 0 3px 0 rgba(12, 12, 12, 0.03);
            -webkit-box-shadow: 0 3px 0 rgba(12, 12, 12, 0.03);
            box-shadow: 0 3px 0 rgba(12, 12, 12, 0.03);
            -moz-border-radius: 3px;
            -webkit-border-radius: 3px;
            border-radius: 3px;
            padding: 30px;
            padding-top: 10px;
        }

        .signup-footer{border-top: solid 1px rgba(255,255,255,.3);margin:20px 0;padding-top: 15px;}

        @media screen and (max-width: 768px) {
            .signinpanel,
            .signuppanel {
                margin: 0 auto;
                width: 420px!important;
                padding: 20px;
            }
            .signinpanel form {
                margin-top: 20px;
            }
            .signup-footer {
                margin-bottom: 10px;
            }
            .signuppanel .form-control {
                margin-bottom: 10px;
            }
            .signup-footer .pull-left,
            .signup-footer .pull-right {
                float: none !important;
                text-align: center;
            }
            .signinpanel .signin-info ul {
                display: none;
            }
        }
        @media screen and (max-width: 320px) {
            .signinpanel,
            .signuppanel {
                margin:0 20px;
                width:auto;
            }
        }
    </style>

    <script type="text/javascript">
        function check(form) {
            console.log(form);
            var ajax_option = {
                url: "http://localhost:8080/index/login/vertify",
                success: function (data) {
                    console.log(data)
                    if(data.page == "index"){
                        window.location.href = "http://localhost:8080/index";
                    }else {
                        window.location.href = "http://localhost:8080/index/admin.jsp"
                    }
                }
            }
            $("#formId").ajaxSubmit(ajax_option);
        }
    </script>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>管理后台登录界面</title>
    <meta name="keywords" content="">
    <meta name="description" content="">


    <%--<script>--%>
        <%--if (window.top !== window.self) {--%>
            <%--window.top.location = window.location;--%>
        <%--}--%>
    <%--</script>--%>
</head>
<body class="signin">
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-12">

            <form class="m-t" role="form" id="formId" action="./login/vertify" method="post">
                <h3 class="m-t-md">欢迎登录交通数据管理系统</h3>
                <p class="no-margins">登录：</p>
                <div class="form-group">
                    <input type="email" name="username" class="form-control" placeholder="用户名" required="">
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="密码" required="">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>

            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
            &copy; PYF
        </div>
    </div>
</div>

</body>



</html>
