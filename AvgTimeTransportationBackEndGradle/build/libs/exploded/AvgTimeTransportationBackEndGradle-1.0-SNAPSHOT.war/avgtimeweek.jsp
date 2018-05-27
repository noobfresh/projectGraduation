<%--
  Created by IntelliJ IDEA.
  User: PYF
  Date: 2018/5/27
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<head>
    <title>管理页面</title>

    <meta charset="UTF-8">
    <meta name="viewport"content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <script type="text/javascript">
        /* rem 适应*/
        /*让文字和标签的大小随着屏幕的尺寸做变话 等比缩放*/
        var html = document.getElementsByTagName('html')[0];
        /*取到屏幕的宽度*/
        var width = window.innerWidth;
        /* 640 100  320 50 */
        if (width > 1080) {
            width = 1080;
        }
        else if (width < 320 ) {
            width = 320 ;
        }
        var fontSize = 100/1080*width;
        /*设置fontsize*/

        html.style.fontSize = fontSize +'px';
        window.onresize = function(){
            var html = document.getElementsByTagName('html')[0];
            /*取到屏幕的宽度*/
            var width = window.innerWidth;
            if (width > 1080) {
                width = 1080;
            }
            else if (width < 320 ) {
                width = 320 ;
            }
            /* 640 100  320 50 */
            var fontSize = 100/1080 * width;
            /*设置fontsize*/
            html.style.fontSize = fontSize +'px';
        }
    </script>

    <style type="text/css">
        #menu {
            overflow: hidden;
            height: 100%;
            float: left;
            -webkit-touch-callout: none;
            -webkit-user-select: none;
            -khtml-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }
        #menu #ensconce {
            /*隐藏菜单样式*/
            width: 0.35rem;
            height: 100%;
            background-color: #20343c;
            float: left;
            text-align: center;
            position: relative;
            display: none;
        }
        #menu #ensconce h2 {
            cursor: pointer;
            color: #fff;
            font-size: 0.24rem;
            line-height: 0.5rem;
            width: 100%;
            position: absolute;
            top: 35%;
            -webkit-transform: translate(-50%, -50%);
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(0%, -50%);
        }
        #menu #ensconce h2 img {
            width: 52%;
        }
        #menu #open {
            /*显示菜单样式*/
            width: 2.6rem;
            height: 100%;
            background-color: #363a45;
            padding-bottom: 0.1rem;
            box-sizing: border-box;
            -webkit-transition: all 0.8s ease;
            -moz-transition: all 0.8s ease;
            -o-transition: all 0.8s ease;
            -ms-transition: all 0.8s ease;
        }
        #menu #open .navH {
            height: 0.6rem;
            background-color: #44495a;
            line-height: 0.6rem;
            text-align: center;
            font-size: 0.2rem;
            color: #fff;
            position: relative;
            box-sizing: border-box;
        }
        #menu #open .navH span {
            position: absolute;
            top: 49%;
            right: 0;
            padding: 0 0.15rem;
            cursor: pointer;
            -webkit-transform: translate(0, -50%);
            -moz-transform: translate(0, -50%);
            -ms-transform: translate(0, -50%);
            -o-transform: translate(0, -50%);
            transform: translate(0, -50%);
            display: inline-block;
        }
        #menu #open .navH span .obscure {
            width: 0.24rem;
        }
        #menu #open .navBox {
            height: 100%;
            overflow-y: auto;
            padding-left: 0.05rem;
            padding-right: 0.07rem;
            margin-top: 0.1rem;
        }
        #menu #open .navBox ul li {
            background-color: #393c4a;
            cursor: pointer;
            margin-bottom: 4px;
        }
        #menu #open .navBox ul li .obtain {
            background-color: #3889D4;
        }
        #menu #open .navBox ul li .obtain:hover {
            background-color: #3D8332;
        }
        #menu #open .navBox ul li h2 {
            position: relative;
            width: 100%;
            height: 3%;
            text-align: center;
            color: #fff;
            font-size: 0.15rem;
            padding: 0.15rem 0;
            -webkit-transition: all 0.6s ease;
            -moz-transition: all 0.6s ease;
            -o-transition: all 0.6s ease;
            -ms-transition: all 0.6s ease;
        }
        #menu #open .navBox ul li h2 i {
            position: absolute;
            top: 50%;
            right: 0.15rem;
            border-top: 0.07rem transparent dashed;
            border-left: 0.07rem solid #fff;
            border-right: 0.07rem transparent dashed;
            border-bottom: 0.07rem transparent dashed;
            display: inline-block;
            -webkit-transition: -webkit-transform 0.6s ease;
            -moz-transition: -moz-transform 0.6s ease;
            -o-transition: -o-transform 0.6s ease;
            -ms-transition: -ms-transform 0.6s ease;
            transform-origin: 4px 3px;
            -webkit-transform: translate(0, -50%);
            -moz-transform: translate(0, -50%);
            -ms-transform: translate(0, -50%);
            -o-transform: translate(0, -50%);
            transform: translate(0, -50%);
        }
        #menu #open .navBox ul li h2 .arrowRot {
            -webkit-transform: rotate(90deg);
            -moz-transform: rotate(90deg);
            -o-transform: rotate(90deg);
            -ms-transform: rotate(90deg);
            transform: rotate(90deg);
        }
        #menu #open .navBox ul li .secondary {
            overflow: hidden;
            height: 0;
            -webkit-transition: all 0.6s ease;
            -moz-transition: all 0.6s ease;
            -o-transition: all 0.6s ease;
            -ms-transition: all 0.6s ease;
        }
        #menu #open .navBox ul li .secondary h3 {
            padding: 0.1rem 0;
            text-align: center;
            font-size: 0.13rem;
            background-color: #282c3a;
            color: #ffffff;
            border-bottom: 0.8px solid #42495d;
            -webkit-transition: all 0.4s ease;
            -moz-transition: all 0.4s ease;
            -o-transition: all 0.4s ease;
            -ms-transition: all 0.4s ease;
        }
        #menu #open .navBox ul li .secondary h3:hover {
            background-color: #1acbfc;
        }
        #menu #open .navBox ul li .secondary .seconFocus {
            background-color: #1acbfc;
            -webkit-box-shadow: 3px 3px 3px #aa8c51;
            -moz-box-shadow: 3px 3px 3px #aa8c51;
            box-shadow: 3px 3px 3px #aa8c51;
        }

        body, ul, li, ol, dl, dd, dt, p, h1, h2, h3, h4, h5, h6, form, img {
            margin: 0;
            padding: 0;
        }

        body, html {
            font-size: 16px;
            font-family: "微软雅黑";
            height: 100%;
            width: 100%;
            box-sizing: border-box;
        }

        /*定位居中*/
        .middle {
            position: absolute;
            left: 50%;
            top: 50%;
            -webkit-transform: translate(-50%, -50%);
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
        }

        a {
            color: #3e3e3e;
            text-decoration: none;
        }

        img, input, button, textarea {
            border: none;
            padding: 0;
            margin: 0;
            outline-style: none;
        }

        ul {
            list-style: none;
        }

        input {
            padding-top: 0;
            padding-bottom: 0;
            font-family: "SimSun", "宋体";
        }

        /*去掉行内替换元素空白缝隙*/
        img {
            border: 0;
            vertical-align: middle;
        }

        h1, h2, h3, h4, h5, h6 {
            text-decoration: none;
            font-weight: normal;
            font-size: 100%;
        }

        s, i, em, u {
            font-style: normal;
            text-decoration: none;
        }

        .fl {
            float: left;
        }

        .fr {
            float: right;
        }

        /*清除浮动*/
        .clearfix:before,
        .clearfix:after {
            content: "";
            display: table;
        }

        .clearfix:after {
            clear: both;
        }

        .clearfix {
            *zoom: 1;
            /*IE/7/6*/
        }

    </style>

</head>
<body>


<div id="menu">
    <!--隐藏菜单-->
    <div id="ensconce">
        <h2>
            <img src="${pageContext.request.contextPath}/img/show.png" alt="">
            国内各地景点
        </h2>
    </div>

    <!--显示菜单-->
    <div id="open">
        <div class="navH">
            国内各地景点
            <span><img class="obscure"
                       src="${pageContext.request.contextPath}/img/obscure.png" alt=""></span>
        </div>
        <div class="navBox">
            <ul>
                <li>
                    <h2 class="obtain">旅程时间<i></i></h2>
                    <div class="secondary">
                        <h3 id="avgtimeday" onclick="goToOther(this)">全天</h3>
                        <h3 id="avgtimeweek" onclick="goToOther(this)">一周</h3>
                    </div>
                </li>
                <li>
                    <h2 class="obtain">LSTM<i></i></h2>
                    <div class="secondary">
                        <h3 id="lstmday" onclick="goToOther(this)">全天</h3>
                        <h3 id="lstmweek" onclick="goToOther(this)">一周</h3>
                    </div>
                </li>
                <li>
                    <h2 class="obtain">统计<i></i></h2>
                    <div class="secondary">
                        <h3 id="statday" onclick="goToOther(this)">全天</h3>
                        <h3 id="statweek" onclick="goToOther(this)">一周</h3>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>
<div id="showPart" style="float: left;">
    <label for="dateInput" style="margin-left: 50px; margin-top: 50px; margin-bottom: 50px;">
        选择日期：</label>
    <input type="date" value="2017-09-01" id="dateInput"/>
    <button id="searachBtn" onclick="requestByDate()" style="width: 100px; height: 50px">查询</button>
    <div id="main"
         style="height: 400px; background-color: #000000; width: 900px; margin-left: 50px"></div>
</div>
<script type="text/javascript">
    window.onload = function () {
        var flag = true;
        var liC = document.querySelectorAll(".navBox li h2");
        // 主导航nav点击事件
        for (var i = 0; i < liC.length; i++) {
            liC[i].onclick = function () {
                if (flag) {
                    // 节流阀
                    flag = false;
                    setTimeout(function () {
                        flag = true;
                    }, 500)
                    // 自点
                    if (this.className === "obFocus") {
                        this.querySelector("i").classList.remove("arrowRot");
                        getNext(this).style.height = "0";
                        this.classList.add("obtain");
                        this.classList.remove("obFocus");
                        return
                    }

                    var sec = getNext(this);
                    var sib = siblings(sec.parentNode);
                    var otherArr = [];
                    var arrowClass = [];
                    // 排他 secondary arrowRot obFocus
                    for (var j = 0; j < sib.length; j++) {
                        var sibSec = sib[j].getElementsByTagName('*');
                        for (var i = 0; i < sibSec.length; i++) {
                            if (sibSec[i].className == "secondary") {
                                otherArr.push(sibSec[i])
                            }
                            if (sibSec[i].className == "arrowRot") {
                                arrowClass.push(sibSec[i])
                            }
                            if (sibSec[i].className == "obFocus") {
                                sibSec[i].classList.remove("obFocus");
                                sibSec[i].classList.add("obtain");

                            }
                        }
                    }
                    for (var i = 0; i < otherArr.length; i++) {
                        otherArr[i].style.height = "0";
                    }
                    if (arrowClass[0]) {
                        arrowClass[0].classList.remove("arrowRot");
                    }

                    // 留自己
                    sec.style.height = 1.0 + "rem";
                    this.getElementsByTagName("i")[0].classList.add("arrowRot");
                    this.classList.remove("obtain");
                    this.classList.add("obFocus");
                }

            }
        }

        // 子导航点击事件
        // var seconC = document.querySelectorAll(".secondary h3");
        // for (var i = 0; i < seconC.length; i++) {
        //     seconC[i].onclick = function () {
        //         for (var i = 0; i < seconC.length; i++) {
        //             //焦点变色问题
        //             seconC[i].classList.remove("seconFocus");
        //         }
        //         this.classList.add("seconFocus");
        //         // window.location.href = 'http://localhost:8080/index/';
        //         console.log(seconC[i]);
        //     }
        // }

        // 隐藏菜单
        var obscure = document.querySelector(".navH span");
        var open = document.querySelector("#open");
        var ensconce = document.querySelector("#ensconce");
        obscure.onclick = function () {
            open.style.marginLeft = "-300px";
            setTimeout(function () {
                ensconce.style.display = "block";
            }, 350)

        }
        //显示菜单
        var showC = document.querySelector("#ensconce h2");
        showC.onclick = function () {
            open.style.marginLeft = "0px";
            setTimeout(function () {
                ensconce.style.display = "none";
            }, 100)

        }
    }

    function getByClass(clsName, parent) {
        var oParent = parent ? document.getElementById(parent) : document,
            boxArr = new Array(),
            oElements = oParent.getElementsByTagName('*');
        for (var i = 0; i < oElements.length; i++) {
            if (oElements[i].className == clsName) {
                boxArr.push(oElements[i]);
            }
        }
        return boxArr;
    }
    // 获取下一个兄弟元素
    function getNext(node) {
        if (!node.nextSibling) return null;
        var nextNode = node.nextSibling;
        if (nextNode.nodeType == 1) {
            return nextNode;
        }
        return getNext(node.nextSibling);
    }

    // 获取除了自己以外的其他亲兄弟元素
    function siblings(elem) {
        var r = [];
        var n = elem.parentNode.firstChild;
        for (; n; n = n.nextSibling) {
            if (n.nodeType === 1 && n !== elem) {
                r.push(n);
            }
        }
        return r;
    }

    function showAndHide(elem) {
        var seconC = document.querySelectorAll(".secondary h3");
        for (var i = 0; i < seconC.length; i++) {
            //焦点变色问题
            seconC[i].classList.remove("seconFocus");
        }
        elem.classList.add("seconFocus");
    }

    function goToOther(elem) {
        showAndHide(elem);
        console.log(elem.id);
        window.location.href = "http://localhost:8080/index/" + elem.id + ".jsp";

    }
</script>
</body>

<script type="text/javascript">
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
        function getOd(ec) {
            var myChart = ec.init(document.getElementById('main'));

            myChart.showLoading();

            var counts = [];
            var date = document.getElementById("dateInput").value;
            var dateArray = date.split("-");
            date = dateArray[0] + dateArray[1] + dateArray[2];
            console.log(date);
            $.ajax({
                type: 'GET',
                url: "http://localhost:8080/index/admin/countDayODs",
                data: "date=" + date,
                dataType: "json",
                success: function (result) {
                    console.log(result);
                    if (result) {
                        counts.push(result.count);
                        console.log(counts);
                        myChart.hideLoading();
                        var option = {
                            title: {
                                text: date + '周平均旅程对比',
                                x: 'center'
                            },
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend: {
                                orient: 'vertial',
                                x: 'left',
                                data: [date]
                            },
                            series: [
                                {
                                    type: 'pie',
                                    name: '数量',
                                    radius: '55%',
                                    center: ['50%', '60%'],
                                    data: [
                                        {name: date, value: result.count}
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


    function requestByDate() {
        var mycharts = require('echarts').init(document.getElementById("main"));
        mycharts.showLoading();
        var counts = [];
        var date = document.getElementById("dateInput").value;
        var dateArray = date.split("-");
        date = dateArray[0] + dateArray[1] + dateArray[2];
        console.log(date);
        $.ajax({
            type: 'GET',
            url: "http://localhost:8080/index/admin/countDayODs",
            data: "date=" + date,
            dataType: "json",
            success: function (result) {
                console.log(result);
                if (result) {
                    counts.push(result.count);
                    console.log(counts);
                    mycharts.hideLoading();
                    var option = {
                        title: {
                            text: date + '周平均旅程对比',
                            x: 'center'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            orient: 'vertial',
                            x: 'left',
                            data: [date]
                        },
                        series: [
                            {
                                type: 'pie',
                                name: '数量',
                                radius: '55%',
                                center: ['50%', '60%'],
                                data: [
                                    {name: date, value: result.count}
                                ]
                            }
                        ]
                    };

                    mycharts.setOption(option);
                    mycharts.hideLoading();
                }

            },
            error: function (errorMsg) {
                alert(errorMsg);
                console.log(errorMsg);
                mycharts.hideLoading();
            }
        })
    }
</script>
</html>
