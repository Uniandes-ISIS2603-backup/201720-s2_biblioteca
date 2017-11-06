(function (ng) {
    var mod = ng.module("reservaModule");
    mod.controller('reservaDeleteCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            var idReserva = $state.params.reservaId;
            $scope.deleteReserva = function () {
                 $http.delete('api/999/usuarios/' + $state.params.usuarioId+'/reservas/'+idReserva, {}).then(function (response) {
                    $state.go('reservaList', {reservaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);