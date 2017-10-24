(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuarioContext", "api/999/usuarios");
    mod.controller('usuarioCtrl', ['$scope', '$http', 'usuarioContext', '$state',
        function ($scope, $http, usuarioContext, $state) {
            $http.get(usuarioContext).then(function (response) {
                $scope.usuarioRecords = response.data;
            });
            
            if ($state.params.usuarioId !== undefined) {
                $http.get(usuarioContext + '/' + $state.params.usuarioId).then(function (response) {
                    $scope.usuarioRecords = response.data.usuarios;
                    $scope.currentUsuario = response.data;
                });
            }
        }
    ]);
}
)(angular);