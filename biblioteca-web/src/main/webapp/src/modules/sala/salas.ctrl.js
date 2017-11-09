(function (ng) {
    var mod = ng.module("salasModule");
    mod.constant("salasContext", "api/999/salas");
    mod.controller('salasCtrl', ['$scope', '$http', 'salasContext', '$state',
        function ($scope, $http, salasContext, $state) {
            $http.get(salasContext).then(function (response) {
                $scope.salaRecords = response.data;
            });
            
            if ($state.params.salaId !== undefined && ($state.params.salaId !== null)) {
                $http.get(salasContext + '/' + $state.params.salaId).then(function (response) {
                      $scope.salaRecords = response.data.salas;
                  $scope.currentsala = response.data;
                });
            }
        }
    ]);
}
)(angular);