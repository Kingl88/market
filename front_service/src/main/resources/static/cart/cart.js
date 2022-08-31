angular.module('market-front').controller('cartController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/cart/api/v1/cart/';

    $scope.loadCart = function () {
        $http.get(contextPath + $localStorage.webGuestCartId)
            .then(function successCallback(response){
                console.log(response);
                $scope.cart = response;
            }, function errorCallback(response) {
                console.log(response);
            });
    }

    $scope.clearCart = function(){
        $http.get(contextPath + $localStorage.webGuestCartId + '/clear').then(function (response){
            console.log(response);

        });
    }


    $scope.incrementProduct = function (item) {
        $http.get(contextPath + $localStorage.webGuestCartId + '/add/' + item.productId).then(function (response) {
            console.log(response);
            $scope.loadCart();
        })
    }

    $scope.decrementProduct = function (item) {
        $http.get(contextPath + $localStorage.webGuestCartId + '/decrement/' + item.productId).then(function (response) {
            console.log(response);
            $scope.loadCart();
        })
    }

    $scope.deleteProduct = function (item) {
        $http.get(contextPath + $localStorage.webGuestCartId + '/remove/' + item.productId).then(function (response) {
            console.log(response);
            $scope.loadCart();
        });
    }
    $scope.loadCart();

});