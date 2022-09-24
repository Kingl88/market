angular.module('market-front').controller('authController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/auth/api/v1';
    $scope.tryToAuthWithKeycloak = function () {
        $http.post(contextPath + '/auth/keycloak', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    // $http.defaults.headers.common.username = $scope.user.username;
                    $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;
                }
                $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.webGuestCartId + '/merge').then(function successCallback(response) {
                    console.log(response);
                });
                location.reload();
            }, function errorCallback(response) {
            });

    };
});
