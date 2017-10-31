(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuariosContext", "api/999/usuarios");
    mod.controller('usuarioNewCtrl', ['$scope', '$http', 'usuariosContext', '$state', '$rootScope',
        function ($scope, $http, usuariosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createUsuario = function () {
                $http.post(usuariosContext, {
                    name: $scope.usuarioName,
                    telefono: $scope.usuarioTelefono,
                    direccion: $scope.usuarioDireccion
                }).then(function (response) {
                    //Usuario created successfully
                    $state.go('usuarioList', {usuarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
