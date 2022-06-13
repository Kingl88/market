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

angular.module('market-frontApp', []).controller('appController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/';
    let pageDefault = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + 'products',
            method: 'GET',
            params: {
                page: pageIndex
            }
        }).then(function (response) {
            console.log(response);
            $scope.productsPage = response.data;
        });
    }

    $scope.nextPage = function () {
        pageDefault++;
        $scope.loadProducts(pageDefault);
    }

    $scope.previewPage = function () {
        pageDefault--;
        $scope.loadProducts(pageDefault);
    }

    $scope.deleteProduct = function (product) {
        $http.get(contextPath + 'products/delete/' + product.id).then(function (response) {
            console.log(response);
            $scope.products = response.data;
            $scope.loadProducts(pageDefault);
        });
    }
    $scope.loadProducts(pageDefault);

});