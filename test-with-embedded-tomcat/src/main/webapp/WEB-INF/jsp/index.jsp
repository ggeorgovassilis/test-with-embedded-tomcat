<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!doctype html>
<html ng-app="twetapp">
<head>
<meta charset="utf-8">
<title>TWET News</title>
<script type="text/javascript" src="static/angular.js"></script>
<script type="text/javascript" src="static/newscontroller.js"></script>
<script type="text/javascript" src="static/stockcontroller.js"></script>
<link href="static/styles.css" rel="stylesheet" />
</head>
<body>
	<div id="headlines">
		<h1>TWET News</h1>
		<div class="today">
			The weather today
			<c:out value="${date}" />
			is warm at
			<c:out value="${temperature}" />
			°C
		</div>
		<form ng-submit="lookupSymbol()" class="stocks" ng-controller="StockController">
		<input id="stocksymbol" name="stocksymbol" ng-model="symbol" title="Stock symbol"/>
		<input type="submit" value="Lookup stock symbol"/>
		<div class="stock" ng-if="stock">
		<div class="symbol">{{stock.symbol}}</div>&nbsp;
		<div class="name">{{stock.name}} </div>&nbsp;
		<div class="value">{{stock.value}} &euro;</div>&nbsp;
		<div class="change">{{stock.change}}%</div>
		</div>
		<div ng-if="error" class="error">{{error}}</div>
		</form>
		<div class="headlines">
		<div ng-controller="NewsController">
			<div class="headline" ng-repeat="headline in headlines">
			<b>{{headline.title}}</b>
			<p>{{headline.description}}</p>
			</div>
		</div>
		</div>
	</div>
</body>
</html>