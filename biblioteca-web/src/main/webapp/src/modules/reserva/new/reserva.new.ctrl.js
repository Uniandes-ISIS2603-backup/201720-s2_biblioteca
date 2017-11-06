(function (ng) {
    var mod = ng.module("reservaModule");
    mod.constant("reservasContext", "api/999/reservas");
    mod.controller('reservaNewCtrl', ['$scope', '$http', 'reservasContext' , '$state', '$rootScope',
        function ($scope, $http, reservasContext, $state, $rootScope) {
            $scope.createReserva = function () {
                $http.post('api/999/usuarios/' + $state.params.usuarioId+'/reservas', {
                    completada: $scope.completada
                   
                }).then(function (response) {
                
                    $state.go('reservaList', {reservaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);