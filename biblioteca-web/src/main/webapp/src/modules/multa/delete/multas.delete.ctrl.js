(function (ng) {
    var mod = ng.module("multaModule");
    mod.controller('multaDeleteCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            var idMulta = $state.params.multaId;
            $scope.deleteMulta = function () {
                 $http.delete('api/999/usuarios/' + $state.params.usuarioId+'/multas/' + idMulta, {}).then(function (response) {
                    $state.go('multaList', {multaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);