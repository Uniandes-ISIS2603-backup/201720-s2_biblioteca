(function (ng) {
    var mod = ng.module("prestamoModule");
    mod.constant("prestamoContext", "api/999/prestamos");
    mod.controller('prestamoCtrl', ['$scope', '$http', 'prestamoContext', '$state',
        function ($scope, $http, prestamoContext, $state) {
            $http.get(prestamoContext).then(function (response) {
                $scope.prestamoRecords = response.data;
            });
            
            if ($state.params.prestamoId !== undefined) {
                $http.get(prestamoContext + '/' + $state.params.prestamoId).then(function (response) {
                    $scope.prestamoRecords = response.data.prestamos;
                    $scope.currentUsuario = response.data;
                });
            }
        }
    ]);
}
)(angular);