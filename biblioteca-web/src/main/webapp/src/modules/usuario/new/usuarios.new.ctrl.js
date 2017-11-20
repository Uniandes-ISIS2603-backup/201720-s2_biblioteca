 (function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuariosContext", "api/999/usuarios");
    mod.controller('usuarioNewCtrl', ['$scope', '$http', 'usuariosContext', '$state', '$rootScope',
        function ($scope, $http, usuariosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.dataNewUsuario={};
            $scope.createUsuario = function () {
                var infoz=$scope.dataNewUsuario.name;
                console.info(infoz);
                $http.post(usuariosContext, $scope.dataNewUsuario).then(function (response) {
                    //Usuario created successfully
                    $state.go('usuarioList', {usuarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
