var twetapp = angular.module('twetapp', []);

twetapp.controller('NewsController', function ($scope, $http) {
	$http.get("api/news/headlines").then(function(response){
		$scope.headlines=response.data;
	});
});