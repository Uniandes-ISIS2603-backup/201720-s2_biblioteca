(function (ng) {
    var mod = ng.module("medioPagoModule");
    mod.constant("medioPagosContext", "api/999/medioPagos");
    mod.controller('medioPagoNewCtrl', ['$scope', '$http', 'medioPagosContext' , '$state', '$rootScope',
        function ($scope, $http, medioPagosContext, $state, $rootScope) {
            $scope.createMedioPago = function () {
                $http.post('api/999/usuarios/' + $state.params.usuarioId+'/medioPago', {
                    tipo: $scope.medioPagoTipo,
                    descripcion: $scope.medioPagoDescripcion
                }).then(function (response) {
                    //MedioPago created successfully
                    $state.go('medioPagoList', {medioPagoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);