(function (ng) {
    var mod = ng.module("prestamoModule");
    mod.constant("prestamosContext", "api/999/prestamos");
    mod.controller('prestamoNewCtrl', ['$scope', '$http', 'prestamosContext' , '$state', '$rootScope',
        function ($scope, $http, prestamosContext, $state, $rootScope) {
            $scope.createPrestamo = function () {
                $http.post('api/999/usuarios/' + $state.params.usuarioId+'/prestamos', {
                    fechaInicio: $scope.prestamoFechaInicio,
                    fechaFinal: $scope.prestamoFechaFinal,
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