(function (ng) {
    var mod = ng.module("medioPagoModule");
    mod.constant("medioPagosContext", "api/999/medioPagos");
    mod.controller('medioPagoNewCtrl', ['$scope', '$http', 'medioPagosContext' , '$state', '$rootScope',
        function ($scope, $http, medioPagosContext, $state, $rootScope) {
            $scope.medioPagoTipo=0;
             
            $scope.createMedioPago = function () {
                var eltipo = $scope.medioPagoTipo;
                console.info(eltipo);
                $http.post('api/999/usuarios/' + $state.params.usuarioId+'/medioPago', {
                    tipo: eltipo,
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