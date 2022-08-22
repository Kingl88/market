angular.module('market-front').controller('statisticController', function ($scope, $http, $localStorage) {
    let stompClient = null;

    function connect() {
        let socket = new SockJS('ws');
        console.log(socket);
        stompClient = Stomp.over(socket);
        console.log(stompClient);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/getFileAboutProducts', function (response) {
                console.log(response.body);
                $localStorage.isCreatedReport = response.body;
                $localStorage.linkForDownloadReport = contextPath + '/getFileAboutProducts';
                location.reload();
                disconnect();
            });
        });
    }

    function disconnect() {
        if (stompClient !== null) {
            stompClient.disconnect();
        }
        console.log("Disconnected");
    }

    const contextPath = 'http://localhost:8189/market/api/v1/statistic';
    $scope.loadStatistic = () => {
        $http.get(contextPath + '/')
            .then(function (response) {
                console.log(response);
                $scope.statistic = response.data;
            });
    }
    $scope.getFile = function () {
        stompClient.send('/app/giveMeFile');
    }
    if ($localStorage.isCreatedReport == null || $localStorage.isCreatedReport === false) {
        connect();
    }

    $scope.loadStatistic();
});
