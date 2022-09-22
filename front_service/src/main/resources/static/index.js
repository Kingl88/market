/** Описание создаваемого модуля, принимает 3 параметра:
 1. Название модуля (market-frontApp)
 2. Набор других модулей в вите строкового массива. от которых данный модуль зависит
 3. Конфигурационные настройки модуля (необязательный параметр)

 controller(name, constructor): создает контроллер
 - appController - имя контроллера
 - function ($scope, $http), где
 - $scope - сервис через который контроллер передает данные в предстваление
 - $http - сервис для взаимодействия с удаленным HTTP-сервром через JSON

 **/
(function () {
    angular
        .module('market-front', ['ngRoute', 'ngStorage']) //подключаем дополнительный модуль 'ngRoute'
        .config(config) //конфигурируем приложение
        .run(run); //запускаем приложение

    function config($routeProvider) {
        $routeProvider
            .when('/', { //если путь '/'
                templateUrl: 'welcome/welcome.html', // подставляем страничку по пути 'welcome/welcome.html'
                controller: 'welcomeController' // и выбираем контроллер 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/updateProduct/:productId', {
                templateUrl: 'updateProduct/updateProduct.html',
                controller: 'updateProductController'
            })
            .when('/createProduct', {
                templateUrl: 'createProduct/createProduct.html',
                controller: 'createProductController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/createUser', {
                templateUrl: 'createUser/createUser.html',
                controller: 'createUserController'
            })
            .when('/statistic', {
                templateUrl: 'statistic/statistic.html',
                controller: 'statisticController'
            })
            .when('/order', {
                templateUrl: 'order/order.html',
                controller: 'orderController'
            })
            .otherwise({
                redirectTo: '/' //если указываем путь, которого нету в перечисление делаем редирект на '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.webMarketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webMarketUser.token;
        }
        if (!$localStorage.webGuestCartId) {
            $http.get('http://localhost:5555/cart/api/v1/cart/generate')
                .then(function successCallback(response) {
                    $localStorage.webGuestCartId = response.data.value;
                });
        }
    }
})();
angular.module('market-front').controller('indexController', function ($rootScope, $scope, $http, $localStorage) {
    // let params = window
    //     .location
    //     .search
    //     .replace('?','')
    //     .split('&')
    //     .reduce(
    //         function(p,e){
    //             var a = e.split('=');
    //             p[ decodeURIComponent(a[0])] = decodeURIComponent(a[1]);
    //             return p;
    //         },
    //         {}
    //     );
    // if(params['code']  && !localStorage.webMarketUser.token){
    //     $http.post('http://localhost:5555/auth/api/v1/auth/keycloak', params['code'])
    //         .then(function successCallback(response) {
    //             console.log(response);
    //             if (response.data.token) {
    //                 $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
    //                 $localStorage.webMarketUser = {token: response.data.token};
    //             }
    //             $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.webGuestCartId + '/merge').then(function successCallback(response) {
    //                 console.log(response);
    //             });
    //             // location.reload();
    //         }, function errorCallback(response) {
    //         });
    // }
    const contextPath = 'http://localhost:5555/auth/api/v1';
    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;
                }
                $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.webGuestCartId + '/merge').then(function successCallback(response) {
                    console.log(response);
                });
                // location.reload();
            }, function errorCallback(response) {
            });

    };
    $scope.authWithKeycloak = function () {

        window.location = "http://localhost:8090/realms/market-realm/protocol/openid-connect/auth?response_type=code&client_id=market-client&state=gdfdffghfshbbsfhsdfhsdf&scope=profile&redirect_uri=http://localhost:3000/front/";
    }
    $scope.closeAlertForDownloading = () => {
        $localStorage.isCreatedReport = false;
    }
    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.webMarketUser;
        $http.defaults.headers.common.Authorization = '';
        location.reload()
    };
    $scope.link = $localStorage.linkForDownloadReport;
    $scope.fileCreated = $localStorage.isCreatedReport;
    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.webMarketUser) {
            return true;
        } else {
            return false;
        }
    };
});