
<%--
- list.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:multi-list readonly="true">
<acme:list-column code="administrator.dashboard.list.label.totalNotices" path="totalNumberNotices" />
<acme:list-column code="administrator.dashboard.list.label.totalTechnologyRecords" path="totalNumberTechnologyRecords" />
<acme:list-column code="administrator.dashboard.list.label.totalToolRecords" path ="totalNumberToolRecords" />
</acme:multi-list>
<br></br>
<acme:message code="administrator.dashboard.list.label.MinMoneyInquires"/>
<acme:multi-list readonly="true">
<acme:list-column code="administrator.dashboard.list.label.minMoneyInquires1" path ="minMoneyInquires1" />
<acme:list-column code="administrator.dashboard.list.label.maxMoneyInquires1" path ="maxMoneyInquires1" />
<acme:list-column code="administrator.dashboard.list.label.avgMoneyInquires1" path ="avgMoneyInquires1" />
<acme:list-column code="administrator.dashboard.list.label.stdMoneyInquires1" path ="stdMoneyInquires1"/>
</acme:multi-list>
<br></br>
<acme:message code="administrator.dashboard.list.label.MaxMoneyInquires"/>
<acme:multi-list readonly="true">
<acme:list-column code="administrator.dashboard.list.label.minMoneyInquires2" path ="minMoneyInquires2" />
<acme:list-column code="administrator.dashboard.list.label.maxMoneyInquires2" path ="maxMoneyInquires2" />
<acme:list-column code="administrator.dashboard.list.label.avgMoneyInquires2" path ="avgMoneyInquires2"/>
<acme:list-column code="administrator.dashboard.list.label.stdMoneyInquires2" path ="stdMoneyInquires2" />
</acme:multi-list>
<br></br>
<acme:message code="administrator.dashboard.list.label.MinMoneyOvertures"/>
<acme:multi-list readonly="true">
<acme:list-column code="administrator.dashboard.list.label.minMoneyOvertures1" path ="minMoneyOvertures1" />
<acme:list-column code="administrator.dashboard.list.label.maxMoneyOvertures1" path ="maxMoneyOvertures1" />
<acme:list-column code="administrator.dashboard.list.label.avgMoneyOvertures1" path ="avgMoneyOvertures1" />
<acme:list-column code="administrator.dashboard.list.label.stdMoneyOvertures1" path ="stdMoneyOvertures1"/>
</acme:multi-list>
<br></br>
<acme:message code="administrator.dashboard.list.label.MaxMoneyOvertures"/>
<acme:multi-list readonly="true">
<acme:list-column code="administrator.dashboard.list.label.minMoneyOvertures2" path ="minMoneyOvertures2" />
<acme:list-column code="administrator.dashboard.list.label.maxMoneyOvertures2" path ="maxMoneyOvertures2" />
<acme:list-column code="administrator.dashboard.list.label.avgMoneyOvertures2" path ="avgMoneyOvertures2"/>
<acme:list-column code="administrator.dashboard.list.label.stdMoneyOvertures2" path ="stdMoneyOvertures2" />
</acme:multi-list>

<acme:message code="administrator.dashboard.list.label.IvestmentData"/>
<acme:multi-list readonly="true">
<acme:list-column code="administrator.dashboard.list.label.avgInvestmentRoundPerEntrepreneur" path ="avgInvestmentRoundPerEntrepreneur" />
<acme:list-column code="administrator.dashboard.list.label.avgInvestmentRoundApplicationPerEntrepreneur" path ="avgInvestmentRoundApplicationPerEntrepreneur" />
<acme:list-column code="administrator.dashboard.list.label.avgInvestmentRoundApplicationPerInvestor" path ="avgInvestmentRoundApplicationPerInvestor"/>
</acme:multi-list>

<acme:message code="administrator.dashboard.legend.TNN"/>
<br>
<acme:message code="administrator.dashboard.legend.TNTER"/> 
 <br>
<acme:message code="administrator.dashboard.legend.TNTR"/>  
<br>

<div style="height:30%; width:30%; display:inline-block">
<acme:message code="administrator.dashboard.chart.technologyPerSource"/> 
<canvas id = "ratioOfTechnologiesPerSource" > </canvas>
</div>

<div style="height:30%; width:30%; display:inline-block">
<acme:message code="administrator.dashboard.chart.technologyBySector"/> 
<canvas id = "numberOfTechnologiesBySector" > </canvas>
</div>

<div style="height:30%; width:30%;display:inline-block">
<acme:message code="administrator.dashboard.chart.toolPerSource"/> 
<canvas id = "ratioOfToolsPerSource" > </canvas>
</div>

<br>

<div style="height:30%; width:30%;display:inline-block">
<acme:message code="administrator.dashboard.chart.toolBySector"/> 
<canvas id = "numberOfToolsBySector" > </canvas>
</div>

<div style="height:30%; width:30%;display:inline-block">
<acme:message code="administrator.dashboard.chart.ratioOfInvestmentRoundByKind"/> 
<canvas id = "investmentRoundByKind" > </canvas>
</div>

<div style="height:30%; width:30%;display:inline-block">
<acme:message code="administrator.dashboard.chart.ratioOfInvestmentRoundApplicationByStatus"/> 
<canvas id = "investmentRoundApplicationByStatus" > </canvas>
</div>

<br>

<div>
<acme:message code="administrator.dashboard.timeSeries.Pending"/> 
<canvas id = "timeSeriesPending" > </canvas>
</div>
<br>

<div>
<acme:message code="administrator.dashboard.timeSeries.Accepted"/> 
<canvas id = "timeSeriesAccepted" > </canvas>
</div>
<br>

<div>
<acme:message code="administrator.dashboard.timeSeries.Rejected"/> 
<canvas id = "timeSeriesRejected" > </canvas>
</div>
<br>
		<!-- Control Check -->
		
<div style="height:30%; width:30%;display:inline-block">
<acme:message code="administrator.dashboard.chart.ratioOfInvestmentRoundByRequest"/> 
<canvas id = "investmentRoundByRequest" > </canvas>
</div>

<div style="height:30%; width:30%;display:inline-block">
<acme:message code="administrator.dashboard.chart.ratioOfInvestmentRoundApplicationByLink"/> 
<canvas id = "investmentRoundApplicationByLink" > </canvas>
</div>

<div style="height:30%; width:30%;display:inline-block">
<acme:message code="administrator.dashboard.chart.ratioOfInvestmentRoundApplicationByPassword"/> 
<canvas id = "investmentRoundApplicationByPassword" > </canvas>
</div>

<script type = "text/javascript">
$(document).ready(function(){
var CanvasTechnologyPerSource = document.getElementById("ratioOfTechnologiesPerSource");
Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;
var DataTechnologyPerSource = {
	    labels: [
				<jstl:forEach items = "${ratioOfTechnologiesPerSource}" var = "item">
				"<jstl:out value = "${item[0]}" />" ,
				</jstl:forEach>
	    ],
	    datasets: [
	        {
	            data: [
	            	<jstl:forEach items = "${ratioOfTechnologiesPerSource}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
	            ], 
	            backgroundColor :["red", "blue"]
	        }]
	};
var pieChartTechnologyPerSource = new Chart(CanvasTechnologyPerSource, {
	  type: 'pie',
	  data: DataTechnologyPerSource
	});
});
</script>

<script type = "text/javascript">
$(document).ready(function(){
var CanvasTechnologyBySector = document.getElementById("numberOfTechnologiesBySector");
Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;
var DataTechnologyBySector = {
	    labels: [
				<jstl:forEach items = "${numberOfTechnologiesGroupedBySector}" var = "item">
				"<jstl:out value = "${item[0]}" />" ,
				</jstl:forEach>
	    ],
	    datasets: [
	        {
	            data: [
	            	<jstl:forEach items = "${numberOfTechnologiesGroupedBySector}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
	            ], 
	            backgroundColor :["red", "green", "blue", "purple", "magenta"]
	        }]
	};
var pieChartTechnologyBySector = new Chart(CanvasTechnologyBySector, {
	  type: 'pie',
	  data: DataTechnologyBySector
	});
});
</script>

<script type = "text/javascript">
$(document).ready(function(){
var CanvasToolPerSource = document.getElementById("ratioOfToolsPerSource");
Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;
var DataToolPerSource = {
	    labels: [
				<jstl:forEach items = "${ratioOfToolsPerSource}" var = "item">
				"<jstl:out value = "${item[0]}" />" ,
				</jstl:forEach>
	    ],
	    datasets: [
	        {
	            data: [
	            	<jstl:forEach items = "${ratioOfToolsPerSource}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
	            ],
	            backgroundColor :["red", "green", "blue", "purple", "magenta"]
	        }]
	};
var pieChartToolPerSource = new Chart(CanvasToolPerSource, {
	  type: 'pie',
	  data: DataToolPerSource
	});
});
</script>

<script type = "text/javascript">
$(document).ready(function(){
var CanvasToolBySector = document.getElementById("numberOfToolsBySector");
Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;
var DataToolBySector = {
	    labels: [
				<jstl:forEach items = "${numberOfToolsGroupedBySector}" var = "item">
				"<jstl:out value = "${item[0]}" />" ,
				</jstl:forEach>
	    ],
	    datasets: [
	        {
	            data: [
	            	<jstl:forEach items = "${numberOfToolsGroupedBySector}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
	            ],
	            backgroundColor :["red", "green", "blue", "purple", "magenta"]
	        }]
	};
var pieChartToolBySector = new Chart(CanvasToolBySector, {
	  type: 'pie',
	  data: DataToolBySector
	});
});
</script>

<script type = "text/javascript">
$(document).ready(function(){
var CanvasIRByKind = document.getElementById("investmentRoundByKind");
Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;
var DataIRByKind = {
	    labels: [
				<jstl:forEach items = "${ratioOfInvestmentRoundByKind}" var = "item">
				"<jstl:out value = "${item[0]}" />" ,
				</jstl:forEach>
	    ],
	    datasets: [
	        {
	            data: [
	            	<jstl:forEach items = "${ratioOfInvestmentRoundByKind}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
	            ],
	            backgroundColor :["red", "green", "blue", "purple", "magenta"]
	        }]
	};
var pieChartIRByKind = new Chart(CanvasIRByKind, {
	  type: 'pie',
	  data: DataIRByKind
	});
});

</script>
<script type = "text/javascript">
$(document).ready(function(){
var CanvasIRAByStatus = document.getElementById("investmentRoundApplicationByStatus");
Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;
var DataIRAByStatus = {
	    labels: [
				<jstl:forEach items = "${ratioOfInvestmentRoundApplicationByStatus}" var = "item">
				"<jstl:out value = "${item[0]}" />" ,
				</jstl:forEach>
	    ],
	    datasets: [
	        {
	            data: [
	            	<jstl:forEach items = "${ratioOfInvestmentRoundApplicationByStatus}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
	            ],
	            backgroundColor :["red", "green", "blue", "purple", "magenta"]
	        }]
	};
var pieChartIRAByStatus = new Chart(CanvasIRAByStatus, {
	  type: 'pie',
	  data: DataIRAByStatus
	});
});
</script>

<!--  PENDING  -->
<script type = "text/javascript">
$(document).ready(function(){
	var CanvasAppsPending = document.getElementById("timeSeriesPending");
	Chart.defaults.global.defaultFontFamily = "Lato";
	Chart.defaults.global.defaultFontSize = 18;
	Chart.defaults.global.legend.display = false;
	var DataAppsPending = {
		    labels: [
					<jstl:forEach items = "${numberOfApplicationsPendingPerDayFromLast15Days}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
		    ],
		    datasets: [
		        {fill: false,
		        	lineTension: 0,
		            data: [
		            	<jstl:forEach items = "${numberOfApplicationsPendingPerDayFromLast15Days}" var = "item">
						"<jstl:out value = "${item[0]}" />" ,
						</jstl:forEach>
		            ],
		            backgroundColor : 'rgba(102,255,51,1)',
		            borderColor: 'rgba(255,0,0,1)'
		        }]
		};
	var pieChartAppsPending = new Chart(CanvasAppsPending, {
		  type: 'line',
		  data: DataAppsPending
		});
});
</script>

<!--  ACCEPTED  -->
<script type = "text/javascript">
$(document).ready(function(){
	var CanvasAppsAccepted = document.getElementById("timeSeriesAccepted");
	Chart.defaults.global.defaultFontFamily = "Lato";
	Chart.defaults.global.defaultFontSize = 18;
	Chart.defaults.global.legend.display = false;
	var DataAppsAccepted = {
		    labels: [
					<jstl:forEach items = "${numberOfApplicationsAcceptedPerDayFromLast15Days}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
		    ],
		    datasets: [
		        {fill: false,
		        	lineTension: 0,
		            data: [
		            	<jstl:forEach items = "${numberOfApplicationsAcceptedPerDayFromLast15Days}" var = "item">
						"<jstl:out value = "${item[0]}" />" ,
						</jstl:forEach>
		            ],
		            backgroundColor : 'rgba(102,255,51,1)',
		            borderColor: 'rgba(255,0,0,1)'
		        }]
		};
	var pieChartAppsAccepted = new Chart(CanvasAppsAccepted, {
		  type: 'line',
		  data: DataAppsAccepted
		});
});
</script>


<!--  REJECTED  -->
<script type = "text/javascript">
$(document).ready(function(){
	var CanvasAppsRejected = document.getElementById("timeSeriesRejected");
	Chart.defaults.global.defaultFontFamily = "Lato";
	Chart.defaults.global.defaultFontSize = 18;
	Chart.defaults.global.legend.display = false;
	var DataAppsRejected = {
		    labels: [
					<jstl:forEach items = "${numberOfApplicationsRejectedPerDayFromLast15Days}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
		    ],
		    datasets: [
		        {fill: false,
		        	lineTension: 0,
		            data: [
		            	<jstl:forEach items = "${numberOfApplicationsRejectedPerDayFromLast15Days}" var = "item">
						"<jstl:out value = "${item[0]}" />" ,
						</jstl:forEach>
		            ],
		            backgroundColor : 'rgba(102,255,51,1)',
		            borderColor: 'rgba(255,0,0,1)'
		        }]
		};
	var pieChartAppsRejected = new Chart(CanvasAppsRejected, {
		  type: 'line',
		  data: DataAppsRejected
		});
});
</script> 

		<!-- Control Check -->
		
<script type = "text/javascript">
$(document).ready(function(){
var CanvasIRByRequest = document.getElementById("investmentRoundByRequest");
Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;
var DataIRByRequest = {
	    labels: [
				<jstl:forEach items = "${ratioOfInvestmentRoundByRequest}" var = "item">
				"<jstl:out value = "${item[0]}" />" ,
				</jstl:forEach>
	    ],
	    datasets: [
	        {
	            data: [
	            	<jstl:forEach items = "${ratioOfInvestmentRoundByRequest}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
	            ],
	            backgroundColor :["red", "green", "blue", "purple", "magenta"]
	        }]
	};
var pieChartIRByRequest = new Chart(CanvasIRByRequest, {
	  type: 'pie',
	  data: DataIRByRequest
	});
});

</script>
<script type = "text/javascript">
$(document).ready(function(){
var CanvasIRAByLink = document.getElementById("investmentRoundApplicationByLink");
Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;
var DataIRAByLink = {
	    labels: [
				<jstl:forEach items = "${ratioOfInvestmentRoundApplicationByLink}" var = "item">
				"<jstl:out value = "${item[0]}" />" ,
				</jstl:forEach>
	    ],
	    datasets: [
	        {
	            data: [
	            	<jstl:forEach items = "${ratioOfInvestmentRoundApplicationByLink}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
	            ],
	            backgroundColor :["red", "green", "blue", "purple", "magenta"]
	        }]
	};
var pieChartIRAByLink = new Chart(CanvasIRAByLink, {
	  type: 'pie',
	  data: DataIRAByLink
	});
});
</script>
<script type = "text/javascript">
$(document).ready(function(){
var CanvasIRAByPass = document.getElementById("investmentRoundApplicationByPass");
Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;
var DataIRAByPass = {
	    labels: [
				<jstl:forEach items = "${ratioOfInvestmentRoundApplicationByPass}" var = "item">
				"<jstl:out value = "${item[0]}" />" ,
				</jstl:forEach>
	    ],
	    datasets: [
	        {
	            data: [
	            	<jstl:forEach items = "${ratioOfInvestmentRoundApplicationByPass}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
	            ],
	            backgroundColor :["red", "green", "blue", "purple", "magenta"]
	        }]
	};
var pieChartIRAByPass = new Chart(CanvasIRAByPass, {
	  type: 'pie',
	  data: DataIRAByPass
	});
});
</script>