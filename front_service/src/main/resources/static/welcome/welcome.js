angular.module('market-front').controller('welcomeController', function ($scope, $http, $localStorage) {
    var params = window
        .location
        .search
        .replace('?','')
        .split('&')
        .reduce(
            function(p,e){
                var a = e.split('=');
                p[ decodeURIComponent(a[0])] = decodeURIComponent(a[1]);
                return p;
            },
            {}
        );
    console.log( params['code']);
    if(params['code']){
        $http.post('http://localhost:5555/auth/api/v1/auth/keycloak', params['code'])
            .then(function successCallback(response) {
                console.log(response);
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webMarketUser = {token: response.data.token};
                }
                $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.webGuestCartId + '/merge').then(function successCallback(response) {
                    console.log(response);
                });
                // location.reload();
            }, function errorCallback(response) {
            });
    }
    // $scope.authWithKeycloak = function () {
    //
    //     window.location = "http://localhost:8090/realms/market-realm/protocol/openid-connect/auth?response_type=code&client_id=market-client&state=gdfdffghfshbbsfhsdfhsdf&scope=profile&redirect_uri=http://localhost:3000/front/";
    // }
    // $scope.LogOutKeycloak = function () {
    //     window.location = "http://localhost:8090/realms/market-realm/protocol/openid-connect/logout?post_redirect_uri=http://localhost:3000/front/";
    // }
});
