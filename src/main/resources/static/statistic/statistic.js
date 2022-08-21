angular.module('market-front').controller('statisticController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1';
    $scope.loadStatistic = function () {
        $http.get(contextPath + '/statistic')
            .then(function (response) {
                console.log(response);
                $scope.statistic = response.data;
            });
    }
    $scope.loadStatistic();
});
