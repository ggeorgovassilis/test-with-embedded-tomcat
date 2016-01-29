twetapp.controller('StockController', function ($scope, $http) {
	$scope.lookupSymbol=function(){
		$scope.error=null;
		$scope.stock=null;
		$http.get("api/stocks/"+escape($scope.symbol)).then(function(response){
			$scope.stock=response.data;
		}, function(response){
			$scope.error=response.statusText;
		});
	};
	
});