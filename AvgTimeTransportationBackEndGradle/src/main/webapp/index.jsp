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
    <title>$Title$</title>
  </head>
  <body>
  <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
  <div id="main" style="height:400px"></div>

  <div id="main2" style="height: 400px"></div>

  <div id="main3" style="height: 400px"></div>

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
                      if(result){
                          counts.push(result.count);
                          console.log(counts);
                          myChart.hideLoading();
                          var option = {
                              title : {
                                  text: '20170901出行OD量',
                                  x:'center'
                              },
                              tooltip: {
                                  trigger: 'item',
                                  formatter: "{a} <br/>{b} : {c} ({d}%)"
                              },
                              legend: {
                                  orient: 'vertial',
                                  x: 'left',
                                  data:['20170901']
                              },
                              series:[
                                  {
                                      type: 'pie',
                                      name: '数量',
                                      radius : '55%',
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

  </script>
</html>
