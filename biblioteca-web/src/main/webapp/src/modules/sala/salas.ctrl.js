(function (ng) {
    var mod = ng.module("salaModule");
    mod.constant("salaContext", "api/salas");
    mod.controller('salaCtrl', ['$scope', '$http', 'salaContext',
        function ($scope, $http, salaContext) {
            $http.get('data/salas.json').then(function (response) {
                $scope.salaRecords = response.data;
            });
        }
    ]);
}
)(angular);