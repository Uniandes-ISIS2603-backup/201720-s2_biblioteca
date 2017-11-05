(function (ng) {
    var mod = ng.module("prestamoModule");
    mod.controller('prestamoDeleteCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            var idPrestamo = $state.params.prestamoId;
            $scope.deletePrestamo = function () {
                 $http.delete('api/999/usuarios/' + $state.params.usuarioId+'/prestamos/'+idPrestamo, {}).then(function (response) {
                    $state.go('prestamoList', {prestamoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);