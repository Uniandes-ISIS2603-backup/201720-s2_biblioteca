(function (ng) {
    var mod = ng.module("medioPagoModule");
    mod.constant("medioPagoContext", "medioPagos");
    mod.constant("usuarioContext","api/999/usuarios");
    mod.controller('medioPagoCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            if ($state.params.usuarioId !== undefined) {
                $http.get('api/999/usuarios/' + $state.params.usuarioId+'/medioPago').then(function (response) {
                    $scope.medioPagoRecords = response.data;                    
                });
            }            
        }
    ]);
}
)(angular);