(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.controller('usuarioDeleteCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            var idUsuario = $state.params.usuarioId;
            $scope.deleteUsuario = function () {
                 $http.delete('api/999/usuarios' + '/' + idUsuario, {}).then(function (response) {
                    $state.go('usuarioList', {usuarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);