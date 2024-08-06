<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/views/admin/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>${fns:getConfig('productName')}${fns:getConfig('version')}</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<meta name="decorator" content="admin"/>
</head>
<body>

	<!--body wrapper start-->
	<div class="wrapper">
		<!--breadcrumbs start -->
		<ul class="breadcrumb panel">
			<li><a href="#"><i class="fa fa-home"></i> 控制台</a></li>
			<li><a href="#">商品管理</a></li>
			<li class="active">商品添加</li>
		</ul>
		<!--breadcrumbs end -->
		<!--alert start-->
		<div class="alert alert-block alert-danger fade in">
			<button type="button" class="close close-sm" data-dismiss="alert">
				<i class="fa fa-times"></i>
			</button>
			<strong>升级提醒!</strong> 升级提醒:最新版本为xxshop v2.0 [ 更新日期2016-5-5 ] 您的版本是v1.0 【<a href="#">点此升级</a>】
		</div>
		<!--alert end-->

		<!-- 首页四图 start -->
		<div class="row states-info">
			<div class="col-md-3">
				<div class="panel red-bg">
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-4">
								<i class="fa fa-money"></i>
							</div>
							<div class="col-xs-8">
								<span class="state-title">今日成交额</span>
								<h4>￥23500.00</h4>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel blue-bg">
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-4">
								<i class="fa fa-tag"></i>
							</div>
							<div class="col-xs-8">
								<span class="state-title">待处理订单/今日订单</span>
								<h4>0/13</h4>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel green-bg">
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-4">
								<i class="fa fa-gavel"></i>
							</div>
							<div class="col-xs-8">
								<span class="state-title">商铺总数/今日新增</span>
								<h4>26/2</h4>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel yellow-bg">
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-4">
								<i class="fa fa-eye"></i>
							</div>
							<div class="col-xs-8">
								<span class="state-title">会员总数/今日新增</span>
								<h4>226/12</h4>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 首页四图 end -->

		<!-- 销售额走势图 start -->
		<div class="row">
			<div class="col-md-8">
				<section class="panel">
					<header class="panel-heading">
						销售额走势图 <span class="tools pull-right"> <a href="javascript:;" class="fa fa-chevron-down"></a> <a href="javascript:;"
							class="fa fa-times"></a>
						</span>
					</header>
					<div class="panel-body">
						<div id="visitors-chart">
							<div id="visitors-container" style="width: 100%; height: 364px; text-align: center; margin: 0 auto;">
							<div id="container" style="height: 100%"></div>
							</div>
						</div>
					</div>
				</section>
			</div>

			<div class="col-md-4">
				<div class="panel">
					<header class="panel-heading">
						商家销量排行 <span class="tools pull-right"> <a href="javascript:;" class="fa fa-chevron-down"></a> <a href="javascript:;"
							class="fa fa-times"></a>
						</span>
					</header>
					<div class="panel-body">
						<ul class="goal-progress">
							<li>
								<div class="prog-avatar">
									<img src="${ctxStatic}/sicheng-admin/images/photos/user1.png" alt="" />
								</div>
								<div class="details">
									<div class="title">
										<a href="#">apple xx旗舰店</a>
									</div>
									<div class="progress progress-xs">
										<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 70%">
											<span class="">20389.00</span>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="prog-avatar">
									<img src="${ctxStatic}/sicheng-admin/images/photos/user2.png" alt="" />
								</div>
								<div class="details">
									<div class="title">
										<a href="#">奔马汽车 xxx旗舰店</a>
									</div>
									<div class="progress progress-xs">
										<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
											style="width: 91%">
											<span class="">20389.00</span>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="prog-avatar">
									<img src="${ctxStatic}/sicheng-admin/images/photos/user3.png" alt="" />
								</div>
								<div class="details">
									<div class="title">
										<a href="#">奔马汽车 xxx旗舰店</a>
									</div>
									<div class="progress progress-xs">
										<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
											style="width: 40%">
											<span class="">20389.00</span>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="prog-avatar">
									<img src="${ctxStatic}/sicheng-admin/images/photos/user4.png" alt="" />
								</div>
								<div class="details">
									<div class="title">
										<a href="#">奔马汽车 xxx旗舰店</a>
									</div>
									<div class="progress progress-xs">
										<div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
											style="width: 20%">
											<span class="">20389.00</span>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="prog-avatar">
									<img src="${ctxStatic}/sicheng-admin/images/photos/user5.png" alt="" />
								</div>
								<div class="details">
									<div class="title">
										<a href="#">奔马汽车 xxx旗舰店</a>
									</div>
									<div class="progress progress-xs">
										<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
											style="width: 45%">
											<span class="">20389.00</span>
										</div>
									</div>
								</div>
							</li>
						</ul>
						<div class="text-center">
							<a href="#">查看更多</a>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- 销售额走势图 end -->

	</div>
	<!--body wrapper end-->
      <script type="text/javascript" src="${ctxStatic}/echarts3.6.2/echarts.min.js"></script>

       <script type="text/javascript">
			var dom = document.getElementById("container");
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			app.title = '堆叠柱状图';
			
			option = {
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend: {
			        data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎','百度','谷歌','必应','其他']
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : ['周一','周二','周三','周四','周五','周六','周日']
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'直接访问',
			            type:'bar',
			            data:[320, 332, 301, 334, 390, 330, 320]
			        },
			        {
			            name:'邮件营销',
			            type:'bar',
			            stack: '广告',
			            data:[120, 132, 101, 134, 90, 230, 210]
			        },
			        {
			            name:'联盟广告',
			            type:'bar',
			            stack: '广告',
			            data:[220, 182, 191, 234, 290, 330, 310]
			        },
			        {
			            name:'视频广告',
			            type:'bar',
			            stack: '广告',
			            data:[150, 232, 201, 154, 190, 330, 410]
			        },
			        {
			            name:'搜索引擎',
			            type:'bar',
			            data:[862, 1018, 964, 1026, 1679, 1600, 1570],
			            markLine : {
			                lineStyle: {
			                    normal: {
			                        type: 'dashed'
			                    }
			                },
			                data : [
			                    [{type : 'min'}, {type : 'max'}]
			                ]
			            }
			        },
			        {
			            name:'百度',
			            type:'bar',
			            barWidth : 5,
			            stack: '搜索引擎',
			            data:[620, 732, 701, 734, 1090, 1130, 1120]
			        },
			        {
			            name:'谷歌',
			            type:'bar',
			            stack: '搜索引擎',
			            data:[120, 132, 101, 134, 290, 230, 220]
			        },
			        {
			            name:'必应',
			            type:'bar',
			            stack: '搜索引擎',
			            data:[60, 72, 71, 74, 190, 130, 110]
			        },
			        {
			            name:'其他',
			            type:'bar',
			            stack: '搜索引擎',
			            data:[62, 82, 91, 84, 109, 110, 120]
			        }
			    ]
			};
			;
			if (option && typeof option === "object") {
			    myChart.setOption(option, true);
			}
       </script>
</body>
</html>