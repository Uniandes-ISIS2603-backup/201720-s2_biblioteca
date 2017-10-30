(function (ng) {
    var mod = ng.module("multaModule");
    mod.constant("multaContext", "multas");
    mod.constant("usuarioContext","api/999/usuarios");
    mod.controller('multaCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            if ($state.params.usuarioId !== undefined && $state.params.usuarioId !== null) {
                $http.get('api/999/usuarios/' + $state.params.usuarioId+'/multas').then(function (response) {
                    $scope.multaRecords = response.data;                    
                });
            }
            
            if ($state.params.prestamoId !== undefined) {
                $http.get('api/999/usuarios/' + $state.params.usuarioId+'/multas/'+$state.params.prestamoId).then(function (response) {
                    $scope.multaRecords = response.data.prestamos;
                    $scope.currentMulta = response.data;
                });
            }
            
        }
    ]);
}
)(angular);