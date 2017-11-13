(function (ng) {
    var mod = ng.module("prestamoModule");
    mod.constant("prestamosContext", "api/999/prestamos");
    mod.controller('prestamoNewCtrl', ['$scope', '$http', 'prestamosContext' , '$state', '$rootScope',
        function ($scope, $http, prestamosContext, $state, $rootScope) {
            $scope.createPrestamo = function () {
                var feIn=$scope.anioFechaInicio+'-'+$scope.mesFechaInicio+'-'+$scope.diaFechaInicio;
                var feFi=$scope.anioFechaFinal+'-'+$scope.mesFechaFinal+'-'+$scope.diaFechaFinal;
                console.info(feIn);
                console.info(feFi);
                $http.post('api/999/usuarios/' + $state.params.usuarioId+'/prestamos', {
                    fechaInicio: feIn,
                    fechaFinal: feFi,
                    externo: $scope.prestamoExterno
                }).then(function (response) {
                    //Prestamo created successfully
                    $state.go('prestamoList', {prestamoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);