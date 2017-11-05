(function (ng) {
    var mod = ng.module("medioPagoModule");
    mod.controller('medioPagoDeleteCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            var idMedioPago = $state.params.medioPagoId;
            $scope.deleteMedioPago = function () {
                 $http.delete('api/999/usuarios/' + $state.params.usuarioId+'/medioPago/'+idMedioPago, {}).then(function (response) {
                    $state.go('medioPagoList', {medioPagoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);