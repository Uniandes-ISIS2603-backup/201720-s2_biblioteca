(function (ng) {
    var mod = ng.module("medioPagoModule");
    mod.controller('medioPagoDeleteCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            var idUs=$state.params.usuarioId;            
            var idMedioPago = $state.params.medioPagoId;
            
            console.info(idUs);
            console.info(idMedioPago);
            $scope.deleteMedioPago = function () {
                 $http.delete('api/999/usuarios/' + idUs+'/medioPago/'+idMedioPago, {}).then(function (response) {
                    $state.go('medioPagoList', {medioPagoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);