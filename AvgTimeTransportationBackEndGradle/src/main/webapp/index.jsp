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

            <form class="m-t" role="form" action="index.html">
                <h4 class="no-margins">登录：</h4>
                <p class="m-t-md">欢迎登录交通数据管理系统</p>
                <div class="form-group">
                    <input type="email" class="form-control" placeholder="用户名" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" required="">
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


<%--<script type="text/javascript">
    // 路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });

    // 使用

    require(
        [
            'echarts',
            'echarts/chart/pie'
        ],
        function (ec) {
            var myChart = ec.init(document.getElementById('main'));

            myChart.showLoading();

            var counts = [];

            $.ajax({
                type: 'GET',
                url: "http://localhost:8080/index/admin/countDayODs",
                data: "date=0901",
                dataType: "json",
                success: function (result) {
                    console.log(result);
                    if (result) {
                        counts.push(result.count);
                        console.log(counts);
                        myChart.hideLoading();
                        var option = {
                            title: {
                                text: '20170901出行OD量',
                                x: 'center'
                            },
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend: {
                                orient: 'vertial',
                                x: 'left',
                                data: ['20170901']
                            },
                            series: [
                                {
                                    type: 'pie',
                                    name: '数量',
                                    radius: '55%',
                                    center: ['50%', '60%'],
                                    data: [
                                        {name: '20170901', value: result.count}
                                    ]
                                }
                            ]
                        };

                        myChart.setOption(option);
                        myChart.hideLoading();
                    }

                },
                error: function (errorMsg) {
                    alert(errorMsg);
                    console.log(errorMsg);
                    myChart.hideLoading();
                }
            })
        }
    );

</script>--%>
</html>
